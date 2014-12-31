package net.plannet.util;

public class ErrorUtil {
	public static void printError(String message, Exception e) {
		System.out.println("[" + message + "]" + " : " + e.getMessage());
		e.printStackTrace();
	}
}
