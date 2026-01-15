package com.fbt.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fbt.entity.DoctorsTimeOff;
import com.fbt.repository.DoctorTimeOffRepository;

@Service
public class DoctorServices {

	@Autowired
	private DoctorTimeOffRepository doctorTimeOffRepository;

	public void setTimeOff(DoctorsTimeOff doctorsTimeOff) {
		List<DoctorsTimeOff> existingDoctorTimeOff = getDoctorTimeOff(doctorsTimeOff.getDoctorUserame(),doctorsTimeOff.getTimeOffDate());
//		if() 
//		{
//			
//		}
	}

	public List<DoctorsTimeOff> getDoctorTimeOff(String doctorUsername, LocalDate date) {
		return doctorTimeOffRepository.findByDoctorUserameAndTimeOffDate(doctorUsername, date);
	}

//	@Override
//	public List<DoctorsTimeOff> getDoctorTimeOff(String doctorUsername) {
//		
//		return dao.getDoctorTimeOff(doctorUsername);
//	}

}
