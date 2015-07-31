package com.dlp.sso.cas_demo.client.config;

import org.jasig.cas.client.session.SingleSignOutFilter;
import org.jasig.cas.client.validation.Cas20ServiceTicketValidator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.cas.ServiceProperties;
import org.springframework.security.cas.authentication.CasAuthenticationProvider;
import org.springframework.security.cas.web.CasAuthenticationEntryPoint;
import org.springframework.security.cas.web.CasAuthenticationFilter;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.AuthenticationUserDetailsService;
import org.springframework.security.web.authentication.logout.LogoutFilter;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.security.web.util.matcher.RegexRequestMatcher;

import com.dlp.sso.cas_demo.client.security.userservice.impl.CasAuthenticationUserDetailsService;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(AuthenticationManagerBuilder auth)
			throws Exception {
		auth.authenticationProvider(casAuthenticationProvider());
	}

	@Bean
	public CasAuthenticationProvider casAuthenticationProvider() {
		CasAuthenticationProvider casAuthenticationProvider = new CasAuthenticationProvider();
		casAuthenticationProvider
				.setAuthenticationUserDetailsService(authenticationUserDetailsService());
		casAuthenticationProvider.setServiceProperties(serviceProperties());
		casAuthenticationProvider
				.setTicketValidator(cas20ServiceTicketValidator());
		casAuthenticationProvider.setKey("an_id_for_this_auth_provider_only");
		return casAuthenticationProvider;
	}

	@Bean
	public AuthenticationUserDetailsService authenticationUserDetailsService() {
		return new CasAuthenticationUserDetailsService();
	}

	@Bean
	public Cas20ServiceTicketValidator cas20ServiceTicketValidator() {
		return new Cas20ServiceTicketValidator(
				"https://demo-cas.com:8443/cas/p3");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/secure").hasRole("ADMIN")
				.antMatchers("/registerpost").hasRole("ADMIN").anyRequest()
				.permitAll().and().csrf().disable();
		http.addFilter(casAuthenticationFilter());
		http.addFilterBefore(requestSingleLogoutFilter(), LogoutFilter.class);
		http.addFilterBefore(singleSignOutFilter(),
				CasAuthenticationFilter.class);
		http.exceptionHandling().authenticationEntryPoint(
				casAuthenticationEntryPoint());
	}

	@Bean
	public CasAuthenticationFilter casAuthenticationFilter() throws Exception {
		CasAuthenticationFilter casAuthenticationFilter = new CasAuthenticationFilter();
		casAuthenticationFilter
				.setAuthenticationManager(authenticationManager());
		return casAuthenticationFilter;
	}

	@Bean
	public CasAuthenticationEntryPoint casAuthenticationEntryPoint() {
		CasAuthenticationEntryPoint casAuthenticationEntryPoint = new CasAuthenticationEntryPoint();
		casAuthenticationEntryPoint
				.setLoginUrl("https://demo-cas.com:8443/cas/login");
		casAuthenticationEntryPoint.setServiceProperties(serviceProperties());
		return casAuthenticationEntryPoint;
	}

	@Bean
	public ServiceProperties serviceProperties() {
		ServiceProperties serviceProperties = new ServiceProperties();
		// spring security version 3.2.3
		// serviceProperties.setService("http://localhost:8080/app1/j_spring_cas_security_check");

		// spring security version 4.0.1
		serviceProperties
				.setService("http://localhost:8080/role-based-access/login/cas");
		serviceProperties.setSendRenew(false);
		return serviceProperties;
	}

	@Bean
	SingleSignOutFilter singleSignOutFilter() {
		SingleSignOutFilter singleSignOutFilter = new SingleSignOutFilter();
		return singleSignOutFilter;
	}

	@Bean
	LogoutFilter requestSingleLogoutFilter() {
		SecurityContextLogoutHandler securityContextLogoutHandler = new SecurityContextLogoutHandler();
		LogoutFilter logoutFilter = new LogoutFilter(
				"https://demo-cas.com:8443/cas/logout",
				securityContextLogoutHandler);
		RegexRequestMatcher logoutRequestMatcher = new RegexRequestMatcher(
				"/logout/cas", null);
		logoutFilter.setLogoutRequestMatcher(logoutRequestMatcher);
		return logoutFilter;
	}

}