package br.com.app.infrastructure.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateTimeoutConfig {
	
	@Value("${resttemplate.connection-timeout:10000}")
	private int connectionTimeout;
	
	@Value("${resttemplate.read-timeout:10000}")
	private int readTimeout;
	
	@Bean
	RestTemplate restTemplate() {
	    SimpleClientHttpRequestFactory clientHttpRequestFactory  = new SimpleClientHttpRequestFactory();
	    clientHttpRequestFactory.setConnectTimeout(connectionTimeout);
	    clientHttpRequestFactory.setReadTimeout(readTimeout);
	    return new RestTemplate(clientHttpRequestFactory);
	}

}
