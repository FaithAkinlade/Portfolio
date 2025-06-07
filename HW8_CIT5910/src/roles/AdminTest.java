package roles;


import static org.junit.jupiter.api.Assertions.*;

import java.util.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.fail;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.equalTo;

import courses.Course;
import files.FileInfoReader;


class AdminTest {
	
	//Instance of Admin object to test.
	Admin ad;
	Admin ad2;
	Admin ad3;
	
	//Instance of file info reader.
	FileInfoReader fileReader;
	
	public static ArrayList<Student> students = new ArrayList<>();
	public static ArrayList<Professor> professors = new ArrayList<>();
	public static ArrayList<Admin> admins = new ArrayList<>();
	public static ArrayList<Course> courses = new ArrayList<>();

		
	
	@BeforeEach
	void setUp() throws Exception {
		// initialize movie trivia object
		ad = new Admin("004", "Carla Jeep", "CJ876", "password876");
		ad2 = new Admin("005", "Tessa Red", "TR678", "password678");
		
		FileInfoReader fileReader = new FileInfoReader("courseInfo.txt", "studentInfo.txt", "profInfo.txt",
				"adminInfo.txt");

		// load text file info
		fileReader.loadAllData();

		// access loaded data
		courses = fileReader.getCourses();
		students = fileReader.getStudents();
		professors = fileReader.getProfessors();
		admins = fileReader.getAdmins();
		
		
	}
	
	@Test
	void testToString() {
		assertEquals("Admin: { id=004 name = Carla Jeep username: CJ876}",
				ad.toString());
	}
	
	//
	@Test
    public void testAddNewCourses() {
    	// check typical case
        Admin admin = new Admin("1", "Admin", "admin", "password");
        Scanner scanner = new Scanner("CSE102\nData Structures\nJohn Doe\nMTWRF\n09:00\n10:00\n100\n001");
        admin.addNewCourses(scanner);
        assertEquals(courses.size() + 1, admin.getCourses().size());
    }
	
	
	
}
