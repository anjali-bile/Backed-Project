package com.fbt.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fbt.entity.ContactUs;
import com.fbt.repository.ContactUsRepository;

@Service
public class ContactUsService {
	
	 @Autowired
	    private ContactUsRepository repository;

	    public ContactUs saveContactUs(ContactUs contactUs) {
	        return repository.save(contactUs);
	    }

}
