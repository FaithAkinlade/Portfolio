package roles;

import java.util.*;
import courses.*;
import files.*;

public class Student extends User {

	/**
	 * String that contains course grade.
	 */
	public ArrayList <String> courseGrade = new ArrayList <String> ();
	
	/**
	 * String that contains courseId
	 */
	public ArrayList <String> courseId = new ArrayList <String> ();

	/**
	 * Array list of courses that student is currently enrolled in.
	 */
	private ArrayList <Course> myCourses = new ArrayList <Course> ();
	
	
	//Constructor
	/**
	 * Creates a new instance of user as student(1), professor(2), or admin(3) or quits program.
	 * @param Id
	 * @param name
	 * @param username
	 * @param password
	 * @param courseId 
	 * @param courseGrade
	 */
	public Student(String id, String name, String username, String password, HashMap<String, String> courseGrades) {
		super(id, name, username, password);
		//Getting courseId and courseGrade data.
		for (String key : courseGrades.keySet()) {
			this.courseId.add(key);
			this.courseGrade.add(courseGrades.get(key));
		}
	}

	/**
	 * Displays student menu.
	 */
	@Override
	public void displayMenu() {
		System.out.println("----------------------------------");
		System.out.println("     Welcome, " + this.name); 
		System.out.println("----------------------------------");
		System.out.println(" 1 -- View all courses            ");
		System.out.println(" 2 -- Add courses to your list    ");
		System.out.println(" 3 -- View enrolled courses       ");
		System.out.println(" 4 -- Drop courses in your list   ");
		System.out.println(" 5 -- View grades                 ");
		System.out.println(" 6 -- Return to previous menu     ");
		System.out.println(" ");
		System.out.println("Please enter your option, eg. '1' ");
	}

	/**
	 * Set list of student courses.
	 */
	public void setCourses(ArrayList <Course> courses) {
		this.myCourses = courses;
	}
	
	/**
	 * Adds course to the student's list.
	 */
	public void addCourseToYourList(String courseId) {
		for (Course course : courses) {
			if(courseId == course.getCourseId()) {
				myCourses.add(course);
				course.setCurrentEnrollment(course.getCurrentEnrollment()+1);
			}
		}
	}
	

	/**
	 * Displays all courses where student is enrolled.
	 */
	public void viewEnrolledCourses() {
		for (Course course : myCourses) {
			System.out.println(course.toString());
		}
	}

	/**
	 * Drops course from the student's list.
	 */
	public void dropCoursesInYourList(String courseId) {
		for (Course course : myCourses) {
			if(courseId == course.getCourseId()) {
				myCourses.remove(course);
				course.setCurrentEnrollment(course.getCurrentEnrollment()-1);
			}
		}
	}

	/**
	 * Displays student's grades.
	 */
	public void viewGrades() {
		System.out.println("Here are the courses you have already taken, with your grade in a letter format:");
		for (int i = 0; i < this.courseGrade.size(); i++) {
			courseGrade.get(i);
			System.out.println("Grade of " + courseId.get(i) + " " + findCourseName(courseId.get(i)) + ": " + courseGrade.get(i));
		}
	}
	
	/**
	 * Gets name of course using courseId.
	 */
	public String findCourseName(String courseId) {
		//Initializing name variable.
		String name = "";
		//Iterating through courses by id to find the one specified and returning the name.
		for(Course course : courses) {
			if(courseId == course.getCourseId()) {
				name = course.getCourseName();
			}
		}
		return name;
	}
}