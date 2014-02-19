package mapper;
import module.Consultant.*;
import framework.GPSISDataMapper;
import object.ConsultantObject;
import object.SpecialityTypeObject;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Set;
import java.util.HashSet;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

//Inherits GPSISDataMapper<SpecialityTypeObject>'s methods
public class SpecialityTypeDMO extends GPSISDataMapper<SpecialityTypeObject>{   
	// stores the only instance of this DataMapper
	private static SpecialityTypeDMO instance;
	
	//SpecialityTypeDMO Constructor 
    private SpecialityTypeDMO(String tableName){
        this.tableName = tableName;
    }    
    
    //getInstance
    public static SpecialityTypeDMO getInstance(){
        if(instance == null)
            instance = new SpecialityTypeDMO("ConsultantSpeciality");
        return instance;
    }
        
    //Returns a Set of all Speciality's
    //getAll
    public Set<SpecialityTypeObject> getAll(){
        return getAllByProperties(new SQLBuilder());
    }
    
    //Returns a SpecialityType object that relates to the id
    //getById 
    public SpecialityTypeObject getById(int id){
        return this.getByProperties(new SQLBuilder("id", "=", ""+id));
    }
    
    //Returns the first SpecialityType object matching the criteria
    //getByProperties
    public SpecialityTypeObject getByProperties(SQLBuilder query){
        try 
        {
            ResultSet res = GPSISDataMapper.getResultSet(query, this.tableName);            
            
            if (res.next()) // if found, create a the SpecialityTypeobject 
            {
            	return this.buildSpecialityType(res);
            }
            
        } 
        catch (SQLException e) 
        {
            e.printStackTrace();
        }
        return null;
    }
        
    //returns a Set of SpecialityTypes that match the given criteria
    //getAllByProperties
    public Set<SpecialityTypeObject> getAllByProperties(SQLBuilder query) 
    {
          Set<SpecialityTypeObject> SpecialityType = new HashSet<>();
          
          try 
          {            
            ResultSet res = GPSISDataMapper.getResultSet(query, this.tableName);            
            while(res.next()) // While there's a SpecialityType, create a the SpecialityType object and add it to a Set
            {
            	SpecialityType.add(this.buildSpecialityType(res));
            }

        } 
        catch (SQLException e) 
        {
            e.printStackTrace();
        }
        return SpecialityType;
    }
    
    //builds a SpecialityType object from the given Result Set. Used as a helper method in retrieving SpecialityTypes from the Database
    //buildSpecialityTypeObject
    private SpecialityTypeObject buildSpecialityType(ResultSet res) throws SQLException
    {
    	if (res != null) // if found, create a the SpecialityType object 
        {
  
    				return new SpecialityTypeObject(
    								res.getInt("id"),
    								res.getString("name"),
    								res.getInt("consultant_id"));
    			
        }
        else 
        {
            JOptionPane.showMessageDialog(null,"EMPTY SET - No Invoice Found matching the criteria");
        }
		return null;
    }
    
    //Remove a SpecialityType from the database given its Id
    //removeById
    public void removeById(int id){
        try 
        {
            removeByProperty(new SQLBuilder("id","=",""+id));
        } 
        catch (SQLException e) 
        {
        	JOptionPane.showMessageDialog(null,e.getMessage());
        }
    }
    
    //Removes all SpecialityTypes from the database that match the given criteria
    //removeByProperty
    public void removeByProperty(SQLBuilder query) throws SQLException 
    {
        GPSISDataMapper.removeByPropertyHelper(query, this.tableName);        
    }

    //Put a given SpecialityType object onto the Database. Used for INSERT and UPDATE
    //put
    public void put(SpecialityTypeObject o){
    	
       SQLBuilder sql = new SQLBuilder("id","=",""+o.getId())
                .SET("name","=",""+o.getName())
                .SET("consultant_id", "=", ""+o.getConID());
        try 
        {
            putHelper(sql, this.tableName, o);
        } 
        catch (SQLException e) 
        {
        	System.err.println(e.getMessage());
        }
    }

}
