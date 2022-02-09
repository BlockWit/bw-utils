package com.blockwit.utils;

import io.vavr.control.Either;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

public class DoubleHumanAdoptedParserTest {

	@Test
	public void testDoubleHumanAdoptedParser() {
		Map<String, Either<Integer, Double>> testCases = new HashMap<>();
		testCases.put("  , 89 090 001", Either.right(0.89090001));
		testCases.put(null, Either.left(DoubleHumanAdoptedParser.ERR_CODE_VALUE_IS_NULL));
		testCases.put("718 1671 1 152612 ,", Either.right(71816711152612.0));
		testCases.put("718 1671 1 152612 , 89 090 001", Either.right(71816711152612.89090001));
		testCases.put(" . 89 090 001", Either.right(0.89090001));
		testCases.put(". 89 090 001", Either.right(0.89090001));
		testCases.put(" 718 1671 1 152612 . ", Either.right(71816711152612.0));
		testCases.put(" 718 1671 1 152612 . 908912 89", Either.right(71816711152612.90891289));
		testCases.put("999999999999999999999999999999999999.999999999999999999999999999999999999", Either.right(999999999999999999999999999999999999.999999999999999999999999999999999999));
		testCases.put("9999999999999999999999999999999999999.999999999999999999999999999999999999", Either.left(DoubleHumanAdoptedParser.ERR_CODE_VALUE_LENGTH_LIMIT));
		testCases.put("", Either.left(DoubleHumanAdoptedParser.ERR_CODE_VALUE_IS_EMPTY));
		testCases.put("jkkledfw", Either.left(DoubleHumanAdoptedParser.ERR_CODE_VALUE_NOT_MATCHED));
		testCases.forEach((input, output) -> {
			Either<Integer, Double> either = DoubleHumanAdoptedParser.parseDouble(input);
			if (output.isRight()) {
				Double toCheck = (Double) output.get();
				if (either.isRight()) {
					Double result = either.get();
					if (Double.compare(result, toCheck) != 0) {
						System.err.println("Test failed for " + input + " with result: result " + result + " not equal with checked value " + toCheck);
					}
				} else {
					System.err.println("Test failed for " + input + " with result: " + either.getLeft() + ", should be " + toCheck);
				}
			} else {
				if (either.isRight()) {
					Double result = either.get();
					System.err.println("Test failed for " + input + " with result: result " + result + " not equal with checked error value " + output.getLeft());
				} else {
					if (!either.getLeft().equals(output.getLeft())) {
						System.err.println("Test failed for " + input + " with result: " + either.getLeft() + ", should be error " + output.getLeft());
					}
				}
			}

		});

	}

	@Test
	public void testDoubleHumanAdoptedParserWithPercent() {
		Map<String, Either<Integer, Double>> testCases = new HashMap<>();
		testCases.put("  , 89 090 001%", Either.right(0.89090001));
		testCases.put(null, Either.left(DoubleHumanAdoptedParser.ERR_CODE_VALUE_IS_NULL));
		testCases.put("718 1671 1 152612 , % ", Either.right(71816711152612.0));
		testCases.put("718 1671 1 152612 , 89 090 001 %   ", Either.right(71816711152612.89090001));
		testCases.put(" . 89 090 001 % ", Either.right(0.89090001));
		testCases.put(". 89 090 001%", Either.right(0.89090001));
		testCases.put(" 718 1671 1 152612 . %", Either.right(71816711152612.0));
		testCases.put(" 718 1671 1 152612 . 908912 89%", Either.right(71816711152612.90891289));
		testCases.put("999999999999999999999999999999999999.999999999999999999999999999999999999", Either.right(999999999999999999999999999999999999.999999999999999999999999999999999999));
		testCases.put("9999999999999999999999999999999999999.999999999999999999999999999999999999", Either.left(DoubleHumanAdoptedParser.ERR_CODE_VALUE_LENGTH_LIMIT));
		testCases.put("", Either.left(DoubleHumanAdoptedParser.ERR_CODE_VALUE_IS_EMPTY));
		testCases.put("jkkledfw", Either.left(DoubleHumanAdoptedParser.ERR_CODE_VALUE_NOT_MATCHED));
		testCases.forEach((input, output) -> {
			Either<Integer, Double> either = DoubleHumanAdoptedParser.parseDoubleWithPercent(input);
			if (output.isRight()) {
				Double toCheck = (Double) output.get();
				if (either.isRight()) {
					Double result = either.get();
					if (Double.compare(result, toCheck) != 0) {
						System.err.println("Test failed for " + input + " with result: result " + result + " not equal with checked value " + toCheck);
					}
				} else {
					System.err.println("Test failed for " + input + " with result: " + either.getLeft() + ", should be " + toCheck);
				}
			} else {
				if (either.isRight()) {
					Double result = either.get();
					System.err.println("Test failed for " + input + " with result: result " + result + " not equal with checked error value " + output.getLeft());
				} else {
					if (!either.getLeft().equals(output.getLeft())) {
						System.err.println("Test failed for " + input + " with result: " + either.getLeft() + ", should be error " + output.getLeft());
					}
				}
			}

		});
	}


}
