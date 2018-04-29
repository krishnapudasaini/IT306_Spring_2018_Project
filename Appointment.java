package IT306_Spring_2018_Project;
/**
* @author Team Power Rangers(Team 8)
* Professor Setareh
* Class: IT 306-001
* Project Phase 5 (Final Implementation)
* April 22, 2018
* The Appointment class contains the information about appointment. This class also contains a default
* constructor, specific constructor, accessor, mutator, and toString method. 
*/
public class Appointment {
	//instance variables for the appointment class
	private static int id = 1;
	private int ID;
	private String appDate;
	private String appTimeIn;
	private String appTimeOut;
	public static int numOfAppointments;
	private  Doctor doctor;
	private  Patient patient;
	
//default constructor for appointment class
public Appointment()
{
	ID = id;
	id++;
	++numOfAppointments;
	this.doctor = new Doctor();
	this.patient = new Patient();	
}
//specific constructor for appointment class
public Appointment(int ID,String appDate, String appTimeIn, String appTimeOut,int pID, 
					String pName, String pGender,int pAge, String pPhoneNum, String description,
					int dID, String dName, String dGender, int dAge, String dPhoneNum, String dType)
{
	this.ID = ID;
	this.appDate = appDate;
	this.appTimeIn = appTimeIn;
	this.appTimeOut = appTimeOut;
	this.patient = new Patient(pID, pName, pGender, pAge, pPhoneNum, description);
	this.doctor = new Doctor(dID, dName, dGender, dAge, dPhoneNum, dType);
}

public int getID(){ return this.ID; }


//get method for appointment date variable
public String getAppDate(){ return this.appDate; }

//get method for appointment time in
public String getAppTimeIn(){ return this.appTimeIn;	}

//get method for appointment time out
public String getAppTimeOut(){ return this.appTimeOut; }

//get method for number of appointments
public static int getNumOfAppointments(){ return numOfAppointments; }

//get method for patient
public Patient getPatient() { return this.patient; }

//get method for doctor
public Doctor getDoctor() { return this.doctor;}

public boolean setID(int id)
{
		ID = id;
		return true;
}
//set method for appointment date to validate the time input is in the correct format
public boolean setAppDate(String appDate)
{	
	if (appDate == null || appDate.length() != 10) return false;
	if (!appDate.substring(2, 3).equals("/")) return false;
	if (!appDate.substring(5, 6).equals("/")) return false;
	int month = validateTimeNum(appDate.substring(0, 2));
	int day = validateTimeNum(appDate.substring(3, 5));
	int year = validateTimeNum(appDate.substring(6));
	if (month < 0 || month > 12) return false;
	if (day < 0 || day > 32) return false;
	if (year < 2018 || year > 2020) return false;
	this.appDate = appDate.trim();
	return true;
}

//get method for appointment time in to validate the time input is in the correct format
public boolean setAppTimeIn(String timeIn)
{ 
if (timeIn.length() != 5) return false;
if (!timeIn.substring(2, 3).equals(":")) return false;
int hour = validateTimeNum(timeIn.substring(0, 2));
int minute = validateTimeNum(timeIn.substring(3));
if (hour < 0 || hour >= 24) return false;
if (minute < 0 || minute >= 60) return false;
appTimeIn = timeIn.trim();
return true;
}
 
public int validateTimeNum(String time) {
    try {
        int number = Integer.valueOf(time);
        return number;
    } catch (NumberFormatException e) {
        return -1;
    }
}

//set method for setting appointment time out to validate the time input is in the correct format
public boolean setAppTimeOut(String timeOut) 
{
	if (timeOut.length() != 5) return false;
	if (!timeOut.substring(2, 3).equals(":")) return false;
	int hour = validateTimeNum(timeOut.substring(0, 2));
	int minute = validateTimeNum(timeOut.substring(3));
	if (hour < 0 || hour >= 24) return false;
	if (minute < 0 || minute >= 60) return false;
	appTimeOut = timeOut.trim();
	return true;
}

//setting patient object
public void setPatient() { this.patient = new Patient();}

//setting patient description info
public void setPatient(int pID, String pName, String pGender, int pAge, String pPhoneNum, String description )
{
	this.patient = new Patient(pID, pName, pGender, pAge, pPhoneNum, description);
}

//setting doctor object
public void setDoctor() { this.doctor = new Doctor();}

//setting doctor info
public void setDoctor(int dID, String dName, String dGender, int dAge, String pPhoneNum, String dType)
{
	this.doctor = new Doctor(dID, dName, dGender, dAge, pPhoneNum, dType); 
}

//to string method  to print everything
public String toString()
{
	String output = "";
    	output += "|"+ getAppDate()
    			+ "|"+ getAppTimeIn()
    			+ "|"+ getAppTimeOut()
    			+ this.getPatient() 
    			+ this.getDoctor();

	return output;	
}
public String format()
{
	String output = "\nAppointment Information\n***************************";
    	output += "\nAppointment Date: "+ getAppDate()
    			+ "\n Appointment In Time: "+ getAppTimeIn()
    			+ "\n Appointment Out Time: "+ getAppTimeOut()
    			+ this.getPatient().format() 
    			+ this.getDoctor().format();

	return output;	
}
}
