package courseSystem;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class courseRegistration implements Serializable {
	static List<Course> courseList = new ArrayList<Course>();//make global
	static List<Student> studentList = new ArrayList<Student>();//make global
	private static final long serialVersionUID = 1L;
	
	public static void main(String[] args) throws IOException {
		
		Deserialize();
			
		//PROGRAM RUN
		//Check to see if they are an admin or student
		System.out.println("Welcome to the Course Registraion System");
		Scanner input = new Scanner(System.in);
		System.out.println("\n" + "Are you a student or an admin? ");
		String userInput = input.nextLine();
		
		//ADMIN LOGIN
		if (userInput.toLowerCase().equals("admin")) {
			//Prompt for login info
			Admin admin = new Admin();
			System.out.println("Please enter your username: ");
			String adminUsername = input.next();
			System.out.println("Please enter your password: ");
			String adminPassword = input.next();
			//Validate login info
			while (!adminUsername.equals(admin.username) || !adminPassword.equals(admin.password)) {
				System.out.println("Your login information is incorrect. Please try to login again.");
				System.out.println("Please enter your username: ");
				adminUsername = input.next();
				System.out.println("Please enter your password: ");
				adminPassword = input.next();
			}
			System.out.println("You have successfully logged in.\n");
			//Display Admin Options 
			String adminChoice = "ok";
			while (adminChoice!="Q"){
			System.out.println("\n Select from the options below.");
			System.out.println("\n");
	        System.out.println("______________________________________________________________________\n");
	        System.out.println("  CNC  - Create a new course");
	        System.out.println("  DC   - Delete a course ");
	        System.out.println("  EC   - Edit a course");
	        System.out.println("  DI   - Display information for a given course");
	       	System.out.println("  RS   - Register a student");
	       	System.out.println("  S    - Sort courses by number of students enrolled");
	 		System.out.println("  VC   - View all courses");
	 		System.out.println("  VFC  - View all courses that are full");
	 		System.out.println("  VNFC - View all courses that are NOT full");
	 		System.out.println("  VNSR - View the names of the students that are registered in a specific course");
	 		System.out.println("  VCSR - View the list of courses that a given student is registered in");
	        System.out.println("  Q    - Exit the program");
	        System.out.println("______________________________________________________________________\n");

	        System.out.println("\n  Enter: CNC, DC, EC, DI, RS, RSC, VC, VFC, VNFC, VNSR, VCSR, or Q  =>  ");
	        
	        adminChoice = input.next().toUpperCase();
	        switch (adminChoice) {
	        case("CNC"):
	        	admin.CreateACourse();
	        	break;
	        case("DC"):
	        	admin.RemoveCourse();
	        	break;
	        case("EC"):
	        	admin.EditCourse();
	        	break;
	        case("DI"):
	        	admin.ViewCourseInfo();
	        	break;
	        case("RS"):
	        	admin.CreateAStudent();
	        	break;
	        case("S"):
	        	Collections.sort(courseList);
	        	break;
	        case("VC"):
	        	admin.ViewAllCourses();
	        	break;
	        case("VFC"):
	        	admin.ViewFullCourses();
	        	break;
	        case("VNFC"):
	        	admin.ViewNotFullCourses();
	        	break;
	        case("VNSR"):
	        	admin.ViewRegisteredStudents();
	        	break;
	        case("VCSR"):
	        	admin.ViewStudentsCourses();
	        	break;
	        case("Q"):
	        	Exit();
	        	break;
	        default:
	        	System.out.println("Sorry the option you entered is unavailable.");
	        	break;
	        }   
		}
			
		}
		
		//STUDENT LOGIN
		else if (userInput.toLowerCase().equals("student")) {
			//Prompt student for login info
			System.out.println("Please enter your username: ");
			String studentUsername = input.next();
			System.out.println("Please enter your password: ");
			String studentPassword = input.next();
			int studentIndex = 0;
			//Validate Login info
			boolean loginInfo = false;
			while(loginInfo == false) {
				//first check that the student list even has students
				if(courseRegistration.studentList.size() == 0) {
					System.out.println("There are currently no students that exist in this system.");
					Exit();
				}
				//Iterate through student list and match up username and password
				for(int i = 0; i < courseRegistration.studentList.size(); i++) {
					if (courseRegistration.studentList.get(i).getUsername().equals(studentUsername)) {
						if(courseRegistration.studentList.get(i).getPassword().equals(studentPassword)) {
							loginInfo = true;
							studentIndex = i;
							break;
						}
					}
				}
				if(loginInfo == false) {
					System.out.println("Your login information was incorrect, please try again.");
					System.out.println("Please enter your username: ");
					studentUsername = input.next();
					System.out.println("Please enter your password: ");
					studentPassword = input.next();
				}
			}
			System.out.println("You have successfully logged in.\n");
			
			Student student  = courseRegistration.studentList.get(studentIndex);
			String firstname = courseRegistration.studentList.get(studentIndex).getFirstName();
			String lastname = courseRegistration.studentList.get(studentIndex).getLastName();
			
			String studentChoice = "";
			while (studentChoice!="Q"){
			//Display Student Options 
			System.out.println("\n Select from the options below.");
			System.out.println("\n");
	        System.out.println("______________________________________________________________________\n");
	       	System.out.println("  EC   - Enroll in a course");
	        System.out.println("  WD   - Withdraw from a course ");
	 		System.out.println("  VC   - View all courses");
	 		System.out.println("  VFC  - View all courses that are full");
	 		System.out.println("  VMC  - View all the courses you are currently enrolled in");
	        System.out.println("  Q    - Exit the program");
	        System.out.println("______________________________________________________________________\n");

	        System.out.println("\n  Enter: EC, WD, VC, VFC, VNFC, VMC, or Q  =>  ");
	        
	        studentChoice = input.next().toUpperCase();
	        switch (studentChoice) {
	        case("EC"):
	        	student.RegisterInCourse(studentIndex, firstname, lastname);
	        	break;
	        case("WD"):
	        	student.RemoveCourse(studentIndex, firstname, lastname);
	        	break;
	        case("VC"):
	        	student.ViewAllCourses();
	        	break;
	        case("VFC"):
	        	student.ViewFullCourses();
	        	break;
	        case("VMC"):
	        	student.ViewAllMyCourses(studentIndex);
	        	break;
	        case("Q"):
	        	Exit();
	        	break;
	        default:
	        	System.out.println("Sorry the option you entered is unavailable.");
	        }
			}
	}
		
		
		//serialize course and student list
		try
        {
            FileOutputStream fos = new FileOutputStream("courseListData.ser");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(courseList);
            oos.close();
            fos.close();
            
            FileOutputStream fos2 = new FileOutputStream("studentListData.ser");
            ObjectOutputStream oos2 = new ObjectOutputStream(fos2);
            oos2.writeObject(studentList);
            oos2.close();
            fos2.close();
        } 
        catch (IOException ioe) 
        {
            ioe.printStackTrace();
        }
	
		
	}
	
	
	
	
	//THIS METHOD CREATES A COURSE LIST	
	public static void CreateCourseList() throws IOException {
		//Establish file path
		String csvFile = System.getProperty("user.dir") + "/MyUniversityCourses.csv";
		String line = "";
		
		
		//Create BufferedReader instance
		BufferedReader reader = new BufferedReader(new FileReader(csvFile));
		
		//Try to find the csv file
		try {reader.readLine();} 
		
		catch (IOException e) {
				System.out.println("Could not find file");
				e.printStackTrace();
			}
			   
		//Read each line with while loop, storing each element into an object
		while((line = reader.readLine()) != null) {
			String[] data=line.split(",");
			Course course = new Course(data[0], data[1], Integer.parseInt(data[2]), Integer.parseInt(data[3]), new ArrayList<String>(), data[5], data[6], data[7]);
			courseList.add(course);
		}
		reader.close();
	}
	
	//Deserialize CourseList
	public static void Deserialize() throws IOException {
	try
    {
        FileInputStream fis = new FileInputStream("courseListData.ser");
        ObjectInputStream ois = new ObjectInputStream(fis);
        courseList = (ArrayList<Course>) ois.readObject();
        
        ois.close();
        fis.close();
        
        FileInputStream fis2 = new FileInputStream("studentListData.ser");
        ObjectInputStream ois2 = new ObjectInputStream(fis2);
        studentList = (ArrayList<Student>) ois2.readObject();
        
        ois2.close();
        fis2.close();
        
   
    } 
    catch (IOException ioe) 
    {
    	//If ser file doesn't exist, read in csv file
    	CreateCourseList();
    } 
    catch (ClassNotFoundException c) 
    {
        System.out.println("Class not found");
        c.printStackTrace();
        return;
    }	
	}
	
	//Exits program and serializes course list and student list
	public static void Exit() {
		try
        {
            FileOutputStream fos = new FileOutputStream("courseListData.ser");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(courseRegistration.courseList);
            oos.close();
            fos.close();
            
            FileOutputStream fos2 = new FileOutputStream("studentListData.ser");
            ObjectOutputStream oos2 = new ObjectOutputStream(fos2);
            oos2.writeObject(courseRegistration.studentList);
            oos2.close();
            fos2.close();
        } 
        catch (IOException ioe) 
        {
            ioe.printStackTrace();
        }
		System.exit(0);
	}
	
	
}
	



