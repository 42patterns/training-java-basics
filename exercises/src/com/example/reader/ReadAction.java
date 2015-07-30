package com.example.reader;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.example.Action;
import com.example.app.ApplicationState;
import com.example.app.CurrentApplicationState;
import com.example.reader.files.SourceList;
import com.example.reader.files.SourceListFactory;

public class ReadAction extends Action {

	public ReadAction(ApplicationState app) {
		super(app);
	}

	@Override
	public ApplicationState execute() {
		String filename = app.getArgument();
		
		SourceList source = SourceListFactory.create(filename);
		File[] listFiles = source.getFiles();
		
		List<String> words = new ArrayList<>();
		for (File f: listFiles) {
			System.out.println("Odczyt z pliku: " + f.getName());
			try (Scanner fs = new Scanner(f)) {
				while (fs.hasNext()) {
					String word = fs.nextLine();
					System.out.println("[" + words.size() + "] " + word);
					words.add(word);
				}					
			} catch (FileNotFoundException e) {
				throw new IllegalStateException(e);
			}
		}
		return new CurrentApplicationState(app, words);
	
	}

	@Override
	public boolean isValid() {
		String filename = app.getArgument();
		
		if (filename.isEmpty()) {
			System.out.println("Niepoprawna nazwa pliku");
			return false;
		}
		
		if (!filename.endsWith("txt") && !filename.endsWith("csv")
				&& !filename.endsWith("xml") && !filename.endsWith("json")) {
			System.out.println("Brak rozszerzenia. Używam domyślnego: txt");
			filename = filename + ".txt";
			this.app = new CurrentApplicationState(app, "read " + filename);			
		}
		
		if (!filename.matches("[a-zA-Z0-9\\*\\?]+\\.[a-z]{1,4}")) {
			System.out.println("Nieporawna nazwa pliku [" +filename+ "]");
			return false;
		}
		
		return true;
	}

}
