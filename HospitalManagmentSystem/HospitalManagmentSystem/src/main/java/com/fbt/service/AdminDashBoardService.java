package com.fbt.service;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fbt.entity.User;
import com.fbt.repository.AppoitmentRepository;
import com.fbt.repository.UserRepository;

@Service
public class AdminDashBoardService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private AppoitmentRepository appoitmentRepository;

	public long getCountOfAppoitment() {
		LocalDate today = LocalDate.now();
		return appoitmentRepository.countByAppointmentDate(today);
	}

	public long getCountOfDoctor() {
		return userRepository.countByRole_Id(2);

	}

	public long getCountOfPatient() {
		return userRepository.countByRole_Id(3);
	}

	public User getDoctorByName(String doctorName) {

		Optional<User> byUserName = userRepository.findByUserName(doctorName);
		if (byUserName.isPresent()) {
			User user = byUserName.get();
			if(user.getRole().getId()==2) {
				return user;
			}
		}
		return null;

	}

}
