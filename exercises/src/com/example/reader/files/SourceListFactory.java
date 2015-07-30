package com.example.reader.files;

public class SourceListFactory {

	public static SourceList create(String filename) {
		if (filename.contains("*") || filename.contains("?")) {
			return new FilteredFilesSourceList(filename);
		} else {
			return new FileSourceList(filename);
		}
	}

}
