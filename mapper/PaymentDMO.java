package mapper;
/** ReferralDMO
 * This Data Mapper contains all of the methods concerned with the Referall Table.
 * There are no many-to-many relations with other Entities.
 */

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

public class PaymentDMO extends GPSISDataMapper<PaymentObject> 
{   
	
	// stores the only instance of this DataMapper
	private static PaymentDMO instance;
	
	/** ReferralDMO Constructor 
	 * This is Private as part of a Singleton implementation.
	 * @param tableName
	 */
    private PaymentDMO(String tableName)
    {
        this.tableName = tableName;
    }    
    
    /** getInstance
     * returns the only instance of the PaymentDMO
     * @return
     */
    public static PaymentDMO getInstance() 
    {
        if(instance == null)
            instance = new PaymentDMO("Payment");
        return instance;
    }
        
    /** getAll
     * return a Set of all Payments
     */
    public Set<PaymentObject> getAll()
    {
        return getAllByProperties(new SQLBuilder());
    }
    /** getById 
     * @param id the id of the Referral to retrieve
     * @return a Referral object that relates to the id
     */
    public PaymentObject getById(int id)
    {
        return this.getByProperties(new SQLBuilder("id", "=", ""+id));
    }
    
    /** getByProperties
     * Returns the first Referral object matching the criteria
     * @param query an SQLBuilder query
     * @return the first Referral object in the ResultSet
     */
    public PaymentObject getByProperties(SQLBuilder query) 
    {
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
        
    /** getAllByProperties
     * returns a Set of Referrals that match the given criteria
     * @param query an SQLBuild query
     * @return a Set containing all of the Referrals that match the given criteria
     */
    public Set<PaymentObject> getAllByProperties(SQLBuilder query) 
    {
          Set<PaymentObject> Payment = new HashSet<>();
          
          try 
          {            
            ResultSet res = GPSISDataMapper.getResultSet(query, this.tableName);            
            while(res.next()) // While there's a Referral, create a the Referral object and add it to a Set
            {
            	Payment.add(this.buildReferral(res));
            }

        } 
        catch (SQLException e) 
        {
            e.printStackTrace();
        }
        return Payment;
    }
    
    /** buildReferral
     * builds a Staff Member object from the given Result Set. Used as a helper method in retrieving Referrals from the Database
     * Returns the correct object type (Receptionist or MedicalReferral) and includes their temp contract if needed
     * @param res
     * @return a complete Staff Member
     * @throws SQLException
     */
    private PaymentObject buildReferral(ResultSet res) throws SQLException
    {
    	if (res != null) // if found, create a the Referral object 
        {
  
    				return new PaymentObject(res.getInt("id"),
    								res.getInt("referral_id"),
    								res.getString("Amount"),
    								res.getString("name_on_card"),
    								res.getString("account_num"),
    								res.getString("exp_date"),
    								res.getString("csc_num"));
    			
        }
        else 
        {
            System.err.println("EMPTY SET - No Referral Found matching the criteria");
        }
		return null;
    }
    
    /** removeById
     * Remove a Referral from the database given its Id
     * @param id the id of the Referral to remove     * 
     */
    public void removeById(int id) 
    {
        try 
        {
            removeByProperty(new SQLBuilder("id","=",""+id));
        } 
        catch (SQLException e) 
        {
        	System.err.println(e.getMessage());
        }
    }
    
    /** removeByProperty
     * WARNING: Removes all Referrals from the database that match the given criteria
     * @param query the criteria to match
     * @throws SQLException
     */
    public void removeByProperty(SQLBuilder query) throws SQLException 
    {
        GPSISDataMapper.removeByPropertyHelper(query, this.tableName);        
    }

    /** put
     * Put a given Referral object onto the Database. Similar to the put method in a Map data structure. Used for INSERT and UPDATE
     * @param o The Referral object
     */
    public void put(PaymentObject o) 
 
    {
     
       SQLBuilder sql = new SQLBuilder("id","=",""+o.getId())
                .SET("referral_id","=",""+o.getRefID())
                .SET("Amount", "=", ""+o.getTotal())
                .SET("name_on_card","=",""+o.getName())
                .SET("account_num", "=",""+o.getAccNum())
                .SET("exp_date", "=", ""+o.getEX())
                .SET("csc_num", "=","" +o.getCSC());
        try 
        {
            putHelper(sql, this.tableName);
        } 
        catch (SQLException e) 
        {
        	System.err.println(e.getMessage());
        }

    }
   
	public static void main(String[] args)
    {
		
		PaymentDMO paymentDMO = PaymentDMO.getInstance();
		GPSISDataMapper.connectToDatabase();
	
		//have to convert boolean to tiny int
    	PaymentObject r = new PaymentObject(223, 323, "t3odtal","n3aame","accdn3um","exda", "csc");
    	paymentDMO.put(r);
    	
    	Referral refer = new Referral();
		refer.setVisible(true);
		refer.setTitle("Referral");
		refer.setSize(295, 160);
		//Closes all windows after referral main window is closed
		refer.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		refer.setResizable(false);
    	
    }
    

}
