package net.plannet.util;

public class RequestResult {
	public final static String Success = "Success";
	public final static String Fail = "Fail";
	
	//LogIn
	public final static String EmailNotFound = "EmailNotFound";
	public final static String PasswordDismatch = "PasswordDismatch";
	public final static String UUIDNotFound = "UUIDNotFound";
	
	//LogOut
	public final static String AlreadyLogout = "AlreadyLogout";
	
	//SignUp
	public final static String EmailOverlap = "EmailOverlap";
	public final static String FormIncorrect = "FormIncorrect";

}
