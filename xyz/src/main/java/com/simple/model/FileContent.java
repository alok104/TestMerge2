package com.simple.model;

import java.util.HashMap;
import java.util.Map;

public class FileContent {
	private String fileName;
	private Map<String, Integer> wordCountMap;
	public FileContent(String name) {
		this.fileName = name;
		wordCountMap = new HashMap<>();
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public Map<String, Integer> getWordCountMap() {
		return wordCountMap;
	}
	public void setWordCountMap(Map<String, Integer> wordCountMap) {
		this.wordCountMap = wordCountMap;
	}
	
}
