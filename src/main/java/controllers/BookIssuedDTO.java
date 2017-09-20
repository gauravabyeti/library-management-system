package controllers;


public class BookIssuedDTO {
	
	private int id;
	private String  issueddate;
	private String  returndate;
	private StudentDto student;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	
	
	
	public StudentDto getStudent() {
		return student;
	}
	public void setStudent(StudentDto student) {
		this.student = student;
	}
	public void setIssueddate(String issueddate) {
		this.issueddate = issueddate;
	}
	public String getIssueddate() {
		return issueddate;
	}
	public String getReturndate() {
		return returndate;
	}
	public void setReturndate(String returndate) {
		this.returndate = returndate;
	}
	
}
