package com.coderscampus.uservalidation;
import java.io.BufferedReader;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class UserService {
	
	// setup a User users array static variable that will hold user's information
	public static User[] users = new User[4];
	
	// function to validate user's inputs and outputs the right information to the console
	public void validateUser (String username, String password) {
		User user = new User(null, null, null);
		BufferedReader fileReader = null;
		Scanner scanner = new Scanner(System.in);
		Boolean matchFound = false;

		try {

			fileReader = new BufferedReader(new FileReader("data.txt"));

			String line = "";

			// read the csv file and store all the info in users array
			int idx = 0;
			while ((line = fileReader.readLine()) != null) {
				String[] props = line.split(",");
				user.setUsername(props[0]);
				user.setPassword(props[1]);
				user.setName(props[2]);
				users[idx] = new User(props[0], props[1], props[2]);
				idx++;
			}

			// loop through 5 times until either a match is found or close the app if maximum attemps are reached
			for (int i = 1; i <= 5; i++) { 
				
				// loop through all the users info and see if there is a match or not
				for (User eachUser: users) {

					if (username.equals(eachUser.getUsername()) && password.equals(eachUser.getPassword())) {
						System.out.println("Welcome: " + eachUser.getName()); 
						matchFound = true;
					}
				}

				// close the app if 5 attempts are reached
				if (i == 5){
					System.out.println("Too many failed login attempts, you are now locked out."); 
					break;
				}

				// keep trying if attempts are less than 5
				if (matchFound == false) {
					System.out.println("Invalid Login, please try again (" + i + "/5 " + "attempts used)"); 
					System.out.println("Enter your email:");
					username = scanner.nextLine().toLowerCase();
					System.out.println("Enter your password:"); 
					password = scanner.nextLine();
				}
				// otherwise close the app if a match is found!
				else {
					break;
				}
			}

			// catch necessary exceptions
		} catch (FileNotFoundException e) {
			System.out.println("Oops, the file wasn't found");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("Oops, there was an I/O Exception");
			e.printStackTrace();
			// close fileReader and scanner
		} finally {
			try {
				fileReader.close();
				scanner.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
