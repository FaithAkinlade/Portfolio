import java.util.ArrayList;
import java.util.Scanner;

import courses.Course;
import files.FileInfoReader;
import roles.Admin;
import roles.Professor;
import roles.Student;
import roles.User;

public class Controller {

	// instance variables

	public static ArrayList<Student> students = new ArrayList<>();
	public static ArrayList<Professor> professors = new ArrayList<>();
	public static ArrayList<Admin> admins = new ArrayList<>();
	public static ArrayList<Course> courses = new ArrayList<>();

	public static void main(String[] args) {
		// Create instance
		Controller controller = new Controller();

		// create an instance of FileInforReader with file paths passed in the
		// constructor
		FileInfoReader fileReader = new FileInfoReader("courseInfo.txt", "studentInfo.txt", "profInfo.txt",
				"adminInfo.txt");

		// load text file info
		fileReader.loadAllData();

		// access loaded data
		courses = fileReader.getCourses();
		students = fileReader.getStudents();
		professors = fileReader.getProfessors();
		admins = fileReader.getAdmins();

		Scanner scanner = new Scanner(System.in);

		// print the main menu and start the interaction
		User currentUser = controller.startMainMenu(scanner);
//		System.out.println(courses);
//		System.out.println(currentUser);

		while (true) {
			if (currentUser == null) {
				currentUser = controller.startMainMenu(scanner);
			}
			
			if (currentUser instanceof Admin) {
//				System.out.println(courses);
//				System.out.println(currentUser);
				controller.adminTasks(scanner, (Admin) currentUser);

			}		
			
			
//			if (currentUser instanceof Student) {
//			controller.studentTasks(scanner, (Student) currentUser);
//			}		
//
//			if (currentUser instanceof Professor) {
//				controller.professorTasks(scanner, (Professor) currentUser);
//			}	
//			
		}
	}

	// controller have different menus to show different user inputs
	private User startMainMenu(Scanner scanner) {
		User currentUser = null;
		
		
		while (true) {
			System.out.println("Hey there! Welcome to the Student Management System or SMS for short. Ready to log in?");
			printMainMenu();
			int choice = 0;
			
			// read user input and validate login info
			try {
				choice = Integer.parseInt(scanner.nextLine().trim());
			} catch (NumberFormatException e) {
				System.out.println("Invalid input, please enter a number.");
				continue; // Skip this iteration if input is invalid
			}

			if (choice == 4) { // exit option
				System.out.println("You exit the system. Goodbye!");
				break; // exit the loop and quit
			}
			
			// user login section
				System.out.println("Please enter your username, or type 'q' to quit.");
				String inputUserName = scanner.nextLine().trim();		
	            if (inputUserName.equalsIgnoreCase("q")) {
	              System.out.println("Goodbye!");
	              break; // Exit if user types 'q'
	            }	
				
				System.out.println("Please enter your password, or type 'q' to quit.");
				String inputPassword = scanner.nextLine().trim();		
	            if (inputPassword.equalsIgnoreCase("q")) {
	                System.out.println("Goodbye!");
	                break; // Exit if user types 'q'
	            }		
	            
				currentUser = login(inputUserName, inputPassword, choice);
				
				if (currentUser != null) {

					return currentUser; // exit the main menu while loop
					// Handle user-specific menu (e.g., Student, Professor, Admin menu)
				} else {
					System.out.println("Invalid username or password. Please try again.\n");
				}

		}
		return currentUser; // return null if only invalid input available
	}

	/**
	 * Method to start with main menu and check login for each role to verify user
	 * credentials
	 * 
	 * @param inputUserName
	 * @param inputPassword
	 * @param choice
	 * @return
	 */
	private User login(String inputUserName, String inputPassword, int choice) {
		switch (choice) {

		case 1: // student login
			for (Student student : students) {
				if (student.isLogin(inputUserName, inputPassword)) {
					System.out.println("Login successfully as " + student.getUsername() + "\n");
					student.setCourses(courses); // Set the courses for the student
					this.printStudentMenu(student);
					return student;
				}
			}
			break;

		case 2: // professor login
			for (Professor professor : professors) {
				if (professor.isLogin(inputUserName, inputPassword)) {
					System.out.println("Login successfully as " + professor.getUsername() + "\n");
					professor.setCourses(courses);
					professor.setStudents(students);
					this.printProfessorMenu(professor);
					return professor;
				}
			}
			break;

		case 3: // admin login
			for (Admin admin : admins) {
				if (admin.isLogin(inputUserName, inputPassword)) {
					System.out.println("Login successfully as " + admin.getUsername() + "\n");
					admin.setCourses(courses); // set the courses for the admin
					admin.setStudents(students);
					admin.setProfessors(professors);
	                this.printAdminMenu(admin);
					return admin;
				}
			}
			break;

		default:
			System.out.println("Invalid input. Please enter a number between 1 and 4.");
		}

		return null;
	}

	/**
	 * Perform admin tasks defined in the admin menu
	 * 
	 * @param scanner
	 * @param admin
	 */
	private void adminTasks(Scanner scanner, Admin admin) {
		boolean loggedIn = true;

		while (loggedIn) {

			this.printAdminMenu(admin); // recursing after exiting each option
			int action = Integer.parseInt(scanner.nextLine());

			switch (action) {
				case 1:
					admin.viewAllCourses(courses);
					break;
	
				case 2:
					admin.addNewCourses(scanner);
					break;
	
				case 3:
					admin.deleteCourses(scanner);
					break;
	
				case 4:
					admin.addNewProfessor(scanner);
					break;
	
				case 5:
					admin.deleteProfessor(scanner);
					break;
	
				case 6:
					admin.addNewStudent(scanner);
	
					break;
				case 7:
					admin.deleteStudent(scanner);
					break; // out of the admin login loop
	
				case 8:
					System.out.println("Back to main menu... \n");
					loggedIn = false;
					admin = null; // Log out the user
					startMainMenu(scanner);
					return; // exit the entire method
	
				default:
					System.out.println("Invalid choice. Try again.");
					break;
			}
		}
	}


	/**
	 * Print main menu
	 */
	public void printMainMenu() {
		System.out.println("-------------------------------");
		System.out.println("   Students Management System  ");
		System.out.println("-------------------------------");
		System.out.println(" 1 -- Login as a student       ");
		System.out.println(" 2 -- Login as a professor     ");
		System.out.println(" 3 -- Login as an admin        ");
		System.out.println(" 4 -- Quit the system          ");
		System.out.println();
		System.out.println("Please enter your option, eg. '1'.");
	}

	/**
	 * Prints the admin menu interface
	 */
	public void printAdminMenu(Admin admin) {
		System.out.println("-----------------------------------------------");
		System.out.println("          Welcome, " + admin.getUsername());
		System.out.println("-----------------------------------------------");
		System.out.println(" 1 -- View all courses");
		System.out.println(" 2 -- Add new courses");
		System.out.println(" 3 -- Delete courses");
		System.out.println(" 4 -- Add new professor");
		System.out.println(" 5 -- Delete professor");
		System.out.println(" 6 -- Add new student");
		System.out.println(" 7 -- Delete student");
		System.out.println(" 8 -- Return to previous menu");
		System.out.println();
		System.out.println("Please enter your option, eg. '1'.");
	}

	/**
	 * Prints the professor menu interface
	 */
	public void printProfessorMenu(Professor prof) {
		System.out.println("-----------------------------------------------");
		System.out.println("         Welcome, " + prof.getUsername());
		System.out.println("-----------------------------------------------");
		System.out.println(" 1 -- View given courses");
		System.out.println(" 2 -- View student list of the given course");
		System.out.println(" 3 -- Return to the previous menu");
		System.out.println();
		System.out.println("Please enter your option, eg. '1'.");
	}

	/**
	 * prints the student menu interface
	 */
	public void printStudentMenu(Student stud) {
		System.out.println("-----------------------------------------------");
		System.out.println("          Welcome, " + stud.getUsername());
		System.out.println("-----------------------------------------------");
		System.out.println(" 1 -- View all courses");
		System.out.println(" 2 -- Add courses to your list");
		System.out.println(" 3 -- View enrolled courses");
		System.out.println(" 4 -- Drop courses in your list");
		System.out.println(" 5 -- View grades");
		System.out.println(" 6 -- Return to previous menu");
		System.out.println();
		System.out.println("Please enter your option, eg. '1'.");
	}

}