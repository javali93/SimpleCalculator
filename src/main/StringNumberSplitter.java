package main;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author chetana
 * @created on: 07, May 2021
 */
public class StringNumberSplitter {

    private static final String OR = "|";

    List<String> getNumbersList(String text) {
        String delimiters = getDelimiters();
        return splitStringToNumberList(text, delimiters);
    }

    private List<String> splitStringToNumberList(String text, String delimiters) {
        if(hasCustomDelimiter(text)){
            int newLineIndex = text.indexOf("\n");

            delimiters += OR + customDelimiter(text.substring(0,newLineIndex+1));
            text = text.subSequence(newLineIndex+1,text.length()).toString();
        }
        return Arrays.asList(text.split(delimiters));
    }

    private String customDelimiter(String text) {
        return text.replace("//","").replace("\n","");
    }

    private boolean hasCustomDelimiter(String text) {
        Pattern pattern = Pattern.compile("//(.|\\[.*\\])\n(.{0,})");
        Matcher matcher = pattern.matcher(text);
        if(matcher.matches()){
            return true;
        }
        return false;
    }

    private String getDelimiters() {
        String delimiters = "," + OR + "\n"; //Default delimiters
        return delimiters;
    }
}

