import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class RegisterFrame {

	private JFrame frame;
	private JTextField textField;     
	private JTextField textField_1;      
	private JPasswordField passwordField;    
	private JPasswordField passwordField_1;   
	
	// textField --> User Name
	// passwordField --> Password
	// textField_1 --> UserID
	// passworldField_1 --> confirmpassword field
	
	
	 // Default constructor. runs Initialize GUI
	
	public RegisterFrame() {
		initRegisterGUI();
		frame.setVisible(true);
	}
	
	//Designing the GUI, New JFrame
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void initRegisterGUI() {
		frame = new JFrame();
		frame.setResizable(false);
		frame.setBounds(100, 100, 300, 300);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		// NAME TEXTFIELD
		
		textField = new JTextField();
		textField.setBounds(104, 60, 190, 20);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		// JLabel for Register Account
		JLabel label_register = new JLabel("Register New User");
		label_register.setBounds(95, 25, 200, 14);
		frame.getContentPane().add(label_register);
		
		// USER ID SECTION
		
		JLabel lblUserId = new JLabel("User ID:");
		lblUserId.setBounds(10, 95, 74, 14);
		frame.getContentPane().add(lblUserId);
		
		// PASSWORD SECTION
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setBounds(10, 130, 74, 14);
		frame.getContentPane().add(lblPassword);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Researcher", "Administrator"}));
		comboBox.setBounds(104, 200, 190, 20);
		frame.getContentPane().add(comboBox);
		
		JButton btnRegister = new JButton("Register");
		btnRegister.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent arg0) {
				if (textField.getText().equals("") || textField_1.getText().equals("") || passwordField.getText().equals("") || passwordField_1.getText().equals("")) {
					JOptionPane.showMessageDialog(null, 
                            "Please fill in all the fields!", 
                            "Error", 
                            JOptionPane.WARNING_MESSAGE);
				}
				else {
					if (passwordField.getText().equals(passwordField_1.getText())) {
						Register newUser = new Register();
						if (newUser.registerUser(textField_1.getText(), textField.getText(), passwordField.getText(), comboBox.getSelectedItem().toString())) {
							JOptionPane.showMessageDialog(null, 
				                    "Successfully registered your account!", 
				                    "Success", 
				                    JOptionPane.INFORMATION_MESSAGE);
							frame.dispose();
						} else {
							JOptionPane.showMessageDialog(null, 
				                    "User with that ID is already exists!", 
				                    "Error", 
				                    JOptionPane.WARNING_MESSAGE);
						}
					}
					else {
						JOptionPane.showMessageDialog(null, 
	                            "The password and confirm password are not the same!", 
	                            "Error", 
	                            JOptionPane.WARNING_MESSAGE);
					}
				}
			}
		});
		
		// REGISTER BUTTON SECTION
		
		btnRegister.setBounds(10, 240, 280, 32);
		frame.getContentPane().add(btnRegister);
		
		// PASSWORD SECTION
		
		passwordField = new JPasswordField();
		passwordField.setBounds(104, 130, 190, 20);
		frame.getContentPane().add(passwordField);
		
		// USER TYPE SECTION
		
		JLabel lblUserType = new JLabel("User Type:");
		lblUserType.setBounds(10, 202, 74, 14);
		frame.getContentPane().add(lblUserType);
		
		// NAME SECTION
		
		JLabel lblName = new JLabel("Name:");
		lblName.setBounds(10, 60, 74, 14);
		frame.getContentPane().add(lblName);
		
		textField_1 = new JTextField();
		textField_1.setBounds(104, 95, 190, 20);
		frame.getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		// CONFIRM PASSWORD SECTION
		
		JLabel lblConfirmPassword = new JLabel("Confirm pass:");
		lblConfirmPassword.setBounds(10, 165, 84, 14);
		frame.getContentPane().add(lblConfirmPassword);
		
		passwordField_1 = new JPasswordField();
		passwordField_1.setBounds(104, 165, 190, 20);
		frame.getContentPane().add(passwordField_1);

		// background color
		
		 JLabel background_color = new JLabel();
	     background_color.setBackground(new java.awt.Color(240, 229, 218));
	     background_color.setOpaque(true);
	     background_color.setBounds(0, 0, 300, 300);
	     frame.add(background_color);
	}
}