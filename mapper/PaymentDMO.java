package mapper;
import module.Referral.*;
import framework.GPSISDataMapper;
import object.PaymentObject;
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

//Inherits GPSISDataMapper<PaymentObject>'s methods
public class PaymentDMO extends GPSISDataMapper<PaymentObject> 
{   
	// stores the only instance of this DataMapper
	private static PaymentDMO instance;
	
	//PatmentDMO Constructor 
    private PaymentDMO(String tableName) {
        this.tableName = tableName;
    }    
    //getInstance
    public static PaymentDMO getInstance()  {
        if(instance == null)
            instance = new PaymentDMO("Payment");
        return instance;
    }
        
   
    //Returns a Set of all Payments
  	//getAll
    public Set<PaymentObject> getAll() {
        return getAllByProperties(new SQLBuilder());
    }
    
    //return a Payment object that relates to the id
    //getById 
    public PaymentObject getById(int id) {
        return this.getByProperties(new SQLBuilder("id", "=", ""+id));
    }
    
    //getByProperties
    //Return the first Payment object in the ResultSet
    public PaymentObject getByProperties(SQLBuilder query){
        try{
            ResultSet res = GPSISDataMapper.getResultSet(query, this.tableName);            
            
            if (res.next()) // if found, create a the Payment object 
            {
            	return this.buildPayment(res);
            }
            
        } 
        catch (SQLException e) 
        {
           JOptionPane.showMessageDialog(null, "No Search Results Matching Criteria");
        }
        return null;
    }
        
    //Returns a Set containing all of the Payments that match the given criteria
    //getAllByProperties
    public Set<PaymentObject> getAllByProperties(SQLBuilder query){
    	//Makes set
          Set<PaymentObject> Payment = new HashSet<>();
          try{            
            ResultSet res = GPSISDataMapper.getResultSet(query, this.tableName);            
            while(res.next()) // While there's a Payment, create a the Payment object and add it to a Set
            {
            	Payment.add(this.buildPayment(res));
            }

        } catch (SQLException e)   {
        	//Pop-up message if not results found
        	JOptionPane.showMessageDialog(null, "No Search Results Matching Criteria");
        }
        return Payment;
    }
    
  //buildPayment
  //builds a Payment object from the given Result Set. Used as a helper method in retrieving Payments from the Database
    
    private PaymentObject buildPayment(ResultSet res) throws SQLException
    {
    	if (res != null) // if found, create a the Payment object 
        {
    				return new PaymentObject(
    								res.getInt("id"),
    								res.getInt("referral_id"),
    								res.getString("Amount"),
    								res.getString("name_on_card"),
    								res.getString("account_num"),
    								res.getString("exp_date"),
    								res.getString("csc_num"));
    			
        }
        else 
        {
           JOptionPane.showMessageDialog(null,"EMPTY SET - No Referral Found matching the criteria");
        }
		return null;
    }
    
   //removeById
   //Remove a Payment from the database given its Id
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
    
    //Removes all Payments from the database that match the given criteria
  //removeByProperty
    public void removeByProperty(SQLBuilder query) throws SQLException 
    {
        GPSISDataMapper.removeByPropertyHelper(query, this.tableName);        
    }

   //put
   //Put a given Payment object onto the Database. Used for INSERT and UPDATE
    public void put(PaymentObject o){
     
       SQLBuilder sql = new SQLBuilder("id","=",""+o.getId())
                .SET("referral_id","=",""+o.getRefID())
                .SET("Amount", "=", ""+o.getTotal())
                .SET("name_on_card","=",""+o.getName())
                .SET("account_num", "=",""+o.getAccNum())
                .SET("exp_date", "=", ""+o.getEX())
                .SET("csc_num", "=","" +o.getCSC());
        try 
        {
            putHelper(sql, this.tableName, o);
        } 
        catch (SQLException e) 
        {
        	JOptionPane.showMessageDialog(null,e.getMessage());
        }

    }
}
