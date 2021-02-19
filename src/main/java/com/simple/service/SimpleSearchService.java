package com.simple.service;

import java.util.List;
import java.util.Map;

import com.simple.model.FileContent;

public interface SimpleSearchService {
	public Map<String,Integer> findInFiles(String searchLine, List<FileContent> fileContentList);
}
