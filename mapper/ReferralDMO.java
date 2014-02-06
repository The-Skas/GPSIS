package mapper;
/** ReferralDMO
 * This Data Mapper contains all of the methods concerned with the Referall Table.
 * There are no many-to-many relations with other Entities.
 */
import framework.GPSISDataMapper;

import object.ReferralObject;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Set;
import java.util.HashSet;

public class ReferralDMO extends GPSISDataMapper<ReferralObject> 
{   
	
	// stores the only instance of this DataMapper
	private static ReferralDMO instance;
	
	/** ReferralDMO Constructor 
	 * This is Private as part of a Singleton implementation.
	 * @param tableName
	 */
    private ReferralDMO(String tableName)
    {
        this.tableName = tableName;
    }    
    
    /** getInstance
     * returns the only instance of the ReferralDMO
     * @return
     */
    public static ReferralDMO getInstance() 
    {
        if(instance == null)
            instance = new ReferralDMO("Referral");
        return instance;
    }
        
    /** getAll
     * return a Set of all Referrals
     */
    public Set<ReferralObject> getAll()
    {
        return getAllByProperties(new SQLBuilder());
    }
    /** getById 
     * @param id the id of the Referral to retrieve
     * @return a Referral object that relates to the id
     */
    public ReferralObject getById(int id)
    {
        return this.getByProperties(new SQLBuilder("id", "=", ""+id));
    }
    
    /** getByProperties
     * Returns the first Referral object matching the criteria
     * @param query an SQLBuilder query
     * @return the first Referral object in the ResultSet
     */
    public ReferralObject getByProperties(SQLBuilder query) 
    {
        try 
        {
            ResultSet res = GPSISDataMapper.getResultSet(query, this.tableName);            
            
            if (res.next()) // if found, create a the Referral object 
            {
            	this.buildReferral(res);
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
    public Set<ReferralObject> getAllByProperties(SQLBuilder query) 
    {
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
    
    /** buildReferral
     * builds a Staff Member object from the given Result Set. Used as a helper method in retrieving Referrals from the Database
     * Returns the correct object type (Receptionist or MedicalReferral) and includes their temp contract if needed
     * @param res
     * @return a complete Staff Member
     * @throws SQLException
     */
    private ReferralObject buildReferral(ResultSet res) throws SQLException
    {
    	if (res != null) // if found, create a the Referral object 
        {
  
    					new ReferralObject (res.getInt("id"),
    								res.getDate("date_made"),
    								res.getString("doctors_name"),
    								res.getInt("consultant_id"),
    								res.getInt("patient_id"),
    								res.getInt("payment_id"),
    								res.getInt("invoice_id"),
    								res.getBoolean("invoice_paid"));
    			
    	 				return null;
    	 				
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
    public void put(ReferralObject o) 
 
    {
       SQLBuilder sql = new SQLBuilder("id","=",""+o.getId())
                .SET("date_made","=",""+o.getDate())
                .SET("doctors_name", "=", ""+o.getDocName())
                .SET("consultant_id","=",""+o.getConID())
                .SET("patient_id", "=",""+o.getPatID())
                .SET("payment_id", "=", ""+o.getPayID())
                .SET("invoice_id", "=","" +o.getInvID())
       			.SET("invoice_paid", "=", ""+o.isInvPaid());
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
		Date dt = new Date(0);
		System.out.print(dt.toString());
		
    	ReferralDMO referralDMO = ReferralDMO.getInstance();
    	ReferralObject r = new ReferralObject(2, dt, "Matt", 3, 4,6,8,false);
    	referralDMO.put(r);
    }

}
