package com.fbt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.fbt.entity.ContactUs;
import com.fbt.service.ContactUsService;

@Controller
public class ContactUsController {

	@Autowired
	private ContactUsService contactUsService;

	@GetMapping("/contactUs")
	public String showContactUsPage() {
		return "redirect:/contactus.html"; // Redirect to the static resource
	}

	@PostMapping("/submitContactUs")
	public String submitContactUs(ContactUs contactUs) {
	    contactUsService.saveContactUs(contactUs);
	    return "redirect:/contactus.html?success=true";  // Redirect to static page with success param
	}


}
