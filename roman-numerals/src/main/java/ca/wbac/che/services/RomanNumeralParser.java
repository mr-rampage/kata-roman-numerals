package ca.wbac.che.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class RomanNumeralParser {

	private final Map<Character, Integer> numeralDictionary = new HashMap<>();
	final Predicate<String> isValidRomanNumeral;

	public RomanNumeralParser() {
		numeralDictionary.putAll(getRomanNumeralDictionary());
		isValidRomanNumeral = createValidator(numeralDictionary.keySet()
				.stream());
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

	public Integer valueOf(String romanNumerals) {
		if (isValidRomanNumeral.test(romanNumerals)) {
			return this.valueOf(romanNumerals, numeralDictionary);
		}
		return null;
	}

	private Integer valueOf(final String romanNumerals,
			final Map<Character, Integer> dictionary) {
		Integer total = 0;
		Integer lastValue = 0;
		Stream<Integer> valueStream = convertToIntegers(romanNumerals.chars(),
				dictionary);
		List<Integer> values = valueStream.collect(Collectors.toList());
		
		for (Integer value : values) {
			if (lastValue < value) {
				total -= 2 * lastValue;
			}
			total += value;
			lastValue = value;
		}
		
		return total;
	}

	private Stream<Integer> convertToIntegers(final IntStream symbolStream,
			final Map<Character, Integer> dictionary) {
		return symbolStream.mapToObj(c -> (char) c)
				.map(symbol -> dictionary.getOrDefault(symbol, 0));
	}

	private Predicate<String> createValidator(
			final Stream<Character> validSymbols) {
		String validCharacters = validSymbols.map(c -> c.toString()).collect(
				Collectors.joining());
		Predicate<String> containsInvalidRepeat = RuleFactory
				.hasRepeatingSymbols(4);
		Predicate<String> containsValidCharactersOnly = RuleFactory
				.hasValidCharactersOnly(validCharacters);

		return containsInvalidRepeat.negate().and(containsValidCharactersOnly);
	}

}
