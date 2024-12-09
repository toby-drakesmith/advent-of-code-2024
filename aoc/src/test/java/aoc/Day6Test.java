package aoc;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import static org.junit.Assert.assertEquals;

public class Day6Test {

    @Test
    public void calculatePositions() {
        InputStream inputStream = Day6.class.getClassLoader().getResourceAsStream("day6-input");
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        Day6 day6 = new Day6(reader);
        assertEquals(41, day6.part1());
    }

    @Test
    public void calculatePositions2() {
        InputStream inputStream = Day6.class.getClassLoader().getResourceAsStream("day6-input");
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        Day6 day6 = new Day6(reader);
        assertEquals(6, day6.part2());
    }
}