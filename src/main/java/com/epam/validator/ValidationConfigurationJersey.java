//package com.epam.validator;
//
//import java.lang.reflect.Constructor;
//import java.lang.reflect.Method;
//import java.util.Arrays;
//import java.util.List;
//
//import javax.validation.ParameterNameProvider;
//import javax.validation.Validation;
//import javax.ws.rs.core.Context;
//import javax.ws.rs.ext.ContextResolver;
//
//import org.glassfish.jersey.server.validation.ValidationConfig;
//import org.glassfish.jersey.server.validation.internal.InjectingConstraintValidatorFactory;
//
//import com.sun.jersey.api.core.ResourceContext;
//
//public class ValidationConfigurationJersey implements
//		ContextResolver<ValidationConfig> {
//
//	@Context
//	private ResourceContext resourceContext;
//
//	@Override
//	public ValidationConfig getContext(final Class<?> type) {
//		final ValidationConfig config = new ValidationConfig();
//		config.constraintValidatorFactory(resourceContext
//				.getResource(InjectingConstraintValidatorFactory.class));
//		config.parameterNameProvider(new CustomParameterNameProvider());
//		return config;
//	}
//
//	private class CustomParameterNameProvider implements ParameterNameProvider {
//
//		private final ParameterNameProvider nameProvider;
//
//		public CustomParameterNameProvider() {
//			nameProvider = Validation.byDefaultProvider().configure()
//					.getDefaultParameterNameProvider();
//		}
//
//		@Override
//		public List<String> getParameterNames(Constructor<?> constructor) {
//			// TODO Auto-generated method stub
//			return nameProvider.getParameterNames(constructor);
//		}
//
//		@Override
//		public List<String> getParameterNames(Method method) {
//			if ("addContact".equals(method.getName())) {
//				return Arrays.asList("contact");
//			}
//			return nameProvider.getParameterNames(method);
//		}
//
//	}
//}
