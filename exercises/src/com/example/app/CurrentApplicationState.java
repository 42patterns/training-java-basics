package com.example.app;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CurrentApplicationState implements ApplicationState {

	private String command;
	private String argument;
	private List<String> words = Collections.emptyList();
	private Boolean isRunning;
	
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
	public Boolean isRunning() {
		return isRunning;
	}

	@Override
	public String toString() {
		return "CurrentApplicationState [command=" + command + ", argument=" + argument + ", words=" + words.size()
				+ ", isRunning=" + isRunning + "]";
	}

	public StateBuilder newState() {
		return new StateBuilder().withState(this);
	}
	
	CurrentApplicationState(StateBuilder builder) {
		this.command = builder.command;
		this.argument = builder.argument;
		this.words = builder.words;
		this.isRunning = builder.isRunning;
	}
	


}
