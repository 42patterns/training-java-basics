package com.example.reader.files;

import java.io.File;

public class FileSourceList implements SourceList {

	private String filename;

	public FileSourceList(String filename) {
		this.filename = filename;
	}

	@Override
	public File[] getFiles() {
		return new File[] { new File(filename) };
	}

}
