package se.js.aof2015.day8;

import org.junit.Assert;
import org.junit.Test;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class AppTest {
    @Test
    public void countDifferense() {
        var ss = Arrays.stream("""
                ""
                "abc"
                "aaa\\"aa"
                "\\x27"
                """.trim().split("\n")).toList();

        var inCode = ss.stream()
                .map(App::countCharactersInCode)
                .reduce(0, Integer::sum);
        var inMem = ss.stream()
                .map(App::countCharactersInMemory)
                .reduce(0, Integer::sum);
        System.out.println(inCode-inMem);
    }

    @Test
    public void stringWithAnHexValue() {
        var s = """
                "\\x27"
                """.trim().split("\n")[0];
        Assert.assertEquals(6, App.countCharactersInCode(s));
        Assert.assertEquals(1, App.countCharactersInMemory(s));
        Assert.assertEquals(11, App.countCharactersInCodePart2(s));
    }
    @Test
    public void stringWithAnEscapedSlash() {
        var s = """
                "aaa\\\\aaa"
                """.trim().split("\n")[0];

        Assert.assertEquals(10, App.countCharactersInCode(s));
        Assert.assertEquals(7, App.countCharactersInMemory(s));
        Assert.assertEquals(16, App.countCharactersInCodePart2(s));
    }
    @Test
    public void stringWithAnEscapedLetter() {
        var s = """
                "aaa\\"aaa"
                """.trim().split("\n")[0];
        Assert.assertEquals(10, App.countCharactersInCode(s));
        Assert.assertEquals(7, App.countCharactersInMemory(s));
        Assert.assertEquals(16, App.countCharactersInCodePart2(s));
    }
    @Test
    public void stringWithThreeLetters() {
        var s = """
                "aaa"
                """.trim().split("\n")[0];
        Assert.assertEquals(5, App.countCharactersInCode(s));
        Assert.assertEquals(3, App.countCharactersInMemory(s));
        Assert.assertEquals(9, App.countCharactersInCodePart2(s));

    }
    @Test
    public void emptyString() {
        var s = """
                ""
                """.trim().split("\n")[0];

        Assert.assertEquals(2, App.countCharactersInCode(s));
        Assert.assertEquals(0, App.countCharactersInMemory(s));
        Assert.assertEquals(6, App.countCharactersInCodePart2(s));

    }
}
