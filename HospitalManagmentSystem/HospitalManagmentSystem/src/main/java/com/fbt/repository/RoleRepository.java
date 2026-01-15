package com.fbt.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fbt.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Integer> {
	boolean existsByName(String name);

}
