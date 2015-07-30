package com.example;

import com.example.app.ApplicationState;
import com.example.exit.ExitAction;
import com.example.reader.ReadAction;
import com.example.translation.TranslationAction;

public class ActionFactory {

	public static Action getAction(ApplicationState app) {
		if ("exit".equals(app.getCommand())) {
			return new ExitAction(app);
		} else if ("read".equals(app.getCommand())) {
			return new ReadAction(app);
		} else if ("translate".equals(app.getCommand())) {				
			return new TranslationAction(app);
		} 
		
		return new DefaultAction(app);
	}
	
}
