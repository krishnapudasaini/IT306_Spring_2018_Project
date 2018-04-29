package IT306_Spring_2018_Project;

import javax.swing.JOptionPane;

import org.junit.Test;

public class JunitTestApplication {

	@Test
	public static void main (String [] args)
	{
	Appointment appointment = new Appointment (100,"11/11/2018", "11:10", "11:50", 
			100,"Krishna", "male", 37, "(888)777-5555",
			"hello", 1, "Tom", "male", 66, "(777)999-5643", "family doctor");
	Appointment appointment2 = new Appointment (100,"11/11/2018", "11:10", "11:50", 
			100,"Susmita", "female", 37, "(888)777-5555",
			"hello", 1, "Shila", "female", 66, "(777)999-5643", "Pediatician");
	 JOptionPane.showMessageDialog(null, appointment.format() + "\n" + appointment2.format());
	  
	}

}
