package com.alseyahat.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cloud.openfeign.EnableFeignClients;

import com.alseyahat.app.feature.attachment.properties.FileStorageProperties;

import springfox.documentation.swagger2.annotations.EnableSwagger2;


@SpringBootApplication
@EnableSwagger2
@EnableConfigurationProperties(FileStorageProperties.class)
@EnableFeignClients
public class AlseyahatApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(AlseyahatApplication.class, args);
	}
	
	@Override
	protected SpringApplicationBuilder configure(final SpringApplicationBuilder builder) {
	        return builder.sources(AlseyahatApplication.class);
	    }

}
