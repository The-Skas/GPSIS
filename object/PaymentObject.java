package object;
import java.util.Date;
import framework.GPSIS;
import framework.GPSISObject;

public class PaymentObject  extends GPSISObject{
	
		private int id, refid;
		private String total, name, accnum, expdate, csc;
		
		
		public PaymentObject(){
			
		}
		
		public PaymentObject(int id, int refid, String total, String name, String accnum, String expdate, String csc){
			this.id = id;
			this.refid = refid;
			this.total=total;
			this.name=name;
			this.accnum=accnum;
			this.expdate=expdate;
			this.csc=csc;
		}
		
		public int getRefID(){
			return refid;
		}
		public String getTotal(){
			return total;
		}
		public String getName(){
			return name;
		}
		public String getAccNum(){
			return accnum;
		}
		public String getEX(){
			return expdate;
		}
		public String getCSC(){
			return csc;
		}
	}
