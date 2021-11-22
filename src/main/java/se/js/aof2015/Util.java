package se.js.aof2015;

import java.util.Arrays;
import java.util.List;

public class Util {
    public static List<String> input(String data) {
        return Arrays.stream(data.trim().split("\n"))
                .map(s -> s.trim().replaceAll("\n", ""))
                .filter(s -> !s.isBlank())
                .toList();
    }
}
