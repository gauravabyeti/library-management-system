package controllers;

public class SuggestionDTO {
    
	private 	String comment;
	private StudentDto  student;
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public StudentDto getStudent() {
		return student;
	}
	public void setStudent(StudentDto student) {
		this.student = student;
	}
	
	

}
