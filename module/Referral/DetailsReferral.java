package module.Referral;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Set;

import javax.swing.*;

import mapper.ConsultantDMO;
import mapper.ReferralDMO;
import object.ConsultantObject;
import object.ReferralObject;
import framework.GPSISDataMapper;


public class DetailsReferral extends JFrame {
	private JLabel lab1,lab2,lab3,lab4,lab5,lab6,lab7, lab8;
	private JTextArea a1,a2,a3,a4,a5,a6,a7,a8;
	private JButton but1,but2,but3;
	
	public DetailsReferral(String selection,String searchValue, ReferralObject obj){
		setLayout(new FlowLayout());
		Event e = new Event();
		
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
		
		ReferralDMO referralDMO = ReferralDMO.getInstance();
		GPSISDataMapper.connectToDatabase();
		
	
		
		if(selection.equals("Referral ID")){
			a1.setText(""+searchValue);
			a1.setEditable(false);
			a1.setToolTipText("Cannot edit");
			a2.setText(""+obj.getDate());
			a8.setText(""+obj.getDocName());
			a3.setText(""+obj.getConID());
			a4.setText(""+obj.getPatID());
			a5.setText(""+obj.getPayID());
			
		}
		//Change to take object searched by payment id
		else if(selection.equals("Pay ID")){
			a5.setText(""+searchValue);
			a5.setEditable(false);
			a5.setToolTipText("Cannot edit");
			a2.setText(""+obj.getDate());
			a8.setText(""+obj.getDocName());
			a3.setText(""+obj.getConID());
			a4.setText(""+obj.getPatID());
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
				
			}
		}
		
	}
}
