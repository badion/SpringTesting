package com.epam.controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.api.PagedList;
import org.springframework.social.facebook.api.Post;
import org.springframework.social.facebook.api.impl.FacebookTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.epam.dao.CustomerDao;
import com.epam.model.Customer;

@Controller
public class LoginController {

	private final static String APP_ID = "1547575732162222";
	private final static String SECRET_ID = "ae050fe6cfa4a0c6e168eea787834ea5";

	private Facebook facebook;

	@Autowired
	private CustomerDao customerDao;

	@Inject
	public LoginController(Facebook facebook) {
		this.facebook = facebook;
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView login(
			@RequestParam(value = "error", required = false) String error,
			@RequestParam(value = "logout", required = false) String logout) {

		ModelAndView model = new ModelAndView();
		if (error != null) {
			model.addObject("error", "Invalid username and password!");
		}

		if (logout != null) {
			model.addObject("msg", "You've been logged out successfully.");
		}
		model.setViewName("login");

		if (facebook.isAuthorized()) {
			model.addObject("facebookUser", facebook.userOperations()
					.getUserProfile().getName());
			System.out.println("   is authorized facebook ? "
					+ facebook.isAuthorized());

		}
		return model;

	}

	@RequestMapping(value = "/facebookHello")
	public ModelAndView facebook() {
		ModelAndView model = new ModelAndView();
		return model;
	}

	@RequestMapping(method = RequestMethod.GET)
	public String helloFacebook(Model model) {
		if (!facebook.isAuthorized()) {
			return "redirect:/connect/facebook";
		}

		model.addAttribute(facebook.userOperations().getUserProfile());
		PagedList<Post> homeFeed = facebook.feedOperations().getHomeFeed();
		model.addAttribute("feed", homeFeed);
		System.out.println(facebook.userOperations().getUserProfile());
		return "facebookHello";
	}

	@RequestMapping(value = "/connect/facebook")
	public void loginWithFacebook(HttpServletResponse response,
			HttpServletRequest request) {

		System.out.println(request.getServerName());
		System.out.println(request.getScheme());
		System.out.println(request.getServerPort());
		String faceBookLoginUrl = "https://graph.facebook.com/oauth/authorize?client_id="
				+ APP_ID
				+ "&display=page&redirect_uri="
				+ request.getScheme()
				+ "://"
				+ request.getServerName()
				+ ":"
				+ request.getServerPort()
				+ request.getContextPath()
				+ "/facebook/handleAuth.do&scope=email,publish_actions,publish_stream,user_photos";

		try {
			response.sendRedirect(faceBookLoginUrl);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @param response
	 * @param request
	 * @param model
	 * @param code
	 * @return
	 * @throws MalformedURLException
	 */
	@RequestMapping(value = "/facebook/handleAuth.do")
	public String handleAuthResponse(HttpServletResponse response,
			HttpServletRequest request, Model model,
			@RequestParam(value = "code", required = false) String code)
			throws MalformedURLException {

		URL url = new URL(getAuthURL(code, request));

		try {
			String result = readURL(url);
			String accessToken = null;
			Integer expires = null;
			String[] pairs = result.split("&");
			for (String pair : pairs) {
				String[] kv = pair.split("=");
				if (kv.length != 2) {
					throw new RuntimeException("Unexpected auth response");
				} else {
					if (kv[0].equals("access_token")) {
						accessToken = kv[1];
					}
					if (kv[0].equals("expires")) {
						expires = Integer.valueOf(kv[1]);
					}
				}
			}
			if (accessToken != null && expires != null) {

				System.out.println("Token ----  " + accessToken
						+ " Expires ----- " + expires);
				facebook = new FacebookTemplate(accessToken);
				System.out.println(facebook.userOperations().getUserProfile()
						.getId());
				model.addAttribute("accessToken", accessToken);

				try {
					if (isUserInDatabase(facebook.userOperations()
							.getUserProfile().getId())) {
						Customer oldCustomer = customerDao
								.getCustomerByFacebookId(facebook
										.userOperations().getUserProfile()
										.getId());
						addInSession(oldCustomer);
					}
				} catch (EmptyResultDataAccessException e) {
					Customer newCustomer = createNewUserFromFacebookUser(facebook
							.userOperations().getUserProfile().getId());
					customerDao.insert(newCustomer);
					addInSession(newCustomer);
				}

			} else {
				throw new RuntimeException("Access token and expires not found");
			}
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

		model.addAttribute("facebookProfile", facebook.userOperations()
				.getUserProfile());
		PagedList<Post> homeFeed = facebook.feedOperations().getHomeFeed();
		model.addAttribute("feed", homeFeed);
		model.addAttribute("pict", facebook.userOperations().getUserProfile()
				.getId());

		return "facebookHello";

	}

	private void addInSession(Customer customer) {
		Authentication authentication = new UsernamePasswordAuthenticationToken(
				customer.getEmail(), customer.getName());
		SecurityContextHolder.getContext().setAuthentication(authentication);
	}

	private boolean isUserInDatabase(String facebookId) {
		if (customerDao.getCustomerByFacebookId(facebookId) != null) {
			System.out.println("customer true");
			return true;
		} else {
			System.out.println("customer false");
			return false;
		}
	}

	private Customer createNewUserFromFacebookUser(String facebookId) {
		Customer customer = new Customer();
		customer.setName(facebook.userOperations().getUserProfile().getName());
		customer.setAge(22);
		customer.setEmail(facebook.userOperations().getUserProfile().getEmail());
		customer.setFacebookId(facebookId);
		return customer;
	}

	public static String getAuthURL(String authCode, HttpServletRequest request) {
		return "https://graph.facebook.com/oauth/access_token?client_id="
				+ APP_ID + "&redirect_uri=" + request.getScheme() + "://"
				+ request.getServerName() + ":" + request.getServerPort()
				+ request.getContextPath() + "/facebook/handleAuth.do"
				+ "&client_secret=" + SECRET_ID + "&code=" + authCode;
	}

	private String readURL(URL url) throws IOException {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		InputStream is = url.openStream();
		int r;
		while ((r = is.read()) != -1) {
			baos.write(r);
		}
		return new String(baos.toByteArray());
	}
}
