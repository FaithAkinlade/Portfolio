package roles;
import java.util.*;

import courses.*;
import files.FileInfoReader;

public abstract class User {
	
	public static ArrayList<Student> students = new ArrayList<>();
	public static ArrayList<Professor> professors = new ArrayList<>();
	public static ArrayList<Admin> admins = new ArrayList<>();
	public static ArrayList<Course> courses = new ArrayList<>();
	
	/**
	 * String that contains username.
	 */
	public String username;
	
	/**
	 * String that contains password.
	 */
	public String password;
	
	/**
	 * String that contains password.
	 */
	public String id;
	
	/**
	 * String that contains password.
	 */
	public String name;
	
	/**
	 * User constructor
	 */
	public User(String id, String name, String username, String password) {
		this.id = id;
		this.name = name;
		this.username = username;
		this.password = password;
		// create an instance of FileInforReader with file paths passed in the
		// constructor
		FileInfoReader fileReader = new FileInfoReader("courseInfo.txt", "studentInfo.txt", "profInfo.txt", 
				"adminInfo.txt");

		// access loaded data
		courses = fileReader.getCourses();
		students = fileReader.getStudents();
		professors = fileReader.getProfessors();
		admins = fileReader.getAdmins();
	}
	
	//Setters
	/**
	 * Sets the username.
	 */
	public void setUserName(String username) {
		this.username = username;
	}

	/**
	 * Sets the password.
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	
	/**
	 * Sets name of the current user.
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * Sets id of the current user.
	 */
	public void setId(String id) {
		this.id = id;
	}
	
	//Getters
	/**
	 * Gets the username.
	 * @return username of current user.
	 */
	public String getUsername() {
		return this.username;
	}
	
	/**
	 * Gets the password of the current user.
	 * @return password of current user.
	 */
	public String getPassword() {
		return this.password;
	}
	
	/**
	 * Gets name of the current user.
	 * @return name of current user.
	 */
	public String getName() {
		return this.name;
	}
	
	/**
	 * Gets id of the current user.
	 * @return id of current user.
	 */
	public String getId() {
		return this.id;
	}

	/**
	 * Displays the menu associated with the current user.
	 */
	public abstract void displayMenu();
	
	/**
	 * Prompts, receives and returns user input.
	 * @return userInput.
	 */
	public Scanner userInput() {
		Scanner userInput = new Scanner(System.in);
		userInput.close();
		
		return userInput;
	}
	
	/**
	 * Displays all available courses.
	 */
	public void viewAllCourses(ArrayList<Course> courses) {
		if (courses == null) {
			System.out.println("No courses available.");
		} else {
			// iterate over a list of courses
			for (Course course : courses) {
				System.out.println(course.toString());
			}
		}
	}
	
	/**
	 * checks if user login credential used in controller class
	 * @param username
	 * @param password
	 * @return true if logged in successfully
	 */
	public boolean isLogin(String username, String password) {
		
		if (this.username.equals(username) && this.password.equals(password)) {
			return true;
		} else {
			System.out.println("please enter correct username or password.");
		}
		return false;
	}
	
	/**
	 * Helper function
	 * Get user input as a string
	 * @param scanner
	 * @param prompt
	 * @return
	 */
	protected String getUserInput(Scanner scanner, String prompt) {
		while (true) {
	        System.out.println(prompt);
	        String input = scanner.nextLine().trim();
	        
	        // if want to quit
	        if (input.equalsIgnoreCase("q")) {
	            return null; 
	        }
	        
	        // if no input from user
	        if (input.isEmpty()) {
	        	System.out.println("Input can not be empty. Please try again.");
	        	continue;
	        }
	        
	        // otherwise, return user input
	        return input;
	    }
	}
	
	
	
	
	
}	
