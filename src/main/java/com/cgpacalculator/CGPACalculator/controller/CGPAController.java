package com.cgpacalculator.CGPACalculator.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cgpacalculator.CGPACalculator.Entity.Converter;
import com.cgpacalculator.CGPACalculator.Entity.SemesterGPA;
import com.cgpacalculator.CGPACalculator.Entity.Subjects;
import com.cgpacalculator.CGPACalculator.Entity.register;
import com.cgpacalculator.CGPACalculator.Repository.RegisterRepo;
import com.cgpacalculator.CGPACalculator.Service.CGPACalculatorService;

@RestController
public class CGPAController {
	
	@Autowired
	public CGPACalculatorService service;
	
	@Autowired
	public RegisterRepo registerRepo;
	
	@GetMapping("/dashboard")
	public register getActiveUserDashboard(Authentication authentication ){
		List<register> allUsers=registerRepo.findAll();
		for(register user:allUsers) {
			if(user.getEmail().equals(authentication.getName())) {
				return user;
			}
		}
		return new register();
	}
	
	@PostMapping("/register")
	public register saveRegisteredUser(@RequestBody register reg) {
		register savedreg=service.saveRegister(reg);
		return savedreg;
	}
	
	@PostMapping("/subject")
	public Subjects saveSubjects(@RequestBody Subjects subject) {
		Subjects savedSubject=service.addSubject(subject);
		return savedSubject;
	}
	
	@GetMapping("/deptsemester/{dept}/{sem}")
	public List<Subjects> getSubjectList(@PathVariable String dept,@PathVariable int sem){
		List<Subjects> getSubjectList=service.DeptandSemester(dept, sem);
		return getSubjectList;
	}
	
	@PostMapping("/deptsemester/gpa/{dept}/{sem}")
	public double getDepartmentSemester(@PathVariable String dept,@PathVariable int sem,@RequestBody List<String> grades) {
		
		double gpaValue=service.calculateGPA(dept, sem, grades);
		return gpaValue;
		
	}
	
	@PostMapping("/deptsemester/cgpa")
	public double getCGPA(@RequestBody List<Double> gpa) {
		double cgpaVal=service.calculateCGPA(gpa);
		return cgpaVal;
	}
	
	@PostMapping("/deptsemester/converter")
	public double percentageConverter(@RequestBody Converter converter) {
		return service.percentageCal(converter);
	}
	
	@PutMapping("/deptsemester/saveupdateGPA")
	public SemesterGPA saveUpdateGPA(@RequestBody SemesterGPA semesterGPA) {
		return service.saveandupdateSemesterGPA(semesterGPA);
	}
	
	@GetMapping("/deptsemester/semesterGPA/{email}")
	public List<SemesterGPA> getSemesterGPA(@PathVariable String email){
		List<SemesterGPA> getGPA=service.getSemesterByUser(email);
		return getGPA;
	}
	
}
