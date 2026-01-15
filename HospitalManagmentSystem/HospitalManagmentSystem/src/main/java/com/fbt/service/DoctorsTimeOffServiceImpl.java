package com.fbt.service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fbt.entity.DoctorsTimeOff;
import com.fbt.entity.User;
import com.fbt.repository.DoctorTimeOffRepository;
import com.fbt.repository.UserRepository;

import jakarta.transaction.Transactional;

@Service
public class DoctorsTimeOffServiceImpl implements DoctorTimeOffService {

	@Autowired
	private DoctorTimeOffRepository repository;
	@Autowired
	private UserRepository userRepository;

	@Override
	@Transactional
	public int setTimeOff(DoctorsTimeOff doctorsTimeOff) {
		Optional<User> userOpt = userRepository.findByUserNameAndRoleIdIsTwo(doctorsTimeOff.getDoctorUserame());
		if (userOpt.isPresent()) {
			// Find existing time off entries for the doctor on the given date
			List<DoctorsTimeOff> existingList = repository.findByDoctorUserameAndTimeOffDate(
					doctorsTimeOff.getDoctorUserame(), doctorsTimeOff.getTimeOffDate());

			// If full day off is selected or no slots provided, clear slots and set
			// dayOff=true
			if (doctorsTimeOff.getUnavailableTimeSlots() == null
					|| doctorsTimeOff.getUnavailableTimeSlots().isEmpty()) {
				doctorsTimeOff.setUnavailableTimeSlots(Collections.emptyList()); // clear slots explicitly
				doctorsTimeOff.setDayOff(true);
			} else {
				// Link each slot back to this DoctorsTimeOff entity for cascade persistence
				doctorsTimeOff.getUnavailableTimeSlots().forEach(slot -> slot.setDoctorTimeOff(doctorsTimeOff));
				doctorsTimeOff.setDayOff(false);
			}

			if (!existingList.isEmpty()) {
				// Delete all existing records for that doctor and date before saving new
				repository.deleteAll(existingList);
				repository.save(doctorsTimeOff);
				return 2; // updated
			} else {
				repository.save(doctorsTimeOff);
				return 1; // added
			}
		}
		return 4;
	}

	public void getDoctorByName() {

	}
}
