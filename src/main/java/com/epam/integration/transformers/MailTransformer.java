package com.epam.integration.transformers;

import java.util.Date;

import org.springframework.mail.MailMessage;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.messaging.Message;

public class MailTransformer {
	
	private String mailTo;
	private String mailFrom;
	private String mailSubject;

	public String getMailTo() {
		return mailTo;
	}

	public void setMailTo(String mailTo) {
		this.mailTo = mailTo;
	}

	public String getMailFrom() {
		return mailFrom;
	}

	public void setMailFrom(String mailFrom) {
		this.mailFrom = mailFrom;
	}

	public String getMailSubject() {
		return mailSubject;
	}

	public void setMailSubject(String mailSubject) {
		this.mailSubject = mailSubject;
	}

	public MailMessage transform(Message<?> message) {
		Object payload = message.getPayload();
		String mailText = (payload != null) ? payload.toString()
				: "File is empty";

		MailMessage msg = new SimpleMailMessage();

		msg.setTo(mailTo);
		msg.setFrom(mailFrom);
		msg.setSubject(mailSubject);
		msg.setSentDate(new Date());
		msg.setText(mailText);

		return msg;
	}

}
