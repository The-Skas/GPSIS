package mapper;
/** SpecialityDMO
 * This Data Mapper contains all of the methods concerned with the Staff Member Table.
 * There are many-to-many relations with other Entities and therefore there are more complex methods in here.
 */
import exception.EmptyResultSetException;
import framework.GPSISDataMapper;
import object.Receptionist;
import object.Speciality;
import object.TaxForm;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.HashSet;

public class SpecialityDMO extends GPSISDataMapper<Speciality> 
{   
	// stores the only instance of this DataMapper
	private static SpecialityDMO instance;
	
	/** SpecialityDMO Constructor 
	 * This is Private as part of a Singleton implementation.
	 * @param tableName
	 */
    private SpecialityDMO(String tableName)
    {
        this.tableName = tableName;
    }    
    
    /** getInstance
     * returns the only instance of the SpecialityDMO
     * @return
     */
    public static SpecialityDMO getInstance() 
    {
        if(instance == null)
            instance = new SpecialityDMO("Speciality");
        return instance;
    }
    
    /** getByProperties
     * Returns the first Speciality object matching the criteria
     * @param query an SQLBuilder query
     * @return the first Speciality object in the ResultSet
     */
    public Speciality getByProperties(SQLBuilder query) throws EmptyResultSetException
    {
        try 
        {
            ResultSet res = GPSISDataMapper.getResultSet(query, this.tableName);            
            
            if (res.next()) // if found, create a the Speciality object 
            {
            	return new Speciality(res.getInt("id"), res.getString("name"));
            }

        } 
        catch (SQLException e) 
        {
            e.printStackTrace();
        }
        // throw Exception because of empty result set
        throw new EmptyResultSetException();
    }
        
    /** getAllByProperties
     * returns a Set of Specialitys that match the given criteria
     * @param query an SQLBuild query
     * @return a Set containing all of the Specialitys that match the given criteria
     */
    public List<Speciality> getAllByProperties(SQLBuilder query) throws EmptyResultSetException
    {
          List<Speciality> Specialitys = new ArrayList<>();
          
          try 
          {            
            ResultSet res = GPSISDataMapper.getResultSet(query, this.tableName);            
            while(res.next()) // While there's a Speciality, create a the Speciality object and add it to a Set
            {
                Specialitys.add(new Speciality(res.getInt("id"), res.getString("name")));
            }

        } 
        catch (SQLException e) 
        {
            e.printStackTrace();
        }
        if (Specialitys.isEmpty())
        	throw new EmptyResultSetException();
        else
        	return Specialitys;
    }    

    /** put
     * Put a given Speciality object onto the Database. Similar to the put method in a Map data structure. Used for INSERT and UPDATE
     * @param o The Speciality object
     */
    public void put(Speciality o) 
    {
    	SQLBuilder sql = new SQLBuilder("id","=",""+o.getId())
                .SET("username","=",""+o.getName());
        try 
        {
            putHelper(sql, this.tableName, o);
        } 
        catch (SQLException e) 
        {
        	System.err.println(e.getMessage());
        }

    }

	@Override
	public void removeById(int id) {
		// TODO Auto-generated method stub
		
	}
}
