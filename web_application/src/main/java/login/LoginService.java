package login;


public class LoginService {
	public boolean validateUser(String user, String password) {
		return user.equalsIgnoreCase("arjun") && password.equals("arjun");
	}

}
