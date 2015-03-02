package com.epam.exceptions;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class NotFoundInDatabaseException extends WebApplicationException {

	private static final long serialVersionUID = 1L;

	private String exceptionMsg;

	public NotFoundInDatabaseException(String exceptionMsg) {
		super(Response.status(Response.Status.BAD_REQUEST).entity(exceptionMsg)
				.type(MediaType.TEXT_PLAIN).build());
		this.exceptionMsg = exceptionMsg;
	}

	public String getExceptionMsg() {
		return exceptionMsg;
	}

	public void setExceptionMsg(String exceptionMsg) {
		this.exceptionMsg = exceptionMsg;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
