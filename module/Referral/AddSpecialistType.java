package module.Referral;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;

public class AddSpecialistType extends JFrame {
	private JLabel lab1,lab2,lab3;
	private JButton bu1,but2,but3,but4;
	private JTextArea tlb1,tlb2;
	private JScrollPane sp;
	private JComboBox jcb;
	private ArrayList<String> arr1 = new ArrayList<String>();
	private String[] array;
	private JCheckBox jc,jc2;
	private int check = 0;
	
	public AddSpecialistType(){
		setLayout(new FlowLayout());
		Event e = new Event();
		arr1.add("Gastro");
		//Add Dropdown choices
		array = new String[arr1.size()];
		for(int i=0;i<arr1.size();i++){
			array[i] = arr1.get(i);
		}
		
		lab1 = new JLabel("Select Speciality: ");
		add(lab1);
		jcb = new JComboBox(array);
		add(jcb);
		
		jc = new JCheckBox();
		add(jc);
		jc.addActionListener(e);
		jc.setToolTipText("Check to add by dropdown (When add is pressed)");
		//lab2 = new JLabel("                 ");
		//add(lab2);
		
		lab2 = new JLabel("Add Speciality to list: ");
		add(lab2);
		tlb1 = new JTextArea(3,13);
		add(tlb1);
		
		but2 = new JButton("Add Spec. to Consultant");
		add(but2);
		but2.setToolTipText("Chosen from Dropdown");
	
		
	}
	public class Event implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource()==but2){
				//Add speciality from textarea to speciality sql table.
				//Speciality get by name and add by select* from Speciality where 
			}
		}
		
	}
	public static void main(String[] args){
		AddSpecialistType as = new AddSpecialistType();
		as.setVisible(true);
		as.setSize(300, 170);
		as.setTitle("Add Specialist type");
	}
}
