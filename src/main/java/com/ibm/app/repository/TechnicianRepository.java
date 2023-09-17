package com.ibm.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ibm.app.entity.Technician;

@Repository
public interface TechnicianRepository extends JpaRepository<Technician, String> {
	
	@Modifying
	@Query("UPDATE Technician t set t.password = :newPassword where t.empid = :empid")
	int updatePasswordByEmpid(String newPassword, String empid);
}
