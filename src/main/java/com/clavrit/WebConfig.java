package com.clavrit;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {

	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**");
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/**").addResourceLocations("classpath:/static/", "classpath:/static/resume",
				"classpath:/static/profilePic");
	}

//	@Bean
//	public MessageSource messageResource() {
//		ReloadableResourceBundleMessageSource messageResource = new ReloadableResourceBundleMessageSource();
//		messageResource.setBasename("classpath:message");
//		messageResource.setDefaultEncoding("UTF-8");
//		return messageResource;
//	}
//	
//	@Bean(name = "localeResolver")
//	public LocaleResolver localeResolver() {
//		SessionLocaleResolver slr = new SessionLocaleResolver();
//		slr.setDefaultLocale(Locale.US);
//		return slr;
//	}

}
