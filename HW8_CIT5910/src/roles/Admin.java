package roles;

import java.util.*;

import courses.Course;
import files.FileInfoReader;

/**
 * Represent an admin and extend and implement the User class
 */
public class Admin extends User {
	
	// fields


	// constructor
	
//	public Admin(String id, String name, String username, String password) {
//		super(id, name, username, password);
//	}
	
	// concrete methods
	
	/**
	 * Displays student menu.
	 */
	@Override
	public void displayMenu() {
		System.out.println("----------------------------------");
		System.out.println("     Welcome, " + this.name); 
		System.out.println("----------------------------------");
		System.out.println(" 1 -- View all courses            ");
		System.out.println(" 2 -- Add new courses             ");
		System.out.println(" 3 -- Delete courses              ");
		System.out.println(" 4 -- Add new professor           ");
		System.out.println(" 5 -- Delete professor            ");
		System.out.println(" 6 -- Add new student             ");
		System.out.println(" 7 -- Delete student              ");
		System.out.println(" 8 -- Return to previous menu     ");
		System.out.println(" ");
		System.out.println("Please enter your option, eg. '1' ");
	}
			
	/**
	 * Override toString method to provide meaningful representation
	 */
	@Override
	public String toString() {
		return "Admin: { id=" + this.id + " name = " + this.name + " username: " + this.username + "}";
	}
	
	/**
	 * Helper function to see if course is already in a specified array list.
	 * @param courseId
	 * @param Arraylist
	 * @return if course in the specified list.
	 */
	private boolean isCourseInList(String courseId, ArrayList <Course> courses) {
		////Checking if course id is already in main course list.
		ArrayList <String> courseIds = new ArrayList <String> ();
		
		for (Course course : courses) {
			courseIds.add(course.getCourseId());
		}
		
		if(courseIds.contains(courseId)) {
			return true;
		}else {
			return false;
		}
	}
	
	/**
	 * Helper function to see if professor is already in a specified list.
	 * @param profid
	 * @param Arraylist
	 * @return if prof is already in list
	 */
	private boolean isProfInList(String profId, ArrayList <Professor> profs) {
		////Checking if course id is already in main course list.
		ArrayList <String> profIds = new ArrayList <String> ();
		
		for (Professor prof : profs) {
			profIds.add(prof.getId());
		}
		
		if(profIds.contains(profId)) {
			return true;
		}else {
			return false;
		}
	}
	
//	/**
//	 * Adds new course to the main course list if courses does not exist.
//	 */
//	public void addNewCourse() {
//
//		//Initializing variable for string input.
//		String input = "";
//		
//		while(input != "q") {
//			
//			//Getting course ID of user.
//			System.out.println("Please enter the course ID, or type 'q' to end.");
//			String id =  this.userInput().toString();
//			input = id;
//		
//			//Checking if course is in main list of courses.
//			if(this.isCourseInList(id, courses) == true) {
//				//If course is in list, do nothing.
//				System.out.println("The course already exists.");
//			}else {
//				//If course is not in list, we start the process of adding it.
//				
//				//Creating new instance of course.
//				Course newCourse = new Course(id, null, null, null, null, null, 0, 0);
//				
//				//Getting name of course from user and then setting the name if not q.
//				System.out.println("Please enter the course name, or type 'q' to end.");
//				String name = this.userInput().toString();
//				input = name;
//				newCourse.setCourseName(name);
//				
//				//Getting start time from user.
//				System.out.println("Please enter the course start time, or type 'q' to end.");
//				String start = this.userInput().toString();
//				input = start;
//				newCourse.setStartTime(start);
//				
//				//Getting end time from user.
//				System.out.println("Please enter the course end time, or type 'q' to end.");
//				String end = this.userInput().toString();
//				input = end;
//				newCourse.setCourseName(end);
//				
//				//Getting date from user.
//				System.out.println("Please enter the course dates, or type 'q' to end.");
//				String dates = this.userInput().toString();
//				input = dates;
//				newCourse.setCourseDates(dates);
//				
//				//Getting capacity from user.
//				System.out.println("Please enter the course capacity, or type 'q' to end.");
//				String cap = this.userInput().toString();
//				input = cap;
//				newCourse.setCapacity(Integer.parseInt(cap));
//				
//				//Getting course lecturer's id from user.
//				System.out.println("Please enter the course lecturer's ID, or type 'q' to end.");
//				String profid = this.userInput().toString();
//				
//				//Checking if the professor exists in the system.
//				if(this.profExists(profid) == true) {
//					//If the professor exists then we can just add them as the course professor.
//					String profname = this.getProfNameFromId(profid, profs);
//					input = profid;
//					newCourse.setCourseLecturerName(profname);
//				}else {
//					//If the professor does not exist then we have to add the new professor.
//					System.out.println("The professor isn't in the system, please add this professor first.");
//					this.addNewProfessor();
//					
//					String profname = this.getProfNameFromId(profid, profs);
//					input = profid;
//					newCourse.setCourseLecturerName(profname);
//				}
//				
//				//Need to check if course conflists with professors other courses.
//
//				//Adding new course to list of courses.
//				courses.add(newCourse);
//				System.out.println("Successfully added the course: " + newCourse.toString());
//				input = "q";
//				}
//			}
//		}
	
//	/**
//	 * Adds a new professor.
//	 */
//	public void addNewProfessor() {
//		
//		//Initializing variable for string input.
//		String input = "";
//		
//		while (input != "q") {
//			//Getting new professor's ID.
//			System.out.println("Please enter the professor's ID, or type 'q' to end.");
//			String profid = this.userInput().toString();
//			input = profid;
//			
//			if(this.isProfInList(id, profs) == true) {
//				//If course is in list, do nothing.
//				System.out.println("This professor is already registered.");
//			}else {
//				//If course is not in list, we start the process of adding it.
//				
//				//Creating new instance of course.
//				Professor newProf = new Professor(id, null, null, null);
//				
//				//Getting professor's name.
//				System.out.println("Please enter the professor's name, or type 'q' to end.");
//				String profname = this.userInput().toString();
//				input = profname;
//				newProf.setName(profname);
//				
//				//Getting username and password.
//				System.out.println("Please enter a username.");
//				String profusername = this.userInput().toString();
//				input = profusername;
//				newProf.setUserName(profusername);
//				
//				System.out.println("Please enter a password.");
//				String profpassword = this.userInput().toString();
//				input = profpassword;
//				newProf.setPassword(profpassword);
//				
//				profs.add(newProf);
//				System.out.println("Successfully added the new professor: " + newProf.getId() + " " + newProf.getName());
//				input = "q";
//				}
//			
//			}
//
//	}
	
	
	/**
	 * Helper function to get professor's name using their id.
	 * @param id of professor 
	 * @param list of professors
	 * @return name of the professor
	 */
	private String getProfNameFromId(String id, ArrayList<Professor> list) {
		String name = "";
		for (Professor prof : list) {
			if(id == prof.getId()) {
				name += prof.getName();
			}
		}
		return name;
	}
	
	/**
	 * Checks if professor exists.
	 * @param professorid as a string
	 */
	private boolean profExists(String id) {
		ArrayList <String> profIds = new ArrayList <String> ();
		for (Professor prof : professors) {
			profIds.add(prof.getId());
		}
		if(profIds.contains(id)) {
			return true;
		}else {
			return false;
		}
	}
	
	// instance variables
	private ArrayList<Course> courses;
	private ArrayList<Professor> professors;
	private ArrayList<Student> students;

	// constructors


	/**
	 * Overload an admin constructor with array lists of courses, students,
	 * professors Constructor for creating an Admin with user details (ID, name,
	 * username, password)
	 * 
	 * @param id       of admin
	 * @param name     of admin
	 * @param username of admin
	 * @param password of admin
	 */
	public Admin(String id, String name, String username, String password) {
		super(id, name, username, password); // Calling the parent constructor in Users class
		this.courses = new ArrayList<>(); // Default empty list
		this.students = new ArrayList<>(); // Default empty list
		this.professors = new ArrayList<>(); // Default empty list

	}


	// concrete methods

	/**
	 * Displays all available courses
	 */
	@Override
	public void viewAllCourses(ArrayList<Course> courses) {
		if (courses == null) {
			System.out.println("No courses available.");
		} else {

			// iterate over a list of courses
			for (Course course : courses) {
				System.out.println(course);
			}
		}
	}

	/**
	 * add new courses to array list of courses
	 * @param scanner
	 */
	public void addNewCourses(Scanner scanner) {
		
		// initialize local variables;
		String courseId = null;
		String courseDates = null; 
		String startTime = null;
		String endTime = null;

		// prompt for user input
		while (true) {
			courseId = getUserInput(scanner, "Please enter the course ID, or type 'q' to end.");
			if (courseId == null)
				return; // exit if user enters 'q'

			// check if the ID already exists
			if (doesCourseIdExist(courseId)) { // if true, e.g. ID exists
				System.out.println("The course ID already exists. Please enter a different ID.");
			} else {
				break; // exit the loop if ID is unique
			}
		}

		// prompt for other details, exit if any user input is null
		String courseName = getUserInput(scanner, "Please enter the course name, or type 'q' to end.");
		if (courseName == null)
			return;
		
		while (true) {
			startTime = getUserInput(scanner, "Please enter the course start time, or type 'q' to end. (e.g., '19:00')");
			
			if (startTime == null)
				return; // exit if user enters 'q'
			
			if (!Course.validateTimeFormat(startTime)) { // if not true, e.g. time format has issue
				System.out.println("Please enter correct time format (e.g., '19:00').");
			} else {
				break; // exit the time check loop
			}		
		}

		while (true) {
			endTime = getUserInput(scanner,	"Please enter the course end time, or type 'q' to end. (e.g., '20:00')");
			
			if (endTime == null)
				return; // exit if user enters 'q'
			
			if (!Course.validateTimeFormat(endTime)) { // if not true, e.g. time format has issue
				System.out.println("Please enter correct time format (e.g., '20:00').");
			} else {
				break; // exit the time check loop
			}		
		}
		
		while (true) {
			courseDates = getUserInput(scanner, "Please enter the course date, or type 'q' to end. (e.g., 'MW')");
			
			if (courseDates == null)
				return; // exit if user enters 'q'
			
			if (!Course.validateDateFormat(courseDates.trim().toUpperCase())) { // if not true, e.g. time format has issue
				System.out.println("Please enter correct date format (e.g., M, T, W, R, F).");
			} else {
				break; // exit the time check loop
			}		
		}
		
		String capacityString = getUserInput(scanner,
				"Please enter the course capacity, or type 'q' to end. (e.g., '72')");
		if (capacityString == null)
			return;
		int capacity = Integer.parseInt(capacityString);

		String courseLecturerId = getUserInput(scanner,
				"Please enter the course lecturer's ID, or type 'q' to end. (e.g., '001')");
		if (courseLecturerId == null)
			return;

		// Step 3: check if lecturer exist
		Professor newProfessor = null; // To hold the new professor if one is added
		while (!doesLecturerIdExist(courseLecturerId)) { // if not true, i.e., ID dose not exist
			System.out.println("The professor isn't in the system, please add this professor first.");
			newProfessor = addNewProfessor(scanner); // create a new professor
			if (newProfessor != null) { // add the new professor to the list only if they were successfully created
				professors.add(newProfessor); // add the new professor to the list
				break; // exit the while loop if new professor is added
			}
		}

		// Step 4: create a new Course object
		int enrolledStudents = 0;
		Course newCourse = new Course(courseId.trim().toUpperCase(), courseName.trim(), courseLecturerId.trim(),
				courseDates.trim().toUpperCase(), startTime.trim(), endTime.trim(), capacity, enrolledStudents);

		// Step 5: check if time conflict
		for (Course existingCourse : courses) {
			if (existingCourse.isTimeConflict(newCourse)) {
				String message = "The new added course has time conflict with course: ";
				System.out.println(message + existingCourse.toString());
				System.out.println("Unable to add new course: " + newCourse.toString());
				return;
			}
		}

		// If no conflict, add the new course to the list
		courses.add(newCourse);
//		System.out.println("Successfully added the new course: " + newCourse.toString());
		System.out.println("Successfully added the new course: " + newCourse.toString());
	}

	
	/**
	 * delete a course from array list of courses
	 * @param scanner
	 */
	public void deleteCourses(Scanner scanner) {

		// Step 1: prompt for user input
		String courseId = getUserInput(scanner, "Please enter the course ID, or type 'q' to end.");
		if (courseId == null)
			return; // Exit if user enters 'q'

		// Step 2: check if the professor ID dose not exist
		if (!doesCourseIdExist(courseId)) { // if not exist
			System.out.println("The course does not exist.");
			return;
		}

		// Step 3: remove the professor object from array list
		Iterator<Course> iterator = courses.iterator();

		while (iterator.hasNext()) {
			Course course = iterator.next();
			if (course.getCourseId().equalsIgnoreCase(courseId.trim())) {
				iterator.remove();
				System.out.println("The course has been deleted.");
				break; // exit the while loop
			}
		}
	}

	
	/**
	 * Helper function Check if a course ID already existed Called by
	 * addNewProfessor()
	 * 
	 * @param courseId as a string
	 * @return true if the course ID already existed
	 */
	private boolean doesCourseIdExist(String courseId) {

		for (Course course : courses) {
			if (course.getCourseId().equalsIgnoreCase(courseId.trim())) {
				return true;
			}
		}
		return false;
	}

	
	/**
	 * Helper function Check if a lecturer ID already existed Called by
	 * addNewProfessor()
	 * 
	 * @param professorId as a string
	 * @return true if the professor ID already existed
	 */
	private boolean doesLecturerIdExist(String professorId) {

		for (Professor professor : professors) {
//			System.out.println("Checking professor ID: " + professor.getId() + " against input ID: " + professorId);
			if (professor.getId().equalsIgnoreCase(professorId.trim())) {
				return true;
			}
		}
		return false;
	}

	
	/**
	 * Add a new professor to array list of professors
	 * 
	 * @param scanner
	 * @return a professor object
	 */
	public Professor addNewProfessor(Scanner scanner) {

		// prompt for user input
		String professorId;
		while (true) {
			professorId = getUserInput(scanner, "Please enter the professor's ID, or type 'q' to end.");
			if (professorId == null)
				return null; // Exit if user enters 'q'

			// check if the ID already exists
			if (doesLecturerIdExist(professorId)) {
				System.out.println("The professor ID already exists. Please enter a different ID.");
			} else {
				break; // exit the loop if ID is unique
			}
		}

		// prompt for other details, exit if any user input is null
		String professorName = getUserInput(scanner, "Please enter the professor's name, or type 'q' to end.");
		if (professorName == null) {
			return null;
		}

		String professorUsername = getUserInput(scanner, "Please enter a username, or type 'q' to end.");
		if (professorUsername == null) {
			return null;
		}

		String professorPassword = getUserInput(scanner, "Please enter a password, or type 'q' to end.");
		if (professorPassword == null) {
			return null;
		}

		// create a new professor and add to array list of professors
		Professor newProfessor = new Professor(professorId, professorName, professorUsername, professorPassword);
		professors.add(newProfessor);

		// System.out.println(professors); // debugging
		System.out.println("Successfully added the new professor: " + newProfessor.toString());
		return newProfessor;
	}

	
	/**
	 * Delete a professor for array list of professors
	 * 
	 * @param scanner
	 * @param professors as an array list of professors
	 */
	public void deleteProfessor(Scanner scanner) {

		// Step 1: prompt for user input
		String professorId = getUserInput(scanner, "Please enter the professor's ID, or type 'q' to end.");
		if (professorId == null)
			return; // exit if user enters 'q'

		// Step 2: check if the professor ID dose not exist
		if (!doesLecturerIdExist(professorId)) { // if not exist
			System.out.println("The professor does not exist.");
			return;
		}

		// Step 3: remove the professor object from array list
		Iterator<Professor> iterator = professors.iterator();

		while (iterator.hasNext()) {
			Professor professor = iterator.next();
			if (professor.getId().equals(professorId)) {
				iterator.remove();
				System.out.println("The professor has been deleted.");
				break; // exit the loop
			}
		}
	}

	
	/**
	 * Add a new student to array list of students
	 * 
	 * @param scanner
	 * @return a student object
	 */
	public Student addNewStudent(Scanner scanner) {

		// prompt user input
		String studentId;
		while (true) {
			studentId = getUserInput(scanner, "Please enter the student's ID, or type 'q' to end.");
			if (studentId == null)
				return null; // Exit if user enters 'q'

			// check if the ID already exists
			if (doesStudentIdExist(studentId)) { // if true, e.g. ID exists
				System.out.println("The student ID already exists. Please enter a different ID.");
			} else {
				break; // exit the loop if ID is unique
			}
		}

		// prompt for other details, exit if any user input is null
		String studentName = getUserInput(scanner, "Please enter the student's name, or type 'q' to end.");
		if (studentName == null) {
			return null;
		}

		String studentUsername = getUserInput(scanner, "Please enter a username, or type 'q' to end.");
		if (studentUsername == null) {
			return null;
		}

		String studentPassword = getUserInput(scanner, "Please enter a password, or type 'q' to end.");
		if (studentPassword == null) {
			return null;
		}

		// create a new student and add to array list of professors
		Student newStudent = new Student(studentId, studentName, studentUsername, studentPassword, null);
		students.add(newStudent);

		// success message
		System.out.println("Successfully added the new student: " + newStudent.toString());
		return newStudent;
	}

	
	/**
	 * Delete a student for array list of students
	 * 
	 * @param scanner
	 * @param students as an array list of students
	 */
	public void deleteStudent(Scanner scanner) {

		// Step 1: prompt for user input
		String studentId = getUserInput(scanner, "Please enter the student's ID, or type 'q' to end.");
		if (studentId == null)
			return; // exit if user enters 'q'

		// Step 2: check if the student ID exists
		if (!doesStudentIdExist(studentId)) { // if the student does not exist
			System.out.println("The student does not exist.");
			return; // exit the method if student doesn't exist
		}

		// Step 3: remove the student object from array list
		Iterator<Student> iterator = students.iterator();

		while (iterator.hasNext()) {
			Student student = iterator.next();
			if (student.getId().equals(studentId)) {
				iterator.remove(); // remove the student
				System.out.println("The student has been deleted.");
				return; // exit the method after deleting
			}
		}
	}

	
	/**
	 * Helper function Check if a student ID already existed Called by
	 * deleteProfessor()
	 * 
	 * @param professorId as a string
	 * @return true if the professor ID already existed
	 */
	private boolean doesStudentIdExist(String studentId) {

		for (Student student : students) {
			if (student.getId().equals(studentId)) {
				return true;
			}
		}
		return false;
	}


	// getters and setters

	/**
	 * @return the courses
	 */
	public ArrayList<Course> getCourses() {
		return courses;
	}

	/**
	 * @param courses the courses to set
	 */
	public void setCourses(ArrayList<Course> courses) {
		this.courses = courses;
	}

	/**
	 * @return the professors
	 */
	public ArrayList<Professor> getProfessors() {
		return professors;
	}

	/**
	 * @param professors the professors to set
	 */
	public void setProfessors(ArrayList<Professor> professors) {
		this.professors = professors;
	}

	/**
	 * @return the students
	 */
	public ArrayList<Student> getStudents() {
		return students;
	}

	/**
	 * @param students the students to set
	 */
	public void setStudents(ArrayList<Student> students) {
		this.students = students;
	}

}
