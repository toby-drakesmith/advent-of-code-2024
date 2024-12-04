package aoc;

import org.junit.Before;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import static org.junit.Assert.assertEquals;

public class Day1Test {

    Day1 day1;

    @Before
    public void setUp() throws Exception {
        InputStream inputStream = Day1.class.getClassLoader().getResourceAsStream("day1-input");
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        day1 = new Day1(reader);
    }

    @Test
    public void totalDistance() {
        assertEquals(11, day1.calculateTotalDistance());
    }

    @Test
    public void similarityScore() {
        assertEquals(31, day1.calculateSimilarityScore());
    }
}