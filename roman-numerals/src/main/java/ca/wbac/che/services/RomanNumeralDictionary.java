package ca.wbac.che.services;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class RomanNumeralDictionary {
	private final Map<Character, Integer> numeralDictionary = new HashMap<>();

	public RomanNumeralDictionary() {
		numeralDictionary.putAll(getRomanNumeralDictionary());
	}
	
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

	public Integer get(Character symbol) {
		return numeralDictionary.getOrDefault(symbol, 0);
	}
	
	public String getSymbolString() {
		return numeralDictionary.keySet().stream().map(c -> c.toString())
				.collect(Collectors.joining());
	}
}
