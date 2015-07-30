package com.example;

import java.io.File;
import java.io.FileFilter;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.example.app.CommandParameter;

public class Application {

	public static void main(String[] args) throws Exception {
		List<String> words = new ArrayList<>();

		Scanner scanner = new Scanner(System.in);
		
		while (true) {
			System.out.print("{ ~ } » ");
			
			CommandParameter cp = new CommandParameter(scanner.nextLine());
			if ("exit".equals(cp.getCommand())) {
				break;
			} else if ("read".equals(cp.getCommand())) {
				String filename = cp.getArgument();
				
				if (filename.isEmpty()) {
					System.out.println("Niepoprawna nazwa pliku");
					continue;
				}
				
				if (!filename.endsWith("txt") && !filename.endsWith("csv")
						&& !filename.endsWith("xml") && !filename.endsWith("json")) {
					System.out.println("Brak rozszerzenia. Używam domyślnego: txt");
					filename = filename + ".txt";
				}
				
				if (!filename.matches("[a-zA-Z0-9\\*\\?]+\\.[a-z]{1,4}")) {
					System.out.println("Nieporawna nazwa pliku [" +filename+ "]");
					continue;
				}
				
				File[] listFiles;
				if (filename.contains("*") || filename.contains("?")) {
					final String finalFileName = filename;
					File dir = new File("./");
					listFiles = dir.listFiles(new FileFilter() {
						
						public boolean accept(File pathname) {
							String wildcardFilename = finalFileName.replace("?", ".")
									.replace("*", ".*");
							return (pathname.isFile() &&
									pathname.getName().matches(wildcardFilename));
						}
					});
				} else {
					listFiles = new File[] { new File(filename) };
				}
				
				words.clear();
				for (File f: listFiles) {
					if (!f.exists()) {
						System.out.println("Plik [" +filename+ "] nie istnieje.");
						continue;					
					}
					
					System.out.println("Odczyt z pliku: " + f.getName());
					
					Scanner fs = new Scanner(f);
					while (fs.hasNext()) {
						String word = fs.nextLine();
						System.out.println("[" + words.size() + "] " + word);
						words.add(word);
					}					
				}
			} else if ("translate".equals(cp.getCommand())) {				
				String word = words.get(Integer.valueOf(cp.getArgument()));
				
				System.out.println("Tłumaczenie dla słowa: " + word);
				
				URL url = new URL("http://www.dict.pl/dict?words=&lang=PL&word="+word);
				Scanner s = new Scanner(url.openStream());
				
				Pattern pat = Pattern.compile(".*<a href=\"dict\\?words?=(.*)&lang.*");
				boolean polishWord = true;
				while(s.hasNext()) {
					String string = s.nextLine();
					Matcher matcher = pat.matcher(string);
					if (matcher.find()) {
						if (polishWord) {
							System.out.print(matcher.group(matcher.groupCount()) + " - ");
							polishWord = false;
						} else {
							System.out.println(matcher.group(matcher.groupCount()));
							polishWord = true;
						}
					}	
				}
			} 
		}
		
		scanner.close();
		System.out.println("Koniec...");
	}
	
}

