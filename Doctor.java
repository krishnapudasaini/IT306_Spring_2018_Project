package IT306_Spring_2018_Project;
/**
* @author Team Power Rangers(Team 8) Krishna Pudasaini, Susmita Gautam, Somayeh Ameli
* Professor Setareh
* Class: IT 306-001
* Project Phase 5 (Final Implementation)
* May 01, 2018
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
 
/*
@return dType
get method for type instance variable
*/
public String getDType() { return this.dType;}

/*
@return numOfDoctor
get method for numofdoctor instance variable
*/
public static int getNumOfDoctor() { return numOfDoctor;}

/*
@param type
@retrun boolean 
set method for dType instance variable 
ensures that the type is not null
*/
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

/* @return output 
toString method to output this dType
*/
public String toString()
{
	String output = " ";
	output += super.toString() + "|" + getDType() +"\n";	
	return output;
}
/*
@return output
format method  to print well formated report
for doctor information
*/
public String format()
{
	String output = "\nDOCTOR'S INFORMATION => ";
	output += super.format() 
			+ "TYPE: " + getDType()+ "\n";	
	return output;
}
}

