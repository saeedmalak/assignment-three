package com.coderscampus.uservalidation;
import java.util.Scanner;

public class UserLoginApplication {

	public static void main(String[] args) {

		// instantiate the userService & scanner variables 
		UserService userService = new UserService();
		Scanner scanner = new Scanner(System.in);
		
		// ask user some information
		System.out.println("Enter your email:");
		String userEmailInput = scanner.nextLine().toLowerCase();
		System.out.println("Enter your password:"); 
		String userPasswordInput = scanner.nextLine();
		
		// call the validateUser function passing in those two input parameters above
		userService.validateUser(userEmailInput, userPasswordInput);
		
		// close the scanner if its not 'null'
		if (scanner != null) 
			scanner.close();
	}

}
