package aoc;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import static org.junit.Assert.assertEquals;

public class Day3Test {

    @Test
    public void name() {
        InputStream inputStream = Day3.class.getClassLoader().getResourceAsStream("day3-input");
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        Day3 day3 = new Day3(reader);
        assertEquals(161, day3.multiply());
    }

    @Test
    public void name2() {
        InputStream inputStream = Day3.class.getClassLoader().getResourceAsStream("day3-input2");
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        Day3 day3 = new Day3(reader);
        assertEquals(48, day3.multiply2());
    }
}