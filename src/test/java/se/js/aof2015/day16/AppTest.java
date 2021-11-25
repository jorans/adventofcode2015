package se.js.aof2015.day16;

import org.junit.Assert;
import org.junit.Test;
import se.js.aof2015.Util;

import java.util.List;

import static org.junit.Assert.*;

public class AppTest {

    @Test
    public void givenStringOfCompounds_whenParsingIntermediate_shouldReturnMapOfValues() {
        var map = App.parseIntermediate("Sue 1: children: 1, cars: 8, vizslas: 7");
        assertEquals(4,map.size());

        assertEquals(1, map.get("Sue").getAsInt());
        assertEquals(1, map.get("children").getAsInt());
        assertEquals(8, map.get("cars").getAsInt());
        assertEquals(7, map.get("vizslas").getAsInt());
    }

    @Test
    public void givenStringOfCompounds_whenAssembleCompounds_shouldReturnListOfCompounds() {
        var map = App.parseIntermediate("Sue 1: children: 1, cars: 8, vizslas: 7");

        var c = App.assmbleSue(map);
        assertEquals(1, c.id().intValue());
        assertEquals(1, c.children().getAsInt());
        assertEquals(8, c.cars().getAsInt());
        assertEquals(7, c.vizslas().getAsInt());
        assertFalse(c.akitas().isPresent());
        assertFalse(c.cats().isPresent());
        assertFalse(c.goldfish().isPresent());
        assertFalse(c.perfumes().isPresent());
        assertFalse(c.pomeranians().isPresent());
        assertFalse(c.samoyeds().isPresent());
        assertFalse(c.trees().isPresent());
    }
}
