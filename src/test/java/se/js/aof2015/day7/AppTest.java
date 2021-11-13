package se.js.aof2015.day7;

import org.junit.Assert;
import org.junit.Test;
import se.js.aof2015.day7.App.NUM;
import se.js.aof2015.day7.App.WireValues;

import java.util.List;

public class AppTest {
    @Test
    public void givenStringOfOpValues_whenGetValueByWire_shouldReturnValueForWire() {
        var ops = """
            123 -> x
            456 -> y
            x AND y -> d
            x OR y -> e
            x LSHIFT 2 -> f
            y RSHIFT 2 -> g
            NOT x -> h
            NOT y -> ii
            ii -> i
        """;

        var wireValues = new WireValues();
        var opList = App.parse(ops);
        Assert.assertEquals(9, opList.size());
        for (App.Op op : opList) {
            wireValues.add(op);
        }

        Assert.assertEquals(Integer.valueOf(65079), wireValues.getValueByWire("i"));

    }
    @Test
    public void givenStringOfOpValues_whenPrintingResult_shouldReturnResultFromApplyingOps() {
        var ops = """
            NOT y -> i
            NOT x -> h
            y RSHIFT 2 -> g
            x LSHIFT 2 -> f
            x OR y -> e
            x AND y -> d
            456 -> y
            123 -> x
        """;

        var wireValues = new WireValues();
        var opList = App.parse(ops);
        Assert.assertEquals(8, opList.size());
        for (App.Op op : opList) {
            wireValues.apply(op);
        }

        Assert.assertEquals("""
                WireValues{
                d -> 72
                e -> 507
                f -> 492
                g -> 114
                x -> 123
                h -> 65412
                y -> 456
                i -> 65079
                }  
                """.trim(), wireValues.toString());

    }

    @Test
    public void givenNumValue_whenParsing_shouldReturnNumOperation() {
        var ops = App.parse("123 -> x");
        Assert.assertEquals(1, ops.size());
        Assert.assertEquals("NUM[out=x, value=123]", ops.get(0).toString());
    }

    @Test
    public void givenAndValue_whenParsing_shouldReturnAndOperation() {
        var ops = App.parse("a AND b -> c");
        Assert.assertEquals(1, ops.size());
        Assert.assertEquals("AND[in1=a, in2=b, out=c]", ops.get(0).toString());
    }

    @Test
    public void givenOrValue_whenParsing_shouldReturnOrOperation() {
        var ops = App.parse("a OR b -> c");
        Assert.assertEquals(1, ops.size());
        Assert.assertEquals("OR[in1=a, in2=b, out=c]", ops.get(0).toString());
    }

    @Test
    public void givenLshiftValue_whenParsing_shouldReturnLshiftOperation() {
        var ops = App.parse("a LSHIFT 2 -> b");
        Assert.assertEquals(1, ops.size());
        Assert.assertEquals("LSHIFT[in=a, steps=2, out=b]", ops.get(0).toString());
    }

    @Test
    public void givenRshiftValue_whenParsing_shouldReturnRshiftOperation() {
        var ops = App.parse("a RSHIFT 2 -> b");
        Assert.assertEquals(1, ops.size());
        Assert.assertEquals("RSHIFT[in=a, steps=2, out=b]", ops.get(0).toString());
    }

    @Test
    public void givenNotValue_whenParsing_shouldReturnNotOperation() {
        var ops = App.parse("NOT a -> b");
        Assert.assertEquals(1, ops.size());
        Assert.assertEquals("NOT[in=a, out=b]", ops.get(0).toString());
    }

    @Test
    public void givenEmptyWireValues_whenAplyingNumValue_shouldReturnWireValuesWithNumValue() {
        var wireValues = new WireValues();
        wireValues.apply(NUM("A", 1));
        Assert.assertEquals(1, wireValues.get("A").intValue());
    }
    @Test
    public void givenTwoWireValues_whenAplyingAndOp_shouldReturnWireValuesAndOfValues() {
        var wireValues = new WireValues();
        wireValues.apply(NUM("A", 5))
                .apply(NUM("B", 3))
                .apply(AND("A", "B", "C"))
        ;
        Assert.assertEquals(1, wireValues.get("C").intValue());
    }

    @Test
    public void givenTwoWireValues_whenAplyingOrOp_shouldReturnWireValuesOrOfValues() {
        var wireValues = new WireValues();
        wireValues.apply(NUM("A", 5))
                .apply(NUM("B", 3))
                .apply(OR("A", "B", "C"))
        ;
        Assert.assertEquals(7, wireValues.get("C").intValue());
    }
    @Test
    public void givenOneWireValues_whenAplyingLshiftOp_shouldReturnWireValuesLshiftOfValue() {
        var wireValues = new WireValues();
        wireValues.apply(NUM("A", 5))
                .apply(LSHIFT("A", 2, "C"))
        ;
        Assert.assertEquals("10100", Integer.toBinaryString(wireValues.get("C").intValue()));
    }
    @Test
    public void givenOneWireValues_whenAplyingRshiftOp_shouldReturnWireValuesRshiftOfValue() {
        var wireValues = new WireValues();
        wireValues.apply(NUM("A", 9))
                .apply(RSHIFT("A", 2, "C"))
        ;
        Assert.assertEquals("10", Integer.toBinaryString(wireValues.get("C").intValue()));
    }
    @Test
    public void givenOneWireValues_whenAplyingNotOp_shouldReturnWireValuesNotOfValue() {
        var wireValues = new WireValues();
        wireValues.apply(NUM("A", 5))
                .apply(NOT("A", "C"))
        ;
        Assert.assertEquals("1111111111111010", Integer.toBinaryString(wireValues.get("C").intValue()));
    }
    @Test
    public void givenEmptyWireValues_whenOps_shouldReturnWireValuesOfOperations() {
        /*
            123 -> x
            456 -> y
            x AND y -> d
            x OR y -> e
            x LSHIFT 2 -> f
            y RSHIFT 2 -> g
            NOT x -> h
            NOT y -> i
        */
        var wireValues = new WireValues();
        wireValues
                .apply(NUM("x", 123))
                .apply(NUM("y", 456))
                .apply(AND("x", "y","d"))
                .apply(OR("x", "y","e"))
                .apply(LSHIFT("x", 2,"f"))
                .apply(RSHIFT("y", 2,"g"))
                .apply(NOT("x", "h"))
                .apply(NOT("y", "i"))
        ;
        Assert.assertEquals(72, wireValues.get("d").intValue());
        Assert.assertEquals(507, wireValues.get("e").intValue());
        Assert.assertEquals(492, wireValues.get("f").intValue());
        Assert.assertEquals(114, wireValues.get("g").intValue());
        Assert.assertEquals(65412, wireValues.get("h").intValue());
        Assert.assertEquals(65079, wireValues.get("i").intValue());
        Assert.assertEquals(123, wireValues.get("x").intValue());
        Assert.assertEquals(456, wireValues.get("y").intValue());
    }

    private App.Op NUM(String wire, int value) {
        return new NUM(wire, value);
    }
    private App.Op AND(String in1 ,String in2, String out) {
        return new App.AND(in1,in2, out);
    }
    private App.Op OR(String in1 ,String in2, String out) {
        return new App.OR(in1,in2, out);
    }
    private App.Op LSHIFT(String in1 ,int steps, String out) {
        return new App.LSHIFT(in1, steps, out);
    }
    private App.Op RSHIFT(String in1 ,int steps, String out) {
        return new App.RSHIFT(in1, steps, out);
    }
    private App.Op NOT(String in, String out) {
        return new App.NOT(in, out);
    }
}
