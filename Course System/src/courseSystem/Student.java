package courseSystem;
import java.util.Scanner;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Student extends User implements S, Serializable {
	private static final long serialVersionUID = 1L;
	
	//instance variables
	private String firstname;
	private String lastname;
	private String username;
	private String password;
	private ArrayList<String> studentCourses = new ArrayList<>();
		
	
	//Getters and Setters for student
	public String getFirstName() {return this.firstname;}
	
	public void setFirstName(String newFirstName) {this.firstname = newFirstName;}
	
	public String getLastName() {return this.lastname;}
	
	public void setLastName(String newLastName) {this.lastname = newLastName;}
	
	public String getUsername() {return this.username;}
	
	public void setUsername(String newUsername) { this.username = newUsername;}
	
	public String getPassword() {return this.password;}
	
	public void setPassword(String newPassword) {this.password = newPassword;}
	
	public List<String> getStudentCourses() {return studentCourses;}
	
    public void setStudentCourses(String course) {
        this.studentCourses.add(course);
    }
    
    public void removeStudentCourses(String course) {
        this.studentCourses.remove(course);
    }
    
   
    //Default Constructor
    public Student(){}
    

    public Student(String firstname, String lastname, String username, String password) {
    	this.firstname = firstname;
    	this.lastname = lastname;
    	this.username = username;
    	this.password = password;
    }
    
    
    //STUDENT METHODS
    
	public void RegisterInCourse(int studentIndex, String firstname, String lastname) {
		//Prompt student to ask them what course they want the student to register in 
				Scanner input = new Scanner(System.in);
				System.out.println("Enter the course ID of the course you would like to enroll in : ");
				String WantedCourse = input.nextLine();
				System.out.println("Enter the course Section you would like to register in: ");
				String courseSecNum = input.nextLine();
				//Find course in file
				boolean courseFound=false;
				boolean courseFull = true;
				for(int i = 0; i < courseRegistration.courseList.size(); i++) {
					//check to see the course exist
					if (courseRegistration.courseList.get(i).getCourseID().equals(WantedCourse)){
						if (courseRegistration.courseList.get(i).getSectionNumber().equals(courseSecNum)) {
						courseFound = true;
						//Check to see that course isn't full
							if (courseRegistration.courseList.get(i).getCurrStudents() < courseRegistration.courseList.get(i).getMaxStudents()) {	
								courseFull = false;
								//increment current number of students in the course by 1
								courseRegistration.courseList.get(i).setCurrStudents(1);
								//Add students name to the list of names in courses
								courseRegistration.courseList.get(i).addStudent(firstname, lastname);
								//Add course to student object's array of courses
								courseRegistration.studentList.get(studentIndex).setStudentCourses(WantedCourse);
								System.out.println("You have now been added to the course!");
								}
						}
					}
				}
				
				if(courseFound == false || courseFull == true) {
					System.out.println("Sorry, this course does not exist and/or is full.");
				}

	}
	
	
	
	public void RemoveCourse(int studentIndex, String firstname, String lastname) {
		//Get student index
		Scanner input = new Scanner(System.in);
		//Prompt user for the course they would like to remove from their course list
		System.out.println("Enter the ID of the course you would like to withdraw from: ");
		String courseRemoveID = input.nextLine();
		System.out.println("Enter the course section number you would like to withdraw from: ");
		String courseRemoveSecNum = input.nextLine();
		boolean courseFound  = false;
		int courseIndex = 0;
		//Iterate through course list to make sure that it exist
		for (int i = 0 ; i < courseRegistration.studentList.size(); i++) {
			if (courseRegistration.courseList.get(i).getCourseID().equals(courseRemoveID)){
				if (courseRegistration.courseList.get(i).getSectionNumber().equals(courseRemoveSecNum)) {
				courseFound = true;
				courseIndex = i;
				//remove the course from the students array list of objects
				courseRegistration.studentList.get(studentIndex).getStudentCourses().remove(courseIndex);
				//Remove the student from the courses list of names
				courseRegistration.courseList.get(courseIndex).removeStudent(firstname, lastname);
				courseRegistration.courseList.get(courseIndex).setCurrStudents(-1);
	}
	
		}
		}
		if(courseFound == false) {
			System.out.println("Sorry this course is unavailable.");
	}
}


	
	public void ViewAllMyCourses(int studentIndex) {
		//Take student index to locate its course list and print it
		System.out.println("Courses currently enrolled in: " + courseRegistration.studentList.get(studentIndex).getStudentCourses());
			
	}
   
	
 
    
}
    	
    


