package ca.wbac.che.services;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class RomanNumeralParserTest {
	RomanNumeralParser fixture = new RomanNumeralParser();
	
	@Test
	public void shouldConvertITo1() {
		Integer actual = fixture.valueOf("I");
		assertThat(actual, is(1));
	}
	
	@Test
	public void shouldSumWhenNumbersSmaller() {
		assertThat(fixture.valueOf("III"), is(3));
		assertThat(fixture.valueOf("VIII"), is(8));
	}
	
	@Test
	public void shouldSubtractWhenNumbersLarger() {
		assertThat(fixture.valueOf("IV"), is(4));
		assertThat(fixture.valueOf("IX"), is(9));
		
	}
	
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
