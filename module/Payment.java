package module;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.*;


public class Payment extends JFrame {
	 
	private JLabel lab1,lab2,lab3,lab4,lab5,lab6, sp,xtra;
	private JTextArea a1,a2,a3,a4,a5,a6,ax;
	private JButton but1;
	private BufferedWriter writer;
	
	public Payment(){
		setLayout(new FlowLayout());
		Event e = new Event();
		
		lab1 = new JLabel("       Payment ID: ");
		add(lab1); 
		a1 = new JTextArea(1,15);
		add(a1);
		
		xtra = new JLabel("        Referral ID: ");
		add(xtra); 
		ax = new JTextArea(1,15);
		add(ax);
		
		lab6 = new JLabel("  Name on Card: ");
		add(lab6); 
		a6 = new JTextArea(1,15);
		add(a6);
		
		lab2 = new JLabel("                   Total: ");
		add(lab2); 
		a2 = new JTextArea(1,15);
		add(a2);
		
		lab3 = new JLabel("   Account Num: ");
		add(lab3); 
		a3 = new JTextArea(1,15);
		add(a3);
		
		lab4 = new JLabel("          Exp. Date: ");
		add(lab4); 
		a4 = new JTextArea(1,15);
		add(a4);
		
		lab5 = new JLabel("           CSC Num:");
		add(lab5); 
		a5 = new JTextArea(1,15);
		add(a5);
		
		but1 = new JButton("Pay");
		add(but1);
		but1.addActionListener(e);
		
		sp = new JLabel("");
		add(sp);
		
	}
	public class Event implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource()== but1){
				//send payment to text file for Accountants
				sp.setText("Paid!");
				//Send letters(to specialist and Patient
			}
		}
	}
}
