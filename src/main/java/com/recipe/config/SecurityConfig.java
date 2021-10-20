package com.recipe.config;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.recipe.util.JwtRequestFilter;

/**
 * Security configuration for Recipe API.
 * 
 * @author saemmadi
 *
 */
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	private static final Logger logger = LogManager.getLogger(SecurityConfig.class);

	@Autowired
	private UserDetailsService userDetailsService;

	@Autowired
	private JwtRequestFilter jwtRequestFilter;

	private static final String[] PUBLIC_URLS = { "/swagger-ui.html", "/swagger-resources/**", "/h2-console/**",
			"/authenticate" };

	/**
	 * This configure method is used to bypass URL h2 database and authenticate.
	 */
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		logger.info("Start configure() with HttpSecurity method.");
		http.csrf().disable().authorizeRequests().antMatchers(PUBLIC_URLS).permitAll().anyRequest().authenticated()
				.and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
		http.headers().frameOptions().disable();
		logger.info("Exit from configure() method.");
	}

	/**
	 * This configure method is used to bypass Websecurity for Swagger.
	 */
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/swagger-ui.html", "/swagger-resources/**", "/v2/api-docs", "/configuration/ui",
				"/configuration/security", "/webjars/**");
	}

	/**
	 * This configure method is used to config Authentication Manager for
	 * authenticate user.
	 */
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		logger.info("Start configure() with AuthenticationManagerBuilder method.");
		auth.userDetailsService(userDetailsService);
		logger.info("Exit from configure() method.");
	}

	/**
	 * This method is used to generate AuthenticationManager.
	 * 
	 * @return AuthenticationManager
	 */
	@Override
	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	/**
	 * This method is for PasswordEncoder.
	 * 
	 * @return PasswordEncoder
	 */
	@Bean
	public PasswordEncoder getPasswordEncoder() {
		return NoOpPasswordEncoder.getInstance();
	}
}
