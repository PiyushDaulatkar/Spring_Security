package com.securityApp.securityApp.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.securityApp.securityApp.entity.Insurance;

@RestController
@EnableMethodSecurity
public class InsuranceController {

	private List<Insurance> insurances = new ArrayList<Insurance>();
	
	@GetMapping("/")
	public String welcome() {
		return ("<h1>Welcome</h1>");
	}
	
	@GetMapping("/insurances")
	public List<Insurance> getAllInsurances(){
		
		Insurance insurance1 = new Insurance();
		insurance1.setId(1);
		insurance1.setName("Car Insurance");
		insurance1.setPremium(250);
		
		Insurance insurance2 = new Insurance();
		insurance2.setId(2);
		insurance2.setName("Health Insurance");
		insurance2.setPremium(450);
		
		insurances.add(insurance1);
		insurances.add(insurance2);
		
		return insurances;
	}
	
	@PreAuthorize("hasRole('VENDOR')")
	@PostMapping("/insurances")
	public Insurance addInsurance(@RequestBody Insurance insurance) {
		
		insurances.add(insurance);
		return insurance;
	}
}

