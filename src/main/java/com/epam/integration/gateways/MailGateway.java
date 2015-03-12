package com.epam.integration.gateways;

import org.springframework.integration.annotation.Payload;

public interface MailGateway {

	@Payload("#args")
	void sendMail(String name, Integer age, String email, String password);
}
