package com.dockerImgB.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.dockerImgB.controller.EmployeeController;

@Component
public class AuthFilter extends OncePerRequestFilter {
	
	private static final Logger logger = Logger.getLogger(AuthFilter.class);

	@Autowired
	private Authservice authservice;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		String authToken = null;
		String userToken1 = request.getHeader("Auth_Token");
		String userToken2 = request.getHeader("header");
		String userToken = request.getHeader("Authorization");
		if (userToken != null && userToken.startsWith("Bearer ")) {

			authToken = userToken.substring(7);
			try {
				authservice.validateAuth(authToken);
				logger.info("Token is valid");
				filterChain.doFilter(request, response);
			} catch (Exception e) {
				logger.info("Token is not valid");
				response.sendError(HttpStatus.BAD_REQUEST.value(), "Invalid Token");
				return;
				// throw new AuthTokenInvalidException(authToken);
			}
		}
	}

	private List<String> excludeUrlPatterns = new ArrayList<String>(Arrays.asList("/swagger-ui.html",
			"/swagger-uui.html", "/webjars/springfox-swagger-ui/springfox.css",
			"/webjars/springfox-swagger-ui/swagger-ui-bundle.js", "/webjars/springfox-swagger-ui/swagger-ui.css",
			"/webjars/springfox-swagger-ui/swagger-ui-standalone-preset.js",
			"/webjars/springfox-swagger-ui/springfox.js", "/swagger-resources/configuration/ui",
			"/webjars/springfox-swagger-ui/favicon-32x32.png", "/swagger-resources/configuration/security",
			"/swagger-resources", "/v2/api-docs",
			"/webjars/springfox-swagger-ui/fonts/titillium-web-v6-latin-700.woff2",
			"/webjars/springfox-swagger-ui/fonts/open-sans-v15-latin-regular.woff2",
			"/webjars/springfox-swagger-ui/fonts/open-sans-v15-latin-700.woff2",
			"/webjars/springfox-swagger-ui/favicon-16x16.png", // ));
			"/swagger-ui/", "/swagger-ui/index.html", "/favicon.ico", "/swagger-ui/favicon-32x32.png",
			"/swagger-ui/favicon-16x16.png", "/v3/api-docs",
			"/swagger-ui/springfox.css",
			"/swagger-ui/swagger-ui-bundle.js",
			"/swagger-ui/swagger-ui-standalone-preset.js",
			"/swagger-ui/springfox.js","/swagger-ui/swagger-ui.css"));

	@Override
	protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
		String path = request.getRequestURI();
		if (excludeUrlPatterns.contains(path)) {
			return true;
		} else {
			return false;
		}
	}
}
