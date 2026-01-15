package com.fbt.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.fbt.entity.Role;
import com.fbt.entity.User;
import com.fbt.repository.RoleRepository;
import com.fbt.repository.UserRepository;

@Controller
public class AdminController {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;

	@RequestMapping("/adminIndex")
	public ModelAndView adminIndex() {
		return new ModelAndView("admin");
	}

	@GetMapping("/chatbot")
    public String showChatbotPage() {
        return "redirect:/chatbot.html"; // Redirect to the static resource
    }
	
	@GetMapping("/ourServices")
    public String showServicesPage() {
        return "redirect:/ourservices.html"; // Redirect to the static resource
    }
	
	@GetMapping("/visitingDoctors")
    public String showVisitingDoctorsPage() {
        return "redirect:/visitingDoctors.html"; // Redirect to the static resource
    }

	
	
	@PostMapping("/addDoctor")
	public String addDoctor(@ModelAttribute User user, Model model) {
		try {
			Optional<User> byEmail = userRepository.findByEmail(user.getEmail());
			Optional<User> byUserName = userRepository.findByUserName(user.getUserName());
			if (byUserName.isPresent()) {
				System.out.println(1);
				model.addAttribute("message", "already exist username");
				model.addAttribute("alertType", "danger");
			}
			if (byEmail.isEmpty()) {
				user.setActive(true);
				Optional<Role> roleOp = roleRepository.findById(2);
				user.setRole(roleOp.get());
				userRepository.save(user);
				model.addAttribute("message", "Doctor added successfully!");
				model.addAttribute("alertType", "success");
			} else {
				model.addAttribute("message", "Resource with this email already exists.");
				model.addAttribute("alertType", "warning");
			}

		} catch (DataIntegrityViolationException e) {
			model.addAttribute("message", "already exist username");
			model.addAttribute("alertType", "danger");
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("message", "An error occurred while adding the doctor.");
			model.addAttribute("alertType", "danger");
		}

		model.addAttribute("showForm", true); // Ensure the form is shown
		return "admin"; // Return the same page to keep it displayed
	}

}
