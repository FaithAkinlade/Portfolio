package courses;

import java.time.*;
import java.util.*;
import java.util.regex.Pattern;
import java.util.regex.Matcher;


import roles.Student;

/**
 * Represent a single course
 */
public class Course {
	
	// fields
	
	/*
	 * course id
	 */
	private String courseId;
	
	/*
	 * course name
	 */	
	private String courseName;
	
	/*
	 * course lecture name
	 */
	private String courseLecturerName;
	
	/*
	 * course start time in String
	 * format: 13:00 (in 24 hours)
	 */
	private String startTime;
	
	/*
	 * course end time in String
	 * format: 13:00 (in 24 hours)
	 */	
	private String endTime;
	
	/*
	 * course capacity: max number of enrolled students
	 */
	private int capacity;
	
	/**
	 * Number of students currently enrolled.
	 */
	private int currentEnrollment;
	
	/**
	 * course dates in format MWF etc.
	 */
	private String courseDates;
	
	/*
	 * course start time in time format
	 * format: 13:00 (in 24 hours)
	 */
	private LocalTime parsedStartTime;
	private LocalTime parsedEndTime;
//	private HashSet<String> enrolledStudents;

	// constructor

	public Course(String courseId, String courseName, String courseLecturerName, 
			String courseDates, String startTime, String endTime, int capacity, int currentEnrollment) {
		
		this.courseId = courseId;
		this.courseName = courseName;
		this.courseLecturerName = courseLecturerName;
		this.setCurrentEnrollment(currentEnrollment);
		this.startTime = startTime;
		this.endTime = endTime;
		this.capacity = capacity;
		this.courseDates = courseDates;
		this.parsedStartTime = parseToLocalTime(startTime); // parsed start time
		this.parsedEndTime = parseToLocalTime(endTime); // parsed end time
		
	}

	/**
	 * Helper function
	 * Convert time from String to LocalTime type
	 * 
	 * @param time of String
	 * @return time of LocalTime
	 */
	private LocalTime parseToLocalTime(String time) { // question: this method can be private, but set as default for unit testing 

		try {
			// convert string time to LocalTime format
			return LocalTime.parse(time);
		} catch (Exception e) {
			// handle invalid time and return null
			System.out.println("Please provide valid time for parsing: " + time); 
			// question in unit test, how to handle AM/PM vs 24 hours, do we need to? 
			return null;
		}

	}
	
	/**
	 * Helper function 
	 * Parse String to HashSet for course weeks
	 * 
	 * @param course weeks in String
	 * @return course weeks in HashSet
	 */
	private HashSet<String> parseCourseDates(String courseDatesString) { // question: this method can be private, but set as default for unit testing
		
		if (courseDatesString.isEmpty()) {
			System.out.println("Please provide valid week: " + courseDatesString); 			
		}
		
		// declare the HashSet<String> var
		HashSet<String> courseWeeks = new HashSet<String>();
		
		// iterator String to parse from "MF" to "M" and "F" 
		for (int i = 0; i < courseDatesString.length(); i++) {
			 // add to a HashSet collection
			 courseWeeks.add(String.valueOf(courseDatesString.charAt(i)));
		}
		return courseWeeks;
	}

	
	
	/**
	 * Check if date and time of two courses conflict or not
	 * 
	 * @param Course other as another Course object
	 * @return boolean true if time conflicts
	 */
	public boolean isTimeConflict(Course other) {
		
		HashSet<String> thisCourseDates = parseCourseDates(this.courseDates);  
		HashSet<String> otherCourseDates = parseCourseDates(other.courseDates); 

		// check time overlap
		if (this.parsedStartTime == null || this.parsedEndTime == null || other.parsedStartTime == null || other.parsedEndTime == null) {
			System.out.println("Provide two start and end times.");
		}

		// check date and time for overlapping		
		if (
			(thisCourseDates.retainAll(otherCourseDates)) &&  // check weekday intersection
			(	// check for true overlap
				(this.parsedStartTime.isBefore(other.parsedEndTime)) && // check if this course's start time is before the other course's end time
				(this.parsedEndTime.isAfter(other.parsedStartTime))     // check if this course's end time is after the other course's start time
				||  // check for exact start/end time: e.g. one course ends at 16:30 and other course starts at 16:30
				(this.parsedEndTime.equals(other.parsedStartTime)) &&   // check if one course ends exactly when the other starts
				(this.parsedStartTime.isBefore(other.parsedEndTime))
				||  // check for exact start/end time: e.g. one course ends at 16:30 and other course starts at 16:30
				(this.parsedStartTime.equals(other.parsedEndTime)) &&   // check if one course starts exactly when the other ends
				(this.parsedEndTime.isAfter(other.parsedStartTime)) 
			)
			){
			return true; // true if overlaps
		}

		return false;
	}
	
	/**
	 * Helper function 
	 * Check if a course object is empty or not
	 * 
	 * @return true if course object is empty
	 */
	private boolean isEmpty() {
		
		if (this.courseId == null || this.courseName == null || this.courseLecturerName == null ||
			this.capacity < 0  || this.parsedStartTime == null || this.parsedEndTime == null || this.courseDates == null) {
			return true; // if missing properties
		}else {
		return false;
		}
	}
	
	/**
	 * Validate time format in HH:mm in 24 hour style
	 * 
	 * @param inputTime as String
	 * @return true if validated
	 */	
	public static boolean validateTimeFormat(String inputTime) {
        String pattern = "^(0?[0-9]|1[0-9]|2[0-3]):[0-5][0-9]$";
        	// for hours: 9:00 and 09:00 are both okay, so 0? is optional, 0?[1-9] matches 00 - 09 or 0 - 9
        	// 1[0-9] matches hours from 10 to 19
        	// 2[0-3] matches hours from 20 to 23
        	// for minutes 00 to 59:  [0-5] matches any digit from 0 to 5.[0-9] matches any digit from 0 to 9
        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(inputTime);
        return m.find(); // return true if match found
    }	

	/**
	 * Validate date format in M, T, W, R, F
	 * Using regex
	 * 
	 * @param courseDates as string
	 * @return true if validated
	 */
	public static boolean validateDateFormat(String inputDate) {
        String pattern = "^(?!.*(.).*\\1)[MTWRF]+$";
    	// ^  start of the line
    	// ?! ensure not match
    	// (.) a group of single character
        // .* to match 0 or more times
    	// \1 the first captured group
        // [MTWRF]+ match one or more of any letter
    Pattern r = Pattern.compile(pattern);
    Matcher m = r.matcher(inputDate);
    return m.find(); // return true if match found
	}
	
	// getters and setters
	
	/**
	 * Get course Id
	 * @return course Id
	 */
	public String getCourseId() {
		return this.courseId;
	}

	/**
	 * Set course Id
	 * @param courseId
	 */
	public void setCourseId(String courseId) {
		this.courseId = courseId;
	}
	

	/**
	 * Get course name
	 * @return the courseName
	 */
	public String getCourseName() {
		return this.courseName;
	}
	
	
	/**
	 * Set course name
	 * @param courseName the courseName to set
	 */
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	

	/**
	 * get course lecture name
	 * @return the courseLectureName
	 */
	public String getCourseLecturerName() {
		return this.courseLecturerName;
	}

	
	/**
	 * set course lecture name
	 * @param courseLectureName the courseLectureName to set
	 */
	public void setCourseLecturerName(String courseLecturerName) {
		this.courseLecturerName = courseLecturerName;
	}

	
	/**
	 * Get course Dates as HashSet<String>
	 * @return the courseDates
	 */
	public String getCourseDates() {
		return this.courseDates;
	}


	/**
	 * Set course Dates as HashSet<String>
	 * @param courseWeek the courseWeek to set
	 */
	public void setCourseDates(String courseDates) {
		this.courseDates = courseDates;
	}
	

	/**
	 * Get startTime as a String
	 * @return the startTime
	 */
	public String getStartTime() {
		return this.startTime;
	}
	

	/**
	 * Set startTime as a String
	 * @parameter the startTime
	 */
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	
	
	/**
	 * Get endTime as a String
	 * @return the endTime
	 */
	public String getEndTime() {
		return this.endTime;
	}
	
	
	/**
	 * Set endTime as a String
	 * @param the endTime
	 */
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	
	/**
	 * Get course capacity
	 * @return the capacity
	 */
	public int getCapacity() {
		return this.capacity;
	}

	
	/**
	 * Set course capacity
	 * @param capacity the capacity to set
	 */
	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}
	
	/**
	 * Gets number of current students enrolled.
	 * @return number of students currently enrolled in the class.
	 */
	public int getCurrentEnrollment() {
		return this.currentEnrollment;
	}

	/**
	 * Sets number of students currently enrolled in the class.
	 * @param currentEnrollment
	 */
	public void setCurrentEnrollment(int currentEnrollment) {
		this.currentEnrollment = currentEnrollment;
	}


	/**
	 * Displays details for specified course as a string.
	 * @return course details
	 */
	public String toString() {
		return this.getCourseId() + " | " + this.getCourseName() + ", " + this.getStartTime() + "-" + this.getEndTime() + " on " + this.getCourseDates() + ", with course capacity: " + this.getCapacity() + ", students: " + this.getCurrentEnrollment() + ", lecturer: Professor " + this.getCourseLecturerName();
	}

}