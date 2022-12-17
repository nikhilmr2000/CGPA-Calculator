package com.cgpacalculator.CGPACalculator.Entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="GPA")
public class SemesterGPA {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public long id;
	
	@Column(name="semester")
	public int semester;
	
	@Column(name="gpa_value")
	public double gpa;
	
	@ManyToOne(cascade=CascadeType.MERGE)
	@JoinColumn(name="register_id")
	public register reg;


	public SemesterGPA(long id, int semester, double gpa, register reg) {
		super();
		this.id = id;
		this.semester = semester;
		this.gpa = gpa;
		this.reg = reg;
	}


	public SemesterGPA() {
		super();
		// TODO Auto-generated constructor stub
	}


	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}


	public int getSemester() {
		return semester;
	}


	public void setSemester(int semester) {
		this.semester = semester;
	}


	public double getGpa() {
		return gpa;
	}


	public void setGpa(double gpa) {
		this.gpa = gpa;
	}


	public register getRegister() {
		return reg;
	}


	public void setRegister(register register) {
		this.reg = register;
	}


	@Override
	public String toString() {
		return "SemesterGPA [id=" + id + ", semester=" + semester + ", gpa=" + gpa + ", register=" + reg + "]";
	}
	
	
	
}
