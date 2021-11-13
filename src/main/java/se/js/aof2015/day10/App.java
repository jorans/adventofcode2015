package se.js.aof2015.day10;

import java.util.regex.Pattern;

public class App {
    public static void main(String[] args) {
//        var pattern = "(.)\\1*";
//        var r = Pattern.compile(pattern);
//        var m = r.matcher("1112211345511");
//            System.out.println("Groups: " + m.groupCount());
//        while(m.find()) {
//            System.out.println("Group: " + m.group());
//        }
        System.out.println("Part 1: " + lengthAfterInterations("3113322113", 40));
        System.out.println("Part 2: " + lengthAfterInterations("3113322113", 50));
//        Part 1: 329356
//        Part 2: 4666278

    }

    static int lengthAfterInterations(String input, int iterations) {
        var s = input;
        for (int i = 0; i < iterations; i++) {
            s = transform(s);
        }
        return s.length();
    }

    static String transform(String s) {
        var pattern = "(.)\\1*";
        var r = Pattern.compile(pattern);
        var m = r.matcher(s);
        var sb = new StringBuffer();
        while(m.find()) {
            var group = m.group();
            sb.append(group.length());
            sb.append(group.substring(0, 1));
        }
        return sb.toString();
    }

}
