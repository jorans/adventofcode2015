package se.js.aof2015.day11;

import org.junit.Assert;
import org.junit.Test;

import java.util.Set;

import static org.junit.Assert.*;

public class AppTest {
    @Test
    public void givenAPassword_whenCallingNextPassword_shouldReturnNextValidPassword() {
        assertEquals("abcdffaa", App.nextPassword("abcdefgh"));
        assertEquals("ghjaabcc", App.nextPassword("ghijklmn"));
    }
    @Test
    public void givenAnEdgeString_whenCallingNextString_shouldReturnCharacterIncremented() {
        assertEquals("ya", App.nextString("xz"));
        assertEquals("aaa", App.nextString("zzz"));
    }

    @Test
    public void givenAString_whenCallingNextString_shouldReturnLastCharacterIncremented() {
        assertEquals("ab", App.nextString("aa"));
    }

    @Test
    public void givenCorrectPassword_whenValidating_shouldReturnTrue() {
        assertTrue(App.isValid("abcdffaa"));
    }

    @Test
    public void givenNoDoubleCharcters_whenValidating_ShuldReturnFalse() {
        assertFalse(App.isValid("asdfghjz"));
    }
    @Test
    public void givenInvalidCharcter_whenValidating_ShuldReturnFalse() {
        assertFalse(App.isValid("asdfghji"));
        assertFalse(App.isValid("asdfghjo"));
        assertFalse(App.isValid("asdfghjl"));
    }
    @Test
    public void givenNoIncreasingStraight_whenValidating_ShuldReturnFalse() {
        assertFalse(App.isValid("asdfhjlz"));
    }

    @Test
    public void givenIncreasingStraight_whenCheckingIncreasingStraight_ShuldReturnTrue() {
        assertTrue(App.containsSomeThreeCharsIncreasingStraight("aaddfghjk"));
    }

    @Test
    public void givenTwoNonOverlappingCharacters_whenValidatingTwoNonOverlappingCharacters_shuldReturnTrue() {
        assertTrue(App.containsAtLeastTwoNonOverlappingPairsOfCharacters("aadd"));
    }

    @Test
    public void givenTwoOverlappingCharacters_whenValidatingTwoNonOverlappingCharacters_shuldReturnFalse() {
        assertFalse(App.containsAtLeastTwoNonOverlappingPairsOfCharacters("aadaa"));
    }

    @Test
    public void givenStringOfThreeCharacters_whenGeneratingAllStrings_ShuldReturnListOfStrings() {
        var strings = App.allStrings("abc");
        assertFalse(strings.isEmpty());
        assertEquals(6,strings.size());
        assertTrue(strings.contains("a"));
        assertTrue(strings.contains("b"));
        assertTrue(strings.contains("c"));
        assertTrue(strings.contains("ab"));
        assertTrue(strings.contains("bc"));
        assertTrue(strings.contains("abc"));
    }
}
