package com.fbt.controller;

import java.nio.charset.StandardCharsets;
import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.UriUtils;

import com.fbt.entity.DoctorsTimeOff;
import com.fbt.service.DoctorTimeOffService;

@Controller
public class DoctorController {

	@Autowired
	private DoctorTimeOffService service;

	@RequestMapping("/doctorDashboard")
	public ModelAndView doctorDashboard() {
		return new ModelAndView("doctord");
	}

	@GetMapping("/doctorTimeOffPage")
	public String doctorTimeOffPage() {
		return "redirect:/doctortimeoff.html"; // Redirect to the static resource
	}

//	@PostMapping(value = "/set-time-off",produces = {"application/json"})
//	public ResponseEntity<String> setTimeOff(@RequestBody  DoctorsTimeOff doctorsTimeOff) {
//		
//		int status = service.setTimeOff(doctorsTimeOff);
//		
//		if (status == 1) {
//
//			return new ResponseEntity<String>("Time Off Added Successfully.",HttpStatus.CREATED);
//		} else if (status == 2) {
//			throw new ResourceAlreadyExistsException("Time Off Updated");
//		} else if(status==3){
//			return new ResponseEntity<String>("Appointment is already scheduled on this time, You cant create or update timeoff",HttpStatus.CONFLICT);
//		}else {
//			return new ResponseEntity<String>("Something went wrong",HttpStatus.INTERNAL_SERVER_ERROR);
//		}
//				
//	}

	@PostMapping("/setTimeOff")
	public String setTimeOff(@ModelAttribute DoctorsTimeOff doctorsTimeOff) {
	    if (doctorsTimeOff.isDayOff()) {
	        doctorsTimeOff.setUnavailableTimeSlots(Collections.emptyList());
	    }

	    int status = service.setTimeOff(doctorsTimeOff);

	    String message;
	    if (status == 1) {
	        message = "Time Off Added Successfully.";
	    } else if (status == 2) {
	        message = "Time Off Updated Successfully.";
	    } else if (status == 3) {
	        message = "Appointment is already scheduled on this time, you can't create or update time off.";
	    } else {
	        message = "Something went wrong Please Check Username.";
	    }

	    // Encode message to URL param
	    String encodedMessage = UriUtils.encodeQueryParam(message, StandardCharsets.UTF_8);

	    return "redirect:/doctortimeoff.html?msg=" + encodedMessage;
	}

}
