package com.blockwit.utils;

import io.vavr.control.Either;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DoubleHumanAdoptedParser {

	public static final int DEF_DOUBLE_LIMITER = 36;

	public static final int ERR_CODE_VALUE_IS_NULL = 0;
	public static final int ERR_CODE_VALUE_IS_EMPTY = 1;
	public static final int ERR_CODE_VALUE_NOT_MATCHED = 2;
	public static final int ERR_CODE_VALUE_LENGTH_LIMIT = 3;

	public static final Pattern PATTERN_DOUBLE = Pattern.compile("([\\s\\d]+)([.,]{1}([\\s\\d]+))?");

	public static Either<Integer, Double> parseDoubleWithPercent(String value) {
		if (value == null)
			return Either.left(ERR_CODE_VALUE_IS_NULL);
		value = value.trim();
		if (value.endsWith("%"))
			value = value.substring(0, value.length() - 1);
		return parseDouble(value);
	}

	public static Either<Integer, Double> parseDouble(String value) {
		if (value == null)
			return Either.left(ERR_CODE_VALUE_IS_NULL);
		value = value.trim();
		if (value.isEmpty())
			return Either.left(ERR_CODE_VALUE_IS_EMPTY);

		Matcher matcher = PATTERN_DOUBLE.matcher(value);
		if (!matcher.find())
			return Either.left(ERR_CODE_VALUE_NOT_MATCHED);

		String splitter = value.contains(".") ? "\\." : ",";

		String[] splitValues = value.split(splitter);

		String mainPart = fixIntegerPart(splitValues[0]);
		String secondPart = fixIntegerPart(splitValues.length > 1 ? splitValues[1] : null);
		String valueToParse = mainPart + "." + secondPart;

		if (valueToParse.length() > DEF_DOUBLE_LIMITER * 2 + 1)
			return Either.left(ERR_CODE_VALUE_LENGTH_LIMIT);

		Double result = Double.parseDouble(valueToParse);

		return Either.right(result);
	}

	private static String fixIntegerPart(String value) {
		if (value == null)
			return "0";
		value = value.trim();
		if (value.isEmpty())
			return "0";
		value = value.replaceAll("\\s", "");

		return value;
	}


}
