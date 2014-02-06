package module.Referral;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;

public class MakeReferral extends JFrame {
	private JComboBox combo1;
	private ArrayList<String> choicesA = new ArrayList<String>();
	private String[] choicesB;
	private JLabel lab1, lab2, lab3,sp,sp2,findinv,findinv2;
	private JButton but1, but2, but3;
	private JTextArea text1, text2;
	
	
	public MakeReferral(){
		setLayout(new FlowLayout());
		Event e = new Event();
		
		//Combo Box
		lab1 = new JLabel("Choose Type: ");
		add(lab1);
		choicesB = new String[choicesA.size()];
		
		for(int i=0;i<choicesA.size();i++){
			choicesB[i] = choicesA.get(i);
		}
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

		@Override
		public void actionPerformed(ActionEvent e) {
			
			if(e.getSource()== but1){
				if((text1.getText().length() >=1)&&(text2.getText().length() >=1)){
				DetailsReferral r = new DetailsReferral(text1.getText(), text2.getText());
				r.setVisible(true);
				r.setTitle("Created Referral");
				r.setSize(300, 200);
				}
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
