package com.example.translation;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
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
		List<Tuple<String, String>> translations = collectWords(word);

		for (Tuple<String, String> t: translations) {
			System.out.println(t.x + " - " + t.y);
		}
		
		return app;
	}

	private List<Tuple<String, String>> collectWords(String word) {
		List<String> words = extractWords(word);
		
		List<Tuple<String, String>> translations = new ArrayList<>();
		for (int i=0; i<words.size(); i+=2) {
			translations.add(new Tuple<String, String>(words.get(i), words.get(i+1)));
		}
		
		return translations;
	}

	private List<String> extractWords(String word) {
		List<String> words = new ArrayList<>();
		try (Scanner s = new Scanner(new URL("http://www.dict.pl/dict?words=&lang=PL&word=" + word).openStream())) {
			Pattern pat = Pattern.compile(".*<a href=\"dict\\?words?=(.*)&lang.*");
			while (s.hasNext()) {
				String string = s.nextLine();
				Matcher matcher = pat.matcher(string);
				if (matcher.find()) {
					words.add(matcher.group(1));
				}
			}
		} catch (IOException e) {
			throw new IllegalStateException(e);
		}
		return words;
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

	public static class Tuple<X, Y> {
		public final X x;
		public final Y y;

		public Tuple(X x, Y y) {
			this.x = x;
			this.y = y;
		}
	}
}
