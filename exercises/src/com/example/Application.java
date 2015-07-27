package com.example;

import java.util.Scanner;

public class Application {

	public static void main(String[] args) throws Exception {

		Scanner scanner = new Scanner(System.in);
		
		boolean run = true;
		while (run) {
			System.out.print("{ ~ } » ");
			String line = scanner.nextLine();
			
			if ("exit".equals(line)) {
				run = false;
			} else if (line != null && line.startsWith("read")) {
				String[] l = line.split(" ");
				System.out.println("Odczyt z pliku: " + l[1]);
			}
		}
		
		scanner.close();
		System.out.println("Koniec...");
	}
}
