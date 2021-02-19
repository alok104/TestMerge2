package com.simple.utility;

import java.util.Map;

import com.simple.constants.Constants;

public class WordProcessor {

	public static void buildWordCountMap(String line, Map<String, Integer> wordCountMap) {
		for (String word : line.split(Constants.SPACE)) {
			word = word.replaceAll("[^a-zA-Z0-9]+","");
			if (wordCountMap.containsKey(word)) {
				wordCountMap.put(word, wordCountMap.get(word) + 1);
			} else {
				wordCountMap.put(word, 1);
			}
		}
	}

}
