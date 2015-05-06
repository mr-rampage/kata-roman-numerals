package ca.wbac.che.services;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class RomanNumeralDictionary {
	private Map<Character, Integer> cache;
	
	private Map<Character, Integer> getRomanNumeralDictionary() {
		Map<Character, Integer> dictionary = new HashMap<>();
		dictionary.put('I', 1);
		dictionary.put('V', 5);
		dictionary.put('X', 10);
		dictionary.put('L', 50);
		dictionary.put('C', 100);
		dictionary.put('D', 500);
		dictionary.put('M', 1000);
		return dictionary;
	}
	
	private Map<Character, Integer> getDictionary() {
		if (cache == null) {
			cache = getRomanNumeralDictionary();
		}
		return cache;
	}

	public Integer get(Character symbol) {
		return getDictionary().getOrDefault(symbol, 0);
	}
	
	public String getSymbolString() {
		return getDictionary().keySet().stream().map(c -> c.toString())
				.collect(Collectors.joining());
	}
}
