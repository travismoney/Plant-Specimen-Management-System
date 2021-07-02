import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;

import javax.swing.JTextArea;

public class UpdateRecord {
	
	// update existsing plant record by following format
	
	// String commonName, String genus, String species, String stem, String leaf, String time, String date, String location
	
	
	public boolean Update(String initialCommonName, JTextArea commonName, JTextArea genus, JTextArea species, JTextArea stem, JTextArea leaf, JTextArea time, JTextArea date, JTextArea location, JTextArea picturePath) {
		String newLine = commonName.getText() + ";" + genus.getText() + ";" + species.getText() + ";" + stem.getText() + ";" + leaf.getText() + ";" + time.getText() + ";" + date.getText() + ";" + location.getText() 
		+ ";" + picturePath.getText();
		FileOperation fo = new FileOperation();
		try {
			if (fo.removeNthLine("plantrecord.txt", fo.findLine(initialCommonName, "plantrecord.txt"))) {
				final Path path = Paths.get("plantrecord.txt");
				Files.write(Paths.get("plantrecord.txt"), Arrays.asList(newLine),
						StandardCharsets.UTF_8,
						Files.exists(path) ? StandardOpenOption.APPEND : StandardOpenOption.CREATE);
				return true;
			} else {
				return false;
			}
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}
}

