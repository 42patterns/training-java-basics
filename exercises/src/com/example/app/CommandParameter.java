package com.example.app;

public class CommandParameter {

	private String command;
	private String argument;

	public CommandParameter(String nextLine) {
		this.command = nextLine.split(" ")[0];
		this.argument = nextLine.substring(this.command.length()).trim();
	}
	
	public String getCommand() {
		return command;
	}
	
	public String getArgument() {
		return argument;
	}
	

}
