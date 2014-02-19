package mapper;
import module.Referral.*;
import framework.GPSISDataMapper;
import object.ConsultantObject;
import object.InvoiceObject;
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


//Inherits methods from GPSISDataMapper<InvoiceObject> 
public class InvoiceDMO extends GPSISDataMapper<InvoiceObject> 
{   
	
	// stores the only instance of this DataMapper
	private static InvoiceDMO instance;
	//Invoice DMO constructor
    private InvoiceDMO(String tableName){
        this.tableName = tableName;
    }    
  
    //returns the only instance of the PaymentDMO
    //getInstance
    public static InvoiceDMO getInstance(){
        if(instance == null)
            instance = new InvoiceDMO("Invoice");
        return instance;
    }
   
    //return a Set of all Invoices
    //getAll
    public Set<InvoiceObject> getAll(){
        return getAllByProperties(new SQLBuilder());
    }
  
    //returns an Invoice object that relates to the id
    //getById
    public InvoiceObject getById(int id) {
        return this.getByProperties(new SQLBuilder("id", "=", ""+id));
    }

    //return the first Invoice object in the ResultSet
    //getByProperties
    public InvoiceObject getByProperties(SQLBuilder query){
        try {
            ResultSet res = GPSISDataMapper.getResultSet(query, this.tableName);            
            
            if (res.next()) // if found, create a the Invoice object
            	{
            	return this.buildInvoice(res);
            }
            
        } catch (SQLException e) 
        {
            e.printStackTrace();
        }
        return null;
    }
    
    //returns a Set of Invoices that match the given criteria
    //getAllByProperties
    public Set<InvoiceObject> getAllByProperties(SQLBuilder query) 
    {
          Set<InvoiceObject> Invoice = new HashSet<>();
          
          try 
          {            
            ResultSet res = GPSISDataMapper.getResultSet(query, this.tableName);            
            while(res.next()) // While there's an Invoice, create the Invoice object and add it to a Set
            {
            	Invoice.add(this.buildInvoice(res));
            }

        } 
        catch (SQLException e) 
        {
            e.printStackTrace();
        }
        return Invoice;
    }
   
   //builds an Invoice object from the given Result Set. Used as a helper method in retrieving Invoice's from the Database
   //buildInvoice
    private InvoiceObject buildInvoice(ResultSet res) throws SQLException
    {
    	if (res != null) // if found, create a the Invoice object 
    	{
  
    				return new InvoiceObject(
    								res.getInt("id"),
    								res.getInt("referral_id"),
    								res.getDouble("amount"),
    								res.getInt("consultant_id"),
    								res.getString("date_recieved"),
    								res.getInt("paid"));
    			
        }
        else 
        {
            JOptionPane.showMessageDialog(null,"EMPTY SET - No Invoice Found matching the criteria");
        }
		return null;
    }
    
    //Remove an Invoice from the database given its Id
    //removeById
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
   
    //Removes all Invoices from the database that match the given criteria
    //RemoveByProperty
    public void removeByProperty(SQLBuilder query) throws SQLException 
    {
        GPSISDataMapper.removeByPropertyHelper(query, this.tableName);        
    }

    //put
    //Put a given Referral object onto the Database. Similar to the put method in a Map data structure. Used for INSERT and UPDATE
    public void put(InvoiceObject o){
    	 String s = new SimpleDateFormat("yyyy-MM-dd").format(o.getDate());
    
       SQLBuilder sql = new SQLBuilder("id","=",""+o.getId())
                .SET("referral_id","=",""+o.getRefID())
                .SET("amount", "=", ""+o.getAmount())
                .SET("consultant_id","=",""+o.getConID())
                .SET("date_recieved", "=",""+o.getDate())
                .SET("paid", "=", ""+o.getIsPaid());
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
