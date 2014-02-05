package module;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class Consultant extends JFrame{
	private JLabel lab1,lab2,lab3,lab4,lab5,lab6,lab7,lab8;
	private JButton but1,but2,but3;
	private String[] speciality;
	private JTextArea jb1,jb2,jb3,jb4,jb5,jb6,jb7,jb8;
	private int counter = 0;
	
	public Consultant(){
		setLayout(new FlowLayout());
		Event e = new Event();
		
		lab1 = new JLabel("Title: ");
		add(lab1);
		jb1 = new JTextArea(1,15);
		add(jb1);
		
		lab2 = new JLabel("First Name: ");
		add(lab2);
		jb2 = new JTextArea(1,15);
		add(jb2);
		
		lab3 = new JLabel("Last Name: ");
		add(lab3);
		jb3 = new JTextArea(1,15);
		add(jb3);
		
		lab4 = new JLabel("Address: ");
		add(lab4);
		jb4 = new JTextArea(1,15);
		add(jb4);
		
		lab5 = new JLabel("Email: ");
		add(lab5);
		jb5 = new JTextArea(1,15);
		add(jb5);
		
		lab6 = new JLabel("Contact num: ");
		add(lab6);
		jb6 = new JTextArea(1,13);
		add(jb6);
		
		lab8 = new JLabel("Speciality: ");
		add(lab8);
		jb8 = new JTextArea(1,15);
		add(jb8);
		
		but1 = new JButton("Add");
		add(but1);
		but1.addActionListener(e);
		
		lab7 = new JLabel();
		add(lab7);
		
	}
	public class Event implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			if((e.getSource()==but1)&&(counter<1)){
				counter+=1;
				lab7.setText("Added!");
			}
			
		}
		
		
	}
	
}
