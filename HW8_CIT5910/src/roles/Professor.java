package roles;

import java.util.*;
import courses.*;

public class Professor extends User {
	
	/**
	 * Array list of courses that professor teaches.
	 */
	private ArrayList <Course> coursesTaught = new ArrayList <Course> ();
	
	
	public Professor(String id, String name, String username, String password) {
		super(id, name, username, password);
	}
	
	@Override
	public void displayMenu() {
		System.out.println("----------------------------------");
		System.out.println("     Welcome, " + this.name); 
		System.out.println("----------------------------------");
		System.out.println(" 1 -- View given courses            ");
		System.out.println(" 2 -- View student list of the given course.    ");
		System.out.println(" 3 -- Return to previous menu.       ");
		System.out.println(" ");
		System.out.println("Please enter your option, eg. '1' ");
	
	}
	
	/**
	 * Prints professors courses.
	 */
	public void viewGivenCourses() {

	    for (Course course : courses) {
	        if (course.getCourseLecturerName().equalsIgnoreCase(this.getName())) {
	            System.out.println(course.toString());
	            coursesTaught.add(course);
	        }
	    }

	}

	/**
	 * Prints names of student who are taking a given course.
	 * @param courseName
	 * @param courses
	 */
	public void viewStudentListOfGivenCourse(String courseid) {
		
//		
//		
//		for (Course course: courses) {
//			if (course.getCourseName().equalsIgnoreCase(cleanedCourseName)) {
//				System.out.println(course.getStudentsHashSet());
//			}
//		}
	}
	
	/**
	 * Set list of professor's courses.
	 */
	public void setCourses(ArrayList <Course> courses) {
		this.coursesTaught = courses;
	}
	
	/**
	 * Get list of professor's courses.
	 * @return 
	 * @return 
	 */
	public ArrayList<Course> getCourses() {
		return this.coursesTaught;
	}

	public void setStudents(ArrayList<Student> students) {
		// TODO Auto-generated method stub
		
	}

}