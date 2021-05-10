package main;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.util.stream.Collectors.joining;

/**
 * @author chetana
 * @created on: 07, May 2021
 */
public class StringNumberSplitter {

    private static final String OR = "|";
    private static final String DEFAULT_DELIMITERS = "," + OR + "\n";

    private Pattern pattern = Pattern.compile("//(.|(\\[.*\\]){0,})\n(.{0,})");


    protected List<String> splitStringToNumberList(String text) {
        String delimiters = DEFAULT_DELIMITERS;
        if (hasCustomDelimiter(text)) {
            int newLineIndex = text.indexOf("\n");
            //Append custom delimiters
            delimiters += OR + getCustomDelimiters(text.substring(0, newLineIndex + 1));
            //Get text without delimiter initializer
            text = text.subSequence(newLineIndex + 1, text.length()).toString();
        }
        return splitStringBy(text, delimiters);
    }

    private String getCustomDelimiters(String text) {
        text = text.replace("//[", "")
                .replace("//", "")
                .replace("]\n", "")
                .replace("\n", "")
                .replace("*", "\\*");

        return splitStringBy(text, "]\\[")
                .stream()
                .collect(joining(OR));
    }

    private List<String> splitStringBy(String text, String delimiters) {
        return Arrays.asList(text.split(delimiters));
    }

    private boolean hasCustomDelimiter(String text) {
        Matcher matcher = pattern.matcher(text);
        if (matcher.matches()) {
            return true;
        }
        return false;
    }
}

