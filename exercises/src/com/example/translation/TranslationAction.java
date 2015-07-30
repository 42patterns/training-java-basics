package com.example.translation;

import java.io.IOException;
import java.net.URL;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.example.Action;
import com.example.app.ApplicationState;

public class TranslationAction extends Action {

	public TranslationAction(ApplicationState app) {
		super(app);
	}
	
	@Override
	public ApplicationState execute() {
		String word = app.getWords().get(Integer.valueOf(app.getArgument()));
		
		System.out.println("Tłumaczenie dla słowa: " + word);
		try(Scanner s = new Scanner(new URL("http://www.dict.pl/dict?words=&lang=PL&word="+word).openStream())) {
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
		} catch (IOException e) {
			throw new IllegalStateException(e);
		}
		
		return app;
	}

	@Override
	public boolean isValid() {
		if (!app.getArgument().matches("\\d+")) {
			System.out.println("Argument to nie liczba.");
			return false;
		}
		
		if (app.getWords().isEmpty()) {
			System.out.println("Nie załadowano słów do tłumaczenia.");
			return false;
		}
		
		if (Integer.valueOf(app.getArgument()) > app.getWords().size()) {
			System.out.println("Zbyt wysoki index.");
			return false;
		}
		
		return true;
	}

}
