package com.epam.controller;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.WebApplicationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.view.RedirectView;

import com.epam.dao.EmployeeDao;
import com.epam.exceptions.DuplicateEmployeeException;
import com.epam.exceptions.EmployeeInDataBaseIsEmpty;
import com.epam.exceptions.NotFoundInDatabaseException;
import com.epam.model.Employee;

@Controller
public class EmployeeRestController {

	private EmployeeDao employeeDao;

	@Autowired
	@Qualifier(value = "employeeValidator")
	private Validator validator;

	@Inject
	public void setEmployeeDao(EmployeeDao employeeDao) {
		this.employeeDao = employeeDao;
	}

	@InitBinder
	private void initBinder(WebDataBinder binder) {
		binder.setValidator(validator);
	}

	@ModelAttribute("employee")
	public Employee createEmployeeModel() {
		return new Employee();
	}

	@RequestMapping(value = "/emp/{id}", method = RequestMethod.GET)
	public @ResponseBody Employee getEmployee(@PathVariable("id") Integer empId) {
		try {
			Employee employee = employeeDao.getEmpById(empId);
			if (employee != null) {
				return employee;
			}
		} catch (EmptyResultDataAccessException e) {
			throw new NotFoundInDatabaseException("Don't exist in database");
		}
		return null;

	}

	@RequestMapping(value = "/emps", method = RequestMethod.GET)
	public @ResponseBody List<Employee> getAllemployees() {
		List<Employee> emps = employeeDao.getAll();
		if (emps == null) {
			throw new EmployeeInDataBaseIsEmpty("Employee in database is empty");
		}
		return emps;
	}

	@RequestMapping(value = "/emp/save", method = RequestMethod.GET)
	public String saveEmployee(Model model) {
		model.addAttribute("employee", new Employee());
		return "empSave";
	}

	@RequestMapping(value = "/emp/save.do", method = RequestMethod.POST)
	public String saveEmployeeAction(@Validated Employee employee,
			BindingResult bindingResult, Model model) {

		if (bindingResult.hasErrors()) {
			return "empSave";
		} else {
			if (employeeDao.getEmpByName(employee.getName()) != null) {
				System.out.println("ge");
				model.addAttribute("emp", employee);
				employeeDao.insert(employee);
			} else {
				throw new DuplicateEmployeeException(
						"Duplicate employee exception");
			}
			return "empSaveSuccess";
		}
	}

	@RequestMapping(value = "/emp/delete/{id}", method = RequestMethod.GET)
	public String deleteEmployee(@PathVariable("id") int id) {
		employeeDao.delete(employeeDao.getEmpById(id));
		return "admin";
	}
}
