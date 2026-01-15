package com.fbt.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fbt.entity.Appointment;

@Repository
public interface AppoitmentRepository extends JpaRepository<Appointment, Integer> {
	List<Appointment> findByDoctorIdAndAppointmentDate(int doctorId, LocalDate appointmentDate);

	List<Appointment> findByDoctorIdAndAppointmentDateBefore(int doctorId, LocalDate currentDate);
	
	List<Appointment> findByDoctorIdAndAppointmentDateAfter(int doctorId, LocalDate date);
	
	long countByAppointmentDate(LocalDate date);
	
	

}
