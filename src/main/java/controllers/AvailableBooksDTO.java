package controllers;

import models.BookDetails;

public class AvailableBooksDTO {
	
	private int numberofbook;
	private BookDetails bookdetails;
	public int getNumberofbook() {
		return numberofbook;
	}
	public void setNumberofbook(int numberofbook) {
		this.numberofbook = numberofbook;
	}
	public BookDetails getBookdetails() {
		return bookdetails;
	}
	public void setBookdetails(BookDetails bookdetails) {
		this.bookdetails = bookdetails;
	}
	

}
