package com.ibm.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ibm.app.entity.Technician;

@Repository
public interface TechnicianRepository extends JpaRepository<Technician, String> {

}
