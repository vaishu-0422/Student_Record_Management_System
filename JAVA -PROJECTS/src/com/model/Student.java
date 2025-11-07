package com.model;

import java.util.List;

public class Student { 
	private int studentId;
	private String name;
	private String rollNo;
	private String email;
	
	private Department dept;
	private List <Subject> sub;
	private List <Marks> mark;
	
	
	public Student() {
		super();
	}


	public Student(int studentId, String name, String rollNo, String email, Department dept, List<Subject> sub,
			List<Marks> mark) {
		super();
		this.studentId = studentId;
		this.name = name;
		this.rollNo = rollNo;
		this.email = email;
		this.dept = dept;
		this.sub = sub;
		this.mark = mark;
	}


	public int getStudentId() {
		return studentId;
	}


	public void setStudentId(int string) {
		this.studentId = string;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getRollNo() {
		return rollNo;
	}


	public void setRollNo(String rollNo) {
		this.rollNo = rollNo;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public Department getDept() {
		return dept;
	}


	public void setDept(Department dept) {
		this.dept = dept;
	}


	public List<Subject> getSub() {
		return sub;
	}


	public void setSub(List<Subject> sub) {
		this.sub = sub;
	}


	public List<Marks> getMark() {
		return mark;
	}


	public void setMark(List<Marks> mark) {
		this.mark = mark;
	}


	@Override
	public String toString() {
		return "Student [studentId=" + studentId + ", name=" + name + ", rollNo=" + rollNo + ", email=" + email
				+ ", dept=" + dept + ", sub=" + sub + ", mark=" + mark + ", getStudentId()=" + getStudentId()
				+ ", getName()=" + getName() + ", getRollNo()=" + getRollNo() + ", getEmail()=" + getEmail()
				+ ", getDept()=" + getDept() + ", getSub()=" + getSub() + ", getMark()=" + getMark() + ", getClass()="
				+ getClass() + ", hashCode()=" + hashCode() + ", toString()=" + super.toString() + "]";
	}
	
	
	
}
	