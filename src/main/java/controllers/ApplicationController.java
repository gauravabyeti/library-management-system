/**
 * Copyright (C) 2013 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package controllers;


import javax.persistence.EntityManager;

import org.hibernate.Query;

import org.hibernate.metamodel.relational.Loggable;


import models.AdminDetail;
import models.BookDetails;
import models.BooksAvailable;
import models.BookIssued;
import models.Student;
import models.Suggestion;

import java.util.ArrayList;
import java.util.List;
import com.google.inject.Provider;
import java.util.Map;
import java.util.Random;

import com.google.inject.persist.Transactional;

import controllers.ApplicationController.SimplePojo;
import filters.LoggerFilter;
import ninja.ReverseRouter;
import ninja.FilterWith;
import ninja.Result;
import ninja.Results;
import ninja.Router;
import ninja.params.PathParam;
import ninja.session.Session;

import com.google.common.collect.Maps;
import com.google.inject.Inject;
import com.google.inject.Singleton;




public class ApplicationController {
       
    @Inject
    Provider<EntityManager> entitiyManagerProvider;
   
    @Inject
    Router router;
 
    public ApplicationController() {

    }

    /**
     * Method to put initial data in the db...
     * 
     * @return
     */
    public Result index() {

        return Results.html();

    }
    
    public Result studentPanel() {
    	
    	return Results.html();
    }
    
    public Result adminPanel() {
    	
       return  Results.html();	
    	
    }
    
    public Result adminLogin() {
    	
    	
    	return Results.html();
    	
    }
    @FilterWith(LoggerFilter.class)
    public Result studentLogin() {
    	
    	
    	return Results.html();
    	
    }
    
    public Result adminRegistration() {
    	
    	return Results.html();
    	
    }
     
   public Result studentRegistration() {
    	
    	return Results.html();
    	
    }
    
    
    public Result helloWorldJson() {
        
        SimplePojo simplePojo = new SimplePojo();
        simplePojo.content = "Hello i am coming from ajax!";

        return Results.json().render(simplePojo);

    }

    public static class SimplePojo {

        public String content;
        
    }
    
    
    
    
    

     @Transactional 
     public Result postBookDetails(@PathParam("id") int id,@PathParam("title") String title,@PathParam("category") String category,@PathParam("author") String author) {

        
    	 BookDetails bookdetail=new BookDetails();
        System.out.println(title);
        System.out.println(category);
        System.out.println(author);
		
        EntityManager entityManager = entitiyManagerProvider.get();
        bookdetail.setId(id);
        bookdetail.setTitle(title);
        bookdetail.setCategory(category);
        bookdetail.setAuthor(author);
		entityManager.persist(bookdetail);
		return Results.json().render("successfully saved");

	}
    
     @Transactional 
     public Result getBookDetails() {

		
    	 EntityManager entityManager = entitiyManagerProvider.get();

    	    javax.persistence.Query q = entityManager.createQuery("FROM  BookDetails");
    	    List<BookDetails> bookdetails = (List<BookDetails>) q.getResultList();
       	    return Results.json().render(bookdetails);

		}
     
     @Transactional 
     public Result postAdminDetail(AdminDetail admindetail1) {

        boolean flag=false;
		EntityManager entityManager = entitiyManagerProvider.get();
		javax.persistence.Query q = entityManager.createQuery("FROM  AdminDetail");
	    List<AdminDetail> admindetails = (List<AdminDetail>) q.getResultList();
        for(AdminDetail admindetail:admindetails )
        {
        	if(admindetail1.getEmailid().equals(admindetail.getEmailid())&&admindetail.getPassword()==admindetail1.getPassword())
        	{
        		flag=true;
        		break;
        		
        	}
        	
        }
        if(flag)
         return Results.html().template("/views/ApplicationController/adminLogin.ftl.html");
        else
          return Results.json().render("wrong entry");
	}
     
     @Transactional 
     public Result postAdmin(AdminDetail admindetails) {

       
		EntityManager entityManager = entitiyManagerProvider.get();
		Random ran=new Random(); 
	    admindetails.setOtp(ran.nextInt(10000)); 
	    entityManager.persist(admindetails);
		return Results.json().render("successfully registerd");

	}
     
     
     
     
     @Transactional 
     public Result getAdminDetail(@PathParam("email") String email,@PathParam("password") int password  ) {

		    boolean flag=false;
    	    EntityManager entityManager = entitiyManagerProvider.get();
    	    javax.persistence.Query q = entityManager.createQuery("FROM  AdminDetail");
    	    List<AdminDetail> admindetails = (List<AdminDetail>) q.getResultList();
            for(AdminDetail admindetail:admindetails )
            {
            	if(email.equals(admindetail.getEmailid())&&admindetail.getPassword()==password)
            	{
            		flag=true;
            		break;
            		
            	}
            	
            }
            if(!flag)
             return Results.html();
            else
              return Results.json().render("wrong entry");	        
    	       	 
     }
     
     @Transactional 
     public Result postBookIssued(@PathParam("bookid") int bookid,@PathParam("issuedate") String issuedate,@PathParam("returndate") String returndate,@PathParam("studentid") int studentid) {

       
    	 
    	 Student student=new Student();
    	 student.setId(studentid);
    	 BookIssued bookissued =new BookIssued();
    	 bookissued.setId(bookid);
    	 bookissued.setIssueddate(issuedate);
    	 bookissued.setReturndate(returndate);
    	 bookissued.setStudent(student);
		EntityManager entityManager = entitiyManagerProvider.get();
		entityManager.persist(bookissued);
		return Results.json().render("succesfully issued");

	}
     
     @Transactional 
     public Result getBookIssuedByStudent(@PathParam("id") int studentId) {

		 
    	    EntityManager entityManager = entitiyManagerProvider.get();
    	    List<BookIssued> bookissued = new ArrayList<BookIssued>();
    	    bookissued = entityManager.createQuery("SELECT Bi  FROM  BookIssued Bi WHERE Bi.student.id=?1",BookIssued.class)
    	    		.setParameter(1, studentId)
    	    		.getResultList();
    	    List<BookIssuedDTO> biDto =  converttoDTO(bookissued);
    	    return Results.json().render(biDto);     	 
     }

     
     
     
     @Transactional 
     public Result getBookIssued(Session session) {

		     int studentid=Integer.parseInt(session.get("userId"));
    	    EntityManager entityManager = entitiyManagerProvider.get();
    	    List<BookIssued> bookissued = new ArrayList<BookIssued>();
    	    bookissued = entityManager.createQuery("SELECT Bi  FROM  BookIssued Bi WHERE Bi.student.id=?1",BookIssued.class)
    	    		.setParameter(1, studentid)
    	    		.getResultList();
    	    List<BookIssuedDTO> biDto =  converttoDTO(bookissued);
    	    return Results.json().render(biDto);     	 
     }
  
     
     
     private List<BookIssuedDTO> converttoDTO(List<BookIssued> bookissueds) {
    	  List<BookIssuedDTO> bookissueddto=new ArrayList<BookIssuedDTO>();
    	  StudentDto student =new StudentDto();
    	 for(BookIssued bookissued : bookissueds ) {
    	      student.setId(bookissued.getStudent().getId());  
    	      student.setEmailid(bookissued.getStudent().getEmail());
    	      student.setName(bookissued.getStudent().getName());
    		  BookIssuedDTO dto = new BookIssuedDTO();
    		  dto.setId(bookissued.getId());
    		  dto.setIssueddate(bookissued.getIssueddate());
    		  dto.setReturndate(bookissued.getReturndate());      		 
    		  dto.setStudent(student);
    		  bookissueddto.add(dto);
              		 
    	 }	 
		 
	   return bookissueddto;
	}

	@Transactional 
     public Result postStudent(Student student) {

       
		EntityManager entityManager = entitiyManagerProvider.get();
		Random ran=new Random(); 
	    student.setOTP(ran.nextInt(10000)); 
	    student.setFine(0);
		entityManager.persist(student);
		return Results.json().render("successfully registerd");

	}
     
     @Transactional 
     public Result postStudents(Student student1,Session session)  {

		    boolean flag=false;
		   String studentId=null;
    	    EntityManager entityManager = entitiyManagerProvider.get();
    	    javax.persistence.Query q = entityManager.createQuery("FROM  Student" );
    	
    	    
    	   List<Student> students = (List<Student>) q.getResultList();
    	   for(Student student:students ) {
           
           	if(student1.getEmail().equals(student.getEmail())&&student.getPassword()==student1.getPassword()) {
           	
           		studentId=Integer.toString(student.getId());
           		session.put("userId", studentId);
           		flag=true;
           		break; 		
              }
           	
           }
    	  
           if(flag)
            return Results.html().template("/views/ApplicationController/studentLogin.ftl.html").render("id",studentId);
           else {
        	  
        	   session.clear();
             return Results.json().render("wrong entry");
           }
            
     }

     @Transactional 
     public Result postSuggestion(@PathParam("comment") String comment,Session session) {
    	 
        Suggestion suggestion=new Suggestion();
        Student student=new Student();
        int id=Integer.parseInt(session.get("userId"));
        student.setId(id);
        suggestion.setComment(comment);
        suggestion.setStudent(student);
		EntityManager entityManager = entitiyManagerProvider.get();
		entityManager.persist(suggestion);
	    return Results.json().render("comment saved");

	}
     
     @Transactional 
     public Result getSuggestion() {

		
    	    EntityManager entityManager = entitiyManagerProvider.get();
    	    javax.persistence.Query q = entityManager.createQuery("FROM  Suggestion",Suggestion.class);
    	    List<Suggestion> suggestion =  new ArrayList<Suggestion>();
    	    suggestion =q.getResultList();
    	    List<SuggestionDTO> suggestionDto =  converttooDTO(suggestion);
    	    return Results.json().render(suggestionDto);
    	  
     }
     
     
    private List<SuggestionDTO> converttooDTO(List<Suggestion> suggestions) {
   	    List<SuggestionDTO> suggestionDto=new ArrayList<SuggestionDTO>();
   	    
   	  for(Suggestion suggestion : suggestions ) {
   		  StudentDto student =new StudentDto();
   		  student.setId(suggestion.getStudent().getId());  
   	      student.setEmailid(suggestion.getStudent().getEmail());
   	      student.setName(suggestion.getStudent().getName());
   		  SuggestionDTO dto = new SuggestionDTO();
   		  dto.setComment(suggestion.getComment()); 		 
   		  dto.setStudent(student);
   		  suggestionDto.add(dto);
             		 
   	 }	 
		 
	   return suggestionDto;
	}

     
     
     
     
     
     @Transactional 
     public Result postBookAvailable(@PathParam("id") int id,@PathParam("number") int number) {
       BookDetails bookdetails=new BookDetails();
       bookdetails.setId(id);
       BooksAvailable  bookavailable=new BooksAvailable();
		EntityManager entityManager = entitiyManagerProvider.get();
		bookavailable.setNumberofbook(number);
		bookavailable.setBookdetails(bookdetails);
		entityManager.persist(bookavailable);
		return Results.json().render("{}");

	}
     
     
     
     @Transactional 
     public Result getBookAvailable() {

		
    	    EntityManager entityManager = entitiyManagerProvider.get();
    	    javax.persistence.Query q = entityManager.createQuery("FROM BooksAvailable ");
    	    List<BooksAvailable> booksavailable = new ArrayList<BooksAvailable>();
    	    booksavailable= q.getResultList();
            List<AvailableBooksDTO> availableBooksDto =convertToDTO(booksavailable);            
    	    return Results.json().render(availableBooksDto);
    	 
     }
  
     private List<AvailableBooksDTO> convertToDTO(List<BooksAvailable> booksavailables) {
      	    List<AvailableBooksDTO> availablebooksDto=new ArrayList<AvailableBooksDTO>();
      	 for(BooksAvailable booksavailable : booksavailables  ) {
      		  BookDetails bookdetails=new BookDetails();
      	      bookdetails.setId(booksavailable.getBookdetails().getId());
      	      bookdetails.setTitle(booksavailable.getBookdetails().getTitle());
      	      bookdetails.setCategory(booksavailable.getBookdetails().getCategory());
      	      bookdetails.setAuthor(booksavailable.getBookdetails().getAuthor());
      		  AvailableBooksDTO dto = new AvailableBooksDTO();
      		  dto.setNumberofbook(booksavailable.getNumberofbook()); 		 
      		  dto.setBookdetails(bookdetails);
      		  availablebooksDto.add(dto);
                		 
      	 }	 
   		 
   	   return availablebooksDto;
   	}
  
     
     
     

     
     
}
