package com.fbt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fbt.entity.ContactUs;
@Repository
public interface ContactUsRepository extends JpaRepository<ContactUs, Long> {
}