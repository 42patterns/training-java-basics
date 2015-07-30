package com.example;

import java.util.Scanner;

import com.example.app.ApplicationState;
import com.example.app.CurrentApplicationState;
import com.example.app.EmptyApplicationState;
import com.example.exit.ExitAction;
import com.example.reader.ReadAction;
import com.example.translation.TranslationAction;

public class Application {

	public static void main(String[] args) throws Exception {

		ApplicationState app = new EmptyApplicationState();
		Scanner scanner = new Scanner(System.in);
		
		while (app.isRunning()) {
			System.out.print("{ ~ } » ");
			app = new CurrentApplicationState(app, scanner.nextLine());			

			if ("exit".equals(app.getCommand())) {
				app = new ExitAction(app).execute();
			} else if ("read".equals(app.getCommand())) {
				app = new ReadAction(app).execute();
			} else if ("translate".equals(app.getCommand())) {				
				app = new TranslationAction(app).execute();
			} 
		}
		
		scanner.close();
		System.out.println("Koniec...");
	}

}

