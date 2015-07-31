package com.example.app;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;

public class CommandParameterTest {

	private ApplicationState baseState;
	
	@Before
	public void setup() {
		baseState = new EmptyApplicationState();
	}
	
	@Test
	public void should_parse_no_argument_string() {
		ApplicationState p = baseState.newState().withNewCommand("exit");
		
		assertThat(p.getCommand(), is("exit"));
		assertThat(p.getArgument(), is(""));
	}
	
	@Test
	public void should_parse_argument_string() {
		ApplicationState p = baseState.newState().withNewCommand("read words.txt");
		
		assertThat(p.getCommand(), is("read"));
		assertThat(p.getArgument(), is("words.txt"));
	}
	
	@Test
	public void should_parse_no_argument_string_with_whitespaces() {
		ApplicationState p = baseState.newState().withNewCommand("exit    ");
		
		assertThat(p.getCommand(), is("exit"));
		assertThat(p.getArgument(), is(""));
	}


}
