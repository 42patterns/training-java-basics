package com.example;

import java.util.Scanner;

import com.example.app.ApplicationState;
import com.example.app.CurrentApplicationState;
import com.example.app.EmptyApplicationState;

public class Application {

	public static void main(String[] args) throws Exception {

		ApplicationState app = new EmptyApplicationState();
		Scanner scanner = new Scanner(System.in);
		
		while (app.isRunning()) {
			System.out.print("{ ~ } » ");
			app = new CurrentApplicationState(app, scanner.nextLine());
			app = ActionFactory.getAction(app).execute();
		}
		
		scanner.close();
		System.out.println("Koniec...");
	}

}

