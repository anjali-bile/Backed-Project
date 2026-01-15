package com.fbt.controller;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.fbt.entity.Appointment;
import com.fbt.entity.User;
import com.fbt.service.AppointmentService;
import com.fbt.service.EmailService;
import com.fbt.service.UserService;

@Controller
public class AppointmentController {

	@Autowired
	private AppointmentService appointmentService;

	@Autowired
	private UserService userService;

	@Autowired
	private EmailService emailService;

	@RequestMapping("/availablityPage")
	public ModelAndView appointmentPage() {
		return new ModelAndView("availablity");

	}

	@PostMapping(value = "/checkDoctorAvailibility")
	public ResponseEntity<String> doctorAvailibilityChecker(@RequestParam String username, @RequestParam LocalDate date,
			@RequestParam LocalTime startTime, @RequestParam LocalTime endTime) {

		int status = appointmentService.doctorAvailibilityChecker(username, date, startTime, endTime);
		Map<LocalTime, LocalTime> appoitmentData = userService.getAppoitmentData(username, date);
		Map<String, String> doctorTimeOffData = userService.doctorTimeOffData(username, date);
		User user = userService.checkIsDoctorAndReturnData(username);
		// appoitment data by usrename and date username-id(appointment) username
		// doctortimeoff(slots)
		// slots, time off data
		switch (status) {
		case 1:
			return ResponseEntity
					.ok("Doctor is available for appointment. Please Note This Doctor ID : " + user.getUserID());
		case 2:
			return ResponseEntity.ok("Doctor is not available for the given day");
		case 3:
			return ResponseEntity.ok("Doctor is on time off period . Doctor Time Off  Slots : " + doctorTimeOffData);
		case 4:

			return ResponseEntity.ok("This time slot is already booked.Booked Slots :" + appoitmentData);
		case 5:
			return ResponseEntity.ok("Please Check Doctor Name");
		case 6:
			return ResponseEntity.ok("Doctor not found");
		default:
			return ResponseEntity.ok("Invalid status code");
		}

	}

	@RequestMapping("/scheduleAppointment")
	public ResponseEntity<String> scheduleAppointment(@ModelAttribute Appointment appointment) {
		int status = appointmentService.scheduleAppointment(appointment);

		String message;
		switch (status) {
		case 7:
			message = "⚠️ Invalid doctor ID or not a registered doctor. Please verify in the availability form.";
			break;
		case 8:
			message = "❌ Invalid time range.";
			break;
		case 9:
			message = "❌ Invalid time range. End time must be at least 15 minutes after start time.";
			break;
		case 10:
			message = "📅 Invalid appointment date. Please pick a date from today up to 3 months ahead.";
			break;
		case 11:
			emailService.draftEmail(appointment);
			message = "✅ Appointment scheduled successfully Please Check Email!";
			break;
		case 2:
			message = "❌ Doctor is not available on the selected date.";
			break;
		case 4:
			message = "❌ This time slot is already booked.";
			break;
		default:
			message = "⚠️ Unknown error or Please check Doctor Availiblity";
			break;

		}

		return ResponseEntity.ok(message);
	}

	@GetMapping("/appointmentForm")
	public String showAppointmentForm() {
		return "redirect:/appoitment"; // This matches appointmentForm.html
	}

}
