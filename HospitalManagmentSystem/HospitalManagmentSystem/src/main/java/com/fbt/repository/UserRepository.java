package com.fbt.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.fbt.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {
	 Optional<User> findByEmail(String email);
	 Optional<User> findByUserName(String userName);
	 @Query("SELECT u FROM User u WHERE u.userName = :userName AND u.role.id = 2")
	    Optional<User> findByUserNameAndRoleIdIsTwo(@Param("userName") String userName);
	 
	 long countByRole_Id(int roleId);

}
