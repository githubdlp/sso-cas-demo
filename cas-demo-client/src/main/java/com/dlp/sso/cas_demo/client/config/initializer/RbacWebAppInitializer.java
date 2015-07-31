package com.dlp.sso.cas_demo.client.config.initializer;

import javax.servlet.Filter;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import com.dlp.sso.cas_demo.client.config.RootConfig;
import com.dlp.sso.cas_demo.client.config.SecurityConfig;
import com.dlp.sso.cas_demo.client.config.WebConfig;
import com.dlp.sso.cas_demo.client.config.filter.TestAttributeFilter;

public class RbacWebAppInitializer extends
		AbstractAnnotationConfigDispatcherServletInitializer {

	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class<?>[] { RootConfig.class, SecurityConfig.class };
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class<?>[] { WebConfig.class };
	}

	@Override
	protected String[] getServletMappings() {
		return new String[] { "/" };
	}

	@Override
	protected Filter[] getServletFilters() {
		return new Filter[] { new TestAttributeFilter() };
	}

}
