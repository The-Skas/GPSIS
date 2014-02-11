package module.Referral;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import javax.swing.*;
import mapper.ReferralDMO;
import mapper.SpecialityTypeDMO;
import object.ReferralObject;
import object.SpecialityTypeObject;
import object.*;
import framework.GPSISDataMapper;

public class MakeReferral extends JFrame {
	private JComboBox combo1;
	private ArrayList<String> choicesA = new ArrayList<String>();
	private String[] choicesB;
	private JLabel lab1, lab2, lab3,sp,sp2,findinv,findinv2;
	private JButton but1, but2, but3;
	private JTextArea text1, text2;
	
	//GUI for Make Referral form
	public MakeReferral(){
		setLayout(new FlowLayout());
		//Set class for action listener
		Event e = new Event();
		//Populating Dropdown selection box
		SpecialityTypeDMO specialityTypeDMO = SpecialityTypeDMO.getInstance();
		GPSISDataMapper.connectToDatabase();
		SpecialityTypeObject r = new SpecialityTypeObject();
		Set<SpecialityTypeObject> set1  = specialityTypeDMO.getAll();
		//Using enhanced for loop to add just names not the whole object (for the dropdown list)
		for(SpecialityTypeObject x:set1){
			choicesA.add(x.getName());
		}
		//taking out duplicate entrys
		HashSet hset = new HashSet();
		hset.addAll(choicesA);
		choicesA.clear();
		choicesA.addAll(hset);
		//sort arraylist here
		Collections.sort(choicesA);
		//Add Dropdown choices
		choicesB = new String[choicesA.size()];
		for(int i=0;i<choicesA.size();i++){
			choicesB[i] = choicesA.get(i);
		}
		//Combo Box
		lab1 = new JLabel("Choose Type: ");
		add(lab1);
		combo1 = new JComboBox(choicesB);
		add(combo1);
		combo1.addActionListener(e);
		combo1.setToolTipText("Consultant type");
		
		//To break up text
		sp = new JLabel("                                                  ");
		add(sp);
		
		
		//Name
		lab2 = new JLabel("       Enter Name: ");
		add(lab2);
		text1 = new JTextArea(1,10);
		add(text1);
		
		//Add Patient ID
		lab3 = new JLabel("Enter Patient ID: ");
		add(lab3);
		text2 = new JTextArea(1,10);
		add(text2);
		
		//Add Create Button
		but1 = new JButton("CREATE");
		add(but1);
		but1.addActionListener(e);
		
	}
	public class Event implements ActionListener{
		//Events handler
		@Override
		public void actionPerformed(ActionEvent e) {
			//Listening to source (but1)
			if(e.getSource()== but1){
				
				if((text1.getText().length() >=1)&&(text2.getText().length() >=1)){
				
				ReferralDMO referralDMO = ReferralDMO.getInstance();
				GPSISDataMapper.connectToDatabase();
				//Creates Date
				Calendar cal = Calendar.getInstance();
				java.util.Date dt = cal.getTime();
				//Turns strings to integers
				int con = 0,pat = 0,pay = 0;
					try
					{
						pat = Integer.parseInt(text2.getText());
					}
					catch (NumberFormatException nfe)
					{
						// bad data - set to sentinel
						JOptionPane.showMessageDialog(null,  "BAD DATA");
					}
					
				//ConID Search
				
				try{
				String select = (String) combo1.getSelectedItem();
				SpecialityTypeDMO specialityTypeDMO = SpecialityTypeDMO.getInstance();
				GPSISDataMapper.connectToDatabase();
				SpecialityTypeObject r2 = new SpecialityTypeObject();
				Set<SpecialityTypeObject> set1  = specialityTypeDMO.getAll();
				for(SpecialityTypeObject x: set1){
					if(x.getName().equals(select)){
						con = x.getConID();
						System.out.print(con);
					}
				}
				}catch(Exception eee){
					JOptionPane.showMessageDialog(null,  "Selection error");
				}
				//Have to convert boolean to tiny int (1 and zero)
		    	ReferralObject r = new ReferralObject(1, dt, text1.getText(), con, Integer.parseInt(text2.getText()),5244,1,21553);
		    	referralDMO.put(r);
		    	//Prints out id number to use for adding Speciality's
		    	JOptionPane.showMessageDialog(null, "Your Referral ID is: "+ r.getId());
		    	
				//Create payment GUI Form
				Payment r2 = new Payment(referralDMO.getit());
				r2.setVisible(true);
				r2.setTitle("Payment");
				r2.setSize(300, 235);
				}
				
				//Catches if data entered into text fields is bad
				else if(text1.getText().length() <1){
						JOptionPane.showMessageDialog(null, "Enter Your Name!");
				}
				else if(text2.getText().length() <1){
						JOptionPane.showMessageDialog(null, "Enter Patients ID!");
				}
			}
		}
	}
	
}
