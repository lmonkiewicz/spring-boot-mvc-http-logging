package org.moonbit.tests.logging.http;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.ResponseErrorHandler;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.filter.CommonsRequestLoggingFilter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.servlet.Filter;
import java.io.IOException;


@Configuration
@EnableAutoConfiguration
@EnableWebMvc
@ComponentScan
public class ServletInitializer extends SpringBootServletInitializer {

	private static final Logger LOG = LoggerFactory.getLogger(ServletInitializer.class);

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(SpringBootMvcHttpLoggingApplication.class);
	}

	@Bean
	public RestTemplate restTemplate(){
		return new RestTemplateBuilder()
				.defaultMessageConverters()
				.requestFactory(HttpComponentsClientHttpRequestFactory.class)
				.errorHandler(new ResponseErrorHandler() {
					@Override
					public boolean hasError(ClientHttpResponse clientHttpResponse) throws IOException {
						return !clientHttpResponse.getStatusCode().is2xxSuccessful();
					}

					@Override
					public void handleError(ClientHttpResponse clientHttpResponse) throws IOException {
						LOG.error("Error while executing request, response status=" + clientHttpResponse.getStatusCode());
					}
				})
				.build();
	}

	@Bean
	public Filter logFilter() {
		CommonsRequestLoggingFilter filter = new CommonsRequestLoggingFilter();
		filter.setIncludeQueryString(true);
		filter.setIncludePayload(true);
		filter.setIncludeHeaders(true);
		filter.setIncludeClientInfo(true);
		filter.setMaxPayloadLength(5120);
		return filter;
	}

}
