package module;

	import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;


	public class OutStandingInvoice extends JFrame {
		private JLabel lab1,lab2,lab3,lab4,lab5, lab6, lab7, lab8;
		private JTextArea a1,a2,a3,a4,a5, a7;
		private JButton but1,but2, but7;
		private JComboBox combo;
		private int counter = 0;
		private String[] arr1;
		
		public OutStandingInvoice(){
			setLayout(new FlowLayout());
			Event e = new Event();
			
			//getOutsIv inherited from dmo
			arr1 = new String[getOutsInv().size()];
			for(int i=0; i<getOutsInv().size();i++){
				arr1[i] = "" + getOutsInv().get(i);
			}
			lab8 = new JLabel("Choose ID: ");
			add(lab8);
			combo = new JComboBox(arr1);
			add(combo);
			combo.addActionListener(e);
			
			
			but7 = new JButton("Get");
			add(but7);
			but7.addActionListener(e);
			
		}

		public ArrayList<Integer> getOutsInv() {
			ArrayList<Integer> array = new ArrayList<Integer>();
			array.add(1);
			array.add(2);
			array.add(3);
			array.add(4);
			array.add(5);
			array.add(6);
			array.add(7);
			return array;
		}
		
		public class Event implements ActionListener{

			@Override
			public void actionPerformed(ActionEvent e) {
				if(e.getSource()== but7){
				String com = (String) combo.getSelectedItem();
				Invoice i = new Invoice(com);
				i.setSize(300, 200);
				i.setVisible(true);
				i.setTitle("OutStanding");
				}
				
			}
			
		}
		
}
