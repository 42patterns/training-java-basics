package com.example.app;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CurrentApplicationState implements ApplicationState {

	private String command;
	private String argument;
	private List<String> words = Collections.emptyList();
	
	public CurrentApplicationState(ApplicationState app, String nextLine) {
		this.command = nextLine.split(" ")[0];
		this.argument = nextLine.substring(this.command.length()).trim();
		this.words = app.getWords();
	}

	public CurrentApplicationState(ApplicationState app, List<String> words) {
		this.command = app.getCommand();
		this.argument =  app.getArgument();
		this.words = words;
	}
	
	@Override
	public String getCommand() {
		return command;
	}
	
	@Override
	public String getArgument() {
		return argument;
	}

	@Override
	public List<String> getWords() {
		return new ArrayList<>(words);
	}

	@Override
	public String toString() {
		return "ApplicationState [command=" + command + ", argument=" + argument + ", words=" + words.size() + "]";
	}
	
	

}
