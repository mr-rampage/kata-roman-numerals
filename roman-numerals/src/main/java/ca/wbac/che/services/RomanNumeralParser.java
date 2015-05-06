package ca.wbac.che.services;

import java.util.function.Predicate;

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
			return adapter.toInteger(romanNumerals);
		}
		return null;
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
