package se.js.aof2015.day14;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import static java.lang.Integer.parseInt;

public class App {
    public static void main(String[] args) {
        // Part1: 2640
        // Part2: 1102
        System.out.println(part1());
        System.out.println(part2());
    }

    private static int part2(){
        return getMaxScoreFromRace(2503, getReindeers(input(getData())));
    }

    static int getMaxScoreFromRace(int time, List<Reindeer> reindeers) {
        return race(time, reindeers).stream()
                .map(Reindeer::getScore)
                .max(Comparator.naturalOrder())
                .orElse(0);
    }

    static List<Reindeer> race(int time, List<Reindeer> reindeers) {
        for (int t = 1; t <= time; t++) {
            currentlyInTheLead(incrementTraveldDistance(reindeers, t)).stream()
                    .forEach(Reindeer::incrementScore);
        }
        return reindeers;
    }

    static List<Reindeer> incrementTraveldDistance(List<Reindeer> reindeers, int t) {
        for (Reindeer reindeer : reindeers) {
            if (reindeer.isRunning(t)) {
                reindeer.incrementTraveldDistance();
            }
        }
        return reindeers;
    }

    static List<Reindeer> currentlyInTheLead(List<Reindeer> reindeers) {
        var maxDistance = reindeers.stream()
                .map(Reindeer::getTraveldDistance)
                .max(Comparator.naturalOrder())
                .orElse(Integer.MAX_VALUE);

        var reindeer = reindeers.stream()
                .filter(r -> r.getTraveldDistance() == maxDistance)
                .toList();
        return reindeer;
    }

    static List<Reindeer> getReindeers(List<String> input) {
        var reindeers = input.stream()
                .map(App::getMetrics)
                .map(Reindeer::new)
                .toList();
        return reindeers;
    }

    private static int part1() {
        return input(getData()).stream()
                .map(App::getMetrics)
                .map(s -> getDistance(2503, s.speed, s.travelTime, s.restTime))
                .max(Comparator.naturalOrder())
                .orElse(0);
    }

    public static int getDistance(int timeLimit, int speed, int travelTime, int restTime) {
        int cycleTime = travelTime + restTime;
        var cycles = timeLimit / cycleTime;
        var remaningCycletime = timeLimit % cycleTime;
        int accTravelTime = (travelTime * cycles) + Math.min(travelTime, remaningCycletime);
        return speed * accTravelTime;
    }

    static ReindeerMetrics getMetrics(String inp) {
        var parts = inp.split(" ");
        return new ReindeerMetrics(parts[0], parseInt(parts[3]), parseInt(parts[6]),parseInt(parts[13]));
    }
    static class Reindeer{
        final ReindeerMetrics metrics;
        int travveldDistance = 0;
        int score = 0;
        boolean isRunning = false;

        public Reindeer(ReindeerMetrics metrics) {
            this.metrics = metrics;
        }

        boolean isRunning(int time){
            var cycleTime = metrics.travelTime + metrics.restTime;
            var residualTime = time % cycleTime;
            this.isRunning = residualTime != 0 && metrics.travelTime >= residualTime;
            return this.isRunning;
        }

        void incrementTraveldDistance(){
            travveldDistance += metrics.speed;
        }
        int getTraveldDistance(){
            return travveldDistance;
        }
        void incrementScore(){
            score++;
        }

        int getScore(){
            return score;
        }

        @Override
        public String toString() {
            return "{" +
                    "n=" + metrics.name +
                    ", r=" + (isRunning ? "t":"f") +
                    ", d=" + travveldDistance +
                    ", s=" + score +
                    '}';
        }
    }
    record ReindeerMetrics(String name, int speed, int travelTime, int restTime){}

    static List<String> input(String data) {
        return Arrays.stream(data.trim().split("\n"))
                .map(s -> s.trim().replaceAll("\n", ""))
                .filter(s -> !s.isBlank())
                .toList();
    }

    private static String getData() {
        return """
        Dancer can fly 27 km/s for 5 seconds, but then must rest for 132 seconds.
        Cupid can fly 22 km/s for 2 seconds, but then must rest for 41 seconds.
        Rudolph can fly 11 km/s for 5 seconds, but then must rest for 48 seconds.
        Donner can fly 28 km/s for 5 seconds, but then must rest for 134 seconds.
        Dasher can fly 4 km/s for 16 seconds, but then must rest for 55 seconds.
        Blitzen can fly 14 km/s for 3 seconds, but then must rest for 38 seconds.
        Prancer can fly 3 km/s for 21 seconds, but then must rest for 40 seconds.
        Comet can fly 18 km/s for 6 seconds, but then must rest for 103 seconds.
        Vixen can fly 18 km/s for 5 seconds, but then must rest for 84 seconds.                                                """;
    }
}
