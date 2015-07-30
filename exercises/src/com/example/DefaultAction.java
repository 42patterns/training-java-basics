package com.example;

import com.example.app.ApplicationState;

public class DefaultAction extends Action {

	public DefaultAction(ApplicationState app) {
		super(app);
	}

	@Override
	public ApplicationState execute() {
		return app;
	}

}
