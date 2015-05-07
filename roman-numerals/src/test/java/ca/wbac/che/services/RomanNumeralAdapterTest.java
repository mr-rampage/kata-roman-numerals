package ca.wbac.che.services;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class RomanNumeralAdapterTest {
	RomanNumeralDictionary dictionary = new RomanNumeralDictionary();
	RomanNumeralAdapter fixture = new RomanNumeralAdapter(dictionary);
	
	@Test
	public void shouldSumWhenNumbersSmaller() {
		assertThat(fixture.toInteger("III"), is(3));
		assertThat(fixture.toInteger("VIII"), is(8));
	}
	
	@Test
	public void shouldSubtractWhenNumbersLarger() {
		assertThat(fixture.toInteger("IV"), is(4));
		assertThat(fixture.toInteger("IX"), is(9));
		assertThat(fixture.toInteger("XLV"), is(45));
	}
	
	@Test
	public void shouldConvertToDecimal() {
		assertThat(fixture.toInteger("MCMXXV"), is(1925));
		assertThat(fixture.toInteger("CDXCIX"), is(499));
	}

}
