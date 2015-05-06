package ca.wbac.che.services;

import java.util.function.Predicate;
import java.util.regex.Pattern;

public class RuleFactory {
	static Predicate<String> hasRepeatingSymbols(Integer repeatValue) {
		String regexInvalidRepeat = "(.)\\1{" + (repeatValue - 1) + "}";
		return Pattern.compile(regexInvalidRepeat).asPredicate();
	}
	
	static Predicate<String> hasValidCharactersOnly(String validCharacters) {
		String regexValidCharacters = "^[" + validCharacters + "]+$";
		return Pattern.compile(regexValidCharacters).asPredicate();
	}
}
