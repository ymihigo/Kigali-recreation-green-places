package com.mihigo.main.security;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.mapping.GrantedAuthoritiesMapper;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mihigo.main.models.Users;
import com.mihigo.main.service.users.UserServices;

import aj.org.objectweb.asm.Type;

public class CustomAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

	private final AuthenticationManager authenticationManager;

	public CustomAuthenticationFilter(AuthenticationManager authenticationManager) {
		this.authenticationManager = authenticationManager;
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");

		UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username,
				password);

		return authenticationManager.authenticate(authenticationToken);
	}

	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authentication) throws IOException, ServletException {

		User uzer = (User) authentication.getPrincipal();
		Algorithm algo = Algorithm.HMAC256("secret".getBytes());

		String access_token = JWT.create().withSubject(uzer.getUsername())
				.withExpiresAt(new Date(System.currentTimeMillis() + 10 * 60 * 1000))
				.withIssuer(request.getRequestURL().toString())
				.withClaim("roles",
						uzer.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
				.sign(algo);

		String refresh_token = JWT.create().withSubject(uzer.getUsername())
				.withExpiresAt(new Date(System.currentTimeMillis() + 30 * 60 * 1000))
				.withIssuer(request.getRequestURL().toString())
				.withClaim("roles",
						uzer.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
				.sign(algo);

//		response.setHeader("access_token", access_token);
//		response.setHeader("refresh_token", refresh_token);

		Map<String, String> tokens = new HashMap<>();

		tokens.put("access_token", access_token);
		tokens.put("refresh_token", refresh_token);
		tokens.put("username", request.getParameter("username"));

		response.setContentType(MediaType.APPLICATION_JSON_VALUE);

		new ObjectMapper().writeValue(response.getOutputStream(), tokens);
	}

}
