package module;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;

//Only for consultants
public class AddChangeConsultant extends JFrame {
	private JLabel lab1,lab2,lab3;
	private JButton but1,but2,but3;
	private JTextArea jb;
	
	
	public AddChangeConsultant(){
		setLayout(new FlowLayout());
		Event e = new Event();
		
		lab1 = new JLabel("Search by ID: ");
		add(lab1);
		jb = new JTextArea(1,15);
		add(jb);
		//For break
		lab3 = new JLabel("                             ");
		but1 = new JButton("Search");
		add(but1);
		but1.setToolTipText("For consultant to delete");
		but1.addActionListener(e);
		
		but2 = new JButton("Add Consultant");
		add(but2);
		but2.addActionListener(e);
		
		
	}
	public class Event implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource()==but2){
				Consultant consul = new Consultant();
				consul.setVisible(true);
				consul.setTitle("Add Consultant");
				consul.setSize(260,230);	
			}
			
		}
		
	}
	
	
}
