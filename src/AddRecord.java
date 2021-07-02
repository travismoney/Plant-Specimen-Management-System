import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;

import javax.swing.JOptionPane;

public class AddRecord {
	
	// Checking if the plant exists inside the plantrecord.txt returns true or false.
	public boolean isPlantExists(String commonName) {
		FileOperation check = new FileOperation();
		if (check.checkExists(commonName, "plantrecord.txt", 0))
			return true;
		else
			return false;
	}
	
	// inserting new plant record into txt file. 
	
	public boolean add(String commonName, String genus, String species, String stem, String leaf, String time, String date, String location, String picturePath) {
		if (!isPlantExists(commonName)) {
			try {
				
				// gets the plantrecord.txt
				final Path path = Paths.get("plantrecord.txt");
				Files.write(Paths.get("plantrecord.txt"), Arrays.asList(commonName.toLowerCase() + ";" + genus.toLowerCase() + ";" + species.toLowerCase() + ";" + stem.toLowerCase() + ";" + leaf.toLowerCase() + ";" + time.toLowerCase() + ";" + date.toLowerCase() + ";" + location.toLowerCase()
				+ ";" + picturePath),
						StandardCharsets.UTF_8,
						Files.exists(path) ? StandardOpenOption.APPEND : StandardOpenOption.CREATE);

				JOptionPane.showMessageDialog(null, 
	                    "Successfully added plant record!", 
	                    "Success", 
	                    JOptionPane.INFORMATION_MESSAGE);
				
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else { // if plant record exists, prints this
			JOptionPane.showMessageDialog(null, 
                    "Plant with Common Name already exist!", 
                    "Error", 
                    JOptionPane.WARNING_MESSAGE);
		}
		return true;
	}
}
