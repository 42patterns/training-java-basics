package com.example;

import com.example.app.ApplicationState;

public abstract class Action {
	protected ApplicationState app;
	
	public Action(ApplicationState app) {
		this.app = app;
	}
	
	public abstract ApplicationState execute();
	
}
