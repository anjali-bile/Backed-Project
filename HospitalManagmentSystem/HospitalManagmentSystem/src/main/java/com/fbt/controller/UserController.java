package com.fbt.controller;

import java.util.List;
import java.util.Optional;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.fbt.entity.Appointment;
import com.fbt.entity.Role;
import com.fbt.entity.User;
import com.fbt.repository.RoleRepository;
import com.fbt.repository.UserRepository;
import com.fbt.service.AppointmentService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class UserController {

	@Autowired
	SessionFactory sf;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private AppointmentService appointmentService;

	@GetMapping("/appoitmentPage")
	public String appoitmentPage() {
		return "redirect:/appoitment.html"; // Redirect to the static resource
	}

	@RequestMapping("/")
	public ModelAndView indexPage() {
		return new ModelAndView("index");

	}

	@RequestMapping("/logPage")
	public ModelAndView signInPage() {
		return new ModelAndView("login");

	}

	@RequestMapping("/registerUser")
	public ModelAndView registerUser(@ModelAttribute User user) {

		Optional<User> byEmail = userRepository.findByEmail(user.getEmail());
		Optional<User> byUserName = userRepository.findByUserName(user.getUserName());
		if (byEmail.isPresent() || byUserName.isPresent()) {
			return new ModelAndView("login");
		} else {
			user.setActive(true);
			Optional<Role> roleOp = roleRepository.findById(3);
			user.setRole(roleOp.get());
			userRepository.save(user);
			return new ModelAndView("login");

		}
	}

	@RequestMapping("/userLogin")
	public ModelAndView engSignIn(@ModelAttribute User user, HttpServletRequest request) {
		ModelAndView andView = new ModelAndView();

		Optional<User> byEmail = userRepository.findByEmail(user.getEmail());
		User user1 = null;
		if (byEmail.isPresent()) {
			user1 = byEmail.get();
		}

		if (user1 != null && user1.getEmail().equals(user.getEmail()) && user1.getPassword().equals(user.getPassword())
				&& user1.getRole().getId() == 3) {
			andView.setViewName("availablity");
			HttpSession httpSession = request.getSession();
			httpSession.setAttribute("name", user1.getUserName());
			httpSession.setAttribute("useremail", user1.getEmail());
			andView.addObject("message", "You have login successfull");
		} // Controller Method after Successful Doctor Login
		else if (user1 != null && user1.getEmail().equals(user.getEmail())
				&& user1.getPassword().equals(user.getPassword()) && user1.getRole().getId() == 2) {

			// Fetch today's appointments, all appointments, and upcoming appointments for
			// the logged-in doctor
			List<Appointment> todaysAppointments = appointmentService.getTodaysAppoitment(user1.getUserID());
			List<Appointment> allAppointments = appointmentService.getAllAppoitments(user1.getUserID());
			List<Appointment> upcomingAppointments = appointmentService.getUpcomingAppoitments(user1.getUserID());

			// Set session attributes for welcome message and appointments
			String welcomeMessage = "Welcome, Dr. " + user1.getUserName();
			HttpSession session = request.getSession();
			session.setAttribute("welcomeMessage", welcomeMessage);

			// Set all three lists in the session
			session.setAttribute("todaysAppointments", todaysAppointments);
			session.setAttribute("allAppointments", allAppointments);
			session.setAttribute("upcomingAppointments", upcomingAppointments); // <-- Added this line

			// Set view name and session attributes for login details
			andView.setViewName("doctord");
			session.setAttribute("name", user1.getUserName());
			session.setAttribute("useremail", user1.getEmail());
			andView.addObject("message", "You have logged in successfully");
		} else if (user.getEmail().equals("hospitaladmin@gmail.com") && user.getPassword().equals("Admin@123")) {
			andView.setViewName("admin");
		}

		/////

		else {
			andView.setViewName("login");
			andView.addObject("message", "Wrong User Id Or Password");
		}

		return andView;
	}

	@RequestMapping("/userLogOut")
	public ModelAndView engLogOut(HttpServletRequest request) {
		HttpSession ss = request.getSession(false);
		if (ss != null) {
			ss.invalidate();
		}
		ModelAndView mv = new ModelAndView();
		mv.setViewName("index");
		mv.addObject("message", "log out Succesfully...");
		return mv;

	}

	@RequestMapping("/updateProfile")
	public void updateProfile(@ModelAttribute User user) {

	}

}
