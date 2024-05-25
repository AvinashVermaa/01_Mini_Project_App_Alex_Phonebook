package com.avinash.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.avinash.service.ContactService;

import ch.qos.logback.core.model.Model;

@Controller
public class ContactController {

	private ContactService contactService;
	
	@Autowired
	public ContactController(ContactService contactService) {
		System.out.println("ContactController :: constructor");
		this.contactService = contactService;
	}
	
	@GetMapping("/")
	public String getIndexPage(Model model) {
		return "index";
	}
}
