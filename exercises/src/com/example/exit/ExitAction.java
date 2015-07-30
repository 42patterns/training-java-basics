package com.example.exit;

import com.example.Action;
import com.example.app.ApplicationState;
import com.example.app.CurrentApplicationState;

public class ExitAction extends Action {

	public ExitAction(ApplicationState app) {
		super(app);
	}

	@Override
	public ApplicationState execute() {
		return new CurrentApplicationState(app, false);
	}

}
