package se.js.aof2015.day2;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Package {
    List<Integer> dim;

    public Package(String d) {
        dim = Arrays.stream(d.split("x"))
                .map(Integer::parseInt)
                .toList();
    }

    public int wrappingPaperSize() {
        var d = dim.stream()
                .sorted()
                .toList();
        return (3 * d.get(0) * d.get(1)) +
                (2 * d.get(1) * d.get(2)) +
                (2 * d.get(2) * d.get(0));
    }

    public int ribbonLength() {
        var d = dim.stream()
                .sorted()
                .toList();
        return (2 * (d.get(0) + d.get(1)) + (d.get(0) * d.get(1) * d.get(2)));
    }

    @Override
    public String toString() {
        return dim.stream()
                .map(n -> ""+n)
                .collect(Collectors.joining("x"));
    }
}
