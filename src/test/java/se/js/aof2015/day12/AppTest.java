package se.js.aof2015.day12;

import org.junit.Assert;
import org.junit.Test;

import java.util.regex.Pattern;

import static org.junit.Assert.assertEquals;

public class AppTest {
    @Test
    public void givenNumber_whenCalculatingSum_shouldReturnSumOfAllNumbers() {
        assertEquals(2, App.sumOfNumbers("1 -2 3"));
        assertEquals(2, App.sumOfNumbers("[1, -2, 3]"));
        assertEquals(0, App.sumOfNumbers("{\"a\":[-1,1]}"));
        assertEquals(6, App.sumOfNumbers("[1,{\"c\":\"red\",\"b\":2},3]"));
    }

    @Test
    public void givenNumber_whenCalculatingSumPart2_shouldReturnSumOfAllNumbersExceptRed() {
        assertEquals(2, App.sumOfNumbersPart2("1 -2 3"));
        assertEquals(6, App.sumOfNumbersPart2("[1,2,3]"));
        assertEquals(6, App.sumOfNumbersPart2("[1,\"red\",5]"));
        assertEquals(0, App.sumOfNumbersPart2("{\"a\":[-1,1]}"));
        assertEquals(0, App.sumOfNumbersPart2("{\"c\":\"red\",\"b\":2}"));
        assertEquals(4, App.sumOfNumbersPart2("[1,{\"c\":\"red\",\"b\":2},3]"));
    }

    @Test
    public void givenStringWithRed_whenCleaningupRed_shouldReturnStringWithoutRed() {
        assertEquals("[1,2,3]", App.cleanupRed("[1,2,3]"));
        assertEquals("[1,3]", App.cleanupRed("""
        [1,{"c":"red","b":2},3]
        """.strip()));
        assertEquals("[1,3]", App.cleanupRed("""
            [1,{"c":"red","b":2,"c":{"a":10}},3]
        """.strip()));
        assertEquals("", App.cleanupRed("""
            {"d":"red","e":[1,2,3,4],"f":5}
            """).strip());
        assertEquals("[1,\"red\",5]", App.cleanupRed("[1,\"red\",5]"));
        assertEquals("{\"e\":86,\"a\":,\"g\":\"yellow\"}", App.cleanupRed("{\"e\":86,\"a\":{\"a\":[120,169,\"green\",\"red\",\"orange\"],\"b\":\"red\"},\"g\":\"yellow\"}"));
        assertEquals("", App.cleanupRed("{\"a\":[120,169,\"green\",\"red\",\"orange\"],\"b\":\"red\"}"));
        assertEquals("", App.cleanupRed("""
                      {"e": 86,"c": 23,"g": "yellow","b": ["yellow"],"d": "red","f": -19}
                """.strip()));
        assertEquals("", App.cleanupRed("""
                      {
                        "e": 86,
                        "c": 23,
                        "a": {
                          "a": [
                            120,
                            169,
                            "green",
                            "red",
                            "orange"
                          ],
                          "b": "red"
                        },
                        "g": "yellow",
                        "b": [
                          "yellow"
                        ],
                        "d": "red",
                        "f": -19
                      }
                """).replaceAll("\n", "").replaceAll(" ", ""));
    }
}
