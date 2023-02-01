package ru.aasmc.io.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

public class RegexDemo {
    public static void main(String[] args) {
        example("box", "ox");
        example("ox", "box");
        example("\\wbc", "abc");
        example("(Java) \\1", "Java Java");
        example("\\b\\b", "I think");
        System.out.println("Greedy...");
        example(".*end", "wend rend end");
        System.out.println("Lazy...");
        example(".*?end", "wend rend end");
        System.out.println("Possessive...");
        example(".*+end", "wend rend end");
    }

    private static void example(String reg, String input) {
        try {
            Pattern p = Pattern.compile(reg);
            Matcher matcher = p.matcher(input);
            while (matcher.find()) {
                System.out.println("Located [" + matcher.group() + "] starting at " +
                        matcher.start() + " and ending at " +
                        (matcher.end() - 1));
            }
        } catch (PatternSyntaxException pse) {
            System.err.println("Bad regex: " + pse.getMessage());
            System.err.println("Description: " + pse.getDescription());
            System.err.println("Index: " + pse.getIndex());
            System.err.println("Incorrect Pattern: " + pse.getPattern());
        }
    }
}
