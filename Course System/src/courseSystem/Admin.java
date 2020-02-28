package courseSystem;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.BufferedWriter;
import java.util.Collections;
import java.io.FileWriter;
import java.io.IOException;

public class Admin extends User implements A, Serializable{
	public String username = "Admin";
	public String password = "Admin001";
	
	//Constructor
	Admin(){}
		
	
	public void CreateACourse() {
		//Create new course object
		Course course = new Course();
		Scanner input = new Scanner(System.in);
		//Get course name
		System.out.println("Enter the course name you would like to create: ");
		String NewCourseName = input.nextLine();
		course.setCourseName(NewCourseName);
		//Get course ID
		System.out.println("Enter the course ID: ");
		String newCourseID = input.nextLine();
		course.setCourseID(newCourseID);
		
		//Get Instructor
		System.out.println("Enter the instructor for this course: ");
		String newInstructor = input.nextLine();
		course.setInstructor(newInstructor);
		//Get Location
		System.out.println("Enter the course location: ");
		String newLocation = input.nextLine();
		course.setLocation(newLocation);
		//Get Section Number
		System.out.println("Enter the course Section Number: ");
		String newSecNum = input.next();
		course.setSectionNumber(newSecNum);
		//get max students
		System.out.println("Enter the max number of students that can be in the course: ");
		int newMaxStudents = input.nextInt();
		course.setMaxStudents(newMaxStudents);
		//Get Current Students
		System.out.println("Enter the current number of students in the course: ");
		int newCurrStudents = input.nextInt();
		course.setCurrStudents(newCurrStudents);
		//Have user add students names based on the amount of students they say are enrolled in the course
		String firstname = "";
		String lastname = "";
		for(int i = 0; i< newCurrStudents; i++) {
			int temp = 1;
			//Look to see if this is a new student or an existing one
			System.out.println("Is this a new or existing student? (new/exist) ");
			String studentExist = input.next();
			if (studentExist.equals("new")) {
				System.out.print("Please enter the students first name, last name, username, and password (seperate by space)");
				firstname = input.next();
				lastname = input.next();
				String username = input.next();
				String password = input.next();
				//Take the newly assigned variables and pass them to student constructor to create new object
				Student student = new Student(firstname, lastname, username, password);
				courseRegistration.studentList.add(student);
				//Now that this student is created, add them to the course list of names
				course.addStudent(student.getFirstName(),student.getLastName());
				//Add this course to the students array list of courses
				student.setStudentCourses(course.getCourseID());
				temp++;
					}
			else if(studentExist.equals("exist")) {
					System.out.println("Enter the the first name and last name for student" + temp + " enrolled in the course: ");
					firstname = input.next();
					lastname = input.next();
					course.addStudent(firstname, lastname);	
					temp++;
			}
				}
		courseRegistration.courseList.add(course);
		System.out.println("The new course has now been created.");
	}
	
	
	public void RemoveCourse() {
		//Prompt user for the course they would like to remove
		Scanner input = new Scanner(System.in);
		System.out.println("Enter the ID of the course you would like to remove: ");
		String courseRemove = input.nextLine();
		System.out.println("Enter the section number of the course you would like to remove: ");
		String courseRemoveSecNum = input.nextLine();
		//Iterate through course list to make sure that it exist
		boolean courseFound=false;
		for (int j = 0 ; j < courseRegistration.courseList.size(); j++) {
			if (courseRegistration.courseList.get(j).getCourseID().equals(courseRemove)) {
				if (courseRegistration.courseList.get(j).getSectionNumber().equals(courseRemoveSecNum)) {
				courseFound = true;
				//remove course object at that index
				courseRegistration.courseList.remove(j);
				break;
				}
	}			
 }
		if(courseFound == false) {
			System.out.println("Sorry, this course does not exist.");
		}
		
		//Now check each student's array list of courses to see if they have that course so that it can be removed
		for (int i = 0; i<courseRegistration.studentList.size(); i++) {
			if (courseRegistration.studentList.get(i).getStudentCourses().contains(courseRemove)){
				courseRegistration.studentList.get(i).getStudentCourses().remove(courseRemove);
			}
		}
		
		
 }
	
	public void ViewCourseInfo() {
		//Prompt the user for the course they would like to view
		Scanner input = new Scanner(System.in);
		System.out.println("Enter the ID of the course you would like to view: ");
		String courseID = input.nextLine();
		//Iterate through course list to make sure that it exist
		boolean courseFound=false;
		for (int j = 0 ; j < courseRegistration.courseList.size(); j++) {
			if (courseRegistration.courseList.get(j).getCourseID().equals(courseID)) {
				//print course info
				courseFound=true;
				System.out.println("Course Name: " + courseRegistration.courseList.get(j).getCourseName());
				System.out.println("Course ID: " + courseRegistration.courseList.get(j).getCourseID());
				System.out.println("Max # of Students: " + courseRegistration.courseList.get(j).getMaxStudents());
				System.out.println("Current # of Students: " + courseRegistration.courseList.get(j).getCurrStudents());
				System.out.println("Current List of Names: " + courseRegistration.courseList.get(j).getListOfNames());
				System.out.println("Instructor: " + courseRegistration.courseList.get(j).getInstructor());
				System.out.println("Section Number: " + courseRegistration.courseList.get(j).getSectionNumber());
				System.out.println("Location: " + courseRegistration.courseList.get(j).getLocation());
				break;
	}
				
		
	}
		if(!courseFound) {
			System.out.println("Sorry, this course does not exist.");
		}
}
	
	
	public void EditCourse() {
		//Prompt the user for the course they would like to edit
				Scanner input = new Scanner(System.in);
				System.out.println("Enter the ID of the course you would like to edit ");
				String courseID = input.nextLine();
				System.out.println("Enter the section number of the course you would like to edit: ");
				String courseSecNum = input.nextLine();
				//Iterate through course list to make sure that it exist
				boolean courseFound=false;
				for (int j = 0 ; j < courseRegistration.courseList.size(); j++) {
					if (courseRegistration.courseList.get(j).getCourseID().equals(courseID)) {
						if(courseRegistration.courseList.get(j).getSectionNumber().equals(courseSecNum)){
						//prompt them to see what aspect of the course they would like to edit
						courseFound = true;
						System.out.println("What edit would you like to make to the course? (max students/current students/list of names/instructor/section number/location) ");
						String choice = input.nextLine();
						switch (choice) {
							case "max students":
								System.out.println("What would you like the new number of max students to be? ");
								int newMaxStudents = input.nextInt();
								courseRegistration.courseList.get(j).setMaxStudents(newMaxStudents);
								break;
							case "current students":
								System.out.println("What would you like the new number of current students to be? ");
								int newCurrStudents = input.nextInt();
								courseRegistration.courseList.get(j).ResetCurrStudents(newCurrStudents);
								break;
							case "instructor":
								System.out.println("What would you like the new instructor to be? ");
								String newInstructor = input.nextLine();
								courseRegistration.courseList.get(j).setInstructor(newInstructor);
								break;
							case "section number":
								System.out.println("What would you like the new section number to be? ");
								String newSectionNumber = input.nextLine();
								courseRegistration.courseList.get(j).setSectionNumber(newSectionNumber);
								break;
							case "location":
								System.out.println("What would you like the new location to be? ");
								String newLocation = input.nextLine();
								courseRegistration.courseList.get(j).setLocation(newLocation);
								break;
							case "list of names":
								System.out.println("Enter the first name and last name of the student whose name you would like to add or delete: ");
								String firstname = input.next();
								String lastname = input.next();
								input.nextLine();
								//Find the student
								int studentIndex = 0;
								for (int i = 0; i < courseRegistration.studentList.size(); i++) {
									if (courseRegistration.studentList.get(i).getFirstName().equals(firstname)) {
										if (courseRegistration.studentList.get(i).getLastName().equals(lastname)) {
											studentIndex = i;
											//Prompt user to see if they would like to remove or add student
											System.out.println("Would you like to add or delete the student? (add/delete) ");
											String userInput = input.nextLine();
											if (userInput.equals("add")) {
												//add student to list of names
												courseRegistration.courseList.get(j).addStudent(firstname, lastname);
												courseRegistration.courseList.get(j).setCurrStudents(1);
												//add course to students list of courses
												courseRegistration.studentList.get(studentIndex).setStudentCourses(courseID);
										}
											if (userInput.equals("delete")){
												//delete student from list of names
												courseRegistration.courseList.get(j).removeStudent(firstname, lastname);
												courseRegistration.courseList.get(j).setCurrStudents(-1);
												//delete course from students list of courses
												courseRegistration.studentList.get(studentIndex).removeStudentCourses(courseID);
											}
									}
								}
								}
								break;
							default:
								System.out.println("Sorry, that was an invalid edit option.");
								break;
						}
					}
		}	}
				if(!courseFound) {
					System.out.println("Sorry, this course does not exist.");
				}
	}
	
	
	public void CreateAStudent() {
		//Prompt the user to provide student info
		Scanner input = new Scanner(System.in);
		System.out.print("Please enter the students first name, last name, username, and password (seperate by space)");
		String firstname = input.next();
		String lastname = input.next();
		String username = input.next();
		String password = input.next();
		//Take the newly assigned variables and pass them to student constructor to create new object
		Student student = new Student(firstname, lastname, username, password);
		courseRegistration.studentList.add(student);
	}
	
	public void ViewFullCourses() throws IOException {
		//REMEMBER TO WRITE THIS TO A FILE
		BufferedWriter writer = new BufferedWriter(new FileWriter("FullCourses.txt"));
		boolean courseIsFull = false;
		for (int i = 0; i < courseRegistration.courseList.size(); i++) {
			if (courseRegistration.courseList.get(i).getCurrStudents() == courseRegistration.courseList.get(i).getMaxStudents()) {
				System.out.println("The following courses are full: ");
				System.out.println(courseRegistration.courseList.get(i).getCourseID());
				System.out.println(courseRegistration.courseList.get(i).getCourseName());
				writer.write(courseRegistration.courseList.get(i).getCourseName());
				writer.write(courseRegistration.courseList.get(i).getCourseID());
				courseIsFull = true;
				}
			}
		if(!courseIsFull) {
			System.out.println("None of the courses are full.");
		}
				
		}
	
	public void ViewRegisteredStudents() {
		//Prompt user to ask them what course they to get list of Registered students for
		Scanner input = new Scanner(System.in);
		System.out.println("Enter the course ID you would like to see the list of students registered in: ");
		String courseID = input.nextLine();
		System.out.println("Enter the section number of that course: ");
		String courseSecNum = input.nextLine();
		boolean courseFound = false;
		//Iterate through courses to find that particular course
		for (int i = 0; i < courseRegistration.courseList.size(); i++) {
			if (courseRegistration.courseList.get(i).getCourseID().equals(courseID)) {
				if(courseRegistration.courseList.get(i).getSectionNumber().equals(courseSecNum)) {
					System.out.println("Here are the list of students registed: ");
					System.out.println(courseRegistration.courseList.get(i).getListOfNames());
					courseFound = true;
				}
			}
			}
		if(courseFound == false) {
			System.out.println("Course not found.");
		}
		}
	

	public void ViewStudentsCourses() {
	//Prompt the user to enter the first and last name of the student they would like to view
	Scanner input = new Scanner(System.in);
	System.out.println("Enter the students first and last name whose courses you would like to view: ");
	String firstname = input.next();
	String lastname = input.next();
	input.nextLine();
	boolean studentFound = false;
	//Iterate thought student list to find the student by firstname and lastname
	for (int i = 0; i < courseRegistration.studentList.size(); i++) {
		if (courseRegistration.studentList.get(i).getFirstName().equals(firstname)) {
			if (courseRegistration.studentList.get(i).getLastName().equals(lastname)) {
				System.out.println("Here are the student's enrolled courses: ");
				System.out.println(courseRegistration.studentList.get(i).getStudentCourses());
				studentFound = true;
			}
				
		}
	}
		if(studentFound == false) {
			System.out.println("Student not found.");
		}
	
	}
		
	
	
	}
	
	

