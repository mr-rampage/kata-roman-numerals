package ca.wbac.che.services;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Predicate;
import java.util.regex.Pattern;

public class RomanNumeralDictionary {
	private final String REGEX_VALID_ROMAN_NUMERAL = "^M{0,4}(CM|CD|D?C{0,3})(XC|XL|L?X{0,3})(IX|IV|V?I{0,3})$";
	private final Map<Character, Integer> dictionary = getRomanNumeralDictionary();

	public final Predicate<String> isValidRomanNumeral = Pattern.compile(
			REGEX_VALID_ROMAN_NUMERAL).asPredicate();

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

	public Integer get(final Character symbol) {
		return dictionary.getOrDefault(symbol, 0);
	}
}
