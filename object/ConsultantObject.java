package object;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import framework.GPSIS;
import framework.GPSISObject;

public class ConsultantObject extends GPSISObject{
		private int id;
		private String fname, lname, title, address, email, contactnum, speciality;
		private double price;
		
		public ConsultantObject(){
			
		}
		
		public ConsultantObject(String title, String fname, String lname,String address, String email, String contactnum,
				double price){
			this.id= getId();
			this.title = title;
			this.fname=fname;
			this.lname = lname;
			this.address = address;
			this.email = email;
			this.contactnum= contactnum;
			this.speciality = speciality;
			this.price = price;
			
		}
		
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
		
		
		
}
