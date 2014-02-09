package object;
import java.util.Date;

import framework.GPSIS;
import framework.GPSISObject;

public class ReferralObject extends GPSISObject{
	private int id;
	private Date dateMade;
	private String docName;
	private int conID,patID,payID,invID;
	private int invPaid;
	
	public ReferralObject(){
		
	}
	
	public ReferralObject(Date dateMade, String docName, int conID, int patID, int invID, int invPaid, int payID){
		this.id = id;
		this.dateMade = dateMade;
		this.docName = docName;
		this.conID=conID;
		this.patID=patID;
		this.payID=payID;
		this.invID=invID;
		this.invPaid=invPaid;
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
	public int isInvPaid(){
		return invPaid;
	}
}
