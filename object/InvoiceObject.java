package object;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import framework.GPSIS;
import framework.GPSISObject;

public class InvoiceObject  extends GPSISObject{
	
		private int id, refid;
		private double amount;
		private int conid, ispaid;
		private Date date2;
		
		public InvoiceObject(){
			
		}
		
		public InvoiceObject(int id, int refid, double amount, int conid,String date, int ispaid){
			this.id = id;
			this.refid = refid;
			this.amount = amount;
			this.conid = conid;
			this.ispaid = ispaid;
			// Turn string to date 
		   String s = date;
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			try {
				Date dt = formatter.parse(s);
				date2 = java.sql.Date.valueOf(s);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		 
		}
		//Get methods for variables
		
		public int getRefID(){
			return refid;
		}
		public double getAmount(){
			return amount;
		}
		public int getConID(){
			return conid;
		}
		public int getIsPaid(){
			return ispaid;
		}
		public Date getDate(){
			return date2;
		}
		
}
