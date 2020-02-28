package courseSystem;
import java.io.Serializable;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Scanner;

public abstract class User implements Serializable {
	String username;
	String password;
	
	//THIS METHOD VIEWS ALL COURSES
	public void ViewAllCourses() {
		for (int i = 0; i < courseRegistration.courseList.size(); i++)
			System.out.println(courseRegistration.courseList.get(i));
	}
	
	public void ViewNotFullCourses(){
		//Iterate through course list comparing current num of students to max num of students
		System.out.print("The following courses are not full: ");
		for (int i = 0; i < courseRegistration.courseList.size(); i++) {
			if (courseRegistration.courseList.get(i).getCurrStudents() != courseRegistration.courseList.get(i).getMaxStudents())
				System.out.println(courseRegistration.courseList.get(i));
		}
	}
	
	public void ViewFullCourses() throws IOException{}
	
	public void RegisterInCourse() {}
	
	public void RemoveCourse() {}
	
	

}
	

