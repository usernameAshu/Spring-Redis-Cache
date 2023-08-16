package com.ibm.app.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "TECHNICIAN")
@Data
public class Technician implements Serializable{
	
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "EMPID")
	private String empid;
	
	@Column(name = "PASSWORD")
	private String password;
	
	@Column(name = "PAGERCO")
	private String pagerCo;
	
	@Column(name = "BIRTHDATE")
	private String birthdate;
	
	@Column(name = "TIMESTAMP")
	private LocalDateTime timestamp;

}
