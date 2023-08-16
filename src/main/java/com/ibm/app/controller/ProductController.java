package com.ibm.app.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ibm.app.entity.Technician;
import com.ibm.app.exception.CannotPersistInDatabaseException;
import com.ibm.app.exception.DataNotFoundException;
import com.ibm.app.repository.TechnicianRepository;

@RestController
@RequestMapping("/api/v1/product")
public class ProductController {
	
	private final static String TECH_CACHE = "tech-cache";
	
	@Autowired
	private TechnicianRepository techRepo;
	
	@GetMapping("/all")
	public List<Technician> getAllTech() {
		System.out.println("Database call...");
		return techRepo.findAll();
	}
	
	@Cacheable(value = TECH_CACHE , key = "#empid")
	@GetMapping("/get")
	public Technician getById(@RequestParam("empid") String empid) {
		System.out.println("Database call...");
		return techRepo.findById(empid).orElseThrow(() -> new DataNotFoundException("Technician not present"));
	}
	
	@CachePut(value = TECH_CACHE, key = "#technician.empid")
	@PostMapping("/add")
	public Technician addTech(@RequestBody Technician technician) {
		System.out.println("Database call...");
		Optional<Technician> save = Optional.of(techRepo.save(technician));
		return save.orElseThrow(() -> new CannotPersistInDatabaseException("Technician Cannot be Added"));
	}
	
	@CachePut(value = TECH_CACHE, key = "#technician.empid")
	@PutMapping("/update")
	public Technician updateTech(@RequestBody Technician technician) {
		System.out.println("Database call...");
		Optional<Technician> save = Optional.of(techRepo.save(technician));
		return save.orElseThrow(() -> new CannotPersistInDatabaseException("Technician Cannot be Updated"));
	}
	
	@CacheEvict(value = TECH_CACHE, key = "#empid")
	@DeleteMapping("/delete")
	public Technician deleteTech(@RequestParam("empid") String empid) {
		Optional<Technician> findById = techRepo.findById(empid);
		techRepo.deleteById(empid);
		return findById.orElseThrow(() -> new DataNotFoundException("Technician not present"));
	}
}
