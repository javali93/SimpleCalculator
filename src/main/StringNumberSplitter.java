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

    protected List<String> splitStringToNumberList(String text) {
        String delimiters = getDefaultDelimiters();
        if (hasCustomDelimiter(text)) {
            int newLineIndex = text.indexOf("\n");
            delimiters += OR + getCustomDelimiters(text.substring(0, newLineIndex + 1));
            text = text.subSequence(newLineIndex + 1, text.length()).toString();
        }
        return Arrays.asList(text.split(delimiters));
    }

    private String getCustomDelimiters(String text) {
        text = text.replace("//[", "")
                .replace("//", "")
                .replace("]\n", "")
                .replace("\n", "")
                .replace("*", "\\*");

        return Arrays.asList(text.split("]\\[")).stream().collect(joining(OR));
    }

    private boolean hasCustomDelimiter(String text) {
        Pattern pattern = Pattern.compile("//(.|(\\[.*\\]){0,})\n(.{0,})");
        Matcher matcher = pattern.matcher(text);
        if (matcher.matches()) {
            return true;
        }
        return false;
    }

    private String getDefaultDelimiters() {
        String defaultDelimiters = "," + OR + "\n";
        return defaultDelimiters;
    }
}

