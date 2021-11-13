package se.js.aof2015.day4;

import org.junit.Assert;
import org.junit.Test;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import static org.junit.Assert.assertEquals;

public class AppTest {
    @Test
    public void givenPassoword_whenHasing_shouldReturnHash() {
        var num = App.findLowestNumberForAdventCoin("abcdef",5);
        assertEquals(609043,num);
    }
    @Test
    public void givenPassoword2_whenHasing_shouldReturnHash2() {
        var num = App.findLowestNumberForAdventCoin("pqrstuv",5);
        assertEquals(1048970,num);
    }

}
