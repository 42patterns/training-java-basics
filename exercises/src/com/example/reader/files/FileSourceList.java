package com.example.reader.files;

import java.io.File;

public class FileSourceList implements SourceList {

	private String filename;

	public FileSourceList(String filename) {
		this.filename = filename;
	}

	@Override
	public File[] getFiles() {
		File f = new File(filename);
		if (f.exists()) {
			return new File[] { f };
		}
		
		return new File[] {};
	}

}
