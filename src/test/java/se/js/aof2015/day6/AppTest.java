package se.js.aof2015.day6;

import org.junit.Assert;
import org.junit.Test;

public class AppTest {
    @Test
    public void givenEmptyInstrunctions_whenCalulateingHowManyLightsAreLit_shouldReturnZero() {
        Assert.assertEquals(0, App.howManyLightsAreLit(""));
    }
    @Test
    public void givenInstructionsToTurnOneLightOn_whenCalulateingHowManyLightsAreLit_shouldReturn() {
        Assert.assertEquals(1, App.howManyLightsAreLit("turn on 0,0 through 0,0"));
    }
    @Test
    public void givenInstructionsToTurnNineLightOn_whenCalulateingHowManyLightsAreLit_shouldReturn() {
        Assert.assertEquals(9, App.howManyLightsAreLit("turn on 0,0 through 2,2"));
    }
    @Test
    public void givenInstructionsToToggleNineLight_whenCalulateingHowManyLightsAreLit_shouldReturn() {
        Assert.assertEquals(9, App.howManyLightsAreLit("toggle 0,0 through 2,2"));
    }
    @Test
    public void givenTwoToggleInstructions_whenCalulateingHowManyLightsAreLit_shouldReturn() {
        Assert.assertEquals(8, App.howManyLightsAreLit("""
            toggle 0,0 through 2,2
            toggle 0,0 through 0,0
            """));
    }
    @Test
    public void givenTwoInstructions_whenCalulateingHowManyLightsAreLit_shouldReturn() {
        Assert.assertEquals(8, App.howManyLightsAreLit("""
            turn on 0,0 through 2,2
            turn off 0,0 through 0,0
            """));
    }
    @Test
    public void givenInstructionsToTurnAllLightsOn_whenCalulateingHowManyLightsAreLit_shouldReturn() {
        Assert.assertEquals(1_000_000, App.howManyLightsAreLit("""
           turn on 0,0 through 999,999
            """));
        Assert.assertEquals(1_000, App.howManyLightsAreLit("""
           toggle 0,0 through 999,0
            """));
    }
}
