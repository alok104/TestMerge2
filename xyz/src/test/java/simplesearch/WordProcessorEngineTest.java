package simplesearch;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import com.simple.utility.WordProcessor;

public class WordProcessorEngineTest {
	@Test
	public void testWordCount() {
		String testStr = "to do or not to do";
		Map<String,Integer> wordCountMap = new HashMap<>();
		WordProcessor.buildWordCountMap(testStr, wordCountMap);
		assertEquals(wordCountMap.get("to"), Integer.valueOf(2));
		assertNotEquals(wordCountMap.get("do"), Integer.valueOf(3));
	}
}
