package com.tkming.elasticsearchrest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.web.filter.CharacterEncodingFilter;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@EnableAutoConfiguration
public class ElasticSearchRestApplication {
	@Bean
	public FilterRegistrationBean charFilterRegistrationBean() {
		FilterRegistrationBean registrationBean = new FilterRegistrationBean();
		CharacterEncodingFilter apiCorsFilter = new CharacterEncodingFilter();
		apiCorsFilter.setForceEncoding(true);
		apiCorsFilter.setEncoding("utf-8");
		registrationBean.setFilter(apiCorsFilter);
		List<String> urlPatterns = new ArrayList<String>();
		urlPatterns.add("/*");
		registrationBean.setUrlPatterns(urlPatterns);
		return registrationBean;
	}

	public static void main(String[] args) {
		SpringApplication.run(ElasticSearchRestApplication.class, args);
	}
}
