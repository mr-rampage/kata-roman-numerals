package ca.wbac.che.services;

import java.util.stream.Stream;

public class RomanNumeralAdapter {

	private final RomanNumeralDictionary dictionary;
	
	public RomanNumeralAdapter(RomanNumeralDictionary dictionary) {
		this.dictionary = dictionary;
	}

	public Stream<Integer> toIntegerStream(final String romanNumerals) {
		return romanNumerals.chars().mapToObj(c -> (char) c)
				.map(dictionary::get);
	}
}
