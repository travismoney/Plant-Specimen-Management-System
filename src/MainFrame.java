import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JPanel;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.JButton;
import javax.swing.ScrollPaneConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
public class MainFrame {
	
	// main variables
	private String userType;
	private JFrame plantManagementSystem;
	private JTabbedPane tabbedPane;
	private JTextField searchTextField;
	@SuppressWarnings("rawtypes")
	private JComboBox searchComboBox;
	private JTextArea searchCommonName, searchGenus, searchSpecies, searchStem, searchLeaf, searchTime, searchDate, searchLocation, searchPicturePath;
	
	// update field
	private JTextField updateTextField;
	@SuppressWarnings("rawtypes")
	private JComboBox updateCombobox;
	private JTextArea updateCommonName, updateGenus, updateSpecies, updateStem, updateLeaf, updateTime, updateDate, updateLocation, updatePicturePath;
	private JButton updateButtonRemove, updateButtonUpdate;
	
	// add field
	private JTextArea addCommonName, addGenus, addSpecies, addStem, addLeaf, addTime, addDate, addLocation, addPicturePath;
	
	// Logo handling
	private ImageIcon image1;
	private JLabel label1;
	
	// Image Background
	private ImageIcon img_background;
	private JLabel label_background;
	private ImageIcon tempPicture;
	private JLabel temp_label;
	
	private String filename = null;
	
	private JTable table_1;
	
	// Default Constructor for setting the usertype (Researcher or Admin) and calling the function below - Initialize()
	
	public MainFrame(String userType) {
		
		this.userType = userType;
		
		initialize();
		
		plantManagementSystem.setVisible(true);
		
	}

	// function to store textArea inside the search tab. will read from plant specimen file and store it here
	
	// function to clear the image after searching new palm
	
	
	public void setSearchInformation(String commonName, String genus, String species, String stem, String leaf, String time, String date, String location, String picturePath) {
		
		searchCommonName.setText(commonName);
		searchGenus.setText(genus);
		searchSpecies.setText(species);
		searchStem.setText(stem);
		searchLeaf.setText(leaf);
		searchTime.setText(time);
		searchDate.setText(date);
		searchLocation.setText(location);
		searchPicturePath.setText(picturePath);
				
	}
	
	// function to store textArea inside the update tab. will read from plant specimen file and store it here
	
	public void setUpdateInformation(String commonName, String genus, String species, String stem, String leaf, String time, String date, String location, String picturePath) {
		
		// tokken 0, 1, 2, 3, 4, 5, 6, 7
		
		updateCommonName.setText(commonName);
		updateGenus.setText(genus);
		updateSpecies.setText(species);
		updateStem.setText(stem);
		updateLeaf.setText(leaf);
		updateTime.setText(time);
		updateDate.setText(date);
		updateLocation.setText(location);
		updatePicturePath.setText(picturePath);
		
	}
	
	// Function to clear fields in Add Section after sucessfully added plant record into txt file
	
	public void clearAddFields() {
		
		addCommonName.setText("");
		addGenus.setText("");
		addSpecies.setText("");
		addStem.setText("");
		addLeaf.setText("");
		addTime.setText("");
		addDate.setText("");
		addLocation.setText("");
		addPicturePath.setText("");
		temp_label.setIcon(null);
		
	}
	
	// function to clear fields in update section after sucessfully updated plant record in txt file
	public void clearUpdateFields() {
		
		updateTextField.setText("");
		updateCommonName.setText("");
		updateGenus.setText("");
		updateSpecies.setText("");
		updateStem.setText("");
		updateLeaf.setText("");
		updateTime.setText("");
		updateDate.setText("");
		updateLocation.setText("");
		updatePicturePath.setText("");
		temp_label.setIcon(null);
		
	}
	
	
    // function to search plant record inside the stored txt file. 
	
	// searchBox - user input to search files
	// searchType - 0 Search Panel
	// searchType - 1 Update Panel
	
	public boolean searchPlantRecord(String searchBox, String comboBox, int searchType) {
		
		SearchRecord search = new SearchRecord();
		
		if (!search.isPlantExists(searchBox.toLowerCase(), comboBox)) {
			
			JOptionPane.showMessageDialog(null, "Cannot find the plant record!", "Error!", JOptionPane.WARNING_MESSAGE);
			return false;
			
		}
		else {
			
			try {
				
				File file = new File("plantrecord.txt");
				
				Scanner scan = new Scanner(file);
				
				String[] tokens = {};
				
				
				while (scan.hasNext()) {
					
					tokens = scan.nextLine().split(";");
					
					// tokens[0] --> Common Name
					if (comboBox.equals("Common Name")) {
						
						if (tokens[0].equals(searchBox))
							
							break;
					// tokens[1] --> Genus
						
					} else if (comboBox.equals("Genus")) {
						
						if (tokens[1].equals(searchBox))
							
							break;
						
					// tokens[2] --> Species
					} else {
						
						if (tokens[2].equals(searchBox))
							
							break;
						
					}
				}
				

				
				// SearchType == 0 --> fills up the setSearchInformation
				if (searchType == 0)
					
					// String commonName, String genus, String species, String stem, String leaf, String time, String date, String location, String picturePath

					setSearchInformation(tokens[0], tokens[1], tokens[2], tokens[3], tokens[4], tokens[5], tokens[6], tokens[7], tokens[8]);
				
				else
				// SearchType == 1 --> fills up the setUpdateInformation
					setUpdateInformation(tokens[0], tokens[1], tokens[2], tokens[3], tokens[4], tokens[5], tokens[6], tokens[7], tokens[8]);
					scan.close();
				
			} catch (IOException e) {
				
				e.printStackTrace();
				
			}
			return true;
			
		}
		
	}

	// Initializing the GUI, function creates a new JFRAME
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void initialize() {
		
		plantManagementSystem = new JFrame();
		plantManagementSystem.setTitle("Palm Specimen Management System");
		
		// main frame dimension 
		plantManagementSystem.setBounds(200, 500, 700, 730);
		plantManagementSystem.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		plantManagementSystem.getContentPane().setLayout(null);
		plantManagementSystem.setLocationRelativeTo(null);
		plantManagementSystem.setResizable(false);
		
		// UNIMAS LOGO
		
		image1 = new ImageIcon(getClass().getResource("unimas-logo.png"));
		label1 = new JLabel(image1);
		label1.setBounds(110,10,500,100);
		plantManagementSystem.getContentPane().add(label1);
		
	
		// Heading
		JLabel labelHeading = new JLabel("Palm Specimen Management System");
		labelHeading.setBounds(85,125,700,35);
		Font f = new Font("Arial", Font.BOLD, 30);
		labelHeading.setFont(f);
		plantManagementSystem.getContentPane().add(labelHeading);
		
		// Footer 
		JLabel labelFooter = new JLabel("\u00a9 Copyright UNIMAS 2020");
		labelFooter.setBounds(270,665,700,40);
		plantManagementSystem.getContentPane().add(labelFooter);
		
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);	
		tabbedPane.setBounds(10, 170, 680, 505);
		plantManagementSystem.getContentPane().add(tabbedPane);
		
		/* SEARCH PANEL STARTS HERE! */
		
		JPanel panel = new JPanel();
		tabbedPane.addTab("Search Record", null, panel, null);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Search by:");
		lblNewLabel.setBounds(10, 25, 150, 14);
		panel.add(lblNewLabel);

		
		searchComboBox = new JComboBox();
		searchComboBox.setModel(new DefaultComboBoxModel(new String[] {"Common Name", "Genus", "Species"}));
		searchComboBox.setBounds(115, 21, 150, 23);
		panel.add(searchComboBox);
		
		searchTextField = new JTextField();
		searchTextField.setBounds(260, 22, 300, 20);
		panel.add(searchTextField);
		searchTextField.setColumns(10);
		
		JButton searchButtonSearch = new JButton("Search");
		searchButtonSearch.addActionListener(new ActionListener() {
			
			
			public void actionPerformed(ActionEvent arg0) {

				
				if (searchTextField.getText().equals("")) {
					
					JOptionPane.showMessageDialog(null, "Please fill in the text field!", "Error", JOptionPane.ERROR_MESSAGE);
					
				} else {
					
					searchPicturePath.setText(null);
					searchPlantRecord(searchTextField.getText(), searchComboBox.getSelectedItem().toString(), 0);
					
				}
				
				// revalidate and repaint panel to display picture
				panel.revalidate();
				panel.repaint();
				// setting the picturePath into the string to insert into ImageIcon
				
		        tempPicture = new ImageIcon(searchPicturePath.getText());
		        temp_label = new JLabel(tempPicture);
		        temp_label.setBounds(480,63,160,201);
		        panel.add(temp_label);
		        panel.setComponentZOrder(temp_label, 0);
                
			}
		});
		
		//string commonName, String genus, String species, String stem, String leaf, String time, String date, String location
		
		// photo label
		
		JLabel label_photo = new JLabel("Photo");
		label_photo.setBounds(540, 74, 150, 20);
		panel.add(label_photo);
		
		searchButtonSearch.setBounds(555, 22, 95, 23);
		panel.add(searchButtonSearch);
		
		// Label for Common Name
		
		JLabel labelCommonName = new JLabel("Common Name:");
		labelCommonName.setBounds(10, 64, 150, 20);
		panel.add(labelCommonName);
		
		// Label for Genus
		
		JLabel labelGenus = new JLabel("Genus:");
		labelGenus.setBounds(10, 94, 150, 20);
		panel.add(labelGenus);
		
		// Label for Species
		
		JLabel labelSpecies = new JLabel("Species:");
		labelSpecies.setBounds(10, 124, 150, 20);
		panel.add(labelSpecies);
		
		// Label for Time
		
		JLabel labelTime = new JLabel("Time:");
		labelTime.setBounds(10, 154, 150, 20);
		panel.add(labelTime);
		
		// Label for Date
		
		JLabel labelDate = new JLabel("Date:");
		labelDate.setBounds(10, 184, 150, 20);
		panel.add(labelDate);
		
		// Label for Location
		
		JLabel labelLocation = new JLabel("Location:");
		labelLocation.setBounds(10, 214, 150, 20);
		panel.add(labelLocation);
		
		// Label for Stem
		
		JLabel labelStem = new JLabel("Stem:");
		labelStem.setBounds(10, 244, 150, 20);
		panel.add(labelStem);
		
		
		// Label for Leaf
		
		JLabel labelLeaf = new JLabel("Leaf:");
		labelLeaf.setBounds(10, 350, 150, 20);
		panel.add(labelLeaf);
		
		// Picture display area
		
		// Display SearchPicturePath JTextArea();
		// .getText from the SearchPicturePath
			
		searchPicturePath = new JTextArea();	
		
		// Text input for Common Name
		
		searchCommonName = new JTextArea();
		searchCommonName.setEditable(false);
		searchCommonName.setBounds(120, 64, 345, 20);
		panel.add(searchCommonName);
		
		// Text input for Genus
		
		searchGenus = new JTextArea();
		searchGenus.setEditable(false);
		searchGenus.setBounds(120, 94, 345, 20);
		panel.add(searchGenus);
		
		// Text input for Species
		
		searchSpecies = new JTextArea();
		searchSpecies.setEditable(false);
		searchSpecies.setBounds(120, 124, 345, 20);
		panel.add(searchSpecies);
				
		// Text input for Time
		
		searchTime = new JTextArea();
		searchTime.setEditable(false);
		searchTime.setBounds(120, 154, 345, 20);
		panel.add(searchTime);
		
		// Text input for Date
		
		searchDate = new JTextArea();
		searchDate.setEditable(false);
		searchDate.setBounds(120, 184, 345, 20);
		panel.add(searchDate);
		
		// Text input for Location
		
		searchLocation = new JTextArea();
		searchLocation.setEditable(false);
		searchLocation.setBounds(120, 214, 345, 20);
		panel.add(searchLocation);
		
		// Text input for Stem
		
		searchStem = new JTextArea();
		searchStem.setEditable(false);
		searchStem.setWrapStyleWord(true);
		searchStem.setLineWrap(true);
		JScrollPane scroll = new JScrollPane(searchStem);
		scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scroll.setBounds(120, 244, 525, 95);
		panel.add(scroll);
		
		// Text input for Leaf
		
		searchLeaf = new JTextArea();
		searchLeaf.setEditable(false);
		searchLeaf.setWrapStyleWord(true);
		searchLeaf.setLineWrap(true);
		JScrollPane scroll_2 = new JScrollPane(searchLeaf);
		scroll_2.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scroll_2.setBounds(120, 350, 525, 95);
		panel.add(scroll_2);
		
		// panel for updating plant record
		
		JPanel panel_update = new JPanel();
		tabbedPane.addTab("Update Record", null, panel_update, null);
		panel_update.setLayout(null);
		
		JLabel label = new JLabel("Search by:");
		label.setBounds(10, 26, 150, 14);
		panel_update.add(label);
		
		
		// update section 
		
		updateCombobox = new JComboBox();
		updateCombobox.setModel(new DefaultComboBoxModel(new String[] {"Common Name", "Genus", "Species"}));
		updateCombobox.setBounds(115, 21, 150, 23);
		panel_update.add(updateCombobox);
		
		updateTextField = new JTextField();
		updateTextField.setColumns(10);
		updateTextField.setBounds(260, 22, 300, 20);
		panel_update.add(updateTextField);
		
		// JButton updateButtonSearch and its method goes here!
		
		JButton updateButtonSearch = new JButton("Search");
		
		updateButtonSearch.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				if (updateTextField.getText().equals("")) {
					
					JOptionPane.showMessageDialog(null, "Please fill in the search fill!", "Error", JOptionPane.ERROR_MESSAGE);
					
				} else {
					
					if (searchPlantRecord(updateTextField.getText(), updateCombobox.getSelectedItem().toString(), 1)) {
												
						updateCommonName.setEnabled(true);
						updateGenus.setEnabled(true);
						updateSpecies.setEnabled(true);
						updateStem.setEnabled(true);
						updateLeaf.setEnabled(true);
						updateTime.setEnabled(true);
						updateDate.setEnabled(true);
						updateLocation.setEnabled(true);
						updateButtonRemove.setEnabled(true);
						updateButtonUpdate.setEnabled(true);		

					}
					
					panel_update.revalidate();
					panel_update.repaint();
					
					String picturePath = updatePicturePath.getText();
								
			        tempPicture = new ImageIcon(picturePath);
			        temp_label = new JLabel(tempPicture);
			        temp_label.setBounds(480,83,160,128);
			        panel_update.add(temp_label);
			        panel_update.setComponentZOrder(temp_label, 0);
			        
				}
			}
		});
		
		// JButton updatePhoto and its method goes here!
		

		
		updateButtonSearch.setBounds(555, 22, 95, 23);
		panel_update.add(updateButtonSearch);
		
		// Label for Update Common Name
		
		JLabel label_UpdateCommonName = new JLabel("Common Name:");
		label_UpdateCommonName.setBounds(10, 64, 150, 20);
		panel_update.add(label_UpdateCommonName);
		
		// labe for photo
		
		JLabel label_UpdatePhoto = new JLabel("Photo");
		label_UpdatePhoto.setBounds(540, 64, 150, 20);
		panel_update.add(label_UpdatePhoto);
		
		// Label for Update Genus
		
		JLabel label_UpdateGenus = new JLabel("Genus:");
		label_UpdateGenus.setBounds(10, 94, 150, 20);
		panel_update.add(label_UpdateGenus);
		
		// Label for Update Species
		
		JLabel label_UpdateSpecies = new JLabel("Species:");
		label_UpdateSpecies.setBounds(10, 124, 150, 20);
		panel_update.add(label_UpdateSpecies);
				
		// Label for Update Time
		
		JLabel label_UpdateTime = new JLabel("Time:");
		label_UpdateTime.setBounds(10, 154, 150, 20);
		panel_update.add(label_UpdateTime);
		
		// Label for Update Date
		
		JLabel label_UpdateDate = new JLabel("Date:");
		label_UpdateDate.setBounds(10, 184, 150, 20);
		panel_update.add(label_UpdateDate);
		
		// Label for Update Location 
		
		JLabel label_UpdateLocation = new JLabel("Location:");
		label_UpdateLocation.setBounds(10, 214, 150, 20);
		panel_update.add(label_UpdateLocation);
		
		// Label for Update Stem
		
		JLabel label_UpdateStem = new JLabel("Stem:");
		label_UpdateStem.setBounds(10, 244, 150, 20);
		panel_update.add(label_UpdateStem);
		
		// Label for Update Leaf
		
		JLabel label_UpdateLeaf = new JLabel("Leaf:");
		label_UpdateLeaf.setBounds(10, 350, 150, 20);
		panel_update.add(label_UpdateLeaf);
		
		// upload photo section! 
		
		JButton buttonUpdatePhoto = new JButton("Upload");
		buttonUpdatePhoto.setBounds(480, 215, 160, 20);
		panel_update.add(buttonUpdatePhoto);
		
		JLabel updatePicturePth = new JLabel("Picture Path:");
		updatePicturePth.setBounds(10, 395, 150, 22);
		panel_update.add(updatePicturePth);

		
		// Update Processing Button
		
		updateButtonUpdate = new JButton("Update");
		updateButtonUpdate.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				UpdateRecord update = new UpdateRecord();
				
				if (update.Update(updateTextField.getText(), updateCommonName, updateGenus, updateSpecies, updateStem, updateLeaf, updateTime, updateDate, updateLocation, updatePicturePath)) {
					
					JOptionPane.showMessageDialog(null, "Succesfully updated the plant speciment record data!", "Success", JOptionPane.INFORMATION_MESSAGE);
					
					// Clear Update Fields function after succesfully insert into txt file
					clearUpdateFields();
					panel_update.revalidate();
					panel_update.repaint();
				}
				else {
					
					JOptionPane.showMessageDialog(null, "Failed to update the plant speciment record data!", "Error!", JOptionPane.ERROR_MESSAGE);
				}
				
			}
			
		});
		
		updateButtonUpdate.setEnabled(false);
		updateButtonUpdate.setBounds(329, 430, 89, 23);
		panel_update.add(updateButtonUpdate);
		
		// removing plant record from file.
		
		updateButtonRemove = new JButton("Delete");
		updateButtonRemove.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				
				try {
					
					FileOperation fo = new FileOperation();
					
					if (fo.removeNthLine("plantrecord.txt", fo.findLine(updateCommonName.getText(), "plantrecord.txt"))) {
						
						JOptionPane.showMessageDialog(null, "Plant Record Data Sucessfully removed!", "Success!", JOptionPane.INFORMATION_MESSAGE);
						
						// clear update fields function after deleting plant record
						clearUpdateFields();
					}
					else {
						
						JOptionPane.showMessageDialog(null, "Plant Record Data Not removed!", "Error!", JOptionPane.ERROR_MESSAGE);
						
					}

				} catch (IOException e) {
					
					e.printStackTrace();
				}
						
			}
		});
		

		buttonUpdatePhoto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
		        JFileChooser chooser = new JFileChooser();
		        chooser.showOpenDialog(null);
		        File f = chooser.getSelectedFile();
		        String filename = f.getAbsolutePath();
		        updatePicturePath = new JTextArea();
				updatePicturePath.setEnabled(false);
				updatePicturePath.setBounds(120, 395, 520, 20);
				panel_update.add(updatePicturePath);
		        updatePicturePath.setText(filename);
		        tempPicture = new ImageIcon(filename);
		        temp_label = new JLabel(tempPicture);
		        temp_label.setBounds(480,85,160,125);
		        panel_update.add(temp_label);
			}
		});

		updatePicturePath = new JTextArea();
		updatePicturePath.setEditable(false);
		updatePicturePath.setBounds(120,397,520,20);
		panel_update.add(updatePicturePath);
        panel_update.setComponentZOrder(updatePicturePath, 0);
        
		updateButtonRemove.setEnabled(false);
		updateButtonRemove.setBounds(240, 430, 89, 23);
		panel_update.add(updateButtonRemove);
		
		// Text Area for Common Name
		
		updateCommonName = new JTextArea();
		updateCommonName.setDragEnabled(false);
		updateCommonName.setBounds(120, 64, 345, 20);
		panel_update.add(updateCommonName);
		
		// Text Area for Genus
		
		updateGenus = new JTextArea();
		updateGenus.setDragEnabled(false);
		updateGenus.setBounds(120, 94, 345, 20);
		panel_update.add(updateGenus);
		
		// Text Area for Species
		
		updateSpecies = new JTextArea();
		updateSpecies.setDragEnabled(false);
		updateSpecies.setBounds(120, 124, 345, 20);
		panel_update.add(updateSpecies);
				
		// Text Area for Time
		
		updateTime = new JTextArea();
		updateTime.setDragEnabled(false);
		updateTime.setBounds(120, 154, 345, 20);
		panel_update.add(updateTime);
		
		// Text Area for Date
		
		updateDate = new JTextArea();
		updateDate.setDragEnabled(false);
		updateDate.setBounds(120, 184, 345, 20);
		panel_update.add(updateDate);
		
		// Text Area for Location
		
		updateLocation = new JTextArea();
		updateLocation.setDragEnabled(false);
		updateLocation.setBounds(120, 214, 345, 20);
		panel_update.add(updateLocation);
		
		// Text Area for Stem
		
		updateStem = new JTextArea();
		updateStem.setEnabled(false);
		updateStem.setLineWrap(true);
		updateStem.setWrapStyleWord(true);
		JScrollPane scroll_3 = new JScrollPane(updateStem);
		scroll_3.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scroll_3.setBounds(120, 244, 525, 65);
		panel_update.add(scroll_3);
		
		// Text Area for Leaf
		
		updateLeaf = new JTextArea();
		updateLeaf.setEnabled(false);
		updateLeaf.setLineWrap(true);
		updateLeaf.setWrapStyleWord(true);
		JScrollPane scroll_4 = new JScrollPane(updateLeaf);
		scroll_4.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scroll_4.setBounds(120, 320, 525, 65);
		panel_update.add(scroll_4);

		// PANEL ADD GOES HERE!
		// ADD RECORD STARTS HERE!!!
		
		JPanel panel_add = new JPanel();
		tabbedPane.addTab("Add Record", null, panel_add, null);
		panel_add.setLayout(null);
		
		// Label for AddCommonName - DONE
		
		JLabel label_AddCommonName = new JLabel("Common Name:");
		label_AddCommonName.setBounds(10, 15, 150, 22);
		panel_add.add(label_AddCommonName);
		
		addCommonName = new JTextArea();
		addCommonName.setBounds(120, 15, 345, 20);
		panel_add.add(addCommonName);

		// Label for AddGenus - DONE
		
		JLabel label_AddGenus =  new JLabel("Genus:");
		label_AddGenus.setBounds(10, 45, 150, 22);
		panel_add.add(label_AddGenus);
		
		addGenus = new JTextArea();
		addGenus.setBounds(120, 45, 345, 20);
		panel_add.add(addGenus);
		
		// Label for AddSpecies - DONE
		
		JLabel label_AddSpecies = new JLabel("Species:");
		label_AddSpecies.setBounds(10, 75, 150, 22);
		panel_add.add(label_AddSpecies);
		
		addSpecies = new JTextArea();
		addSpecies.setBounds(120, 75, 345, 20);
		panel_add.add(addSpecies);
				
		// Label for AddTime - DONE
		
		JLabel label_AddTime = new JLabel("Time:");
		label_AddTime.setBounds(10, 105, 81, 14);
		panel_add.add(label_AddTime);
		
		addTime = new JTextArea();
		addTime.setBounds(120, 105, 345, 20);
		panel_add.add(addTime);
		
		// Label for AddDate - DONE

		JLabel label_AddDate = new JLabel("Date:");
		label_AddDate.setBounds(10, 135, 81, 14);
		panel_add.add(label_AddDate);
		
		addDate = new JTextArea();
		addDate.setBounds(120, 135, 345, 20);
		panel_add.add(addDate);
		
		// Label for AddLocation - DONE
		
		JLabel label_AddLocation = new JLabel("Location:");
		label_AddLocation.setBounds(10, 165, 81, 14);
		panel_add.add(label_AddLocation);
		
		addLocation = new JTextArea();
		addLocation.setBounds(120, 165, 345, 20);
		panel_add.add(addLocation);
		
		// Label for AddStem
		
		JLabel label_AddStem = new JLabel("Stem:");
		label_AddStem.setBounds(10, 195, 81, 14);
		panel_add.add(label_AddStem);
		
		addStem = new JTextArea();
		addStem.setWrapStyleWord(true);
		addStem.setLineWrap(true);
		JScrollPane scroll_5 = new JScrollPane(addStem);
		scroll_5.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scroll_5.setBounds(120, 195, 520, 80);
		panel_add.add(scroll_5);
		
		// Label for AddLeaf 
		
		JLabel label_AddLeaf = new JLabel("Leaf:");
		label_AddLeaf.setBounds(10, 284, 81, 14);
		panel_add.add(label_AddLeaf);
		
		addLeaf = new JTextArea();
		addLeaf.setWrapStyleWord(true);
		addLeaf.setLineWrap(true);
		JScrollPane scroll_6 = new JScrollPane(addLeaf);
		scroll_6.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scroll_6.setBounds(120, 284, 520, 80);
		panel_add.add(scroll_6);

		// Add Picture
	
		JLabel label_AddPhoto = new JLabel("Photo");
		label_AddPhoto.setBounds(540, 15, 500, 14);
		panel_add.add(label_AddPhoto);
		
		JButton buttonAddPhoto = new JButton("Upload");
		buttonAddPhoto.setBounds(480, 165, 160, 20);
		panel_add.add(buttonAddPhoto);
		
		JLabel picturePth = new JLabel("Picture Path:");
		picturePth.setBounds(10, 380, 150, 22);
		panel_add.add(picturePth);

		
		buttonAddPhoto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
		        JFileChooser chooser = new JFileChooser();
		        chooser.showOpenDialog(null);
		        File f = chooser.getSelectedFile();
		        filename = f.getAbsolutePath();
		        addPicturePath = new JTextArea();
				addPicturePath.setEnabled(false);
				addPicturePath.setBounds(120, 382, 520, 20);
				panel_add.add(addPicturePath);
		        addPicturePath.setText(filename);
		        tempPicture = new ImageIcon(filename);
		        temp_label = new JLabel(tempPicture);
		        temp_label.setBounds(480,35,160,125);
		        panel_add.add(temp_label);
			}
		});
		
		// Setting picture path and displaying the picture goes here!
		
			
		JButton buttonAddRecord = new JButton("Add Record");
		
		buttonAddRecord.addActionListener(new ActionListener(){
			
			public void actionPerformed(ActionEvent e) {
				
				if (addCommonName.getText().equals("") || addGenus.getText().equals("") || addSpecies.getText().equals("") || addStem.getText().equals("") || 
						addLeaf.getText().equals("") || addTime.getText().equals("") || addDate.getText().equals("") || addLocation.getText().equals("") || addPicturePath.getText().equals("")) {
						
						JOptionPane.showMessageDialog(null, "Please fill in all the required fields!", "Error!", JOptionPane.ERROR_MESSAGE);
						
					} else {
						
						AddRecord addRecord = new AddRecord();
						addRecord.add(addCommonName.getText(), addGenus.getText(), addSpecies.getText(), addStem.getText(), addLeaf.getText(), addTime.getText(),
								addDate.getText(), addLocation.getText(), addPicturePath.getText());
						
						// Clear Add Fields Function
						clearAddFields();
						panel_add.revalidate();
						panel_add.repaint();
					}
			}
			
		});
		

		buttonAddRecord.setBounds(265, 420, 150, 23);
		panel_add.add(buttonAddRecord);
		
		// Panel for Summary
		// Panel for Summary
		
		JPanel panel_summary = new JPanel();
		tabbedPane.addTab("Summary Report", null, panel_summary, null);
		
		JButton btnNewButton = new JButton("Load Summary Report");
		btnNewButton.setBounds(26, 15, 170, 23);
		btnNewButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
			
				try {
					
					 FileReader fr = new FileReader("plantrecord.txt");
					   BufferedReader br = new BufferedReader(fr);
					   DefaultTableModel model = (DefaultTableModel)table_1.getModel();
					   
					   Object[] lines = br.lines().toArray();
					   
					   for(int i = 0; i < lines.length; i++){
						   String line = lines[i].toString().trim();
			                 String[] dataRow = line.split(";");
			                 dataRow[3] = dataRow[5]; 
			                 dataRow[4] = dataRow[6];
			                 dataRow[5] = dataRow[7];
			                 dataRow[6] = dataRow[8];
			                 model.addRow(dataRow);
			               
			                 br.close();
			               
			              // dataRow[0] - > Common Name
			              // dataRow[1] - > Genus
			              // dataRow[2] - > Species
			              // dataRow[3] - > Stem
			              // dataRow[4] - > Leaf
			              // dataRow[5] - > Time
			              // dataRow[6] - > Date
			              // dataRow[7] - > Location
			              // dataRow[8] - > PicturePath

			            }
					   
					  
				} catch  (IOException ex) {
					JOptionPane.showMessageDialog(null, e);
					
					
				}
		
				
			}
		});
		panel_summary.setLayout(null);
		panel_summary.add(btnNewButton);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(18, 52, 624, 387);
		panel_summary.add(scrollPane);
		
		table_1 = new JTable();
		table_1.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Common Name", "Genus", "Species", "Time", "Date", "Location"
			}
		));
		scrollPane.setViewportView(table_1);
		
		//	Table Filter Sorter goes here!
		
		final JTextField filterField = new JTextField();
		filterField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				
				DefaultTableModel table = (DefaultTableModel)table_1.getModel();
				String search = filterField.getText().toLowerCase();
				TableRowSorter<DefaultTableModel> tr = new TableRowSorter<DefaultTableModel>(table);
				table_1.setRowSorter(tr);
				tr.setRowFilter(RowFilter.regexFilter(search));
				
			}
		});
		filterField.setBounds(305, 15, 330, 20);
		panel_summary.add(filterField);
		filterField.setColumns(10);
		
		JLabel label_Search = new JLabel("Filter Input:");
		label_Search.setBounds(215, 15, 150, 20);
		panel_summary.add(label_Search);
            
		// Image Background
		
		img_background = new ImageIcon(getClass().getResource("background.png"));
		label_background = new JLabel(img_background);
		label_background.setBounds(0,0,700,730);
		plantManagementSystem.getContentPane().add(label_background);

		
		if (!userType.equals("Administrator")) {
			tabbedPane.remove(panel_update);
			//tabbedPane.remove(panel_add);
		}
	}
}
