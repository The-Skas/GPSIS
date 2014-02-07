package module.Referral;
import java.awt.*;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.swing.*;

import mapper.ReferralDMO;
import object.ReferralObject;
import framework.GPSISDataMapper;


public class DetailsReferral extends JFrame {
	private JLabel lab1,lab2,lab3,lab4,lab5,lab6,lab7, lab8;
	private JTextArea a1,a2,a3,a4,a5,a6,a7,a8;
	private JButton but1,but2,but3;
	
	public DetailsReferral(String selection,String searchValue, int num){
		setLayout(new FlowLayout());
		Event e = new Event();
		System.out.print(selection + ":" + searchValue);
		
		lab1 = new JLabel("      Referral ID: ");
		add(lab1);
		a1 = new JTextArea(1,15);
		add(a1);
		
		lab2 = new JLabel("      Date Made: ");
		add(lab2);
		a2 = new JTextArea(1,15);
		add(a2);
		
		lab8 = new JLabel("Doctors Name: ");
		a8 = new JTextArea(1,15);
		add(lab8);
		add(a8);
		
		lab3 = new JLabel(" Consultant ID: ");
		add(lab3);
		a3 = new JTextArea(1,15);
		add(a3);
		
		lab4 = new JLabel("         Patient ID: ");
		add(lab4);
		a4 = new JTextArea(1,15);
		add(a4);
		
		lab5 = new JLabel("     Payment ID: ");
		add(lab5);
		a5 = new JTextArea(1,15);
		add(a5);
		
		lab7 = new JLabel("Invoice Paid: ");
		add(lab7);
		lab6 = new JLabel("NOT PAID");
		add(lab6);
		
		if(selection.equals("Referral ID")){
			a1.setText(searchValue);
			a1.setEditable(false);
			a1.setToolTipText("Cannot edit");
		}
		else if(selection.equals("Patient ID")){
			a4.setText(searchValue);
			a4.setEditable(false);
			a4.setToolTipText("Cannot edit");
		}
		
	}
	
	public DetailsReferral(String docName, String PatientID){
		setLayout(new FlowLayout());
		Event e = new Event();
		
		lab1 = new JLabel("        Referral ID: ");
		add(lab1);
		a1 = new JTextArea(1,15);
		add(a1);
		
		lab2 = new JLabel("        Date Made: ");
		add(lab2);
		a2 = new JTextArea(1,15);
		add(a2);
		
		
		lab8 = new JLabel("  Doctors Name: ");
		a8 = new JTextArea(1,15);
		a8.setText(docName);
		add(lab8);
		add(a8);
		
		lab3 = new JLabel("   Consultant ID: ");
		add(lab3);
		a3 = new JTextArea(1,15);
		add(a3);
		
		lab4 = new JLabel("          Patient ID: ");
		add(lab4);
		a4 = new JTextArea(1,15);
		add(a4);
		a4.setText(PatientID);
		 
		/*lab5 = new JLabel("      Payment ID: ");
		add(lab5);
		a5 = new JTextArea(1,15);
		add(a5);*/
		
		lab7 = new JLabel("Invoice Paid: ");
		add(lab7);
		lab6 = new JLabel("NOT PAID");
		add(lab6);
		
		but1 = new JButton("Make Payment");
		add(but1);
		but1.addActionListener(e);
		//In button action
		String timeStamp = new SimpleDateFormat("yyyyMMdd").format(Calendar.getInstance().getTime());
		System.out.println(timeStamp);
		
		
	}
	public class Event implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource()==but1){
				
				//Save data
				ReferralDMO referralDMO = ReferralDMO.getInstance();
				GPSISDataMapper.connectToDatabase();
				Calendar cal = Calendar.getInstance();
				java.util.Date dt = cal.getTime();
				//Turns strings to integers
				int con = 0,pat = 0,pay = 0;
				
				try
				{
				   con = Integer.parseInt(a3.getText());
				   pat = Integer.parseInt(a4.getText());
				   pay = Integer.parseInt(a3.getText());
				}
				catch (NumberFormatException nfe)
				{
				   // bad data - set to sentinel
				   System.out.print("NO");
				}
				
				
				//have to convert boolean to tiny int
		    	ReferralObject r = new ReferralObject(5,dt, a8.getText(), con, pat,pay,8,1);
		    	referralDMO.put(r);
		    	System.out.print(referralDMO.getById(0).getDocName());
		    	
				//Create payment
			
				Payment r2 = new Payment();
				r2.setVisible(true);
				r2.setTitle("Payment");
				r2.setSize(300, 235);
				
				
			}
		}
		
	}
}
