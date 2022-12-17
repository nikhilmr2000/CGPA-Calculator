package com.cgpacalculator.CGPACalculator.Service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.cgpacalculator.CGPACalculator.Entity.Converter;
import com.cgpacalculator.CGPACalculator.Entity.SemesterGPA;
import com.cgpacalculator.CGPACalculator.Entity.Subjects;
import com.cgpacalculator.CGPACalculator.Entity.register;
import com.cgpacalculator.CGPACalculator.Repository.RegisterRepo;
import com.cgpacalculator.CGPACalculator.Repository.SemesterGPARepo;
import com.cgpacalculator.CGPACalculator.Repository.SubjectRepo;



@Service
public class CGPACalculatorService {
	
	@Autowired
	public RegisterRepo registerRepo;
	
	@Autowired
	public SubjectRepo subjectRepo;
	
	
	@Autowired
	public SemesterGPARepo semesterRepo;
	
	@Autowired
	public PasswordEncoder passwordEncoder;
	
	public register saveRegister(register reg) {
		if(reg.getPassword()!=null) {
			String pass=passwordEncoder.encode(reg.getPassword());
			reg.setPassword(pass);
			register savedRegister=registerRepo.save(reg);
			return savedRegister;
		}
		return new register();
		
	}
	
	public Subjects addSubject(Subjects subject) {
		if(subject!=null) {
			Subjects saveSubject=subjectRepo.save(subject);
			return saveSubject;
		}
		return new Subjects();
	}
	
	//Calculate GPA based on department and Semester
	//CSE is considered
	
	public List<Subjects> DeptandSemester(String dept,int sem) {
		List<Subjects> allSubject=subjectRepo.findAll();
		List<Subjects> newSubjectList=new ArrayList<Subjects>();
		for(Subjects subject : allSubject) {
			if(subject.getDepartment().equals(dept)) {
				if(subject.getSemester()==sem) {
					newSubjectList.add(subject);
				}
			}
		}
		return newSubjectList;
	}
	
	//Calculate GPA based on department and semester
	
	public double calculateGPA(String dept,int sem,List<String> grades) {
		
		List<Subjects> allSubject=subjectRepo.findAll();
		
		double gpaValue=0;
		
		List<Integer> values=new ArrayList<>();
		
		for(String grade:grades) {
			if(grade.equals("O")) {
				values.add(10);
			}
			else if(grade.equals("A+")) {
				values.add(9);
			}
			else if(grade.equals("A")) {
				values.add(8);
			}
			
			else if(grade.equals("B+")) {
				values.add(7);
			}
			
			else if(grade.equals("B")) {
				values.add(6);
			}
			
			else {
				values.add(0);
			}
		}
		
		List<Subjects> semSubjects=new ArrayList<>();
		
		for(Subjects subject : allSubject) {
			if(subject.getDepartment().equals(dept)) {
				if(subject.getSemester()==sem) {
					semSubjects.add(subject);
				}
			}
		}
		
		int totalCreditPoint=0;
		int sumOfCreditGrade=0;
		
		
		if(grades.size()==semSubjects.size()) {
			for(int i=0;i<grades.size();i++) {
				if(!grades.get(i).equals("U")) {
					sumOfCreditGrade+=(semSubjects.get(i).getCredit()*values.get(i));
					totalCreditPoint+=semSubjects.get(i).getCredit();
				}
			}
		}
		
		gpaValue=sumOfCreditGrade/(double)totalCreditPoint;
		
		return gpaValue;
		
	}
	
	//CGPA
	
	public double calculateCGPA(List<Double> gpa) {
		
		double totalgpa=0;
		
		for(Double gpas : gpa) {
			totalgpa+=gpas;
		}
		
		int totalSem=gpa.size();
		double cgpa=totalgpa/(double) totalSem;
		return cgpa;
		
	}
	
	//CGPA to Percentage Converter
	
	public double percentageCal(Converter converter) {
		double val=converter.getValue()*10;
		return val;
	}
	
	//Save Semester GPA Repo
	
	public SemesterGPA saveandupdateSemesterGPA(SemesterGPA sem) {
		
		SemesterGPA semVal=new SemesterGPA();
		int count=0;
		List<SemesterGPA> allSemGPA=semesterRepo.findAll();
		
		if(allSemGPA.size()==0) {
			count=0;
		}
		
		else {
			for(SemesterGPA semester: allSemGPA) {
				if(semester.getSemester()==sem.getSemester() && (semester.getRegister().getEmail().equals(sem.getRegister().getEmail()))) {
					semester.setGpa(sem.getGpa());
					count=1;
					semVal=semester;
				}
			}
		}
			
		if(count==1) {
			return semesterRepo.save(semVal);
		}
		else {
			return semesterRepo.save(sem);
		}
		
		
	}
	
	public List<SemesterGPA> getSemesterByUser(String email){
		
		List<SemesterGPA> allGPA=semesterRepo.findAll();
		
		List<register> allUsers=registerRepo.findAll();
		
		register reg=new register();
		
		for(register user : allUsers) {
			if(user.getEmail().equals(email)) {
				reg=user;
			}
		}
		
		List<SemesterGPA> registeredSemGPA=new ArrayList<>();
		
		for(SemesterGPA sem : allGPA) {
			if(sem.getRegister().getEmail().equals(reg.getEmail())) {
				registeredSemGPA.add(sem);
			}
		}
		
		return registeredSemGPA;
	}
	
	
}
