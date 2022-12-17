package com.cgpacalculator.CGPACalculator.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="subjects")
public class Subjects {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public long id;
	
	@Column(name="subjectName")
	public String name;
	
	@Column(name="coursecode")
	public String coursecode;
	
	@Column(name="semester")
	public int semester;
	
	@Column(name="credit")
	public int credit;
	
	@Column(name="department")
	public String department;
	
	public Subjects() {
		super();
	}
	
	public Subjects(String name,String coursecode,int semester,int credit,String department) {
		this.name=name;
		this.coursecode=coursecode;
		this.semester=semester;
		this.credit=credit;
		this.department=department;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCoursecode() {
		return coursecode;
	}

	public void setCoursecode(String coursecode) {
		this.coursecode = coursecode;
	}

	public int getSemester() {
		return semester;
	}

	public void setSemester(int semester) {
		this.semester = semester;
	}

	public int getCredit() {
		return credit;
	}

	public void setCredit(int credit) {
		this.credit = credit;
	}
	

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	@Override
	public String toString() {
		return "Subjects [id=" + id + ", name=" + name + ", coursecode=" + coursecode + ", semester=" + semester
				+ ", credit=" + credit + "]";
	}
	
	
	
}
