package com.ibm.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ibm.app.cacheManagement.CacheService;
import com.ibm.app.entity.Technician;
import com.ibm.app.exception.CannotPersistInDatabaseException;
import com.ibm.app.exception.DataNotFoundException;
import com.ibm.app.util.PasswordManagerUtil;

@RestController
@RequestMapping("/api/v1/product")
public class ProductController {
	
	@Autowired
	private CacheService cacheService;
	
	@Autowired
	private PasswordManagerUtil passwordManager;
	
	//http://localhost:8080/api/v1/product/all
	@GetMapping("/all")
	public List<Technician> getAllTech() {
		return cacheService.getAllTech();
	}
	
	//http://localhost:8080/api/v1/product/get?empid=222222
	@GetMapping("/get")
	public Technician getById(@RequestParam("empid") String empid) {
		return cacheService.getById(empid);
	}
	
	@PostMapping("/add")
	public Technician addTech(@RequestBody Technician technician) {
		String plainTextPassword = technician.getPassword();
		String encodedPassword = passwordManager.encodePassword(plainTextPassword);
		technician.setPassword(encodedPassword);
		return cacheService.addTech(technician);
	}
	
	//http://localhost:8080/api/v1/product/update
	@PutMapping("/update")
	public Technician updateTech(@RequestBody Technician technician) {
		String jsonString = "{\"ReturnDataSet\":[\"651 439-2394\",\"651 351-5116\",\"651 439-1746\"],\"RequestId\":\"FASFAST\",\"WebServiceName\":\"SIABusService\",\"TargetSchemaVersionUsed\":{\"TargetXSDName\":\"\",\"MajorVersionNumber\":0.0,\"MinorVersionNumber\":0.0},\"MessageStatus\":{\"ErrorCode\":\"\",\"ErrorMessage\":\"\",\"SeverityLevel\":\"\",\"ErrorStatus\":\"S\",\"HostErrorList\":null},\"ARTISInformation\":{\"TotalTime\":\"2844\",\"OverheadTime\":\"2844\"},\"CompletedTimeStamp\":\"2023-09-12T02:20:54.54-05:00\",\"CompletedTimeStampSpecified\":true}";
		String techEnteredPassword = technician.getPassword();
		if (techEnteredPassword.isBlank()) {
			throw new CannotPersistInDatabaseException("Password field is empty");
		} else {
			Technician tech = cacheService.getById(technician.getEmpid());
			boolean isMatch = passwordManager.matches(techEnteredPassword, tech.getPassword());
			if (isMatch) {
				String encodePassword = passwordManager.encodePassword(techEnteredPassword);
				technician.setPassword(encodePassword);
				technician.setCurrentAssignmentResponse(jsonString);
				return cacheService.updateTech(technician);
			} else {
				throw new CannotPersistInDatabaseException("Password does not match");
			}
		}

	}
	
	//http://localhost:8080/api/v1/product/passwordChange?empid=&oldPass=&newPass=
	@GetMapping("/passwordChange")
	public String updatePassword(@RequestParam("empid") String empid, @RequestParam("oldPass") String oldPassword,
			@RequestParam("newPass") String newPassword) {
		
		//if old password matches with DB then allow to change password
		System.out.println("empid: "+ empid +", oldPass: "+oldPassword +", newPass: "+ newPassword);
		Technician technician = cacheService.getById(empid);
		if(technician != null) {
			String encodedPassword = technician.getPassword();
			boolean isMatch = passwordManager.matches(oldPassword, encodedPassword);
			if(isMatch) {
				
				String newEncodedPassword = passwordManager.encodePassword(newPassword);
				technician.setPassword(newEncodedPassword);
				Technician result = cacheService.updateTechPassword(technician);
				if(result != null)
				return "Password changed successfully";
				else 
					throw new CannotPersistInDatabaseException("Cannot save new password");
				
			} else {
				return "Password doesnot match, can't change the password";
			}
			
		} else {
			throw new DataNotFoundException("Technician Not present");
		}
	}
	
	@DeleteMapping("/delete")
	public Technician deleteTech(@RequestParam("empid") String empid) {
		return cacheService.deleteTech(empid);
	}
}
