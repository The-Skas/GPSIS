package object;
import java.sql.Date;

import framework.GPSIS;
import framework.GPSISObject;


public class ReferralObject extends GPSISObject{
	private Date dateMade;
	private String docName;
	private int conID,patID,payID,invID;
	private boolean invPaid;
	
	public ReferralObject(){
		
	}
	
	public ReferralObject(int id,Date dateMade, String docName, int conID, int patID, int payID, int invID, boolean invPaid){
		
	}
	
	public Date getDate(){
		return dateMade;
	}
	public String getDocName(){
		return docName;
	}
	public int getConID(){
		return conID;
	}
	public int getPatID(){
		return patID;
	}
	public int getPayID(){
		return payID;
	}
	public int getInvID(){
		return invID;
	}
	public boolean isInvPaid(){
		return invPaid;
	}
}
