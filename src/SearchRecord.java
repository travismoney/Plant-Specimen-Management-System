
public class SearchRecord {
	
	
	 // Function to search if the plant record exists inside the plant record database

	
	public boolean isPlantExists(String record, String searchType) {
		
		FileOperation check = new FileOperation();
		
		
		if (searchType.equals("Common Name")) {
			
			if (check.checkExists(record, "plantrecord.txt", 0)) 
				return true;
			else
				return false;
			
		} else if(searchType.equals("Genus")) {
			
			if (check.checkExists(record, "plantrecord.txt", 1))
				
				return true;
			
			else
				
				return false;
			
		} else {
			
			if (check.checkExists(record, "plantrecord.txt", 2))
				
				return true;
			
			else
				
				return false;
		}
		
	}
}



