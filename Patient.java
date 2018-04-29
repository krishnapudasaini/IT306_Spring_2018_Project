package IT306_Spring_2018_Project;
/**
* @author Team Power Rangers(Team 8) 
* Professor Setareh
* Class: IT 306-001
* Project Phase 5 (Final Implementation)
* April 22, 2018
* The Patient class contains the information about patient. This class also inherits form person class.
* This class also has a constructor, a specific constructor, accessors, mutator, and toString method. 
*/
//specifying inheritance with Person class
public class Patient extends Person {
//instance variables for 	
	private String description;
	public static int numOfPatients;	

//default constructor	
public Patient()
{
++numOfPatients;
}
//specific constructor
public Patient(int ID, String name, String gender, int age, String phoneNum, String description)
{
	super(ID, name, gender, age, phoneNum);
	this.description = description;
	++numOfPatients;
}

//get method for description instance variable
public String getDescription() { return this.description;}

//get method for counter
public static int getNumOfPatients() { return numOfPatients;}

//set method for description instance variable 
//ensures that the description is not null
public boolean setDescription(String description)
{
	if(description == null || description.equals("") )
		return false;
		this.description = description.trim();
		return true;	
}

//toString method to output this DDC's contents of description
public String toString()
{
	String output = "";
	output += super.toString() + "|" + getDescription().trim();
	return output;
}
public String format()
{
	String output = "\nPATIENT INFORMATION: ";
	output += super.format() 
			+ "\nPatient description" + getDescription();
	return output;
}
}
