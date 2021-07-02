import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;
import java.util.Scanner;

public class FileOperation {

	
	// Checking if record in txt file exists
	
	public boolean checkExists(String checkThis, String fileName, int index) {
		boolean found = false;
		try {
			File file = new File(fileName);
			Scanner scan = new Scanner(file);

			while (scan.hasNext()) {
				String[] tokens = scan.nextLine().split(";");
				if (tokens[index].equals(checkThis)) {
					found = true;
					scan.close();
					break;
				}
			}
			scan.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return found;
	}

	// Write/enter new user into the user.txt file
	public boolean writeUser(String userID, String userFullName, String userPass, String userType) {
		try {
			final Path path = Paths.get("users.txt");
			Files.write(Paths.get("users.txt"), Arrays.asList(userID + ";" + userFullName + ";" + userPass + ";" + userType),
					StandardCharsets.UTF_8,
					Files.exists(path) ? StandardOpenOption.APPEND : StandardOpenOption.CREATE);
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}

	//Function for verifying login credentials are matching
	public boolean checkCredential(String idNo, String password, String userType) {
		
		boolean success = false;
		try {
			File file = new File("users.txt");
			Scanner scan = new Scanner(file);

			while (scan.hasNext()) {
				String[] tokens = scan.nextLine().split(";");
				if (tokens[0].equals(idNo) && tokens[2].equals(password) && tokens[3].equals(userType)) {
					success = true;
					scan.close();
					break;
				}
			}
			scan.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return success;
	}


	//remove the specific line inside the specified file

	public boolean removeNthLine(String file, int toRemove) throws IOException {
	    File tmp = File.createTempFile("tmp", "");

	    BufferedReader br = new BufferedReader(new FileReader(file));
	    BufferedWriter bw = new BufferedWriter(new FileWriter(tmp));

	    for (int i = 0; i < toRemove; i++)
	        bw.write(String.format("%s%n", br.readLine()));

	    br.readLine();

	    String l;
	    while (null != (l = br.readLine()))
	        bw.write(String.format("%s%n", l));

	    br.close();
	    bw.close();

	    File oldFile = new File(file);
	    if (oldFile.delete())  {
	        tmp.renameTo(oldFile);
	        return true;
	    }
	    else
	    	return false;
	}

	// Finding line number
	public int findLine(String id, String filedb) {
		int lineNo = 0;
		try {
			File file = new File(filedb);
			Scanner scan = new Scanner(file);

			while (scan.hasNext()) {
				String[] tokens = scan.nextLine().split(";");
				if (tokens[0].equals(id)) {
					scan.close();
					break;
				}
				lineNo++;
			}
			scan.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return lineNo;
	}


	
}