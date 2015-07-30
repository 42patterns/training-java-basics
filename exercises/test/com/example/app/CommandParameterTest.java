package com.example.app;

import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class CommandParameterTest {

	@Test
	public void should_parse_no_argument_string() {
		CommandParameter p = new CommandParameter("exit    ");
		
		assertThat(p.getCommand(), is("exit"));
		assertThat(p.getArgument(), is(""));
	}
	
	@Test
	public void should_parse_argument_string() {
		CommandParameter p = new CommandParameter("read words.txt");
		
		assertThat(p.getCommand(), is("read"));
		assertThat(p.getArgument(), is("words.txt"));
	}
	
	@Test
	public void should_parse_no_argument_string_with_whitespaces() {
		CommandParameter p = new CommandParameter("exit    ");
		
		assertThat(p.getCommand(), is("exit"));
		assertThat(p.getArgument(), is(""));
	}


}
