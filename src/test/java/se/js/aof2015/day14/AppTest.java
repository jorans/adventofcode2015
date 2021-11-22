package se.js.aof2015.day14;

import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class AppTest {
    @Test
    public void givenReindeerMetrics_whenCalculatingMaxScoreFromRace_shouldGiveMaxScore() {
        var metrics = """
                    Comet can fly 14 km/s for 10 seconds, but then must rest for 127 seconds.
                    Dancer can fly 16 km/s for 11 seconds, but then must rest for 162 seconds.
                """;
        var reindeers = App.getReindeers(App.input(metrics));
        var maxScoreFromRace = App.getMaxScoreFromRace(1000, reindeers);
        assertEquals(689, maxScoreFromRace);
    }
    @Test
    public void givenReindeerMetrics_whenRunningRace_shouldReindeersWithTravelingDistance() {
        var metrics = """
                    Comet can fly 14 km/s for 10 seconds, but then must rest for 127 seconds.
                """;
        var reindeers = App.getReindeers(App.input(metrics));
        var reindeersAfterRace = App.race(1000, reindeers);
        assertEquals(1120, reindeersAfterRace.get(0).travveldDistance);
    }
    @Test
    public void givenReindeerMetrics_whenRacingOneSecond_shouldReturnReindeersWithOneTravelingDistance() {
        var metrics = """
                    Comet can fly 14 km/s for 10 seconds, but then must rest for 127 seconds.
                """;
        var reindeers = App.getReindeers(App.input(metrics));
        var reindeersAfterRace = App.race(1, reindeers);
        assertEquals(14, reindeersAfterRace.get(0).travveldDistance);
    }
    @Test
    public void givenReindeerMetrics_whenRacingOneCycle_shouldReindeersTraveledSpeedXFlyTime() {
        var metrics = """
                    Comet can fly 14 km/s for 10 seconds, but then must rest for 127 seconds.
                """;
        var reindeers = App.getReindeers(App.input(metrics));
        var reindeersAfterRace = App.race(137, reindeers);
        assertEquals(140, reindeersAfterRace.get(0).travveldDistance);
    }
    @Test
    public void givenReindeerMetrics_whenCalculatingLeadingReideers_shouldIncludeReideerThatGotFurtherst() {
        var metrics = """
                    Comet can fly 14 km/s for 10 seconds, but then must rest for 127 seconds.
                    Dancer can fly 16 km/s for 11 seconds, but then must rest for 162 seconds.
                """;
        var reindeers = App.getReindeers(App.input(metrics));
        assertEquals(2, reindeers.size());
        assertEquals(0, reindeers.get(0).getTraveldDistance());
        assertEquals(0, reindeers.get(1).getTraveldDistance());

        reindeers = App.incrementTraveldDistance(reindeers, 1);
        var theLead = App.currentlyInTheLead(reindeers);
        assertEquals(1, theLead.size());
        assertEquals(16, theLead.get(0).getTraveldDistance());
    }

    @Test
    public void givenReindeerMetrics_whenIncrementTraveldDistance_shouldIncrementOnlyWhileRunning() {
        var metrics = """
                    Comet can fly 14 km/s for 10 seconds, but then must rest for 127 seconds.
                """;
        var reindeers = App.getReindeers(App.input(metrics));
        assertEquals(1, reindeers.size());
        assertEquals(0, reindeers.get(0).getTraveldDistance());

        reindeers = App.incrementTraveldDistance(reindeers, 1);
        assertEquals(14, reindeers.get(0).getTraveldDistance());
        reindeers = App.incrementTraveldDistance(reindeers, 2);
        assertEquals(28, reindeers.get(0).getTraveldDistance());

        reindeers = App.incrementTraveldDistance(reindeers, 11);
        assertEquals(28, reindeers.get(0).getTraveldDistance());

    }

    @Test
    public void givenReindeerMetrics_whenCalculatingDistance_shouldReturnCorrectDistance() {
        assertEquals(1120, App.getDistance(1000, 14, 10, 127));
        assertEquals(1056, App.getDistance(1000, 16, 11, 162));
    }

}
