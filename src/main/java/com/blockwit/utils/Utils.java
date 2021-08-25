package com.blockwit.utils;

import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Supplier;

@Slf4j
public class Utils {

	public static <T> void whileTrue(
		Supplier<List<T>> supplier,
		Consumer<List<T>> consumer) {
		List<T> result = supplier.get();
		while (!result.isEmpty()) {
			consumer.accept(result);
			result = supplier.get();
		}
	}

	public static void withDurationInfo(String label, Runnable f) {
		long startTime = System.currentTimeMillis();
		log.trace("Starts " + label);
		f.run();
		long endTime = System.currentTimeMillis();
		long duration = endTime - startTime;
		log.trace("Duration for \"" + label + "\" - " + duration);
	}

}
