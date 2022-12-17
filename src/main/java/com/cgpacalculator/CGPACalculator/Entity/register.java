package com.cgpacalculator.CGPACalculator.Entity;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="Register")
public class register {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	
	@Column(name="name")
	private String name;
	
	@Column(name="email")
	private String email;
	
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	@Column(name="password")
	private String password;
	
	@Column(name="registrationNumber")
	private String registernumber;
	
	@Column(name="contact")
	private String contact;
	
	@JsonIgnore
	@OneToMany(mappedBy="reg",cascade=CascadeType.ALL)
	private List<SemesterGPA> semesters=new ArrayList<>();
	
	
	public register() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public register(String name,String email,String password,String registernumber,String contact) {
		this.name=name;
		this.email=email;
		this.password=password;
		this.registernumber=registernumber;
		this.contact=contact;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRegisternumber() {
		return registernumber;
	}

	public void setRegisternumber(String registernumber) {
		this.registernumber = registernumber;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}
	

	public List<SemesterGPA> getSemesters() {
		return semesters;
	}

	public void setSemesters(List<SemesterGPA> semesters) {
		this.semesters = semesters;
	}

	@Override
	public String toString() {
		return "register [id=" + id + ", name=" + name + ", email=" + email + ", password=" + password
				+ ", registernumber=" + registernumber + ", contact=" + contact + "]";
	}
	
	
	
}
