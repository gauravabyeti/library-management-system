package models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="StudentDetail")
//@JsonIgnoreProperties(value= {"password"})
public class Student {  
private int id;  
private String name;
private String email;
private int fine;
private int  password;
private int otp;


//private List<BookIssued> bookissued=new ArrayList<BookIssued>();
//@OneToMany(mappedBy="S_RollNo")
//public List<BookIssued> getBookissued() {
//	return bookissued;
//}
//public void setBookissued(List<BookIssued> bookissued) {
//	this.bookissued = bookissued;
//}

private List<Suggestion> suggestion=new ArrayList<Suggestion>();

@OneToMany(mappedBy="student")
public List<Suggestion> getSuggestion() {
	return suggestion;
}
public void setSuggestion(List<Suggestion> suggestion) {
	this.suggestion = suggestion;
}
@Column(name="studentid")
@Id
@GeneratedValue(strategy=GenerationType.AUTO)
public int getId() {
	return id;
}
public void setId(int id) {
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
public int getFine() {
	return fine;
}
public void setFine(int fine) {
	this.fine = fine;
}

public int getPassword() {
	return password;
}
public void setPassword(int password) {
	this.password = password;
}
public int getOTP() {
	return otp;
}
public void setOTP(int otp) {
	this.otp = otp;
}
  
}  


