package module;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.*;


public class Invoice extends JFrame {
	private JLabel lab1,lab2,lab3,lab4,lab5, lab6, lab7;
	private JTextArea a1,a2,a3,a4,a5, a7;
	private JButton but1,but2, but7;
	private JComboBox combo;
	private int counter = 0;
	private String[] arr1;
	private BufferedWriter writer;
	
	public Invoice(String id){
		//Set all this a1,a2,a3,etc.setText(from sql)
		setLayout(new FlowLayout());
		Event e = new Event();
		
		lab1 = new JLabel("          Invoice ID: ");
		add(lab1);
		a1 = new JTextArea(1,15);
		add(a1);
		a1.setText(id);
		a1.setEditable(false);
		
		lab7 = new JLabel("         Referral ID:");
		add(lab7);
		a7 = new JTextArea(1,15);
		add(a7);
		
		
		lab2 = new JLabel("           Amount: £");
		add(lab2);
		a2 = new JTextArea(1,15);
		add(a2);
		

		lab3 = new JLabel("   Consultant ID: ");
		add(lab3);
		a3 = new JTextArea(1,15);
		add(a3);
		
		lab5 = new JLabel(" Recieved Date: ");
		add(lab5);
		a5 = new JTextArea(1,15);
		add(a5);
		
		lab4 = new JLabel("Paid: ");
		add(lab4);
		//Just for break
		lab6 = new JLabel("                                                                         ");
		add(lab6);
		
		but1 = new JButton("Pay");
		add(but1);
		but1.addActionListener(e);
	}
	

	public Invoice(){
		setLayout(new FlowLayout());
		Event e = new Event();
		
		//I dont know if i need this here yet
		
		/*lab1 = new JLabel("Invoice ID: ");
		add(lab1);
		a1 = new JTextArea(1,15);
		add(a1);*/
		
		
		lab7 = new JLabel("       Referral ID: ");
		add(lab7);
		a7 = new JTextArea(1,15);
		add(a7);
		
		
		lab2 = new JLabel("         Amount: £");
		add(lab2);
		a2 = new JTextArea(1,15);
		add(a2);
		

		lab3 = new JLabel(" Consultant ID: ");
		add(lab3);
		a3 = new JTextArea(1,15);
		add(a3);
		
		lab5 = new JLabel("Recieved Date: ");
		add(lab5);
		a5 = new JTextArea(1,15);
		add(a5);
		
		lab4 = new JLabel("Paid: ");
		add(lab4);
		//Just for break
		lab6 = new JLabel("                                                                         ");
		add(lab6);
		
		but1 = new JButton("Pay");
		add(but1);
		but1.addActionListener(e);
		
	}
	public class Event implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			//So only one message pop-up is show
			
			counter = 0;
			int count = 0;
			if((e.getSource()==but1)){
				/*
				if((a1.getText().length()==0)&&(counter<1)){
					counter +=1;
				JOptionPane.showMessageDialog(null, "Please Enter an Invoice ID");
				}
				*/
				if((a7.getText().length()==0)&&(counter<1)){
					counter +=1;
				JOptionPane.showMessageDialog(null, "Please Enter a Referral ID");
				}
				else if((a2.getText().length()==0)&&(counter<1)){
					counter +=1;
				JOptionPane.showMessageDialog(null, "Please Enter an Amount");
				}
				else if((a3.getText().length()==0)&&(counter<1)){
					counter +=1;
				JOptionPane.showMessageDialog(null, "Please Enter a Consultant");
				}
				else if((a5.getText().length()==0)&&(counter<1)){
					counter +=1;
				JOptionPane.showMessageDialog(null, "Please Enter a Recieved Date");
				}
			}
			if(/*(a1.getText().length()>=1)&&*/(a7.getText().length()>=1)&&(a2.getText().length()>=1)&&(a3.getText().length()>=1)&&(a5.getText().length()>=1)){
				lab6.setText("PAID");
				//output to text file. (Send To Accounting)
				try
				{
				    writer = new BufferedWriter( new FileWriter( "PayMentID.txt") );
				    writer.write("Card Details");

				}
				catch ( IOException f)
				{
				}
				finally
				{
				    try
				    {
				        if ( writer != null)
				        writer.close( );
				    }
				    catch ( IOException f)
				    {
				    }
				}
				
			}
		}
		
	}
}
