package com.example;

import java.util.Scanner;

public class Application {

	public static void main(String[] args) throws Exception {

		System.out.println("Witaj");
		System.out.println("Czy kontynuowaæ? (t/n)");
		
		Scanner scanner = new Scanner(System.in);
		
		boolean run = true;
		while (run) {
			String line = scanner.nextLine();
			System.out.println("Wpisano: " + line);
			run = (line.equals("t") == true);			
		}
		
		scanner.close();
		System.out.println("Koniec...");
	}
}
