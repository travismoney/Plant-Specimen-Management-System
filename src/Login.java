
public class Login {
	
	private boolean isLoggedIn;
	private String userType;
	
	// Login process 
	public boolean login(String idNo, String password, String userType) {
		FileOperation login = new FileOperation();
		if (login.checkCredential(idNo, password, userType)) {
			this.isLoggedIn = true;
			this.userType = userType;
			return true;
		} else {
			return false;
		}
	}
	
	// returns login status (true / false)
	public boolean isLoggedIn() {
		return this.isLoggedIn;
	}
	
	// returns user type (Researcher/Admin)
	public String getUserType() {
		return this.userType;
	}
}
