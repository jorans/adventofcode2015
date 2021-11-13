package se.js.aof2015.day9;

import java.util.*;
import java.util.stream.Collectors;

public class App {
    public static void main(String[] args) {
        System.out.println("Part 1: " + part1(inpput()));
        System.out.println("Part 2: " + part2(inpput()));
        // Part 1: 117
        // Part 2: 909

    }

    static int part1(String input) {
        return pathsfinder(connections(input)).stream()
                .map(App::legs)
                .map(legs -> routeDistance(legs, distancies(input)))
                .min(Integer::compareTo)
                .orElse(0);
    }

    static int part2(String input) {
        return pathsfinder(connections(input)).stream()
                .map(App::legs)
                .map(legs -> routeDistance(legs, distancies(input)))
                .max(Integer::compareTo)
                .orElse(0);
    }

    static int routeDistance(List<Leg> legs, HashMap<Leg, Integer> distancies) {
        return legs.stream()
                .map(distancies::get)
                .reduce(0, Integer::sum);
    }
    static List<Leg> legs(List<String> cities) {
        var legs = new ArrayList<Leg>();
        for (int i = 0; i < cities.size()-1; i++) {
            legs.add(new Leg(cities.get(i), cities.get(i + 1)));
        }
        return legs;
    }

    private static HashMap<Leg, Integer> distancies(String input) {
        var distances = new HashMap<Leg, Integer>();

        for (String dist : input.split("\n")) {
            var parts = dist.split(" ");
            distances.put(new Leg(parts[0], parts[2]), Integer.parseInt(parts[4]));
            distances.put(new Leg(parts[2], parts[0]), Integer.parseInt(parts[4]));
        }
        return distances;
    }

    static List<List<String>> pathsfinder(HashMap<String,Set<String>> connections){
        var initialPaths = connections.keySet().stream()
                .map(city -> List.of(city))
                .toList();
        return pathsfinder(initialPaths, connections);
    }

    static List<List<String>> pathsfinder(List<List<String>> paths, HashMap<String,Set<String>> connections){
        var newPaths = new ArrayList<List<String>>();
        for (List<String> path : paths) {
            var city = path.get(path.size() - 1);
            var connectedCities = connections.get(city);
            for (String connectedCity : connectedCities) {
                if (!path.contains(connectedCity)) {
                    var newPath = new ArrayList<String>(path);
                    newPath.add(connectedCity);
                    newPaths.add(newPath);
                }
            }
        }

        if (newPaths.isEmpty()) {
            return paths;
        }

        return pathsfinder(newPaths, connections);
    }

    static HashMap<String,Set<String>> connections(String input){
        var c = new HashMap<String, Set<String>>();
        for (String dist : input.split("\n")) {
            var parts = dist.split(" ");
            c.computeIfAbsent(parts[0], s -> new HashSet<>());
            c.computeIfAbsent(parts[2], s -> new HashSet<>());
            c.get(parts[0]).add(parts[2]);
            c.get(parts[2]).add(parts[0]);
        }
        return c;
    }

    record Leg(String from, String to){}
    record Distance(String p1, String p2, int dist){}
    static class Node {
        final String name;
        boolean visited = false;
        List<Node> connections = new ArrayList<>();

        Node(String name) {
            this.name = name;
        }

        void add(Node node) {
            if (!connections.contains(node)) {
                connections.add(node);
            }
        }

        public String getName() {
            return name;
        }

        public List<Node> getUnvisitedNodes() {
            return connections.stream()
                    .filter(Node::isUnvisited)
                    .toList();
        }

        boolean isUnvisited() {
            return !visited;
        }
        public void setVisited(boolean visited) {
            this.visited = visited;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "name='" + name + '\'' +
                    ", visited=" + visited +
                    ", connections=" + connections.stream().map(Node::getName).collect(Collectors.joining(", ")) +
                    '}';
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Node node = (Node) o;
            return name.equals(node.name);
        }

        @Override
        public int hashCode() {
            return Objects.hash(name);
        }
    }
    static String inpput() {
        return """
                Faerun to Tristram = 65
                Faerun to Tambi = 129
                Faerun to Norrath = 144
                Faerun to Snowdin = 71
                Faerun to Straylight = 137
                Faerun to AlphaCentauri = 3
                Faerun to Arbre = 149
                Tristram to Tambi = 63
                Tristram to Norrath = 4
                Tristram to Snowdin = 105
                Tristram to Straylight = 125
                Tristram to AlphaCentauri = 55
                Tristram to Arbre = 14
                Tambi to Norrath = 68
                Tambi to Snowdin = 52
                Tambi to Straylight = 65
                Tambi to AlphaCentauri = 22
                Tambi to Arbre = 143
                Norrath to Snowdin = 8
                Norrath to Straylight = 23
                Norrath to AlphaCentauri = 136
                Norrath to Arbre = 115
                Snowdin to Straylight = 101
                Snowdin to AlphaCentauri = 84
                Snowdin to Arbre = 96
                Straylight to AlphaCentauri = 107
                Straylight to Arbre = 14
                AlphaCentauri to Arbre = 46
                """;
    }
}
