package se.js.aof2015.day11;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class App {

    public static void main(String[] args) {
        // Part 1
        System.out.println(nextPassword("cqjxjnds"));
        // cqjxxyzz

        //Part 2
        System.out.println(nextPassword(nextPassword("cqjxjnds")));
        // cqkaabcc
    }
    public static String nextPassword(String oldPassword) {
        String newPwd = null;
        do {
            newPwd = nextString(newPwd != null ? newPwd : oldPassword);
        } while (!isValid(newPwd));
        return newPwd;
    }

    static String nextString(String in) {
        int length = in.length();
        char c = in.charAt(length - 1);

        if(c == 'z')
            return length > 1 ? nextString(in.substring(0, length - 1)) + 'a' : "a";

        return in.substring(0, length - 1) + ++c;

    }

    static boolean isValid(String s) {
        return s.matches("[a-z]{8}") &&
                containsSomeThreeCharsIncreasingStraight(s) &&
                !s.matches(".*[iol].*") &&
                containsAtLeastTwoNonOverlappingPairsOfCharacters(s)
                ;
    }

    static boolean containsAtLeastTwoNonOverlappingPairsOfCharacters(String s) {
        return allStrings(s).stream()
                .filter(s1 -> s1.length() == 2)
                .filter(s1 -> s1.charAt(0) == s1.charAt(1))
                .collect(Collectors.toUnmodifiableSet())
                .size() > 1;
    }

    static boolean containsSomeThreeCharsIncreasingStraight(String s) {
        return allStrings(s).stream()
                .filter(s1 -> s1.length() == 3)
                .anyMatch(s1 -> (s1.charAt(0) + 1) == s1.charAt(1) && (s1.charAt(1) + 1) == s1.charAt(2));
    }

    static Set<String> allStrings(String s){
        var strings = new HashSet<String>();
        for (int i = 0; i < s.length(); i++) {
            for (int j = s.length(); j > i ; j--) {
                strings.add(s.substring(i, j));
            }
        }
        return strings;
    }

}
