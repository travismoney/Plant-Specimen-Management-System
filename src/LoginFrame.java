import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class LoginFrame {

	private JFrame frame;
	private JTextField textField;
	private JPasswordField passwordField;
	
	// logo
	private ImageIcon image_logo;
	private JLabel label_logo;
	
	// background image
	private ImageIcon background_img;
	private JLabel background;

	// Default Constructor for running function Initializing GUI
	public LoginFrame() {
		initLoginGUI();
	}
	
	// Main function of LoginFrame. 
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginFrame window = new LoginFrame();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	// Function to initialize GUI, crates new JFrame
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void initLoginGUI() {
		frame = new JFrame();
		frame.setResizable(false);
		frame.setBounds(250, 250, 800, 450);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		
		//textfield
		textField = new JTextField();
		textField.setBounds(500, 200, 250, 20);
		textField.setEnabled(false);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		// Heading_1 : UNIMAS
		JLabel label_heading_1 = new JLabel("UNIMAS");
		label_heading_1.setBounds(50, 225, 450, 100);
		Font x_1 = new Font("Times New Roman", Font.BOLD, 85);
		label_heading_1.setFont(x_1);
		frame.getContentPane().add(label_heading_1);
		
		// Heading_2 : Plant Specimen Management
		JLabel label_heading_2 = new JLabel("Palm Specimen Management System");
		label_heading_2.setBounds(65, 300, 450, 30);
		Font x_2 = new Font("Arial", Font.BOLD, 18);
		label_heading_2.setFont(x_2);
		frame.getContentPane().add(label_heading_2);
		
		// Label for Version
		JLabel label_version = new JLabel("Version 1.0");
		label_version.setBounds(300,142,380,380);
		Font x_3 = new Font("Arial", Font.BOLD, 16);
		label_version.setFont(x_3);
		frame.getContentPane().add(label_version);
		
		// Footer
		JLabel labelFooter = new JLabel("\u00a9 Copyright UNIMAS 2020");
		labelFooter.setBounds(135,210,380,380);
		frame.getContentPane().add(labelFooter);
		
		// heading Logo
		
		image_logo = new ImageIcon(getClass().getResource("unimas-small-logo.png"));
		label_logo = new JLabel(image_logo);
		label_logo.setBounds(20,20,400,200);
		frame.add(label_logo);
		
		// background image palm tree
	    background_img = new ImageIcon(getClass().getResource("login-image.png"));
	    background = new JLabel(background_img);
	    background.setBounds(0, 0, 440, 450);
	    frame.add(background);
	     
		// Label for User ID
		JLabel lblUserId = new JLabel("User ID:");
		lblUserId.setBounds(505, 180, 74, 14);
		frame.getContentPane().add(lblUserId);
		
		
		// Label for Password
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setBounds(505, 230, 74, 14);
		frame.getContentPane().add(lblPassword);
		
		// JBUtton For Register
		
		JButton btnRegister = new JButton("Register");
		btnRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new RegisterFrame();
			}
		});
		
		// button register 
		
		btnRegister.setBounds(510, 290, 116, 23);
		frame.getContentPane().add(btnRegister);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Select User", "Researcher", "Administrator"}));
		comboBox.setBounds(500, 150, 252, 20);
		frame.getContentPane().add(comboBox);
		comboBox.addItemListener(new ItemListener() {
	        public void itemStateChanged(ItemEvent arg0) {
	        	if (comboBox.getSelectedItem() == "Select User") {
	    			btnRegister.setEnabled(true);
	    			textField.setEnabled(false);
	    			passwordField.setEnabled(false);
	        	}
	    		else {
	    			btnRegister.setEnabled(false);
	    			textField.setEnabled(true);
	    			passwordField.setEnabled(true);
	    		}
	        }
	    });
		
		passwordField = new JPasswordField();
		passwordField.setBounds(500, 250, 250, 20);
		passwordField.setEnabled(false);
		frame.getContentPane().add(passwordField);
		
		// Label for User Type
		
		JLabel lblUserType = new JLabel("User Type:");
		lblUserType.setBounds(505, 130, 74, 14);
		frame.getContentPane().add(lblUserType);
		
		// JButton Login
		
		JButton btnLogIn = new JButton("Log In");
		btnLogIn.setBounds(625, 290, 116, 23);
		frame.getContentPane().add(btnLogIn);
		
		btnLogIn.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent arg0) {
				if (!comboBox.getSelectedItem().toString().equals("")) {
					Login userLogin = new Login();
					if (userLogin.login(textField.getText(), passwordField.getText(), comboBox.getSelectedItem().toString())) {
						JOptionPane.showMessageDialog(null, 
			                    "Successfully Logged In!", 
			                    "Success", 
			                    JOptionPane.INFORMATION_MESSAGE);
						new MainFrame(comboBox.getSelectedItem().toString());
						frame.dispose();
					} else {
						JOptionPane.showMessageDialog(null, 
								"Login failed!", 
								"Error", 
								JOptionPane.WARNING_MESSAGE);
					}
				}
				else {
					new MainFrame("Select User");
					frame.dispose();
				}
			}
		});

		// JLabel background_color for right half of section!
		
		 JLabel background_color = new JLabel();
	     background_color.setBackground(new java.awt.Color(240, 229, 218));
	     background_color.setOpaque(true);
	     background_color.setBounds(440, 0, 360, 450);
	     frame.add(background_color);
		
	}
}
