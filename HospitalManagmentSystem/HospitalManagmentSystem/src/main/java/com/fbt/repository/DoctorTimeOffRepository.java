package com.fbt.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fbt.entity.DoctorsTimeOff;

public interface DoctorTimeOffRepository extends JpaRepository<DoctorsTimeOff, Long> {
	 List<DoctorsTimeOff> findByDoctorUserameAndTimeOffDate(String doctorUserame, LocalDate timeOffDate);
	 //Optional<DoctorsTimeOff> findByDoctorUserameAndTimeOffDate(String doctorUserame, LocalDate timeOffDate);
	 Optional<DoctorsTimeOff> findFirstByDoctorUserameAndTimeOffDate(String doctorUserame, LocalDate timeOffDate);

	}
