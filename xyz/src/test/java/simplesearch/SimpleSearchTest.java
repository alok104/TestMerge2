package simplesearch;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

import java.io.File;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import com.simple.model.FileContent;
import com.simple.service.SimpleSearchService;
import com.simple.service.SimpleSearchServiceImpl;
import com.simple.utility.FileLoader;
import com.simple.utility.FileProcessorEngine;
public class SimpleSearchTest {
	
	private List<FileContent> fileContentList;
	@Before
	public void setUp() {
		File file = new File("src/test/resources");
		File[] listOfFiles = FileLoader.loadFiles(file);

		fileContentList = FileProcessorEngine.loadContent(listOfFiles);
	}

	
	@Test
	public void testSearch() {
		
		String searchLine = "Dubai is great city";
		
		SimpleSearchService searchService = new SimpleSearchServiceImpl();
		Map<String, Integer> frequencyMap = searchService.findInFiles(searchLine, fileContentList);
		assertNotNull(frequencyMap);
		assertFalse(frequencyMap.get("file1.txt") < 30);
		assertNotEquals(frequencyMap.get("file2.txt"),Integer.valueOf(20));
		assertEquals(frequencyMap.get("file2.txt"),Integer.valueOf(100));

	}
	
}
