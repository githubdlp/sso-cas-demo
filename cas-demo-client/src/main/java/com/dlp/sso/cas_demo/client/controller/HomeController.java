package com.dlp.sso.cas_demo.client.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HomeController {

	@RequestMapping(value = { "/secure" }, method = RequestMethod.GET)
	public String secure(Model data) {
		data.addAttribute("uid", SecurityContextHolder.getContext()
				.getAuthentication().getPrincipal());
		return "secure";
	}

}
