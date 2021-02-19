package com.simple.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.simple.model.FileContent;
import com.simple.utility.WordProcessor;

public class SimpleSearchServiceImpl implements SimpleSearchService {
	
	private int numberOfWordsSearched;
	private int numberOfWordFound;
	
	public int getFrequency() {
		if(numberOfWordsSearched == 0 || numberOfWordFound == 0) {
			return 0;
		}
		return 100/numberOfWordsSearched * numberOfWordFound;
	}

	public void searchInContent(Map<String, Integer> wordCountMap, FileContent fileContent) {
		wordCountMap.entrySet().stream().forEach(wordEntry-> {
			if(fileContent.getWordCountMap().containsKey(wordEntry.getKey())) {
				
				int searchedWordsCount = wordEntry.getValue();
				int wordsPresentInContentCount = fileContent.getWordCountMap().get(wordEntry.getKey());
				
				if(searchedWordsCount > wordsPresentInContentCount) {
					numberOfWordFound += (searchedWordsCount - wordsPresentInContentCount);
				} else if(searchedWordsCount <= wordsPresentInContentCount) {
					numberOfWordFound += searchedWordsCount;
				}
				
			}
		});
	}
	

	@Override
	public Map<String,Integer> findInFiles(String searchLine, List<FileContent> fileContentList) {
		Map<String,Integer> frequncyMap = new HashMap<>();
		numberOfWordsSearched = searchLine.split(" ").length;
		Map<String, Integer> wordCountMap = new HashMap<String, Integer>();
		WordProcessor.buildWordCountMap(searchLine, wordCountMap);
		fileContentList.stream().forEach(fileContent -> {
			searchInContent(wordCountMap,fileContent);
			frequncyMap.put(fileContent.getFileName(),getFrequency());
		});
		return frequncyMap;
	}
	
	
}
