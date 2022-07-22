package com.mihigo.main.security;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
@EnableWebSecurity
public class securityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserDetailsService userDetailsService;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

//	@Override
//	protected void configure(HttpSecurity http) throws Exception {
//		http.csrf().disable();
//		http.authorizeHttpRequests().anyRequest().permitAll();
//		
//	http.cors(c->
//	{
//			CorsConfigurationSource cs = r -> {
//				CorsConfiguration cc = new CorsConfiguration();
//				cc.setAllowedOrigins(List.of("*"));
//				cc.setAllowedMethods(List.of("*"));
//				cc.setAllowedHeaders(List.of("*"));
//				return cc;
//			};
//			c.configurationSource(cs);
//		});
//	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		CustomAuthenticationFilter customAuthenticationFilter = new CustomAuthenticationFilter(
				authenticationManagerBean());
		customAuthenticationFilter.setFilterProcessesUrl("/kigali/login");
		http.csrf().disable();
		http.cors(c -> {
			CorsConfigurationSource cs = r -> {
				CorsConfiguration cc = new CorsConfiguration();
				cc.setAllowedOrigins(List.of("*"));
				cc.setAllowedMethods(List.of("*"));
				cc.setAllowedHeaders(List.of("*"));
				return cc;
			};
			c.configurationSource(cs);
		});

		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		http.authorizeRequests().antMatchers("/kigali/login/**").permitAll();
		http.authorizeRequests().antMatchers("/kigali/all/**").permitAll();
		http.authorizeRequests().antMatchers("GET", "/kigali/admin/**").hasAnyAuthority("Admin");
		http.authorizeHttpRequests().antMatchers(HttpMethod.POST, "/kigali/admin/**").hasAnyAuthority("Admin");
		http.authorizeHttpRequests().antMatchers(HttpMethod.GET, "/kigali/user/**").hasAnyAuthority("Admin",
				"Site_user", "Site_Constructor");
//		http.authorizeRequests().antMatchers("POST","/kigali/admin/**").hasAnyAuthority("Admin");
		http.authorizeRequests().anyRequest().authenticated();
		http.addFilter(customAuthenticationFilter);

		http.addFilterBefore(new CustomAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);

	}

	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

}
