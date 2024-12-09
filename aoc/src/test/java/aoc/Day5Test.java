package aoc;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import static org.junit.Assert.assertEquals;

public class Day5Test {

    @Test
    public void name() throws Exception {
        InputStream inputStream = Day5.class.getClassLoader().getResourceAsStream("day5-input");
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        Day5 day5 = new Day5(reader);
        assertEquals(143, day5.part1());
    }

    @Test
    public void name2() throws Exception{
        InputStream inputStream = Day5.class.getClassLoader().getResourceAsStream("day5-input");
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        Day5 day5 = new Day5(reader);
        assertEquals(123, day5.part2());
    }
}