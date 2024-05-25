package com.avinash.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.avinash.entities.ContactInfo;
import com.avinash.service.ContactService;


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
		model.addAttribute("contact", new ContactInfo());
		return "index";
	}
	
	@PostMapping("/saveContactData")
	public String saveContactData(@ModelAttribute("contact") ContactInfo contactInfo,Model model) {
		System.out.println(contactInfo);
		String msg = contactService.saveContact(contactInfo);
		model.addAttribute("msg", msg);
		return "redirect:/getAllUsers";
	}
	
	@GetMapping("/getAllUsers")
	public String getAllUsers(Model model) {
		List<ContactInfo> lists = contactService.getAllContacts();
		model.addAttribute("lists", lists);
		return "view-users";
	}
	
	@GetMapping("/edit")
	public String editUser(@RequestParam("sno") Integer sno,Model model) {
		System.out.println(sno);
		ContactInfo contact = contactService.getContactDataBasedOnId(sno);
		model.addAttribute("contact", contact);
		return "index";
	}
	
	@GetMapping("/delete")
	public String deleteUser(@RequestParam("sno") Integer sno,Model model) {
		System.out.println(sno);
		contactService.deleteContactsData(sno);
		model.addAttribute("msg", "Contact Deleted Successfully");
		return "forward:/getAllUsers";
	}
	
	@GetMapping("/update")
	public String updateStatus(@RequestParam("sno") Integer sno,@RequestParam("status") String status,Model model) {
		System.out.println(sno+" : "+status);
		contactService.updateCurrentActiveStatus(sno, status);
		if(status.equalsIgnoreCase("Y")) {
			model.addAttribute("msg", "Record is Activated");
		}
		else {
			model.addAttribute("msg", "Record is not Activated");
		}
		return "forward:/getAllUsers";
	}
	
}
