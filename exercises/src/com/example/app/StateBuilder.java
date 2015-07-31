package com.example.app;

import java.util.Collections;
import java.util.List;

public class StateBuilder {
	String command = "";
	String argument = "";
	List<String> words = Collections.emptyList();
	Boolean isRunning = false;

	public StateBuilder withState(ApplicationState state) {
		this.command = state.getCommand();
		this.argument = state.getArgument();
		this.words = state.getWords();
		this.isRunning = state.isRunning();
		return this;
	}
	
	public ApplicationState withNewWords(List<String> words) {
		this.words = words;
		return new CurrentApplicationState(this);
	}
	
	public ApplicationState withNewCommand(String line) {
		this.command = line.split(" ")[0];
		this.argument = line.substring(this.command.length()).trim();
		return new CurrentApplicationState(this);
	}
	
	public ApplicationState andStop() {
		this.isRunning = false;
		return new CurrentApplicationState(this);
	}
	
	public ApplicationState build() {
		return new CurrentApplicationState(this);
	}
	
}