package com.simple;

import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import com.simple.model.FileContent;
import com.simple.service.SimpleSearchService;
import com.simple.service.SimpleSearchServiceImpl;
import com.simple.utility.FileLoader;
import com.simple.utility.FileProcessorEngine;

public class SimpleSearch {
	public static void main(String[] args) {
		if (args.length == 0) {
			throw new IllegalArgumentException("No directory given to index.");
		}
		final File indexableDirectory = new File(args[0]);
		init(indexableDirectory);
	}

	public static void init(File indexableDirectory) {
		// Fetching list of files from current directory
		File[] listOfFiles = FileLoader.loadFiles(indexableDirectory);
		if(listOfFiles == null || listOfFiles.length == 0) {
			throw new IllegalArgumentException("No directory given to index.");
		}

		List<FileContent> fileContentList = FileProcessorEngine.loadContent(listOfFiles);
		
		System.out.println(listOfFiles.length + " files read in directory");
		if (fileContentList.isEmpty()) {
			System.out.println("No text file found in current directory.");
			return;
		}
		String searchLine = getInput();
		
		SimpleSearchService searchService = new SimpleSearchServiceImpl();
		Map<String,Integer> frequncymap = searchService.findInFiles(searchLine, fileContentList);
		frequncymap.entrySet().stream().forEach(frequncyMap-> System.out.println(frequncyMap.getKey()+":"+frequncyMap.getValue()+"%"));

	}

	private static String getInput() {
		String searchLine = null;
		while (searchLine == null || searchLine.isEmpty()) {
			System.out.println("search >");
			@SuppressWarnings("resource")
			Scanner scanner = new Scanner(System.in);
			searchLine = scanner.nextLine();
		}
		return searchLine;
	}
}
