 package framework;

import java.util.Locale;

public class GPSIS {
	
	/** main
	 * Where the application will start.
	 */
	public static void main(String[] args)
	{
		System.out.println(Locale.getDefault().getDisplayCountry());
		GPSISFramework GPSIS = new GPSISFramework();		
		GPSIS.initialise();
	}
}

/**
 * End of File: GPSIS.java
 * Location: gpsis/framework
 */