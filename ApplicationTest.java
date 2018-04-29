package IT306_Spring_2018_Project;
/**
* @author Team Power Rangers(Team 8) Krishna Pudasaini, Susmita Gautam, Somayeh Ameli
* Professor Setareh
* Class: IT 306-001
* Project Phase 4 (Preliminary Implementation)
* April 22, 2018
* This is a ApplicationTest implementation class. The array object has instantiated in this class. 
* Also, hashmap has used in this class. This class prompts the users for all the appointment,
* doctors, patients information and validates all the users input. This class also contains delete, 
* search, add, display all report and display number of appointments, doctors, and patients methods.
*/
//importing java utilities
import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;
import javax.swing.JOptionPane;

//applications test
public class ApplicationTest {
	public static void main(String[] args) {  //initializing the hash map
	    Map<Integer,Appointment> map = new Hashtable<Integer,Appointment>();
		String path = "./src/IT306_Spring_2018_Project/Report.txt";
		updateMap(map, path);
	    printMenu(map, path);
	}	
	//print menu method to print the options for the user to select 	
	private static void printMenu(Map<Integer, Appointment> map, String path) {	
		int menuOption = getMenuChoice(); 
		//while the user has not chosen to exit the application,keep presenting them with the menu option
		while (menuOption != 10) {
			switch (menuOption) {
				case 1:
					addAllPatients(map);
					updateFile(map, path);
					break;
					
				case 2:
					deleteAppointment(map,path);//it updates the map by removing appointment from the map
					updateFile(map, path);//updates the file with the modified map
					break;
	
				case 3:
					displayAllAppointments(map,path);
					break;
	
				case 4:
					displayCurrentAppointmentsDoctorsPatients(map);
					break;
					
				case 5:
					searchID(map);
					break;
				
				case 6:
					searchByPatientName(map);
					break;
					
				case 7:
					searchByDoctorName(map);
					break;
					
				case 8:
					sortByDoctor(map,path);
					break;
					
				case 9:
					sortByPatient(map,path);
					break;
	
				default:
					break;
			}
			menuOption = getMenuChoice();
		}
	}
	//menu choice option with exceptions if they dont input the proper integer range
	private static int getMenuChoice() {
			int menuOption;
			do {
				try {
					menuOption = Integer.parseInt(JOptionPane
							.showInputDialog("Select the menu option from below:"
									+ "\n(1) Add an Appointment"
									+ "\n(2) Delete an Appointment"
									+ "\n(3) Display all Appointments"
									+ "\n(4) Display current number of appointments, doctors and patients"
									+ "\n(5) Search an appointments by appointment id"
									+ "\n(6) Search an appointments by patient's name"
									+ "\n(7) Search an appointments by doctor's name"
									+ "\n(8) Sort appointment by doctor's name"
									+ "\n(9) Sort appointment by patient's name"
									+ "\n(10) Exit Application"));
				} catch (NumberFormatException e) {
					menuOption = 0;
				}
				if (menuOption < 1 || menuOption > 10) {
					JOptionPane.showMessageDialog(null, "Invalid Option. Enter a valid option please");
				}
			} while (menuOption < 1 || menuOption > 10);

			return menuOption;  
		}		
	//add patients method that is called when the user selects from the menu to add patients
	private static void addAllPatients( Map<Integer, Appointment> map) {	//index to keep track of the appointments from the class
		int index = map.values().size()+1;
		do {
			map.put(index++, addAppointment());
		}while(JOptionPane.showConfirmDialog(null,"Add another Appointment?") == JOptionPane.YES_OPTION);
	}	    
	//adding patients into the has map itself
	public static void updateFile(Map<Integer, Appointment> map, String path) {	//using a buffered writer function to read and write onto the file to keep track of patient and doctor info for appointment
		BufferedWriter bw = null;
		try {
			bw = new BufferedWriter(new FileWriter(new File(path)));
 		 	//for every entry in the appointment object, write it onto the text file
 			for(Map.Entry<Integer, Appointment> entry : map.entrySet()) {  
	 		    	bw.append(entry.toString());
 		    	}
 			bw.close();//closing the buffered writer variable
		}catch(FileNotFoundException ex) {
			ex.printStackTrace();
		}catch(IOException ex) {
			ex.printStackTrace();
		}
	}
	//adding the appointment information from the user input from the menu 
	private static Appointment addAppointment() {
		Appointment appointment = new Appointment();
		boolean valid = false;
		//below has all the user input requirements and validation for the requirements
		do {
			valid = appointment.setAppDate(JOptionPane.showInputDialog("Enter the appointment date, such as 11/12/2018"));
			if(!valid) {
				printErrorMessage("Invalid input for date");
			}
		}while(!valid);
		
		valid = false;
		do {
			valid = appointment.setAppTimeIn(JOptionPane.showInputDialog("Enter the appointment In time,such as 11:12"));
			if(!valid)
			{
				printErrorMessage("Invalid input for appointment in time");
			}
		}while(!valid);
		
		valid = false;
		do {
			valid =appointment.setAppTimeOut(JOptionPane.showInputDialog("Appointment Out time, such as 11:12"));
			if(!valid) {
				printErrorMessage("Invalid input for appointment out time");
			}
		}while(!valid);
		
		addPatient(appointment);
		return appointment;
	}
	//adding patient information 
	private static void addPatient(Appointment appointment) {
		boolean valid = false;
 		do {
			valid = appointment.getPatient().setName(JOptionPane.showInputDialog("Enter the patient name"));
			if(!valid) {
				printErrorMessage("Invalid input for name");
			}
		}while(!valid);
 		
		valid = false;
		do {
			 valid = appointment.getPatient().setGender(JOptionPane.showInputDialog("Enter patient gender"));
			if(!valid) {
				printErrorMessage("Invalid input for gender");
			}
		}while(!valid);
		
		valid = false;
		do {
			try {
				valid = appointment.getPatient().setAge(Integer.parseInt(JOptionPane.showInputDialog("Enter the patient age")));
			 	if(!valid) printErrorMessage("Invalid input for age");
			}catch(NumberFormatException e) {
				if(!valid) {
					printErrorMessage("Invalid input for age");
				}
			}
		}while(!valid);
		
		valid = false;
		do {
			valid = appointment.getPatient().setPhoneNum(JOptionPane.showInputDialog("Enter patient's phone number in the format of (777)222-3333"));
			if(!valid) {
				printErrorMessage("Invalid input for phone number");
			}
		}while(!valid);
		
		valid = false;
		do {
			 valid = appointment.getPatient().setDescription(JOptionPane.showInputDialog("Enter patient description"));
			if(!valid) {
				printErrorMessage("Invalid input for description");
			}
		}while(!valid);
		
		populateDoctor(appointment);
	}
	//populating doctor information
	private static void 	populateDoctor(Appointment appointment) {
		boolean valid = false;
		do {
			valid = appointment.getDoctor().setName(JOptionPane.showInputDialog("Enter the doctor's name"));
			if(!valid) {
				printErrorMessage("Invalid input for name");
			}
		}while(!valid);
		
		valid = false;
		do {
			valid = appointment.getDoctor().setGender(JOptionPane.showInputDialog("Enter doctor's gender"));
			if(!valid) {
				printErrorMessage("Invalid input for gender");
			}
		}while(!valid);
		
		valid = false;
		do {
			try {
				valid = appointment.getDoctor().setAge(Integer.parseInt(JOptionPane.showInputDialog("Enter the doctor's age")));
			}catch(NumberFormatException e){
				if(!valid){
					printErrorMessage("Invalid input for age");
				}
			}
		}while(!valid);
		
		valid = false;
		do {
			valid = appointment.getDoctor().setPhoneNum(JOptionPane.showInputDialog("Enter doctor's phone number in the format of (777)222-3333"));
			if(!valid) {
				printErrorMessage("Invalid input for phone number");
			}
		}while(!valid);
		
		valid = false;
		do {
			valid = appointment.getDoctor().setDType(JOptionPane.showInputDialog("Enter doctor type"));
			if(!valid) {
				printErrorMessage("Invalid input for doctor type");
			}
		}while(!valid);	
	}
	//error message anytime any excepion is caught and wrong
	private static void printErrorMessage(String message) {
		JOptionPane.showMessageDialog(null, message);
	}
	//displaying all appointments
	private static void displayAllAppointments(Map<Integer, Appointment> map,String path) {
		ArrayList<Appointment> appointmentRecords = readFile(path);
		if(!map.isEmpty()){
			String output = "";
			for (Appointment appointment : appointmentRecords) {
		        	output += appointment.format(); 
		    }
		    JOptionPane.showMessageDialog(null,output);
		}else {
			JOptionPane.showMessageDialog(null, "Appointment not found!");		
	}	
	}
	//deleting appointment method
	private static void deleteAppointment(Map<Integer, Appointment> map, String path) {
		String output = "";	
		if(!map.isEmpty()){	
			int key =0;
			for(Map.Entry<Integer, Appointment> display : map.entrySet()) {
				output += "Appointment id" + display.getKey() + display.getValue() + "\n";
			}
			do {
				try {
					 key = Integer.parseInt(JOptionPane.showInputDialog(output + "\nPlease enter the appointment number you would like to delete."));
					 if(key < 1){
						 printErrorMessage("The Appointment number must be selected from the above list");
					 	}
					}catch(NumberFormatException e) {
						printErrorMessage("Key can not be empty value!");
						}
			}while(key == 0);
				if(map.containsKey(key)) {
					map.remove(key);	
				}
			}else {
				JOptionPane.showMessageDialog(null, "Appointment not found!");		
			}
	}	

	//method to display current total appointments, patients and doctors as per requirements
	private static void 	displayCurrentAppointmentsDoctorsPatients(Map<Integer, Appointment> map) {
		if(!map.isEmpty()) {
			int appointmentNumber = map.values().size();
			int patientNumber;
			int doctorNumber;
			
			ArrayList <String> patientArray = new ArrayList<String> ();
			ArrayList <String> doctorArray = new ArrayList<String> ();
			for (Appointment appt:map.values()) {
				String patientName = appt.getPatient().getName();
				String docName = appt.getDoctor().getName();
				if(!patientArray.contains(patientName)) {
					patientArray.add(patientName);
				}
				if(!doctorArray.contains(docName)) {
					doctorArray.add(docName);
				}
			}
			patientNumber = patientArray.size();
			doctorNumber = doctorArray.size();
			JOptionPane.showMessageDialog(null,"Total Appointments " + appointmentNumber
					+ "\nTotal Patients " + patientNumber
					+ "\nTotal Doctors " + doctorNumber);
		}else {
			JOptionPane.showMessageDialog(null, "Appointment not found!");		
		}	
	}
	//method to search the Hashmap by ID 
	private static void searchID(Map<Integer, Appointment> map) {
		Iterator it =null; 
		if(!map.isEmpty()){
			it=  map.entrySet().iterator();
			int key = Integer.parseInt(JOptionPane.showInputDialog("Please enter the appointment number you would like to display."));
			while(it.hasNext()){
				if(!map.isEmpty() && map.containsKey(key)) {
					JOptionPane.showMessageDialog(null, "Appointment ID" + map.get(key));
					break;
				}
			}
		}else {
				JOptionPane.showMessageDialog(null, "Appointment not found!");		
		}
	}
	
	private static void searchByPatientName(Map<Integer, Appointment> map) {
		int index = map.values().size()+1;
		String name = JOptionPane.showInputDialog("Please enter the patient name for the appointment you would like to find.");
		ArrayList <Integer> hitList = new ArrayList <Integer> ();
		for(Appointment appt:map.values()) {
			index--;
			String patientName = appt.getPatient().getName();
			if(patientName.equals(name)) {
				hitList.add(index);
			}
		}
		String output = "";
		for(Integer i: hitList) {
			if(map.containsKey(i)) {
				output += "Appointment ID" + map.get(i);
			}
			output+="\n";
		}
		if(hitList.isEmpty()) {
			String message = "The patient name could not be found.";
			JOptionPane.showMessageDialog(null, message);
		}else {
			JOptionPane.showMessageDialog(null, output);
		}
	}

	private static void searchByDoctorName(Map<Integer, Appointment> map) {
		int index = map.values().size()+1;
		String name = JOptionPane.showInputDialog("Please enter the doctor name for the appointment you would like to find.");
		ArrayList <Integer> hitList = new ArrayList <Integer> ();
		for(Appointment appt:map.values()) {
			index--;
			String doctorName = appt.getDoctor().getName();
			if(doctorName.equals(name)) {
				hitList.add(index);
			}
		}
		String output = "";
		for(Integer i: hitList) {
			if(map.containsKey(i)) {
				output += "Appointment ID" + map.get(i);
			}
			output+="\n";
		}
		if(hitList.isEmpty()) {
			String message = "The patient name could not be found.";
			JOptionPane.showMessageDialog(null, message);
		}else {
			JOptionPane.showMessageDialog(null, output);
		}
	}

	
	private static ArrayList<Appointment> readFile(String path) {
		BufferedReader bufRead = null;	
		try{
			ArrayList<Appointment> appointmentRecords = new ArrayList<Appointment>();
			bufRead = new BufferedReader(new FileReader(new File(path)));
			
			String currentLine = bufRead.readLine();
	       	while (currentLine != null) {
	       		String [] appDetail = currentLine.split("\\|");		
			    int ID = Integer.parseInt(appDetail[0].replaceAll("=", ""));
			    String appDate = appDetail[1];
			    String timeIn = appDetail[2];
			    String timeOut = appDetail[3];
			    int pID = Integer.parseInt(appDetail[4]);
			    String pName = appDetail[5];
			    String pGender = appDetail[6];
			    int pAge = Integer.parseInt(appDetail[7]);
			    String pPhone = appDetail[8];
			    String pDescription = appDetail[9];
			    int dID = Integer.parseInt(appDetail[10]);
			    String dName = appDetail[11];
			    String dGender = appDetail[12];
			    int dAge = Integer.parseInt(appDetail[13]);
			    String dPhone = appDetail[14];
			    String dType = appDetail[15];
			    
			    Appointment newAppt = new Appointment(ID,appDate, timeIn,timeOut,
			    		pID,pName,pGender, pAge, pPhone, pDescription,
			    		dID, dName,dGender, dAge, dPhone, dType);
			    
			    appointmentRecords.add(newAppt);
			    currentLine = bufRead.readLine();
	        }
       	 	bufRead.close();
       	 	return appointmentRecords;
		}catch (Exception e) {
		      e.printStackTrace();
		}
		return null;
	}
	private static void updateMap(Map<Integer,Appointment> map,String path) {
		ArrayList<Appointment> appointmentRecords = readFile(path);
		map.clear();
		
	    int index = 1;
		for (Appointment appointment : appointmentRecords) {
	        	map.put(index++, appointment); 
	    }
	}
	private static void sortByDoctor(Map<Integer,Appointment> map,String path) {
		ArrayList<Appointment> appointmentRecords = readFile(path);
		
	    Collections.sort(appointmentRecords, new Comparator<Appointment>() {
	    	    public int compare(Appointment a1, Appointment a2) {
	    	        return a1.getDoctor().getName().toLowerCase().compareTo(a2.getDoctor().getName().toLowerCase());
	    	    }
	    });
		
	    String output = "";
		for (Appointment appointment : appointmentRecords) {
	        	output += appointment.format(); 
	    }
	    JOptionPane.showMessageDialog(null,output);
	}
	private static void sortByPatient(Map<Integer,Appointment> map,String path) {
		ArrayList<Appointment> appointmentRecords = readFile(path);
		
	    Collections.sort(appointmentRecords, new Comparator<Appointment>() {
	    	    public int compare(Appointment a1, Appointment a2) {
	    	        return a1.getPatient().getName().toLowerCase().compareTo(a2.getPatient().getName().toLowerCase());
	    	    }
	    });
		
	    String output = "";
		for (Appointment appointment : appointmentRecords) {
	        	output += appointment.format(); 
	    }
	    JOptionPane.showMessageDialog(null,output);
	}
}
