
public class Register {
	
	// Check if the USERID exits in the txt file
	public boolean isUserExists(String idNo) {
		FileOperation check = new FileOperation();
		if (check.checkExists(idNo, "users.txt", 0))
			return true;
		else
			return false;
	}

	// inserting user data (userid, userfullname, userpass, usertype)
	public boolean registerUser(String userID, String userFullName, String userPass, String userType) {
		if (!isUserExists(userID)) {
			FileOperation register = new FileOperation();
			if (register.writeUser(userID, userFullName, userPass, userType))
				return true;
			else
				return false;
		} else {
			return false;
		}
	}
}
	