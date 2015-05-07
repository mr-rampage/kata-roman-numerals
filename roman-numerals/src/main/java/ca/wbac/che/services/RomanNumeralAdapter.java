package ca.wbac.che.services;

import java.util.List;
import java.util.Optional;
import java.util.function.IntPredicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class RomanNumeralAdapter {

	private final RomanNumeralDictionary dictionary;

	public RomanNumeralAdapter(final RomanNumeralDictionary dictionary) {
		this.dictionary = dictionary;
	}

	public Integer toInteger(final String romanNumerals) {
		return Optional.ofNullable(romanNumerals)
				.filter(this.dictionary.isValidRomanNumeral)
				.map(this::toIntegerList).map(this::toInteger).orElse(null);
	}

	private List<Integer> toIntegerList(final String romanNumerals) {
		return romanNumerals.chars().mapToObj(c -> (char) c)
				.map(dictionary::get).collect(Collectors.toList());
	}

	private Integer toInteger(final List<Integer> values) {
		IntPredicate isSmallerThanNext = i -> values.get(i) < values.get(i + 1);
		Integer total = values.stream().mapToInt(Integer::intValue).sum();
		Integer subtraction = 2 * IntStream.range(0, values.size() - 1)
				.filter(isSmallerThanNext).map(values::get).sum();
		return total - subtraction;
	}

}
