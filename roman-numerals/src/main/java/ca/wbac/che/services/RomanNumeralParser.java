package ca.wbac.che.services;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class RomanNumeralParser {

	private final RomanNumeralAdapter adapter;
	final Predicate<String> isValidRomanNumeral;
	final Predicate<Integer> canSubtract;

	public RomanNumeralParser() {
		RomanNumeralDictionary dictionary = new RomanNumeralDictionary();
		String validCharacters = dictionary.getSymbolString();
		
		adapter = new RomanNumeralAdapter(dictionary);
		isValidRomanNumeral = createValidator(validCharacters);
		canSubtract = RuleFactory.isSpecialValue(1, 10, 100);
	}

	public Integer valueOf(final String romanNumerals) {
		if (isValidRomanNumeral.test(romanNumerals)) {
			Stream<Integer> valueStream = adapter.toIntegerStream(romanNumerals);
			return this.valueOf(valueStream);
		}
		return null;
	}

	private Integer valueOf(final Stream<Integer> valueStream) {
		Integer total = 0;
		Integer lastValue = 0;
		
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

	private Predicate<String> createValidator(
			final String validCharacters) {
		Predicate<String> containsInvalidRepeat = RuleFactory
				.hasRepeatingSymbols(4);
		Predicate<String> containsValidCharactersOnly = RuleFactory
				.hasValidCharactersOnly(validCharacters);

		return containsInvalidRepeat.negate().and(containsValidCharactersOnly);
	}

}
