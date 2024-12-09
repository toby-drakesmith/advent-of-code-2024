package aoc;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import static org.junit.Assert.assertEquals;

public class Day4Test {

    @Test
    public void name() {
        InputStream inputStream = Day4.class.getClassLoader().getResourceAsStream("day4-input");
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        Day4 day4 = new Day4(reader);

        assertEquals(18, day4.findXmasCount());
    }

    @Test
    public void findXMas() {
        InputStream inputStream = Day4.class.getClassLoader().getResourceAsStream("day4-input");
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        Day4 day4 = new Day4(reader);

        assertEquals(9, day4.findXmasCrossCount());
    }
}