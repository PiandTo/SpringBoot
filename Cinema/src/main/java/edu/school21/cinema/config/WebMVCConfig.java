package edu.school21.cinema.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.validation.DefaultMessageCodesResolver;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.view.freemarker.FreeMarkerViewResolver;

import java.util.Locale;

@Configuration
public class WebMVCConfig implements WebMvcConfigurer {

//<<<<<<< HEAD
//	@Override
//	public void addResourceHandlers(ResourceHandlerRegistry registry) {
//		registry
//				.addResourceHandler("/webjars/**", "/static/static/**").addResourceLocations("/webjars/", "/static/static/");
//	}
//
//=======
//>>>>>>> origin/hemelia2
	@Bean
	public ViewResolver freemarkerViewResolver() {
		FreeMarkerViewResolver resolver = new FreeMarkerViewResolver("", ".ftl");
		resolver.setContentType("text/html; charset=utf-8");
		resolver.setCache(true);
		return resolver;
	}

	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/").setViewName("index");
	}

	// In order to take effect, LocaleChangeInterceptor bean
	// needs to be added to the application's interceptor registry
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(localeChangeInterceptor());
	}

	// to be able to determine which locale is currently being used
	@Bean
	public LocaleResolver localeResolver() {
		CookieLocaleResolver slr = new CookieLocaleResolver();
		slr.setDefaultLocale(Locale.US);
		return slr;
	}

	// an interceptor bean that will switch to a new locale based on the lang parameter
	// appended to a request
	@Bean
	public LocaleChangeInterceptor localeChangeInterceptor() {
		LocaleChangeInterceptor lci = new LocaleChangeInterceptor();
		lci.setParamName("lang");
		return lci;
	}

	// register ResourceBundleMessageSource to use messages in freemarker templates
	@Bean
	public MessageSource messageSource() {
		ReloadableResourceBundleMessageSource messageSource =
				new ReloadableResourceBundleMessageSource();
		messageSource.setBasenames("classpath:/messages/translate_messages" ,
				"classpath:/messages/errors_messages");
		messageSource.setDefaultEncoding("UTF-8");
		return messageSource;
	}
	@Bean
	public SimpleMailMessage simpleMailMessage() {
		SimpleMailMessage msg = new SimpleMailMessage();
		msg.setText("" +
				"You try to register! Submit you registration:" + "\n%s\n");
		return msg;
	}

	//To use custom name messages in a properties file like we need to define
	// a LocalValidatorFactoryBean and register the messageSource
	@Bean
	public LocalValidatorFactoryBean getValidator() {
		LocalValidatorFactoryBean bean = new LocalValidatorFactoryBean();
		bean.setValidationMessageSource(messageSource());
		return bean;
	}

	@Bean
	public DefaultMessageCodesResolver defaultMessageCodesResolver() {
		DefaultMessageCodesResolver def = new DefaultMessageCodesResolver();
		def.setPrefix("validation:");
		return def;
//		BeanPropertyBindingResult bindingResult =
//				new BeanPropertyBindingResult(User.class);
//		DefaultMessageCodesResolver messageCodesResolver =
//				(DefaultMessageCodesResolver) bindingResult.getMessageCodesResolver();
//		messageCodesResolver.setMessageCodeFormatter(
//				DefaultMessageCodesResolver.Format.POSTFIX_ERROR_CODE);
//		return messageCodesResolver;
	}
}
