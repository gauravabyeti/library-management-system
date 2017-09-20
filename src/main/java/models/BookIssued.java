package models;

import javax.persistence.Column;

//import java.util.Calendar;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
@Entity
public class BookIssued {

	private int id;
	private String  issueddate;
	private String  returndate;

	private Student  student ;
	/*private int S_RollNo;
	
	
	public int getS_RollNo() {
		return S_RollNo;
	}
	public void setS_RollNo(int s_RollNo) {
		S_RollNo = s_RollNo;
	}*/
	@Id
	@Column(name="bookid")
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getIssueddate() {
		return issueddate;
	}
	public void setIssueddate(String issueddate) {
		this.issueddate = issueddate;
	}
	public String getReturndate() {
		return returndate;
	}
	public void setReturndate(String returndate) {
		this.returndate = returndate;
	}
	@ManyToOne
	public Student getStudent() {
		return student;
	}
	public void setStudent(Student student) {
		this.student = student;
	}
	

}

