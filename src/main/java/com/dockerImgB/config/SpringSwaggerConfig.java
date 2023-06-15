package com.dockerImgB.config;

import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Parameter;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import springfox.documentation.spi.service.contexts.SecurityContext;
@Configuration
@EnableSwagger2
@EnableWebMvc
public class SpringSwaggerConfig {

	  private static final Set<String> DEFAULT_PRODUCES_CONSUMES = new HashSet<String>(Arrays.asList("application/json")); 

		public static final String AUTHORIZATION_HEADER = "Authorization";
		
		private ApiKey apiKeys() {
			return new ApiKey("Auth_Token", AUTHORIZATION_HEADER, "header");	
		}
		
		private List<SecurityContext> securityContext(){
			return  Arrays.asList(SecurityContext.builder().securityReferences(sf()).build());
		}
		
		private List<SecurityReference> sf(){
			AuthorizationScope scopes = new AuthorizationScope("globle", "accessEvething");
			return Arrays.asList(new SecurityReference("scope", new AuthorizationScope[] {scopes}));
		}

		@Bean
	    public Docket api() { 
			
			
			/*
			 * ParameterBuilder parameterBuilder = new ParameterBuilder(); List<Parameter>
			 * parameters = new ArrayList<>(); parameters.clear();
			 * parameterBuilder.name("Authorization") .modelRef(new ModelRef("string"))
			 * .parameterType("header") .defaultValue("Basic em9uZTpteXBhc3N3b3Jk")
			 * .description("OAuth2 token") .required(true) .build();
			 * 
			 * parameters.add(parameterBuilder.build());
			 */
		    
	        return new Docket(DocumentationType.SWAGGER_2)
	          .apiInfo(getInfo())
	          .securityContexts(securityContext())
	          .securitySchemes(Arrays.asList(apiKeys()))
	          .select()                                  
	          .apis(RequestHandlerSelectors.any())              
	          .paths(PathSelectors.any())                          
	          .build();
	          
/*	          .produces(DEFAULT_PRODUCES_CONSUMES) 
	  	    .consumes(DEFAULT_PRODUCES_CONSUMES) 
	          .globalOperationParameters(parameters);  */                                         
	    }

		private ApiInfo getInfo() {
			return new ApiInfo("Swagger POC", "This POC Developed By Ashutosh", "1.0", "Terms Of Service", new Contact("Ashutosh", "https://www.google.com/","ashu@gmail.com"), "License Of APIs", "API license URL", Collections.emptyList());
		}
		
		

}