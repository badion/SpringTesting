package com.epam.exceptions;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class EmployeeInDataBaseIsEmpty extends WebApplicationException {
	private static final long serialVersionUID = 1L;

	private String exceptionMsg;

	public String getExceptionMsg() {
		return exceptionMsg;
	}

	public void setExceptionMsg(String exceptionMsg) {
		this.exceptionMsg = exceptionMsg;
	}

	public EmployeeInDataBaseIsEmpty(String exceptionMsg) {
		super(Response.status(Response.Status.NOT_FOUND).entity(exceptionMsg)
				.type(MediaType.TEXT_PLAIN).build());
		this.exceptionMsg = exceptionMsg;
	}

}
