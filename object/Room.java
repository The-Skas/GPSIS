package object;

import mapper.RoomDMO;
import framework.GPSISObject;

public class Room extends GPSISObject {
	private String description;
        
        //Skas: Another Call Replaced to Singleton Pattern.
	protected static RoomDMO tbl = RoomDMO.getInstance();
	
	// get DMO
	// put using DMO
	public Room(String desc)
	{
		this.description = desc;
		
		tbl.put(this);
	}
	
	// used when creating an instance from database by DMO
	public Room(int id, String desc)
	{
		this.id = id;
		this.description = desc;
	}

	public String getDescription()
	{
		return this.description;
	}
}
