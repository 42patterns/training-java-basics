package com.example;

import java.io.File;
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
				
				if (l.length == 1) {
					System.out.println("Niepoprawna nazwa pliku");
					continue;
				}
				String filename = l[1];
				
				if (!filename.endsWith("txt") && !filename.endsWith("csv")
						&& !filename.endsWith("xml") && !filename.endsWith("json")) {
					System.out.println("Brak rozszerzenia. Używam domyślnego: txt");
					filename = filename + ".txt";
				}
				
				if (!filename.matches("[a-zA-Z0-9]+\\.[a-z]{1,4}")) {
					System.out.println("Nieporawna nazwa pliku [" +filename+ "]");
					continue;
				}
								
				File f = new File(filename);
				if (!f.exists()) {
					System.out.println("Plik [" +filename+ "] nie istnieje.");
					continue;					
				}
				
				System.out.println("Odczyt z pliku: " + filename);
				
				Scanner fs = new Scanner(f);
				while (fs.hasNext()) {
					System.out.println(fs.nextLine());
				}
			}
		}
		
		scanner.close();
		System.out.println("Koniec...");
	}
}
