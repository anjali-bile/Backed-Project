package com.fbt.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fbt.entity.Slots;

public interface SlotsRepository extends JpaRepository<Slots, Long> {
	List<Slots> findByDoctorTimeOff_timeoffId(long timeoffId);


	
}
