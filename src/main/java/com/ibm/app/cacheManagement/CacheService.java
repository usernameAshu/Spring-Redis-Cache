package com.ibm.app.cacheManagement;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.ibm.app.entity.Technician;
import com.ibm.app.exception.CannotPersistInDatabaseException;
import com.ibm.app.exception.DataNotFoundException;
import com.ibm.app.repository.TechnicianRepository;

@Service
public class CacheService {
	
	@Autowired
	private TechnicianRepository techRepo;
	
	private final static String TECH_CACHE = "lumen-tech-cache";
	
	public List<Technician> getAllTech() {
		System.out.println("Database call...");
		return techRepo.findAll();
	}
	
	@Cacheable(value = TECH_CACHE , key = "#empid")
//	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.REPEATABLE_READ)
	public Technician getById(String empid) {
		System.out.println("Database call...");
		return techRepo.findById(empid).orElseThrow(() -> new DataNotFoundException("Technician not present"));
	}
	
	@CachePut(value = TECH_CACHE, key = "#technician.empid")
	public Technician addTech(Technician technician) {
		System.out.println("Database call...");
		Optional<Technician> save = Optional.of(techRepo.save(technician));
		return save.orElseThrow(() -> new CannotPersistInDatabaseException("Technician Cannot be Added"));
	}
	
	@CachePut(value = TECH_CACHE, key = "#technician.empid")
	@Transactional
	public Technician updateTech(Technician technician) {
		System.out.println("Database call... Updating tech: "+ technician);
		Optional<Technician> save = Optional.of(techRepo.save(technician));
		return save.orElseThrow(() -> new CannotPersistInDatabaseException("Technician Cannot be Updated"));
	}
	
	@CachePut(value = TECH_CACHE, key = "#technician.empid")
	@Transactional
	public Technician updateTechPassword(Technician technician) {
		return techRepo.save(technician);
	}
	
	@CacheEvict(value = TECH_CACHE, key = "#empid")
	public Technician deleteTech(String empid) {
		Optional<Technician> findById = techRepo.findById(empid);
		techRepo.deleteById(empid);
		return findById.orElseThrow(() -> new DataNotFoundException("Technician not present"));
	}
}
