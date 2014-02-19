package mapper;
import module.Consultant.*;
import exception.EmptyResultSetException;
import framework.GPSISDataMapper;
import object.ConsultantObject;
import object.Register;
import object.Speciality;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Set;
import java.util.HashSet;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

//Inheriting from GPSISDataMapper<ConsultantObject>
public class ConsultantDMO extends GPSISDataMapper<ConsultantObject>{
	
	// stores the only instance of this DataMapper
	private static ConsultantDMO instance;
	
	 //This is Private as part of a Singleton implementation.
	 //@param tableName
    private ConsultantDMO(String tableName){
    	
        this.tableName = tableName;
    }    
    
   //getInstance
   //returns the only instance of the ConsultantDMO
    public static ConsultantDMO getInstance(){
        if(instance == null)
            instance = new ConsultantDMO("Consultant");
        return instance;
    }
       
    //return a Set of all Consultants
    //getAll
    public List<ConsultantObject> getAll(){
        return getAllByProperties(new SQLBuilder());
    }
    //return a Consultant object that relates to the id
    //getById 
    public ConsultantObject getById(int id)
    {
        return this.getByProperties(new SQLBuilder("id", "=", ""+id));
    }
   //Returns the first Consultant object matching the criteria
   //Returns the first Consultant object in the ResultSet
    
   //getByProperties
    public ConsultantObject getByProperties(SQLBuilder query) 
    {
    	//Catch Exceptions
        try 
        {
            ResultSet res = GPSISDataMapper.getResultSet(query, this.tableName);            
            
            if (res.next()) // if found, create a the Consultant object 
            {
            	return this.buildConsultant(res);
            }
        } 
        catch (SQLException e) 
        {
            e.printStackTrace();
        }
        return null;
    }
    
    /** addSpeciality
     * Create a relationship between a given Consultant and Speciality
     * @param o
     * @param s
     */
    public void addSpecialityToConsultant(ConsultantObject c, Speciality s)
    {
    	SQLBuilder sql = new SQLBuilder()
        .SET("consultant_id","=",""+c.getId())
        .SET("speciality_id", "=", ""+s.getId());
    	try {
			putHelper(sql, "ConsultantSpeciality", null);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    public List<Speciality> getSpecialitiesForConsultant(ConsultantObject c) throws EmptyResultSetException
    {
    	SQLBuilder query = new SQLBuilder("consultant_id", "=",""+ c.getId());
    	List<Speciality> conSpecs = new ArrayList<Speciality>();
    	String from = "ConsultantSpeciality "
    			+ "JOIN Speciality ON"
    			+ "ConsultantSpecialty.speciality_id = speciality.id";
    	try {
			ResultSet res = GPSISDataMapper.getResultSet(query, from);
			while (res.next())
			{
				conSpecs.add(new Speciality(res.getInt("speciality_id"), res.getString("name")));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	if (conSpecs.isEmpty())
    		throw new EmptyResultSetException();
    	else
    		return conSpecs;
		
    }
        
    //Returns a Set containing all of the Consultants that match the given criteria
    //getAllByProperties
    public List<ConsultantObject> getAllByProperties(SQLBuilder query) 
    {
          List<ConsultantObject> Consultant = new ArrayList<>();
          
          try 
          {            
            ResultSet res = GPSISDataMapper.getResultSet(query, this.tableName);            
            while(res.next()) // While there's a Consultant, create a the Consultant object and add it to a Set
            {
            	Consultant.add(this.buildConsultant(res));
            }

        } 
        catch (SQLException e) 
        {
            e.printStackTrace();
        }
        return Consultant;
    }
    
     //builds a Consultant object from the given Result Set. Used as a helper method in retrieving Consultant from the Database
    //BuildReferral
    private ConsultantObject buildConsultant(ResultSet res) throws SQLException
    {
    	if (res != null) // if found, create a the Referral object 
        {
    				//
    				return new ConsultantObject(
    								res.getInt("id"),
    								res.getString("title"),
    								res.getString("first_name"),
    								res.getString("last_name"),
    								res.getString("address"),
    								res.getString("email"),
    								res.getString("contact_num"),
    								res.getDouble("price"));
    			
        }
        else 
        {
        	//Pop-up 
            JOptionPane.showMessageDialog(null, "EMPTY SET - No Invoice Found matching the criteria");
        }
		return null;
    }
    
  
    //Remove a Referral from the database given its Id
    //removeById
    public void removeById(int id) 
    {
        try 
        {
            removeByProperty(new SQLBuilder("id","=",""+id));
        } 
        catch (SQLException e) 
        {
        	//Pop-up explaing caught exception
        	JOptionPane.showMessageDialog(null,e.getMessage());
        }
    }
    
    //Removes all Referrals from the database that match the given criteria
    //removeByProperty
    public void removeByProperty(SQLBuilder query) throws SQLException 
    {
        GPSISDataMapper.removeByPropertyHelper(query, this.tableName);        
    }

   
    //Put a Consultant object onto the Database. Used for INSERT and UPDATE
    //put 
    public void put(ConsultantObject o){
    	
    
       SQLBuilder sql = new SQLBuilder("id","=",""+o.getId())
                .SET("title","=",""+o.getTitle())
                .SET("first_name", "=", ""+o.getFName())
                .SET("last_name","=",""+o.getLName())
                .SET("address", "=",""+o.getAddress())
                .SET("email", "=", ""+o.getEmail())
                .SET("contact_num", "=", ""+o.getNum())
                .SET("price", "=", ""+o.getPrice());
        try 
        {
        	//ADD OOOOOO
            putHelper(sql, this.tableName, o);
        } 
        catch (SQLException e) 
        {
        	System.err.println(e.getMessage());
        }
    }
}
