package com.example.exit;

import com.example.Action;
import com.example.app.ApplicationState;

public class ExitAction extends Action {

	public ExitAction(ApplicationState app) {
		super(app);
	}

	@Override
	public ApplicationState execute() {
		return this.app.newState().andStop();
	}

	@Override
	public boolean isValid() {
		return true;
	}

}
