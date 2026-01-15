package com.fbt.service;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fbt.entity.Appointment;
import com.fbt.entity.DoctorsTimeOff;
import com.fbt.entity.User;
import com.fbt.repository.AppoitmentRepository;
import com.fbt.repository.DoctorTimeOffRepository;
import com.fbt.repository.UserRepository;
import com.fbt.utility.OverlapChecker;

@Service
public class AppointmentService {

	@Autowired
	private AppoitmentRepository appoitmentRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private DoctorTimeOffRepository timeOffRepository;

	public int doctorAvailibilityChecker(String username, LocalDate date, LocalTime startTime, LocalTime endTime) {
		boolean isDoctorAvailable = false;
		int status = 0;
		String cleanedUsername = username.trim().replaceAll("\\s{2,}", " ");
		// check doctor time off
		Optional<User> existingUser = userRepository.findByUserName(cleanedUsername);
		if (!existingUser.isPresent()) {
			// If the user does not exist, return status code for invalid user
			status = 6; // Invalid user (user not found)
			return status;
		}

		User user = existingUser.get();
		if (user.getRole().getId() != 2) {
			// If the user is not a doctor, return status code for non-doctor role
			status = 5; // User is not a doctor
			return status;
		}

		// Get doctor ID
		int doctorId = user.getUserID();
		List<DoctorsTimeOff> doctorTimeOff = timeOffRepository.findByDoctorUserameAndTimeOffDate(cleanedUsername, date);
		if (doctorTimeOff.isEmpty()) {
			// available to take appointment
			status = 1;
			isDoctorAvailable = true;
		} else {
			for (DoctorsTimeOff doctorsTimeOff : doctorTimeOff) {

				boolean isDayOff = doctorsTimeOff.isDayOff();

				if (isDayOff) {
					// day off
					status = 2;
					isDoctorAvailable = false;
					break;
				} else {
					isDoctorAvailable = doctorsTimeOff.getUnavailableTimeSlots().stream()
							.anyMatch(slot -> OverlapChecker.isOverlap(startTime, // No need to parse; it's already
																					// LocalTime
									endTime, // No need to parse; it's already LocalTime
									LocalTime.parse(slot.getStartTime()), // Parse only if it is in String
									LocalTime.parse(slot.getEndTime()) // Parse only if it is in String
							));

					List<Boolean> isSlotAvailable = new ArrayList<>();
					isSlotAvailable.add(isDoctorAvailable);

					if (isSlotAvailable.contains(true)) {
						// Doctor is on time off period
						isDoctorAvailable = true;
						status = 3;
					} else {
						// available to take appointment
						status = 1;
					}
				}

			}

		}
		// check doctor appointment time
		if (isDoctorAvailable) {

			List<Appointment> appointments = appoitmentRepository.findByDoctorIdAndAppointmentDate(doctorId, date);
			if (!appointments.isEmpty()) {
				isDoctorAvailable = appointments.stream().anyMatch(appointment -> OverlapChecker.isOverlap(startTime, // Already
																														// LocalTime,
																														// no
																														// parsing
																														// required
						endTime, // Already LocalTime, no parsing required
						appointment.getAppointmentStartTime(), // Already LocalTime, no parsing required
						appointment.getAppointmentEndTime() // Already LocalTime, no parsing required
				));
				if (isDoctorAvailable) {
					// Appointment already taken on this time
					status = 4;
				} else {
					// available to take appointment
					status = 1;
				}
			}

		}
		return status;
	}

	public int scheduleAppointment(Appointment appointment) {
		int status = 0;
		Optional<User> doctorOpt = userRepository.findById(appointment.getDoctorId());
		if (doctorOpt.isEmpty()&&!doctorOpt.isPresent()) {
			return 7;
		}else {

		User doctor = doctorOpt.get();
		if (doctor.getRole().getId() != 2) {
			return 7; // Not a valid doctor
		}

		// Step 2: Check that start and end time differ by at least 15 minutes
		LocalTime startTime = appointment.getAppointmentStartTime();
		LocalTime endTime = appointment.getAppointmentEndTime();
		if (startTime == null || endTime == null || !endTime.isAfter(startTime)) {
			return 8; // Invalid time range
		}

		Duration duration = Duration.between(startTime, endTime);
		if (duration.toMinutes() < 15) {
			return 9; // Less than 15 minutes
		}
		LocalDate today = LocalDate.now();
		LocalDate appointmentDate = appointment.getAppointmentDate();
		if (appointmentDate.isBefore(today) || appointmentDate.isAfter(today.plusMonths(3))) {
			return 10; // Invalid appointment date
		}
		status = doctorAvailibilityChecker(doctor.getUserName(), appointment.getAppointmentDate(),
				appointment.getAppointmentStartTime(), appointment.getAppointmentEndTime());
		if (status == 1) {
			appoitmentRepository.save(appointment);
			return 11;

		}
		return status;
		}
		

	}

	public List<Appointment> getTodaysAppoitment(int doctorId) {

		return appoitmentRepository.findByDoctorIdAndAppointmentDate(doctorId, LocalDate.now());
	}

	public List<Appointment> getAllAppoitments(int doctorId) {

		return appoitmentRepository.findByDoctorIdAndAppointmentDateBefore(doctorId, LocalDate.now());
	}

	public List<Appointment> getUpcomingAppoitments(int doctorId) {

		return appoitmentRepository.findByDoctorIdAndAppointmentDateAfter(doctorId, LocalDate.now());
	}

	

}
