	package models;

	import javax.persistence.Query;

	import org.hibernate.Session;
	import org.hibernate.SessionFactory;
	import org.hibernate.cfg.AnnotationConfiguration;
	import org.hibernate.tool.hbm2ddl.SchemaExport;
	import java.util.*;
	enum Choices{ CREATEADMINACCOUNT,ADMINLOGIN,CREATESTUDNETACCOUNT,STUDENTLOGIN,ADDBOOKS,AVIALABLEBOOK,ISSUEBOOK,SUGGESTION,ADDNUMBEROFBOOKS,EXIT,STUDENTACCOUNT }

	public class StoreData{
		public static Choices getvalue(int n)
		{
			
			  if(n==1)
				return Choices.CREATEADMINACCOUNT;
			  else if(n==2)
			      return Choices.ADMINLOGIN;
			  else if(n==3)
				  return Choices.CREATESTUDNETACCOUNT;
			  else if(n==4)
				  return Choices.STUDENTLOGIN;
			  else if(n==5)
				   return Choices.ADDBOOKS;
			  else if(n==6)
				   return Choices.AVIALABLEBOOK;
			  else if(n==7)
				   return Choices.ISSUEBOOK;
			  else if(n==8)
				    return Choices.SUGGESTION;
			  else if(n==9)
				    return Choices.ADDNUMBEROFBOOKS;
			  else if(n==10)
				    return Choices.EXIT;
			  else if(n==11)
				    return Choices.STUDENTACCOUNT;
			return null;

			
		}
		  
		
		
		@SuppressWarnings("deprecation")
		public static void main(String[] args) {
			AnnotationConfiguration config=new AnnotationConfiguration();
	     	config.addAnnotatedClass(Student.class)
			.addAnnotatedClass(AdminDetail.class)
			.addAnnotatedClass(Suggestion.class)
			.addAnnotatedClass(BookDetails.class)
			.addAnnotatedClass(BookIssued.class)
			.addAnnotatedClass(BooksAvailable.class);
			 config.configure("hibernate.cfg.xml");
		     // new SchemaExport(config).create(true, true);
			 SessionFactory  factory=config.buildSessionFactory();
			 Session session=factory.getCurrentSession();
			session .beginTransaction();
		     
			Scanner sc=new Scanner(System.in);
			
		  while(true)
		  {
			  
			  
			  System.out.println("press 1 to  create the admin account");
			  System.out.println("press 2 to  login as admin");
			  System.out.println("press 3 to  create student account ");
			  System.out.println("press 4 to login as a student ");
			  System.out.println("press 10 to exit");
			  
			  BooksAvailable booksavailable=new BooksAvailable();
			  Student student=new Student();
			  AdminDetail admindetail=new AdminDetail();
			  Suggestion suggestions =new Suggestion();
			  BookDetails bookdetail=new BookDetails();
			  BookIssued bookissued=new BookIssued();
			  int  n=sc.nextInt();
			  Choices choice=getvalue(n);
			  
			  		  
			  switch(choice)
			  {
			     //1
			      case CREATEADMINACCOUNT: System.out.println("ENTER YOUR NAME");
				                           String adminname=sc.next();
				                           System.out.println(" ENTER YOUR EMAIL ID ");
				                           String adminemailid=sc.next();
				                           System.out.println("ENTER YOUR PASSWORD");
				                           int  adminpassword=sc.nextInt();
				      	                   //AdminDetail admindetail=new AdminDetail();
	  	                                   admindetail.setName(adminname);
	  		                               admindetail.setEmailid(adminemailid);
	  		                               admindetail.setPassword(adminpassword);
						  		           Random ran=new Random(); 
						  				   admindetail.setOtp(ran.nextInt(10000));
						  	               session.save(admindetail);    		
						  		            //session.getTransaction().commit();
							               break;
			  
		           //2	  
			       case ADMINLOGIN:        System.out.println("ENTER YOUR REGISTERED EMAIL ID");
							               String emailid=sc.next();
							               System.out.println("ENTER YOUR VALID PASSWORD");
							               int password =sc.nextInt();
							               boolean flag=false;
							               String hql1="SELECT Ad FROM AdminDetail Ad  ";
							               org.hibernate.Query query = session.createQuery(hql1); 
							    	       List<AdminDetail> admindetails =query.list();
							    	       for (AdminDetail admindetail1 :admindetails) {
							    	    	   if(emailid.equals(admindetail1.getEmailid())&&admindetail1.getPassword()==password)
							    	    	    {
							    	    		   flag=true;
							    	    	     }
							    	           }
							    	      if(flag==true)
							    	    	{
							    	    	    while(true) {
	                                            						    	    	  
	 						    	    	      System.out.println("press 5 to  add new books  to library");
			    			                      System.out.println("press 6 to  check available books");
			    			                      System.out.println("press 7 to issue the  book");
			    			                      System.out.println("press 9 to add the no of books");
			    			                      System.out.println("press 11 to check the book issued by students");
			    			                      System.out.println("press 10 to exit");
			    			                      int m=sc.nextInt();
			    			                      Choices c=getvalue(m);
			    			                      switch(c){
			    			                      
			    			                          //5
				    	    	                      case ADDBOOKS: System.out.println("ENTER THE BOOK TITLE");
							                                         String title=sc.next();
							                                         System.out.println("ENTER THE CATEGORY OF BOOK");
							                                         String category=sc.next();
							                                         System.out.println("ENTER THE AUTHOR NAME ");
							                                         String author=sc.next();
							        	                             //BookDetails bookdetail=new BookDetails();
							    	                                 bookdetail.setTitle(title);
							    	                              	 bookdetail.setAuthor(author);
							    		                             bookdetail.setCategory(category);
							    		                             session.save(bookdetail);    		
					    		                                     //session.getTransaction().commit();
						                                             break;
						                
						                              //6
				    	    	                      case AVIALABLEBOOK: String hql="SELECT Ba FROM BooksAvailable Ba ";
					    	                                              org.hibernate.Query query2 = session.createQuery(hql); 
					    	                                              List<BooksAvailable> bookAvailables =query2.list();
					    	                                              for (BooksAvailable bookAvailable : bookAvailables) {
									                                          System.out.println("Book Name: "+ bookAvailable.getBookdetails().getTitle()+  "  Number Of Books:"+bookAvailable.getNumberofbook());
								                                              }
					                                                       break;
					                                                       
			    	                                  //7
				    	    	                      case ISSUEBOOK: System.out.println("ENTER THE BOOK ID");
						                                              int id=sc.nextInt();
						                                              System.out.println("ENTER THE STUDENT ID");
						                                              int studentid=sc.nextInt();
						                                              System.out.println(" ENTER THE ISSUING DATE");
						                                              String issueddate=sc.next();
						                                              System.out.println("ENTER THE RETURNING DATE");
						           	                                  String returndate=sc.next();
						                                              //BookIssued bookissued=new BookIssued();
													    	          bookissued.setId(id);
													    	          bookissued.setIssueddate(issueddate);
													    	          bookissued.setReturndate(returndate);
													    	          student.setId(studentid);
													    	          bookissued.setStudent(student);
													    	          session.save(bookissued);    		
													    		      //session.getTransaction().commit();
														              break;
													          
					                                 //9
				    	                             case ADDNUMBEROFBOOKS : System.out.println("ENTER THE BOOK ID");
						                                                     int bookid=sc.nextInt();
															                 System.out.println(" NIMBER OF BOOKAVIALABLE");
															                 int number=sc.nextInt();
															                 bookdetail.setId(bookid);
															                 booksavailable.setNumberofbook(number);
															                 booksavailable.setBookdetails(bookdetail);
															                 session.save(booksavailable);    
															    	         //session.getTransaction().commit();
																             break;
							                         //11 
							    	    			 case  STUDENTACCOUNT: System.out.println("ENTER THE ROLLNO OF STUDENT");
							    	    			                       int rollno=sc.nextInt();
											    	    				    String hql5="SELECT Bi FROM BookIssued Bi ";
											                                org.hibernate.Query query4 = session.createQuery(hql5); 
											                                List<BookIssued> bookissueds1 =query4.list();
											                                for (BookIssued bookissued2 : bookissueds1) {
											                                if(rollno==bookissued2.getStudent().getId()) {
											                        	      System.out.println("BookId: "+ bookissued2.getId()+ "  Student Name:"+bookissued2.getStudent().getName() +  "  Student EmailId: "+bookissued2.getStudent().getEmail() +" bookissued date : "+bookissued2.getIssueddate()+"  bookreturn date:"+bookissued2.getReturndate());
												                             } 
							                           
		                                                                  }
											                                
											          //10                      
							    	    			case EXIT: session.getTransaction().commit();	
							    	  	    	              java.lang.System.exit(1);                  
				                	                    
							    	  	    	                
							    	    			 default :System.out.println("WRONG CHOICES SELECTION");
							    	    			           break;
			    			                              }   
							    	    	         }
			    	    	                  }
			    	                
							    	  else
			    	                 	 System.out.println("PASSWORD OR EMAILID IS WRONG");
			                            break;
				        
				//3        
			    case CREATESTUDNETACCOUNT: System.out.println("ENTER YOUR NAME");
								           String studentname=sc.next();
								           System.out.println("ENTER YOUR EMAIL ID");
								           String studentemailid=sc.next();
								           System.out.println("ENTER YOUR PASSWORD");
								           int  studentpassword=sc.nextInt();
								           //Student student=new Student();
								    	   student.setName(studentname);
								    	   student.setPassword(studentpassword);
								    	   student.setEmail(studentemailid);
								    	   Random random=new Random(); 
								    	   student.setOTP(random.nextInt(10000));
								    	   //Suggestion suggestion=new Suggestion();
								    	   student.getSuggestion().add(suggestions);
								     	   session.save(student);    		
								    	   //session.getTransaction().commit();
								    	   break;
			    		
			    	
	           //4
			    case STUDENTLOGIN:  System.out.println("ENTER YOUR REGISERED EMAILID");
			                        String registeredemailid=sc.next();
			                        System.out.println("ENTER YOUR VALID PASSWORD");
			                        int validpassword =sc.nextInt();
			                        boolean flag2=false;
			                        String hql2="SELECT Sd FROM Student Sd ";
			                        org.hibernate.Query query1 = session.createQuery(hql2); 
			    	                List<Student> students =query1.list();
			    	                for (Student student1 :students) {
			    	    	        if(registeredemailid.equals(student1.getEmail())&&student1.getPassword()==validpassword)
			    	    	        {
			    	    		      flag2=true;
			    	    	         }
			    	               }
			    	               if(flag2==true)
			    	    	        {
			    	            	while(true) {   
			    	  	            System.out.println("press 6 to  check available books");
			    			        System.out.println("press 11 to check your account");
			    			        System.out.println("press 8 to give suggestion");
			    			        System.out.println("press 10 to exit");
			    	    	        int p=sc.nextInt();
			    	    	        Choices ch=getvalue(p);
			    	    	        switch(ch)
			    	    	          {
			    	    	          //6
			    	    	          case AVIALABLEBOOK: String hql="SELECT Ba FROM BooksAvailable Ba ";
					    	                              org.hibernate.Query query2 = session.createQuery(hql); 
								    	                  List<BooksAvailable> bookAvailables =query2.list();
								    	                  for (BooksAvailable bookAvailable : bookAvailables) {
												          System.out.println("Name: "+ bookAvailable.getBookdetails().getTitle()+ " BookId: "+bookAvailable.getBookdetails().getId()+ "  Number Of Books:"+bookAvailable.getNumberofbook());
											              }
								                          break;
								      //8                    
						    	     case SUGGESTION: System.out.println(" WRITE THE SUGGESTION");
			    	 		                          String comment=sc.nextLine();
			    	 		                          suggestions.setComment(comment);
			    	 		                          session.save(suggestions);    
			    	 		    	                  //session.getTransaction().commit();
			    	 			                      break;
			    	 			       //11               
			    	    			  case STUDENTACCOUNT: String hql4="SELECT Bi FROM BookIssued Bi ";
			    	                                       org.hibernate.Query query3 = session.createQuery(hql4); 
			    	                                       List<BookIssued> bookissueds =query3.list();
			    	                                       for (BookIssued bookissued1 : bookissueds) {
			    	                                       if(registeredemailid.equals(bookissued1.getStudent().getEmail())) {
			    	                        	                System.out.println("BookId: "+ bookissued1.getId()+  "BookIssued Date :"+bookissued1.getIssueddate()+" BookReturn Date:"+bookissued1.getReturndate());
						                                  } 
			    	                           
			    	                                   }
			    	                                       
			    	                  //10                     
			    	    			  case EXIT: session.getTransaction().commit();	
			    	   	    	                 java.lang.System.exit(1);
			    	   	    	           
			    	    			  default :System.out.println("WRONG CHOICES SELECTION");          
			    	    		          
			    	    	          }
			    	            	 
			    	            	}
			        	    	}
			    	    	
			    	    else
			    	    	System.out.println(" WRONG PASSWORD OR EMAILID");
			            break;
			            
			            
			   //11 
			    case EXIT:session.getTransaction().commit();	
		    	   java.lang.System.exit(1);
		    	  
			    default :System.out.println("WRONG CHOICES SELECTION");		  
				  
			   }
			  
		     }
			
			

		}

	}

	
	
	
	

