package ca.wbac.che.services;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class RomanNumeralAdapter {

	private final RomanNumeralDictionary dictionary;
	
	public RomanNumeralAdapter(RomanNumeralDictionary dictionary) {
		this.dictionary = dictionary;
	}

	private Stream<Integer> toIntegerStream(final String romanNumerals) {
		return romanNumerals.chars().mapToObj(c -> (char) c)
				.map(dictionary::get);
	}
	
	public Integer toInteger(final String romanNumerals) {
		Integer total = 0;
		Integer lastValue = 0;
		Stream<Integer> valueStream = toIntegerStream(romanNumerals);
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
}
