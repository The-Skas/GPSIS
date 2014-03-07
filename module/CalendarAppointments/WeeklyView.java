package module.CalendarAppointments;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import exception.EmptyResultSetException;
import mapper.CalendarAppointmentDMO;
import object.CalendarAppointment;
import object.RoutineAppointment;

public class WeeklyView {
	
	JFrame mainFrame = new JFrame("Main Frame");
	
    Calendar cal = Calendar.getInstance();
    Calendar nowCal = Calendar.getInstance();
    
    List<CalendarAppointment> doctorAppointments;  
		
	JPanel p1 = new JPanel();
	JPanel p2 = new JPanel();
	JPanel p3 = new JPanel();
	JPanel p4 = new JPanel();
	JPanel p5 = new JPanel();
	JPanel p6 = new JPanel();
	
	String[] dates = new String[7]; // holds the dates of the current week
	
	public WeeklyView(int doc) throws ParseException{
		
	    int delta = -nowCal.get(GregorianCalendar.DAY_OF_WEEK) + 2; //add 2 if your week start on monday
	    
	    nowCal.add(Calendar.DAY_OF_MONTH, delta );
	    
	    SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
	    
	    for (int i = 0; i < 7; i++)
	    {
	        dates[i] = format.format(nowCal.getTime());
	        nowCal.add(Calendar.DAY_OF_MONTH, 1);
	    }
	    System.out.println("YAY!"+Arrays.toString(dates));
		
		mainFrame.setLayout(new FlowLayout());
		
		p1 = dailyPanel(doc, dates[0]);
		p2 = dailyPanel(doc, dates[1]);
		p3 = dailyPanel(doc, dates[2]);
		p4 = dailyPanel(doc, dates[3]);
		p5 = dailyPanel(doc, dates[4]);
		p6 = dailyPanelSaturday(doc, dates[5]);
		
		mainFrame.add(p1);
		mainFrame.add(p2);
		mainFrame.add(p3);
		mainFrame.add(p4);
		mainFrame.add(p5);
		mainFrame.add(p6);

		mainFrame.pack();
		mainFrame.setVisible(true);
		mainFrame.setLocationRelativeTo(null);
	}	
	
	public JPanel dailyPanel(int doctorId, String day) throws ParseException{
		
		//JLabel l = new JLabel("", JLabel.CENTER);
        JButton[] button = new JButton[34];
        
        
        doctorAppointments = CalendarAppointmentDMO.getInstance().getAppointmentsByDoctorId(doctorId);
        List<CalendarAppointment> newList = new ArrayList<>();

        SimpleDateFormat sDF = new SimpleDateFormat("yyyy/MM/dd");      
        SimpleDateFormat sDF2 = new SimpleDateFormat("HH:mm");
        
        try {
        	
    		this.doctorAppointments = CalendarAppointmentDMO.getInstance().getAppointmentsOfDoctorIdByDay(doctorId, sDF.parse(day));
    		
    		newList = this.doctorAppointments;
    		
    	} catch (EmptyResultSetException e) {
    		System.out.println("No Appointments");
    	} catch (SQLException e) {
    		e.printStackTrace();
    	}
        
        Color lightRed = new Color(237, 199, 199);
        Color blueishInk = new Color(69, 65, 107);      
        
        // convert a String to a Date
        Date date = sDF.parse(day);
        
        // convert a Date to a String
        DateFormat sDF1 = new SimpleDateFormat("EEE dd MMMM yyyy");
        String s = sDF1.format(date); 
        
        JLabel test = new JLabel(s);
        test.setForeground(blueishInk);
        test.setHorizontalAlignment( JLabel.CENTER );
        
        JPanel p1 = new JPanel(new GridLayout(35, 1));
        
        p1.setPreferredSize(new Dimension(160, 620));
        p1.setBackground(Color.WHITE);
        p1.add(test);
        
        cal.set(Calendar.HOUR, 9);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.AM_PM, Calendar.AM);   
        
        if(!newList.isEmpty()) // there are some appointments for that day
        {
         for (int x = 0; x < button.length; x++) {
        	 
        	 //final int selection = x;
        	 
             Date d = cal.getTime();  
             cal.add(Calendar.MINUTE, 15);
             Date d1 = cal.getTime();
        	                  
                 
                 for(CalendarAppointment newListAppointment : newList)
                 {
                	 if((sDF2.format(newListAppointment.getStartTime()).equals(sDF2.format(d.getTime())))){
                		 // appointment is found
                		 System.out.println("Appointment!"+sDF2.format(newListAppointment.getStartTime()));
                		 button[x] = new JButton();
                		 button[x].setFocusPainted(false);
                		 button[x].setText("" + sDF2.format(d.getTime()) + " - " + " Patient: "+((RoutineAppointment) newListAppointment).getPatient());
                		 button[x].setBackground(lightRed);
                		 button[x].setForeground(blueishInk);
                		 final CalendarAppointment cF = newListAppointment;
                         button[x].addActionListener(new ActionListener() {
                             public void actionPerformed(ActionEvent ae) {
                                     //hour = button[selection].getActionCommand();
                            	 new ViewEditAppointment(cF);
                                    // frame.dispose();
                             }
                     });  
                         break;
                	 } else {
                		 button[x] = new DisabledJButton();
                		 button[x].setText("" + sDF2.format(d.getTime()) + " - " + sDF2.format(d1.getTime()) + "   Free   ");
                		 button[x].setBackground(Color.white);	
                		 button[x].setForeground(blueishInk);                 		 
                		 button[x].setActionCommand(sDF2.format(d.getTime()));
                	 }
                 }                                                   
                 p1.add(button[x]);
         }
        } else { // there are no appointments for the specific day
        	for (int x = 0; x < button.length; x++) {
        	 Date d = cal.getTime();  
             cal.add(Calendar.MINUTE, 15);
             Date d1 = cal.getTime();
        	 
             	 button[x] = new DisabledJButton();
                 button[x].setFocusPainted(false);
        		 button[x].setText("" + sDF2.format(d.getTime()) + " - " + sDF2.format(d1.getTime()) + "   Free   ");
        		 button[x].setBackground(Color.white);	
        		 button[x].setForeground(blueishInk); 
        		 button[x].setActionCommand(sDF2.format(d.getTime()));
                 p1.add(button[x]);
        	}
        }
        
		return p1;
	}

public JPanel dailyPanelSaturday(int doctorId, String day) throws ParseException{
		
		//JLabel l = new JLabel("", JLabel.CENTER);
        JButton[] button = new JButton[12];
        //String[] times = new String[12];
        //List<CalendarAppointment> doctorAppointments;
        doctorAppointments = CalendarAppointmentDMO.getInstance().getAppointmentsByDoctorId(doctorId);
        List<CalendarAppointment> newList = new ArrayList<>();

        SimpleDateFormat sDF = new SimpleDateFormat("yyyy/MM/dd");      
        SimpleDateFormat sDF2 = new SimpleDateFormat("HH:mm");
        
        try {
        	
    		this.doctorAppointments = CalendarAppointmentDMO.getInstance().getAppointmentsOfDoctorIdByDay(doctorId, sDF.parse(day));
    		
    		newList = this.doctorAppointments;
    		
    	} catch (EmptyResultSetException e) {
    		System.out.println("No Appointments");
    	} catch (SQLException e) {
    		e.printStackTrace();
    	}
        
        Color lightRed = new Color(237, 199, 199);
        Color blueishInk = new Color(69, 65, 107);      
        
        // convert a String to a Date
        Date date = sDF.parse(day);
        
        // convert a Date to a String
        DateFormat sDF1 = new SimpleDateFormat("EEE dd MMMM yyyy");
        String s = sDF1.format(date); 
        
        JLabel test = new JLabel(s);
        test.setForeground(blueishInk);
        test.setHorizontalAlignment( JLabel.CENTER );
        
        JPanel p1 = new JPanel(new GridLayout(35, 1));
        
        p1.setPreferredSize(new Dimension(160, 620));
        p1.setBackground(Color.WHITE);
        p1.add(test);
        
        cal.set(Calendar.HOUR, 9);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.AM_PM, Calendar.AM);   
        
        if(!newList.isEmpty()) // there are some appointments for that day
        {
         for (int x = 0; x < button.length; x++) {
        	 
        	 //final int selection = x;
        	 
             Date d = cal.getTime();  
             cal.add(Calendar.MINUTE, 15);
             Date d1 = cal.getTime();
        	                  
                 
                 for(CalendarAppointment newListAppointment : newList)
                 {
                	 if((sDF2.format(newListAppointment.getStartTime()).equals(sDF2.format(d.getTime())))){
                		 // appointment is found
                		 System.out.println("Appointment!"+sDF2.format(newListAppointment.getStartTime()));
                		 button[x] = new JButton();
                		 button[x].setFocusPainted(false);
                		 button[x].setText("" + sDF2.format(d.getTime()) + " - " + " Patient: "+((RoutineAppointment) newListAppointment).getPatient());
                		 button[x].setBackground(lightRed);
                		 button[x].setForeground(blueishInk);
                		 final CalendarAppointment cF = newListAppointment;
                         button[x].addActionListener(new ActionListener() {
                             public void actionPerformed(ActionEvent ae) {
                                     //hour = button[selection].getActionCommand();
                            	 new ViewEditAppointment(cF);
                                    // frame.dispose();
                             }
                     });  
                         break;
                	 } else {
                		 button[x] = new DisabledJButton();
                		 button[x].setText("" + sDF2.format(d.getTime()) + " - " + sDF2.format(d1.getTime()) + "   Free   ");
                		 button[x].setBackground(Color.white);	
                		 button[x].setForeground(blueishInk);                 		 
                		 button[x].setActionCommand(sDF2.format(d.getTime()));
                	 }
                 }                                                   
                 p1.add(button[x]);
         }
        } else { // there are no appointments for the specific day
        	for (int x = 0; x < button.length; x++) {
        	 Date d = cal.getTime();  
             cal.add(Calendar.MINUTE, 15);
             Date d1 = cal.getTime();
        	 
             	 button[x] = new DisabledJButton();
                 button[x].setFocusPainted(false);
        		 button[x].setText("" + sDF2.format(d.getTime()) + " - " + sDF2.format(d1.getTime()) + "   Free   ");
        		 button[x].setBackground(Color.white);	
        		 button[x].setForeground(blueishInk); 
        		 button[x].setActionCommand(sDF2.format(d.getTime()));
                 p1.add(button[x]);
        	}
        }
        
		return p1;
	}
}
