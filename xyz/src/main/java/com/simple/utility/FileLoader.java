package com.simple.utility;

import java.io.File;

public class FileLoader {

	public static File[] loadFiles(File file) {
		return file.listFiles();
	}
}
