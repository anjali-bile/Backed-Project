package com.fbt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fbt.entity.User;
import com.fbt.service.AdminDashBoardService;

@Controller
public class AdminDashBoardController {

	@Autowired
	private AdminDashBoardService service;

	@GetMapping("/countAppoitment")
	@ResponseBody
	public long getTodaysAppointments() {
		return service.getCountOfAppoitment();
	}

	@GetMapping("/countDoctor")
	@ResponseBody
	public long countDoctors() {
		return service.getCountOfDoctor();
	}

	@GetMapping("/countPatient")
	@ResponseBody
	public long countPatients() {
		return service.getCountOfPatient();

	}

	@RequestMapping("/getDoctorByName")
	public ResponseEntity<Object> getDoctorByName(@RequestParam String doctorName) {
	    User doctor = service.getDoctorByName(doctorName);
	    if (doctor == null) {
	        return ResponseEntity.ok("Doctor Not Found");
	    }
	    return ResponseEntity.ok(doctor); // ✅ Spring will return JSON here
	}


}
