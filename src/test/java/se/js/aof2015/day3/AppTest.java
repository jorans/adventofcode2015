package se.js.aof2015.day3;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    @Test
    public void shouldMovedNorth_whenMovingToNorth() {
        var house = App.House.start().move("^");
        assertEquals(1, house.y());
        assertEquals(0, house.x());
    }

    @Test
    public void shouldMovedEast_whenMovingToEast() {
        var house = App.House.start().move(">");
        assertEquals(0, house.y());
        assertEquals(1, house.x());
    }

    @Test
    public void shouldMovedSouth_whenMovingToSouth() {
        var house = App.House.start().move("v");
        assertEquals(-1, house.y());
        assertEquals(0, house.x());
    }

    @Test
    public void shouldMovedWest_whenMovingToWest() {
        var house = App.House.start().move("<");
        assertEquals(0, house.y());
        assertEquals(-1, house.x());
    }

    @Test
    public void shouldBeSameHouse_whenMovingToVisitedHouse() {
        var start = App.House.start();
        assertEquals(start, start.move("<").move(">"));
    }

    @Test
    public void shouldDeliveredToTwoHouses_afterDeliveringToTwoSeprateHouses() {
        assertEquals(2, App.deliverBySanta("v"));
    }
    @Test
    public void shouldDeliveredToTwoHouses_afterDeliveringToTwoSeprateHousesMultipleTimes() {
        assertEquals(2, App.deliverBySanta("v^v^v^v^v^v^"));
    }
    @Test
    public void shouldDeliveredToMultipleHouses_afterDeliveringToMultipleHouses() {
        assertEquals(4, App.deliverBySanta(">v<^>v<^"));
    }

}
