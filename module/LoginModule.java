package module;
/** LoginModule
 * Displays the Login Window and Sets the static currentUser variable
 * @author VJ
 */
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import mapper.SQLBuilder;
import object.StaffMember;
import exception.EmptyResultSetException;
import framework.GPSISFramework;

public class LoginModule extends GPSISFramework implements ActionListener {
	
	private JFrame gpsisLogin = new JFrame(APPTITLE + " Login");
	private JTextField usernameFld;
	private JPasswordField passwordFld;
	
	@Override
	public void actionPerformed(ActionEvent e) {		
		String username = this.usernameFld.getText();
		String inputPassword = new String(StaffMember.encrypt(new String(this.passwordFld.getPassword())));
		
		// execute login
		StaffMember sM;
		try 
		{
			sM = staffMemberDMO.getByProperties(new SQLBuilder("username", "=", username));
			String password = new String(sM.getEncryptedPassword());
			if (password.trim().equals(inputPassword.trim()))
			{
				gpsisLogin.dispose(); // close login window
				currentUser = sM; // set the current user
				
				staffMemberDMO.register(currentUser);
				
				MainInterface wM = new MainInterface(); // load the welcome module
				wM.createAndShowGUI(); // show the welcome module
				
				// close login window and open main window with StaffMember object in the parameter
			}
			else
			{
				JOptionPane.showMessageDialog(gpsisLogin, "Invalid Password!", "ERROR", JOptionPane.ERROR_MESSAGE);
			}
		} 
		catch (EmptyResultSetException ex) 
		{
			JOptionPane.showMessageDialog(gpsisLogin, "Invalid User!", "ERROR", JOptionPane.ERROR_MESSAGE);
		}
				
	}

	public void showLogin()
	{
		this.gpsisLogin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.gpsisLogin.setLayout(new GridBagLayout());
		this.gpsisLogin.setBackground(new Color(240, 240, 240));
		this.gpsisLogin.setSize(400, 250);
		
		JPanel h = new JPanel(new GridBagLayout());			
			JLabel hTitle = new JLabel("Login");
				hTitle.setFont(new Font("Serif", Font.BOLD, 24));
				GridBagConstraints gbC = new GridBagConstraints();
				gbC.anchor = GridBagConstraints.CENTER;
			h.add(hTitle, gbC);
			
			gbC = new GridBagConstraints();
			gbC.anchor = GridBagConstraints.PAGE_START;
			gbC.gridx = 0;
			gbC.gridy = 0;
			gbC.weightx = 1;
			gbC.weighty = 1;
			gbC.gridwidth = 3;
			gbC.ipady = 0;
		this.gpsisLogin.add(h, gbC);
		
		JPanel loginForm = new JPanel(new GridBagLayout());
			
			JLabel usernameLbl = new JLabel("Username: ");
				gbC = new GridBagConstraints();
				gbC.anchor = GridBagConstraints.LINE_START;
				gbC.fill = GridBagConstraints.NONE;
				gbC.weightx = 1;
				gbC.weighty = 1;
				gbC.gridx = 0;
				gbC.gridy = 0;
			loginForm.add(usernameLbl, gbC);
		
			this.usernameFld = new JTextField(20);
				gbC = new GridBagConstraints();
				gbC.anchor = GridBagConstraints.CENTER;
				gbC.weightx = 1;
				gbC.weighty = 1;
				gbC.gridx = 1;
				gbC.gridy = 0;
				gbC.gridwidth = 2;
			loginForm.add(this.usernameFld, gbC);
		
			JLabel passwordLbl = new JLabel("Password: ");
				gbC = new GridBagConstraints();
				gbC.anchor = GridBagConstraints.LINE_START;
				gbC.fill = GridBagConstraints.NONE;
				gbC.weightx = 1;
				gbC.weighty = 1;
				gbC.gridx = 0;
				gbC.gridy = 1;
			loginForm.add(passwordLbl, gbC);
		
			this.passwordFld = new JPasswordField(20);
				gbC = new GridBagConstraints();
				gbC.anchor = GridBagConstraints.CENTER;
				gbC.weightx = 1;
				gbC.weighty = 1;
				gbC.gridx = 1;
				gbC.gridy = 1;
				gbC.gridwidth = 2;
				this.passwordFld.addActionListener(this);
			loginForm.add(this.passwordFld, gbC);
			
			JButton loginBtn = new JButton("Login!");
				gbC = new GridBagConstraints();
				gbC.anchor = GridBagConstraints.LAST_LINE_END;
				gbC.weightx = 1;
				gbC.weighty = 1;
				gbC.gridx = 2;
				gbC.gridy = 2;
				loginBtn.addActionListener(this);
			loginForm.add(loginBtn, gbC);
		
			gbC = new GridBagConstraints();
			gbC.anchor = GridBagConstraints.CENTER;
			gbC.gridx = 0;
			gbC.weightx = 1;
			gbC.weighty = 1;
			gbC.gridwidth = 3;
		this.gpsisLogin.add(loginForm, gbC);
		
		this.gpsisLogin.setLocationRelativeTo(null); // center window
		
		this.gpsisLogin.setVisible(true);
	}
	
}
