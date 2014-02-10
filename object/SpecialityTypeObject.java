package object;

import framework.GPSISObject;

public class SpecialityTypeObject extends GPSISObject {
	
	private String name;
	private int id, ConID;
	
	public SpecialityTypeObject(){
		
	}
	
	public SpecialityTypeObject(String name, int ConID){
		this.name = name;
		this.ConID = ConID;
	}
	
	public String getName(){
		return name;
	}
	public int getConID(){
		return ConID;
	}
	
}
