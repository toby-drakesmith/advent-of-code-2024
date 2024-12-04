package aoc;

import org.junit.Before;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import static org.junit.Assert.assertEquals;

public class Day2Test {

    Day2 day2;

    @Before
    public void setUp() throws Exception {
        InputStream inputStream = Day2.class.getClassLoader().getResourceAsStream("day2-input");
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        day2 = new Day2(reader);
    }

    @Test
    public void shouldCalculateSafeReports() {
        assertEquals(2, day2.calculateSafeReports());
    }

    @Test
    public void shouldCalculateDampStuff() {
        assertEquals(4, day2.calculateSafeDampReports());
    }
}