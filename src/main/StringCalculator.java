package main;

import java.util.List;

import static java.util.stream.Collectors.joining;

/**
 * @author chetana
 * @created on: 07, May 2021
 */
public class StringCalculator {

    public static int add(String numbers) {

        if (isEmptyOrNull(numbers)) {
            return 0;
        }
        StringNumberSplitter stringNumberSplitter = new StringNumberSplitter();
        List<String> numbersList = stringNumberSplitter.getNumbersList(numbers);
        checkForNegativeNumbers(numbersList);
        return sumNumbers(numbersList);
    }

    private static boolean isEmptyOrNull(String numbers) {
        return numbers == null || numbers.trim().isEmpty();
    }

    private static void checkForNegativeNumbers(List<String> numbersList) {
        String negativeNumbers = numbersList.stream()
                .filter(s -> s.contains("-"))
                .collect(joining(","));
        if (!negativeNumbers.isEmpty()) {
            throw new IllegalArgumentException("Negatives not allowed: " + negativeNumbers);
        }
    }

    private static int sumNumbers(List<String> numbersList) {
        return numbersList.stream()
                .filter(s -> Integer.parseInt(s) <= 1000)
                .mapToInt(Integer::parseInt)
                .sum();
    }

}
