package com.simple.utility;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import com.simple.constants.Constants;
import com.simple.model.FileContent;

public class FileProcessorEngine {

	public static List<FileContent> loadContent(File[] listOfFiles) {
		List<FileContent> fileContentList = new ArrayList<>();
		for(File file : listOfFiles) {
			
			if(file.getAbsolutePath().contains(Constants.FILE_EXTENSION)) {
				FileContent content = new FileContent(file.getName());
				try {
					addLinesToMap(Files.lines(Paths.get(file.getAbsolutePath())), content);
				} catch (IOException e) {
					System.out.println("Error occured while loading :"+file.getName());
				}
				fileContentList.add(content);
			}
		}
		return fileContentList;
	}
	

	public static void addLinesToMap(Stream<String> lines, FileContent fileContent) {
		lines.forEach(line -> {
				WordProcessor.buildWordCountMap(line, fileContent.getWordCountMap());
		});
	}

}
