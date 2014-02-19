package mapper;
import module.Referral.*;
import framework.GPSISDataMapper;
import object.ReferralObject;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Set;
import java.util.HashSet;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

//Inherits GPSISDataMapper<ReferralObject>'s methods
public class ReferralDMO extends GPSISDataMapper<ReferralObject>{   
	// stores the only instance of this DataMapper
	private static ReferralDMO instance;
	
	//ReferralDMO Constructor
    private ReferralDMO(String tableName){
        this.tableName = tableName;
    }    
    
   //getInstance
    public static ReferralDMO getInstance(){
        if(instance == null)
            instance = new ReferralDMO("Referral");
        return instance;
    }  
   
   //Returns a Set of all Referrals
  //getAll
    public Set<ReferralObject> getAll() {
        return getAllByProperties(new SQLBuilder());
    }
    //getById 
    //Returns a Referral object that relates to the id
    public ReferralObject getById(int id)
    {
        return this.getByProperties(new SQLBuilder("id", "=", ""+id));
    }
    
    //getByProperties
    //Returns the first Referral object matching the criteria
    public ReferralObject getByProperties(SQLBuilder query){
        try 
        {
            ResultSet res = GPSISDataMapper.getResultSet(query, this.tableName);            
            
            if (res.next()) // if found, create a the Referral object 
            {
            	return this.buildReferral(res);
            }
            
        } 
        catch (SQLException e) 
        {
            e.printStackTrace();
        }
        return null;
    }
        
    //getAllByProperties
    //Returns a Set of Referrals that match the given criteria
    public Set<ReferralObject> getAllByProperties(SQLBuilder query){
          Set<ReferralObject> Referral = new HashSet<>();
          
          try 
          {            
            ResultSet res = GPSISDataMapper.getResultSet(query, this.tableName);            
            while(res.next()) // While there's a Referral, create a the Referral object and add it to a Set
            {
                Referral.add(this.buildReferral(res));
            }

        } 
        catch (SQLException e) 
        {
            e.printStackTrace();
        }
        return Referral;
    }
    
    //builds a Staff Member object from the given Result Set. Used as a helper method in retrieving Referrals from the Database
    //buildReferral
    private ReferralObject buildReferral(ResultSet res) throws SQLException
    {
    	if (res != null) // if found, create a the Referral object 
        {
    		System.out.println(res.getInt("id"));
  
    				return	new ReferralObject(
    								res.getInt("id"),
    								res.getDate("date_made"),
    								res.getString("doctors_name"),
    								res.getInt("consultant_id"),
    								res.getInt("patient_id"),
    								res.getInt("invoice_paid"));
        }
        else 
        {
            JOptionPane.showMessageDialog(null, "EMPTY SET - No Referral Found matching the criteria");
        }
		return null;
    }
    
    //Remove a Referral from the database given its Id
    //removeById
    public void removeById(int id){
        try 
        {
            removeByProperty(new SQLBuilder("id","=",""+id));
        } 
        catch (SQLException e) 
        {
        	System.err.println(e.getMessage());
        }
    }
   
    //WARNING: Removes all Referrals from the database that match the given criteria
    //removeByProperty
    public void removeByProperty(SQLBuilder query) throws SQLException 
    {
        GPSISDataMapper.removeByPropertyHelper(query, this.tableName);        
    }

    //put
    //Put a given Referral object onto the Database. Used for INSERT and UPDATE
    public void put(ReferralObject o){
    //To add current date as string 
     String s = new SimpleDateFormat("yyyy-MM-dd").format(o.getDate());
  
       SQLBuilder sql = new SQLBuilder("id","=",""+o.getId())
                .SET("date_made","=",""+s)
                .SET("doctors_name", "=", ""+o.getDocName())
                .SET("consultant_id","=",""+o.getConID())
                .SET("patient_id", "=",""+o.getPatID())
                //So they have the same id as the referral itself
       			.SET("invoice_paid", "=", ""+o.isInvPaid());
        try 
        {
            putHelper(sql, this.tableName, o);
        } 
        catch (SQLException e) 
        {
        	JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
}
