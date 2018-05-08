package IT306_Spring_2018_Project;
/**
* @author Team Power Rangers(Team 8) Krishna Pudasaini, Susmita Gautam, Somayeh Ameli
* Professor Setareh
* Class: IT 306-001
* Project Phase 5 (Final Implementation)
* May 01, 2018
* This is a JUnit testing class. This class inputs the value and prints the well formated information 
* with the same values. 
*/
import static org.junit.jupiter.api.Assertions.*;

import javax.swing.JOptionPane;

import org.junit.jupiter.api.Test;

class TestJunit1 {

	@Test
	void test() {
		Appointment appointment = new Appointment (100," 11/11/2018", " 11:10", " 11:50", 
				100," Krishna", " male", 37, " (888)777-5555",
				"hello", 1, "Tom", "male", 66, "(777)999-5643", " family doctor");
		Appointment appointment2 = new Appointment (100,"11/11/2018", " 11:10", "11:50", 
				100,"Susmita", "female", 37, "(888)777-5555",
				"hello", 1, "Shila", "female", 66, "(777)999-5643", "Pediatrician");
		Appointment appointment3 = new Appointment (103," 04/18/2018", "12:30", "1:50", 
				100,"Arefe", "female", 12, "(703)456-5555",
				"hello", 1, "Shila", "female", 66, "(777)999-5643", "Cardiologist");
		

		
		
		assertEquals(102, appointment2.getID());
		
		assertEquals(100, appointment.getID());
	
		assertEquals(103, appointment3.getID());
		
		assertEquals("11:11", appointment2.getAppDate());
		
		assertEquals("11/11/2018", appointment2.getAppDate());
		
		assertEquals("", appointment.getDoctor());
		
	}

}

