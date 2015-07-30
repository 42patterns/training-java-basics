package com.example.app;

import java.util.List;

public interface ApplicationState {

	String getCommand();

	String getArgument();

	List<String> getWords();

}