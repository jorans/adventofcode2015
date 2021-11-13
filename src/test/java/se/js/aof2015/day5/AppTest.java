package se.js.aof2015.day5;

import org.junit.Assert;
import org.junit.Test;

public class AppTest {
    @Test
    public void givenEmptyString_whenCheckingVowels_shouldReturnFalse() {
        Assert.assertFalse(App.hasAtLeastThreeVowels(""));
    }
    @Test
    public void givenOneVowel_whenCheckingVowels_shouldReturnFalse() {
        Assert.assertFalse(App.hasAtLeastThreeVowels("a"));
    }
    @Test
    public void givenTwoVowel_whenCheckingVowels_shouldReturnFalse() {
        Assert.assertFalse(App.hasAtLeastThreeVowels("ae"));
    }
    @Test
    public void givenThreeVowel_whenCheckingVowels_shouldReturnTrue() {
        Assert.assertTrue(App.hasAtLeastThreeVowels("aei"));
    }
    @Test
    public void givenThreeVowelInMixedString_whenCheckingVowels_shouldReturnTrue() {
        Assert.assertTrue(App.hasAtLeastThreeVowels("qacewis"));
    }
    @Test
    public void givenTwoVowelInMixedString_whenCheckingVowels_shouldReturnFalse() {
        Assert.assertFalse(App.hasAtLeastThreeVowels("qacew"));
    }
    @Test
    public void givenNoVowels_whenCheckingVowels_shouldReturnFalse() {
        Assert.assertFalse(App.hasAtLeastThreeVowels("swxdfg"));
    }

    @Test
    public void givenEmptyStrng_whenCheckingRepetion_shouldReturnFalse() {
        Assert.assertFalse(App.hasDoubleLetters(""));
    }
    @Test
    public void givenSingleCharacter_whenCheckingRepetion_shouldReturnFalse() {
        Assert.assertFalse(App.hasDoubleLetters("a"));
    }
    @Test
    public void givenTwoDifferentCharacters_whenCheckingRepetion_shouldReturnFalse() {
        Assert.assertFalse(App.hasDoubleLetters("as"));
    }
    @Test
    public void givenSameTwoCharacters_whenCheckingRepetion_shouldReturnTrue() {
        Assert.assertTrue(App.hasDoubleLetters("aa"));
    }
    @Test
    public void givenSameThreeCharacters_whenCheckingRepetion_shouldReturnTrue() {
        Assert.assertTrue(App.hasDoubleLetters("aaa"));
    }
    @Test
    public void givenSameTwoCharactersAtStart_whenCheckingRepetion_shouldReturnTrue() {
        Assert.assertTrue(App.hasDoubleLetters("aasd"));
    }
    @Test
    public void givenSameTwoCharactersAtEnd_whenCheckingRepetion_shouldReturnTrue() {
        Assert.assertTrue(App.hasDoubleLetters("sdaa"));
    }
    @Test
    public void givenSameTwoCharactersInMiddle_whenCheckingRepetion_shouldReturnTrue() {
        Assert.assertTrue(App.hasDoubleLetters("sdaaer"));
    }
    @Test
    public void givenEmtpty_whenCheckingForbiddenSequence_shouldReturnFalse() {
        Assert.assertFalse(App.containsForbiddenSequence(""));
    }
    @Test
    public void givenSingleForbiddenSequence_whenCheckingForbiddenSequence_shouldReturnTrue() {
        Assert.assertTrue(App.containsForbiddenSequence("ab"));
        Assert.assertTrue(App.containsForbiddenSequence("cd"));
        Assert.assertTrue(App.containsForbiddenSequence("pq"));
        Assert.assertTrue(App.containsForbiddenSequence("xy"));
    }
    @Test
    public void givenSingleForbiddenSequenceAtStart_whenCheckingForbiddenSequence_shouldReturnTrue() {
        Assert.assertTrue(App.containsForbiddenSequence("absdf"));
        Assert.assertTrue(App.containsForbiddenSequence("cdsdf"));
        Assert.assertTrue(App.containsForbiddenSequence("pqsdf"));
        Assert.assertTrue(App.containsForbiddenSequence("xysdf"));
    }
    @Test
    public void givenSingleForbiddenSequenceInMiddle_whenCheckingForbiddenSequence_shouldReturnTrue() {
        Assert.assertTrue(App.containsForbiddenSequence("weabsdf"));
        Assert.assertTrue(App.containsForbiddenSequence("wecdsdf"));
        Assert.assertTrue(App.containsForbiddenSequence("wepqsdf"));
        Assert.assertTrue(App.containsForbiddenSequence("wexysdf"));
    }
    @Test
    public void givenSingleForbiddenSequenceAtEnd_whenCheckingForbiddenSequence_shouldReturnTrue() {
        Assert.assertTrue(App.containsForbiddenSequence("weab"));
        Assert.assertTrue(App.containsForbiddenSequence("wecd"));
        Assert.assertTrue(App.containsForbiddenSequence("wepq"));
        Assert.assertTrue(App.containsForbiddenSequence("wexy"));
    }
    @Test
    public void givenStringWithoutForbiddenSequence_whenCheckingForbiddenSequence_shouldReturnFalse() {
        Assert.assertFalse(App.containsForbiddenSequence("fghj"));
    }
    @Test
    public void givenEmptyString_whenCheckingIsNicee_shouldReturnFalse() {
        Assert.assertFalse(App.isNice(""));
    }

    @Test
    public void givenStringWithoutDoubleLetter_whenCheckingIsNicee_shouldReturnFalse() {
        Assert.assertFalse(App.isNice("jchzalrnumimnmhp"));
    }

    @Test
    public void givenStringWithForbiddenSequence_whenCheckingIsNicee_shouldReturnFalse() {
        Assert.assertFalse(App.isNice("haegwjzuvuyypxyu"));
    }

    @Test
    public void givenStringWithFewVowels_whenCheckingIsNicee_shouldReturnFalse() {
        Assert.assertFalse(App.isNice("dvszwmarrgswjxmb"));
    }
    @Test
    public void givenANiceString_whenCheckingIsNicee_shouldReturnTrue() {
        Assert.assertTrue(App.isNice("aaa"));
        Assert.assertTrue(App.isNice("ugknbfddgicrmopn"));
    }

    @Test
    public void givenOverlappingDoubleLetters_whenCheckingHasDoubleLettersTwice_shouldReturnFalse() {
        Assert.assertFalse(App.hasDoubleLettersTwice("aaa"));
    }
    @Test
    public void givenNonOverlappingDoubleLetters_whenCheckingHasDoubleLettersTwice_shouldReturnTrue() {
        Assert.assertTrue(App.hasDoubleLettersTwice("xyxy"));
    }
    @Test
    public void givenNonOverlappingDoubleLettersSeparated_whenCheckingHasDoubleLettersTwice_shouldReturnTrue() {
        Assert.assertTrue(App.hasDoubleLettersTwice("xyaxy"));
        Assert.assertTrue(App.hasDoubleLettersTwice("bfxyaxy"));
        Assert.assertTrue(App.hasDoubleLettersTwice("bfxyabxy"));
        Assert.assertTrue(App.hasDoubleLettersTwice("sdxyaxyqw"));
        Assert.assertTrue(App.hasDoubleLettersTwice("qjhvhtzxzqqjkmpb"));
    }

    @Test
    public void givenRepeatedLetterWithoutSeparation_whenCheckingHasRepeatedLettersWithOneLetterBetween_shouldReturnFalse() {
        Assert.assertFalse(App.hasRepeatedSingleLeterWithOneLetterBetween("aa"));
    }
    @Test
    public void givenRepeatedLetterWithSeparation_whenCheckingHasRepeatedLettersWithSomeLetterBetween_shouldReturnTrue() {
        Assert.assertTrue(App.hasRepeatedSingleLeterWithOneLetterBetween("axa"));
        Assert.assertTrue(App.hasRepeatedSingleLeterWithOneLetterBetween("qqaxa"));
        Assert.assertTrue(App.hasRepeatedSingleLeterWithOneLetterBetween("qqaxadf"));
        Assert.assertTrue(App.hasRepeatedSingleLeterWithOneLetterBetween("qjhvhtzxzqqjkmpb"));
    }

    @Test
    public void givenANiceString_whenCheckingIsNicee2_shouldReturnTrue() {
        Assert.assertTrue(App.isNice2("qjhvhtzxzqqjkmpb"));
        Assert.assertTrue(App.isNice2("xxyxx"));
    }

}
