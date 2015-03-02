package com.epam.scheduling;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.mail.internet.MimeMessage;

import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.ui.velocity.VelocityEngineUtils;

import com.epam.model.Customer;

public class SendingEmailWithCustomers implements NumberOfCustomers {

	private JavaMailSender mailSender;

	private VelocityEngine velocityEngine;

	@Override
	public void sendEmail(final String from, final String to,
			final List<Customer> customers) {

		MimeMessagePreparator preparator = new MimeMessagePreparator() {
			@Override
			public void prepare(MimeMessage mimeMessage) throws Exception {
				MimeMessageHelper message = new MimeMessageHelper(mimeMessage);
				message.setTo(to);
				message.setFrom(from);
				Map model = new HashMap();
				model.put("customers", customers);
				VelocityContext context = new VelocityContext();
				context.put("customers", customers);
				String text = VelocityEngineUtils.mergeTemplateIntoString(
						velocityEngine, "/templates/email-inlineimage.vm",
						model);
				message.setText(text, true);
			}
		};
		this.mailSender.send(preparator);

	}

	@Inject
	public void setVelocityEngine(VelocityEngine velocityEngine) {
		this.velocityEngine = velocityEngine;
	}

	public void setMailSender(JavaMailSender mailSender) {
		this.mailSender = mailSender;
	}
}
