package ca.wbac.che.services;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class RomanNumeralDictionaryTest {
	RomanNumeralDictionary fixture = new RomanNumeralDictionary();
	
	@Test
	public void shouldDisallowNonRomanNumerals() {
		assertThat(fixture.isValidRomanNumeral.test("W"), is(false));
		assertThat(fixture.isValidRomanNumeral.test("C"), is(true));
		assertThat(fixture.isValidRomanNumeral.test("5"), is(false));
	}
	
	@Test
	public void shouldDisallowRepeatedValues() {
		assertThat(fixture.isValidRomanNumeral.test("III"), is(true));
		assertThat(fixture.isValidRomanNumeral.test("IIII"), is(false));
	}
}
