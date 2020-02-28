package courseSystem;

import java.io.IOException;

public interface A {

	//COURSE MANAGEMENT METHODS
	void CreateACourse();
	void RemoveCourse();
	void EditCourse(); //Cannot edit ID or name
	void ViewCourseInfo();
	void CreateAStudent();
	void RegisterInCourse();
	
	
	
	//REPORT METHODS
	void ViewRegisteredStudents();
	void ViewStudentsCourses();
	void ViewFullCourses() throws IOException;

}
