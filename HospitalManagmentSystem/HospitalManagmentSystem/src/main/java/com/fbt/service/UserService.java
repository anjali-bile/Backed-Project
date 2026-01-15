package com.fbt.service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fbt.entity.Appointment;
import com.fbt.entity.Slots;
import com.fbt.entity.User;
import com.fbt.repository.AppoitmentRepository;
import com.fbt.repository.DoctorTimeOffRepository;
import com.fbt.repository.SlotsRepository;
import com.fbt.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private AppoitmentRepository appoitmentRepository;

	@Autowired
	private DoctorTimeOffRepository doctorTimeOffRepo;

	@Autowired
	private SlotsRepository slotsRepository;

	public void updateProfile() {
	}

	public User checkIsDoctorAndReturnData(String username) {
		Optional<User> optionalUser = userRepository.findByUserName(username);

		if (optionalUser.isEmpty()) {
			return null; // User not found
		}

		User user = optionalUser.get();

		if (user.getRole().getId() != 2) {
			return null; // User is not a doctor
		}

		return user; // Valid doctor
	}

	public Map<LocalTime, LocalTime> getAppoitmentData(String userName, LocalDate date) {
		User doctor = checkIsDoctorAndReturnData(userName);
		if (doctor == null) {
			System.out.println("User is not a doctor or doesn't exist.");

		}else {

		int doctorId = doctor.getUserID();
		List<Appointment> appointments = appoitmentRepository.findByDoctorIdAndAppointmentDate(doctorId, date);

		if (appointments == null || appointments.isEmpty()) {
			System.out.println("No appointments found for doctor on given date.");

		}

		Map<LocalTime, LocalTime> appointmentTimeMap = new HashMap<>();
		for (Appointment appointment : appointments) {
			appointmentTimeMap.put(appointment.getAppointmentStartTime(), appointment.getAppointmentEndTime());
		}
		

		return appointmentTimeMap;
		}
		return null;

	}

	public Map<String, String> doctorTimeOffData(String userName, LocalDate date) {
		Map<String, String> timeOffMap = new HashMap<>();

		User doctor = checkIsDoctorAndReturnData(userName);
		if (doctor == null) {
			System.out.println("User is not a doctor or doesn't exist.");
			return timeOffMap; // Return empty map if invalid
		}

		doctorTimeOffRepo.findFirstByDoctorUserameAndTimeOffDate(userName, date).ifPresent(timeOff -> {
			List<Slots> slots = slotsRepository.findByDoctorTimeOff_timeoffId(timeOff.getTimeoffId());
			slots.forEach(slot -> timeOffMap.put(slot.getStartTime(), slot.getEndTime()));
		});

		return timeOffMap;
	}

	public User getDoctorById(int userId) {
		Optional<User> byId = userRepository.findById(userId);
		if (byId.isPresent()) {
			User user = byId.get();

			if (user.getRole().getId() == 2) {
				return user; // User is not a doctor
			}
		}
		return null;
	}
}
