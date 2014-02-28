package object;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import framework.GPSIS;
import framework.GPSISObject;
//Extending GPSIS object so methods can be inherited (this is so we have to write less code, makes everything 
//-work neater and fit together properly)
public class ConsultantObject extends GPSISObject{
		private String fname, lname, title, address, email, contactnum, speciality, accName;
		private double price;
		private int accNum, sortCode;
		
		public ConsultantObject(String title, String fname, String lname,String address, String email, String contactnum,
				double price, String AccName, int AccNum, int SortCode){
			
			//Setting variableswhen constructed
			this.id = id;
			this.title = title;
			this.fname=fname;
			this.lname = lname;
			this.address = address;
			this.email = email;
			this.contactnum= contactnum;
			this.speciality = speciality;
			this.price = price;
			this.accName = AccName;
			this.accNum = AccNum;
			this.sortCode = SortCode;
			
		}
		
		public ConsultantObject(int id, String title, String fname, String lname,String address, String email, String contactnum,
				double price, String AccName, int AccNum, int SortCode){
			//Setting variableswhen constructed
			this.id = id;
			this.title = title;
			this.fname=fname;
			this.lname = lname;
			this.address = address;
			this.email = email;
			this.contactnum= contactnum;
			this.speciality = speciality;
			this.price = price;
			this.accName = AccName;
			this.accNum = AccNum;
			this.sortCode = SortCode;
			
		}
		
		
		//Get methods
		public String getFName(){
			return fname;
		}
		public String getLName(){
			return lname;
		}
		public String getTitle(){
			return title;
		}
		public String getAddress(){
			return address;
		}
		public String getEmail(){
			return email;
		}
		public String getNum(){
			return contactnum;
		}
		public double getPrice(){
			return price;
		}
		public String getAccName(){
			return accName;
		}
		public int getAccNum(){
			return accNum;
		}
		public int getSortCode(){
			return sortCode;
		}
		
		
		
}
