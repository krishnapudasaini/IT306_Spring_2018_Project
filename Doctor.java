package IT306_Spring_2018_Project;
/**
* @author Team Power Rangers(Team 8) Krishna Pudasaini, Susmita Gautam, Somayeh Ameli
* Professor Setareh
* Class: IT 306-001
* Project Phase 4 (Preliminary Implementation)
* April 22, 2018
* The Doctor class contains the information about doctor. This class also inherits form person class.
* This class also has a constructor, a specific constructor, accessors, mutator, and toString method. 
*/
//doctor ddc that inherits from Person class
public class Doctor extends Person{
	private String dType;
	private static int numOfDoctor;

//default constructor	
public Doctor()
{
super();	
++numOfDoctor;
}

//specific constructor
public Doctor(int ID, String name, String gender, int age, String phoneNum, String dType)
{
super(ID, name,gender, age, phoneNum);	
this.dType = dType;
++numOfDoctor;
}
 
//get method for type instance variable
public String getDType() { return this.dType;}

//get method for numofdoctor instance variable
public static int getNumOfDoctor() { return numOfDoctor;}

//setter method for type
public boolean setDType(String type)
{
	if(type == null || type.equals(""))
	{
	return false;
	}else {
	dType = type.trim();
	return true;
	}
}

//to string method to output doctor type
public String toString()
{
	String output = " ";
	output += super.toString() + "|" + getDType() +"\n";	
	return output;
}
public String format()
{
	String output = "\nDOCTOR'S INFORMATION:";
	output += super.format() 
			+ "\nDoctor's Type" + getDType()+ "\n";	
	return output;
}
}

