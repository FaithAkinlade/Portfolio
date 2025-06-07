package courses;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.fail;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.equalTo;

import courses.Course;
import files.FileInfoReader;

class CourseTest {
	
	//Instance of Course object to test.
	Course cs;
	Course cs2;
	Course cs3;
	
	//Instance of file info reader.
	FileInfoReader fir;
	
	@BeforeEach
	void setUp() throws Exception {
		// initialize movie trivia object
		cs = new Course("MAT101", "Mathematics", "Chai Tee", "MW", "16:00", "18:00", 23, 5);
		cs2 = new Course("ART101", "Intro to Art", "Kevin Wozniak", "F", "16:30", "19:00", 24, 6);
		cs3 = new Course("DRAMA301", "Theatre", "Crystal Waters", "MWF", "17:00", "19:00", 30, 7);
	}
	
	@Test
	void testIsTimeConflict() {
		assertTrue(cs.isTimeConflict(cs3), 
				"The two courses have the happen at similar hours on monday and wednesday.");
		assertFalse(cs.isTimeConflict(cs2), 
				"The two courses may run around the same time but it is on totally different days.");
	}
	
	@Test
	void testToString() {
		assertEquals("MAT101 | Mathematics, 16:00-18:00 on MW, with course capacity: 23, students: 5, lecturer: Professor Chai Tee",
				cs.toString());
	}
	
}
