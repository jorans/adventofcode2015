package se.js.aof2015.day9;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class AppTest {
    @Test
    public void givenThreeDestination_shouldReturnShortestDistance() {
        var dist = """
                London to Dublin = 464
                London to Belfast = 518
                Dublin to Belfast = 141
                """;
        assertEquals(605, App.part1(dist));
    }

    @Test
    public void givenThreeDestination_whenGeneratingConnections_shouldReturnAllConnections() {
        var input = """
                London to Dublin = 464
                London to Belfast = 518
                Dublin to Belfast = 141
                """;
        var connections = App.connections(input);
        assertEquals(3, connections.size());
        assertTrue(connections.containsKey("London"));
        assertEquals(2, connections.get("London").size());
        assertTrue(connections.get("London").contains("Dublin"));
        assertTrue(connections.get("London").contains("Belfast"));

        assertTrue(connections.containsKey("Dublin"));
        assertEquals(2, connections.get("Dublin").size());
        assertTrue(connections.get("Dublin").contains("London"));
        assertTrue(connections.get("Dublin").contains("Belfast"));

        assertTrue(connections.containsKey("Belfast"));
        assertEquals(2, connections.get("Belfast").size());
        assertTrue(connections.get("Belfast").contains("Dublin"));
        assertTrue(connections.get("Belfast").contains("London"));
    }
    @Test
    public void givenThreeDestination_whenGeneratingAllPaths_shouldReturnAllPaths() {
        var input = """
                London to Dublin = 464
                London to Belfast = 518
                Dublin to Belfast = 141
                """;
        var connections = App.connections(input);
        System.out.println(connections);
        var paths = App.pathsfinder(connections);
        System.out.println(paths);
        assertEquals(6, paths.size());
    }
}
