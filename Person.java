/**
* @author Team Power Rangers(Team 8) Krishna Pudasaini, Susmita Gautam, Somayeh Ameli
* Professor Setareh
* Class: IT 306-001
* Project Phase 5 (Final Implementation)
* May 01, 2018
* The Person class contains the information about each person, such as patient and doctor.
* This class is a super class of patient and doctor classes. This class also has a 
* constructor, a specific constructor, accessors, mutator, and toString method. 
*/
package IT306_Spring_2018_Project;

public class Person {
	private static int id = 200;
	private int ID;
	public String name;
	private String gender;
	private int age;
	private String phoneNum;
	private static int numOfPeople;
//Default constructor
public Person ()
{
	ID = id;
	id++;
++numOfPeople;
}

/**
 * @param ID passing the person ID 
   @param name passing the person name 
   @param gender passing the person gender 
   @param age passing in the person age
   @param phoneNum passing the phone number 
*/
public Person(int ID, String name, String gender, int age, String phoneNum)
{
	this();
	this.ID = ID;
	this.name = name;
	this.gender = gender;
	this.age = age;
	this.phoneNum = phoneNum;	
}

public int getID(){ return this.ID; }

public String getName() { return this.name; }

public String getGender(){ return this.gender; }

public int getAge() { return this.age; }

public String getPhoneNum() { return this.phoneNum; }

public static int getNumOfPeople() { return numOfPeople;}

/** @param id passing in the person ID 
@return boolean
set method for ID instance variable 
ensures that the ID is not null
*/
public boolean setID(int id)
{
	if(id < 1 )
	{
		return false;
	}
	else
	{
		ID = id;
		return true;
	}
}

/** @param name passing in the person name 
@return boolean
set method for name instance variable 
ensures that the name is not null
*/
public boolean setName(String name)
{
	if(name == null || name.equals(""))
		return false;
	for(int i=0; i< name.length(); i++){
		if(Character.isDigit(name.charAt(i))) return false;
	}
		this.name = name.trim();
		return true;
}
/** @param gender passing in the person gender
@return boolean
set method for gender instance variable 
ensures that the gender is not null
*/
public boolean setGender(String gender)
{
	if(gender == null || gender.equals("")) return false;
	if(gender.equalsIgnoreCase("male") ||gender.equalsIgnoreCase("female") 
		||gender.equalsIgnoreCase("not spacified")) 
	{
		this.gender = gender.trim();
		return true;	
	}else {
		return false;
	}
}
/** @param age passing the person age
@return boolean
set method for age instance variable 
ensures that the age is not null
*/ 
public boolean setAge(int age)
{
	if(age < 0 || age > 110)
	{
		return false;
	}
	else
	{
		this.age = age; 
		return true;
	}
}
/** @param phone passing in the phone number
@return boolean
set method for phoneNum instance variable 
ensures that the phoneNum is not null
*/
public boolean setPhoneNum(String phone)
{
	char[] phoneNumber = phone.toCharArray();
	if (phoneNumber.length == 13) {
		for (int i = 0; i < phoneNumber.length; i++) {
			if (i == 0) {
				if (phoneNumber[i] != ("(").charAt(0)) {
					return false;
				}
			} else if (i == 4) {
				if (phoneNumber[i] != (")").charAt(0)) {
					return false;
				}
			} else if (i == 8) {
				if (phoneNumber[i] != ("-").charAt(0)) {
					return false;
				}
			} else if (!Character.isDigit(phoneNumber[i])) {
				return false;
			}
		}
		phoneNum = phone.trim();
		return true;
	}
	return false;
}
/**
 * @ return output 
 * toString method to output this DDC's contents of ID, name, gender, age, phonenumber      
*/
public String toString()
{
	String output = "";
	output += "|" + this.ID 
			+ "|" + getName() 
			+ "|" + getGender()
			+ "|"+ getAge() 
			+ "|" + getPhoneNum();
	return output;
}
/**
@return output
format method  to print well formated report
for person information
*/
public String format()
{
	String output = "";
	output += "ID: " + this.ID +" || "
			+ "NAME: " + getName() +" || "
			+ "GENDER: " + getGender()+" || "
			+ "AGE: "+ getAge() +" || "
			+ "PHONE: " + getPhoneNum()+" || ";
	return output;
}
}
