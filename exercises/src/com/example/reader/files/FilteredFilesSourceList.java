package com.example.reader.files;

import java.io.File;
import java.io.FileFilter;

public class FilteredFilesSourceList implements SourceList {

	private String filename;

	public FilteredFilesSourceList(String filename) {
		this.filename = filename;
	}

	@Override
	public File[] getFiles() {	
		File dir = new File("./");
		return dir.listFiles(new FileFilter() {

			public boolean accept(File pathname) {
				String wildcardFilename = filename.replace("?", ".").replace("*", ".*");
				return (pathname.isFile() && pathname.getName().matches(wildcardFilename));
			}
		});
	}

}
