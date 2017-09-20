package models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class BooksAvailable {	
private BookDetails bookdetails;
private int numberofbook;
private int id;
@Id
@GeneratedValue(strategy=GenerationType.AUTO)
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
@OneToOne
public BookDetails getBookdetails() {
	return bookdetails;
}
public void setBookdetails(BookDetails bookdetails) {
	this.bookdetails = bookdetails;
}
public int getNumberofbook() {
	return numberofbook;
}
public void setNumberofbook(int numberofbook) {
	this.numberofbook = numberofbook;
}


}

