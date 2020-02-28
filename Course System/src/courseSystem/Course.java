package courseSystem;
import java.util.Collections;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

class Course implements Serializable, Comparable<Course> {
	private static final long serialVersionUID = 1L;
	
	//instance vars of course objects
	private String course_Name;
	private String course_ID;
	private int max_Students;
	private int current_Students;
	private ArrayList<String> list_Of_Names = new ArrayList<>();
	private String Instructors;
	private String Sec_Num;
	private String Location;
	
	//Courses Constructor with no parameters
	public Course() {}
	
	//Courses constructor with parameters
	public Course (String course_Name, String course_ID, int max_Students, int current_Students, ArrayList<String> list_Of_Names, String Instructors, String Sec_Num, String Location){
		this.course_Name = course_Name;
		this.course_ID = course_ID;
		this.max_Students = max_Students;
		this.current_Students = current_Students;
		this.list_Of_Names = list_Of_Names;
		this.Instructors = Instructors;
		this.Sec_Num = Sec_Num;
		this.Location = Location;
	}
	
	//Getters and Setters
		
		public String getCourseName() {
		    return this.course_Name;		
		}
		
		public void setCourseName(String newName) {
			this.course_Name = newName;
		}
		
		public String getCourseID() {
		    return this.course_ID;		
		}
		
		public void setCourseID(String newID) {
			this.course_ID = newID;
		}
		
		public int getMaxStudents() {
		    return this.max_Students;		
		}
		
		public void setMaxStudents(int newMax) {
			this.max_Students = newMax;
		}
		
		public int getCurrStudents() {
		    return this.current_Students;		
		}
		
		public void setCurrStudents(int newCurr) {
			this.current_Students += newCurr;
		}
		
		public void ResetCurrStudents(int newCurr) {
			this.current_Students = newCurr;
		}
		
		public List<String> getListOfNames() {
		    return this.list_Of_Names;		
		}
		
		public void setListOfNames(ArrayList<String> listofNames) {
			this.list_Of_Names = listofNames;
		}
		
		public String getInstructor() {
		    return this.Instructors;		
		}
		
		public void setInstructor(String newInstructor) {
			this.Instructors = newInstructor;
		}
		
		public String getSectionNumber() {
		    return this.Sec_Num;		
		}
		
		public void setSectionNumber(String sec_num) {
			this.Sec_Num = sec_num;
		}
		
		public String getLocation() {
		    return this.Location;		
		}
		
		public void setLocation(String newLocation) {
			this.Location = newLocation;
		}
		
		public void addStudent(String firstName, String lastName) {
			this.list_Of_Names.add(firstName + " " + lastName);
		}
		
		public void removeStudent(String firstName, String lastName) {
			this.list_Of_Names.remove(firstName + " " + lastName);
		}
		
		
		@Override
	    public String toString() {
			return String.format(" Course ID: " + course_ID + " Course Name: " + course_Name + " Max Students: " + max_Students + " Current # of Students: " + current_Students + " Names of Students in the course " + list_Of_Names
					+ " Instructor: " + Instructors + " Section Number: " + Sec_Num + " Location: " + Location + " ");
			//System.out.println(courseList.toString());
	}

		@Override
		public int compareTo(Course c) {
			//Compare number of students in each course object and sort array list
			if(this.current_Students < c.current_Students)
				return -1;
			else if(this.current_Students == c.current_Students)
				return 0;
			else
				return 1;
		}
}	
		

