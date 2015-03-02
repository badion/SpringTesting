package com.epam;

import static net.sourceforge.jwebunit.junit.JWebUnit.beginAt;
import static net.sourceforge.jwebunit.junit.JWebUnit.setTextField;
import static net.sourceforge.jwebunit.junit.JWebUnit.submit;

import java.util.Random;

import org.testng.annotations.Test;

public class TestRegistrationForm {

	@Test
	public void testLogin() {

		beginAt("/http://localhost:8080/SpringInActionPart6/registration");

		setTextField("name", "vit" + new Random().nextInt(100));
		setTextField("age", "15");
		setTextField("email", "badion@bigmir.net");
		setTextField("password", "test123");
		submit();
	}
}
