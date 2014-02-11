package module.Referral;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Set;

import javax.swing.*;

import mapper.ReferralDMO;
import mapper.SpecialityTypeDMO;
import object.ReferralObject;
import object.SpecialityTypeObject;
import framework.GPSISDataMapper;


public class Referral extends JFrame {
	private JComboBox combo1;
	private String[] choices = {"Referral ID", "Payment ID"};
	private JLabel lab1,tab1,tab2,tab3,find1,l;
	private JButton but1, but2, but3, but4, but5,find2;
	private JTextArea text1,find3;
	private JMenuBar jb;
	
		public Referral(){
			setLayout(new FlowLayout());
			Event e = new Event();
			l = new JLabel("                                ");   
			add(l);
			combo1 = new JComboBox(choices);
			combo1.setToolTipText("Select Type");
			add(combo1);
			combo1.addActionListener(e);
			tab1 = new JLabel("                              ");
			add(tab1);
			
			lab1 = new JLabel("Find Referral:");
			add(lab1);
			
			
			text1 = new JTextArea(1,10);
			add(text1);
			
			but1 = new JButton("Search");
			add(but1);
			but1.addActionListener(e);
			but1.setToolTipText("Change Search Type with Dropdown");
			
			find1 = new JLabel("  Find Invoice:");
			add(find1);
			
			
			find3 = new JTextArea(1,10);
			add(find3);
			
			find2 = new JButton("Search");
			add(find2);
			find2.addActionListener(e);
			find2.setToolTipText("Enter Referral ID");
			
			
			//but4 = new JButton("Add Invoice");
			//add(but4);
			//but4.addActionListener(e);
			
			
			but2 = new JButton("Outstanding Invoice");
			add(but2);
			but2.addActionListener(e);
			
			but3 = new JButton("Make Referral");
			add(but3);
			but3.addActionListener(e);
			tab2 = new JLabel("                                            ");
			add(tab2);
			
			
			//This is so i can make the message pop up when field is empty
			//Set editable field
			text1.setEditable(true);

		}
		public class Event implements ActionListener{

			@Override
			public void actionPerformed(ActionEvent e) {
				
				if(e.getSource()==but3){
					MakeReferral r = new MakeReferral();
					r.setVisible(true);
					r.setTitle("Make Referral");
					r.setSize(240, 190);
				}
				
				else if((e.getSource()== but1)&&(text1.getText().length()>=1)){
					//Add arg. into method
					String selection = (String)combo1.getSelectedItem();
					String searchValue = text1.getText();
					//Insert number at the end as the other method has 2 string arguments
					
					try{
						
						ReferralDMO referralDMO = ReferralDMO.getInstance();
						GPSISDataMapper.connectToDatabase();
						ReferralObject r2 = referralDMO.getById(Integer.parseInt(searchValue));
					
						DetailsReferral r = new DetailsReferral(selection, searchValue, r2);
						r.setVisible(true);
						r.setTitle("Found Referral");
						r.setSize(320, 200);
						
						}catch(Exception eee){
							JOptionPane.showMessageDialog(null,  "Selection error");
						}
					
				}
				else if((e.getSource()== but1)&&(text1.getText().length()<1)){
					JOptionPane.showMessageDialog(null, "Please enter value");
				}
				
				else if(e.getSource()== but4){
					Invoice r = new Invoice();
					r.setVisible(true);
					r.setTitle("Invoice");
					r.setSize(300, 180);
				}
				else if(e.getSource()== but2){
					DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
					Date date = new Date(0);
				    Calendar cal = Calendar.getInstance();
				    String temp =dateFormat.format(cal.getTime());
				    //removes time so returns just date
				    temp = temp.substring(0,10);
				    //passing in the argument temp.With the date i can return all the outstanding payments in invoice
					OutStandingInvoice r = new OutStandingInvoice();
					r.setVisible(true);
					r.setTitle("Outstanding Invoice");
					r.setSize(300, 80);
				}
			}
			
		}
		public static void main(String[] args){
			
			try{
			ReferralDMO referralDMO = ReferralDMO.getInstance();
			GPSISDataMapper.connectToDatabase();
			ReferralObject r2 = referralDMO.getById(63);
			String s = "";
			if(r2.isInvPaid()>0){
				s = "PAID";
			}
			System.out.print(s);
			System.out.print(r2.getId());
			
			
			}catch(Exception eee){
				JOptionPane.showMessageDialog(null,  "Selection error");
			}

			
			
			
	    	Referral refer = new Referral();
	    	
			refer.setVisible(true);
			refer.setTitle("Referral");
			refer.setSize(320, 160);
			//Closes all windows after referral main window is closed
			refer.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			refer.setResizable(false);
		
		}
}

