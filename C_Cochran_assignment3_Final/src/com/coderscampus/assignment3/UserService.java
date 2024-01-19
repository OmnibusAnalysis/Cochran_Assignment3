package com.coderscampus.assignment3;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;


public class UserService {

	private boolean validateUser;

	public User[] readFile() {
		User[] users = new User[4];
		try (BufferedReader fileReader = new BufferedReader(new FileReader("data.txt"))) {
			String line;
			int i = 0;
			while ((line = fileReader.readLine()) != null) {
				String[] userData = line.split(",");
				User user = createUser(userData[0], userData[1], userData[2]);
				users[i] = user;
				i++;
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {

		}

		return users;

	}

	public User createUser(String username, String password, String name) {
		User user = new User(username, password, name);
		user.setUsername(username);
		user.setPassword(password);
		user.setName(name);

		return user;
	}

	public User validateUser(String name, String username, String password) {
		for (User user : readFile()) {
			if (user.getUsername().equalsIgnoreCase(username) && user.getPassword().equals(password)) {
				return user;
			} else {
				System.out.println("Invalid Username or Password.");

			}

		}
		return null;
	}

	public void userLogin() {
		UserService userService = new UserService();
		User[] user = userService.readFile();
		User user1 = null;

		int inputCount = 0;

		while (inputCount < 5) {
			Scanner scanner = new Scanner(System.in);
			System.out.println("Please enter your name: ");
			String name = scanner.nextLine();
			System.out.println("Hello, " + name + ". Please enter your Username/Email: ");
			String username = scanner.nextLine();
			System.out.println("Please enter your password:");
			String password = scanner.nextLine();
			user1 = userService.validateUser(name, username, password);
			{

				if (userService.validateUser) {
					System.out.println("Welcome, " + name + ".");
				} else {
					System.out.println("Invalid Username or Password.");
				}
				if (inputCount > 5) {
					System.out.println("Too many invlid attemtps. Go to your room.");
				}

				scanner.close();
			}
			inputCount++;
			if (user1 !=null) {
				System.out.println("Welcome, " + user1.getName());
				break; 
			} else if (inputCount < 5 && user1 ==null) {
				System.out.println("Invalid login, please try again");
			}
				
		}
		if (inputCount == 5 && user1 == null) {
			System.out.println("Too many failed login attempts, you are eternally locked out.");
			}
	
	
	}

}

