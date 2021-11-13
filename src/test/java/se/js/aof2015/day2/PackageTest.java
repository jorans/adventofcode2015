package se.js.aof2015.day2;

import org.junit.Assert;
import org.junit.Test;

public class PackageTest {
    @Test
    public void shouldReturnSameString_whenCallingToString() {
        Assert.assertEquals("2x3x4", new Package("2x3x4").toString());
    }
    @Test
    public void shouldReturnCorrectSurface_whenCallingSurface() {
        Assert.assertEquals(58, new Package("2x3x4").wrappingPaperSize());
        Assert.assertEquals(43, new Package("1x1x10").wrappingPaperSize());
    }

    @Test
    public void shouldReturnRibbonLength_whenCallingRibbonLength() {
        Assert.assertEquals(34, new Package("2x3x4").ribbonLength());
        Assert.assertEquals(14, new Package("1x1x10").ribbonLength());
    }
}