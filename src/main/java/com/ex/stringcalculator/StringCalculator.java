package com.ex.stringcalculator;

import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringCalculator {
    public static int add(final String numbers) {
        String delimiter = ",|\\n";
        String numbersWithoutDelimiter = numbers;

        if (numbers.startsWith("//")) {
            Matcher matcher = Pattern.compile("//(\\[.*?]|[^\\[\\]]+)(\\n)(.*)").matcher(numbers);
            if (matcher.matches()) {
                delimiter = extractDelimiters(matcher.group(1));
                numbersWithoutDelimiter = matcher.group(3);
            }
        }

        return add(numbersWithoutDelimiter, delimiter);
    }

    private static String extractDelimiters(String delimiterPart) {
        StringBuilder regexBuilder = new StringBuilder();

        if (delimiterPart.startsWith("[") && delimiterPart.endsWith("]")) {
            String[] delimiters = delimiterPart.substring(1, delimiterPart.length() - 1).split("\\]\\[");
            for (String d : delimiters) {
                regexBuilder.append(Pattern.quote(d)).append("|");
            }
        } else {
            regexBuilder.append(Pattern.quote(delimiterPart));
        }

        return regexBuilder.toString();
    }

    private static int add(final String numbers, final String delimiter) {
        int returnValue = 0;
        List<Integer> negativeNumbers = new ArrayList<>();

        for (String number : numbers.split(delimiter)) {
            if (!number.trim().isEmpty()) {
                int numberInt = Integer.parseInt(number.trim());
                switch (Integer.compare(numberInt, 0)) {
                    case -1 -> negativeNumbers.add(numberInt);
                    case 0, 1 -> returnValue += (numberInt <= 1000) ? numberInt : 0;
                }
            }
        }

        if (!negativeNumbers.isEmpty()) {
            StringJoiner joiner = new StringJoiner(", ");
            negativeNumbers.forEach(n -> joiner.add(String.valueOf(n)));
            throw new RuntimeException("Negatives not allowed: " + joiner.toString());
        }

        return returnValue;
    }
}