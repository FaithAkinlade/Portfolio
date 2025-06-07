package files;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import courses.Course;
import roles.Admin;
import roles.Professor;
import roles.Student;

/**
 * Utility class for reading a text file.
 */
public class FileInfoReader {

	// fields
	
    /**
     *  Collections to store data
     */
	private ArrayList<Course> courses = new ArrayList<>();	
	private ArrayList<Student> students = new ArrayList<>();
    private ArrayList<Professor> professors = new ArrayList<>();
    private ArrayList<Admin> admins = new ArrayList<>();

	/**
	 * Names of file being read.
	 */
    private String courseFile;
    private String studentFile;
    private String professorFile;
    private String adminFile;

	// constructor
	
	/**
	 * Create FileInfoReader with given filename to read.
	 * 
	 * @param filename to read
	 */
    public FileInfoReader(String courseFile, String studentFile, String professorFile, String adminFile) {
        this.courseFile = courseFile;
        this.studentFile = studentFile;
        this.professorFile = professorFile;
        this.adminFile = adminFile;
    }
    
    // methods
	
    /**
     * Load all four text files to four array list of objects
     */
    public void loadAllData() {
    	readCourseFile();
    	readStudentFile();
    	readProfessorFile();
    	readAdminFile();
    }
    
    
	/**
	 * Read course information from course file
	 * such as CIT590; Programming Languages and Techniques; Brandon L Krakowsky; MW; 16:30; 18:00; 110
	 * @return a list of courses 
	 */
	public void readCourseFile() {
		//create myFile, fileReader and bufferedReader objects
		File myFile = new File(this.courseFile);
		FileReader fileReader = null;
		BufferedReader bufferedReader = null;
		
		try {
			fileReader = new FileReader(myFile);
			bufferedReader = new BufferedReader(fileReader);
			String line; // store info of each line
			
			// read each line for each student
			while ((line = bufferedReader.readLine()) != null) {
				
				// parse course data (e.g., CIT590; Programming Languages and Techniques; Brandon L Krakowsky; MW; 16:30; 18:00; 110)
				String[] data = line.trim().split(";");

	            // ensure the data array has the expected length
	            if (data.length != 7) {
	                System.out.println("Invalid course data format: " + line);
	                continue; 
	             }				

	            // get course attributes		
	            String courseId = data[0].trim();
	            String courseName = data[1].trim();
	            String courseLectureName = data[2].trim();
	            String courseDates = data[3].trim();
	            String startTime = data[4].trim();
	            String endTime = data[5].trim();
	            int capacity = Integer.parseInt(data[6].trim());
				int enrolledStudents = 0; // Initialize with zero, can be updated later
					
				// create a new course using parsed data from each line
				Course course = new Course(courseId, courseName, courseLectureName, courseDates, startTime, endTime, capacity, enrolledStudents);
				// add the instance to array list of courses
				courses.add(course);
				}

		} catch (FileNotFoundException e) {
			System.out.println("Sorry, " + myFile.getName() + " not found."); // prints filename as File class provides a getName() method
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			// regardless, close the file objects
			try {
				fileReader.close();
				bufferedReader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}	
	
	
	/**
	 * Read student information from student file   
	 * 
	 * @return a list of students
	 */
	public void readStudentFile() {

		// create myFile, fileReader and bufferedReader objects
		File myFile = new File(this.studentFile);
		FileReader fileReader = null;
		BufferedReader bufferedReader = null;

		try {
			fileReader = new FileReader(myFile);
			bufferedReader = new BufferedReader(fileReader);
			String line; // store info of each line
			
			// read each line for each student
			while ((line = bufferedReader.readLine()) != null) {
				// parse student data: (e.g. 001; StudentName1; testStudent01; password590; CIS191: A, CIS320: A)
				String[] data = line.trim().split(";");

	            // ensure the data array has the expected length
	            if (data.length != 5) {
	                System.out.println("Invalid student data format: " + line);
	                continue; 
	             }	
				
				// get attributes
				String id = data[0].trim();
				String name = data[1].trim();
				String username = data[2].trim();
				String password = data[3].trim();
				String courseData = data[4].trim(); // all course data, such as "cit1:A, cit2: A"
				// Create a new HashMap for each student's course and grades
                HashMap<String, String> courseGrades = parseCourseGrades(courseData);
                
                // create an instance for each line
                Student student = new Student(id, name, username, password, courseGrades);
                // add the instance to array list of students
                students.add(student); 
			}
		} catch (FileNotFoundException e) {
			System.out.println("Sorry, " + myFile.getName() + " not found."); // gets and prints filename
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			// regardless, close the file objects
			try {
				fileReader.close();
				bufferedReader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	
	/**
	 * Read professor information from professor file
	 * 
	 * @return an array list of professors
	 */
	public void readProfessorFile() {
		
		// create myFile, fileReader and bufferedReader objects
		File myFile = new File(this.professorFile);
		FileReader fileReader = null;
		BufferedReader bufferedReader = null;

		try {
			fileReader = new FileReader(myFile);
			bufferedReader = new BufferedReader(fileReader);
			String line; // store info of each line
			
			while ((line = bufferedReader.readLine()) != null) {
				// parse professor data: (e.g. Clayton Greenberg; 001; Greenberg; password590)
				String[] data = line.trim().split(";");

	            // ensure the data array has the expected length
	            if (data.length != 4) {
	                System.out.println("Invalid course data format: " + line);
	                continue; 
	             }	
	            
	            // get attributes
				String name = data[0].trim();
				String id = data[1].trim();					
				String username = data[2].trim();
				String password = data[3].trim();
				
				// create an instance for each line
				Professor professor = new Professor(id, name, username, password);
				// add the instance to array list of professors
				professors.add(professor);
			}

		} catch (FileNotFoundException e) {
			// gets and prints filename
			System.out.println("Sorry, " + myFile.getName() + " not found.");
		} catch (IOException e) {
			// Handle general IOExceptions for BufferedReader
			e.printStackTrace();
		} finally {
			// regardless, close the file objects
			try {
				fileReader.close();
				bufferedReader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	
	/**
	 * Read admin information from admin file
	 * 
	 * @return an array list of admin
	 */
	public void readAdminFile() {
		
		// create myFile, fileReader and bufferedReader objects
		File myFile = new File(this.adminFile);
		FileReader fileReader = null;
		BufferedReader bufferedReader = null;

		try {
			fileReader = new FileReader(myFile);
			bufferedReader = new BufferedReader(fileReader);
			String line; // store info of each line
			
			while ((line = bufferedReader.readLine()) != null) {
				// parse admin data: (e.g. 001; admin; admin01; password590)
				String[] data = line.trim().split(";");

	            // ensure the data array has the expected length
	            if (data.length != 4) {
	                System.out.println("Invalid course data format: " + line);
	                continue; 
	             }	
	            
	            // get attributes
				String id= data[0].trim();
				String name = data[1].trim();					
				String username = data[2].trim();
				String password = data[3].trim();

				// create each admin role for each line
				Admin admin = new Admin(id, name, username, password);
				// add to array list of admin
				admins.add(admin);
			}
		} catch (FileNotFoundException e) {
			// gets and prints filename
			System.out.println("Sorry, " + myFile.getName() + " not found.");
		} catch (IOException e) {
			// Handle general IOExceptions for BufferedReader
			e.printStackTrace();
		} finally {
			// regardless, close the file objects
			try {
				fileReader.close();
				bufferedReader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}


	
	/**
	 * Helper function
	 * Parse course id and course grade from String to Hashmap
	 * @param courseData
	 * @return Hashmap of courseId as the key, grade as the associated value
	 */
	private HashMap<String, String> parseCourseGrades(String courseData) {
		
		// initialize the courseGrades hashmap
		HashMap<String, String> courseGrades = new HashMap<String, String>(); 
		
		// initialize local variables
		String courseId = null, grade = null;
		
		// get each course grade as a string array
		String[] courseGradePairs = courseData.trim().split(","); // each course-garde pair is an element
		
		// iterate each course grade pair to get course Id and grade
		for (String courseGradePair : courseGradePairs) {

			// split course Id and grade, save to a string array
			String[] courseGradeArray = courseGradePair.trim().split(":"); // e.g. "cit1", "A"

			// in case no grade
			if (courseGradeArray.length == 2) {
				// split the two-element array to course id and grade
				courseId = courseGradeArray[0].trim();
				grade = courseGradeArray[1].trim();
			}

			// save course id and grade in the HashMap
			courseGrades.put(courseId, grade);
		}

		return courseGrades;
	}

	

	// Getters for the collections
    public ArrayList<Student> getStudents() {
        return students;
    }

    public ArrayList<Professor> getProfessors() {
        return professors;
    }

    public ArrayList<Admin> getAdmins() {
        return admins;
    }

    public ArrayList<Course> getCourses() {
        return courses;
    }
}