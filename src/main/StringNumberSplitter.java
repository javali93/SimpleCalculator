package main;

import java.util.List;

import static java.util.Arrays.asList;

/**
 * @author chetana
 * @created on: 07, May 2021
 */
public class StringNumberSplitter {


    List<String> getNumbers(String text) {
        String delimiters = getDelimiters();
        return splitNumbers(text, delimiters);
    }

    private List<String> splitNumbers(String string, String delimiters) {
        return asList(string.split(delimiters));
    }

    private String getDelimiters() {
        String delimiters = ",|\n"; //Default delimiters
        return delimiters;
    }
}

