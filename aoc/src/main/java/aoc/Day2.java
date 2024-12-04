package aoc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;

public class Day2 {

    private BufferedReader bufferedReader;

    public Day2(BufferedReader bufferedReader) {
        this.bufferedReader = bufferedReader;
    }

    public int calculateSafeReports() {
        AtomicInteger count = new AtomicInteger();
        bufferedReader.lines().forEach(line -> {
            if (isLineSafe(Arrays.stream(line.split(" ")).mapToInt(Integer::valueOf).toArray())) {
                count.addAndGet(1);
            }
        });
        return count.get();
    }

    public int calculateSafeDampReports() {
        AtomicInteger dampenerCount = new AtomicInteger();
        bufferedReader.lines().map(line -> Arrays.stream(line.split(" ")).mapToInt(Integer::valueOf).toArray()).forEach(array -> {
            if (bruteForceCheck(array)) {
                dampenerCount.addAndGet(1);
            }
        });
        return dampenerCount.get();
    }

    private boolean bruteForceCheck(int[] numbers) {
        for (int i = 0; i < numbers.length; i++) {
            if (isLineSafe(removeElement(numbers, i))) {
                return true;
            }
        }
        return false;
    }

    private boolean isLineSafe(int[] numbers) {
        boolean ascending = numbers[0] < numbers[1];
        for (int i = 1; i < numbers.length; i++) {
            int number = numbers[i];
            int prevNumber = numbers[i - 1];
            if (Math.abs(number - prevNumber) > 3 || number == prevNumber) {
                return false;
            }
            if (ascending) {
                if (number < prevNumber) {
                    return false;
                }
            } else {
                if (number > prevNumber) {
                    return false;
                }
            }
        }
        return true;
    }

    private static int[] removeElement(int[] array, int indexToRemove) {
        int[] result = new int[array.length - 1];
        for (int i = 0, j = 0; i < array.length; i++) {
            if (i != indexToRemove) {
                result[j++] = array[i];
            }
        }
        return result;
    }

    public static void main(String[] args) throws IOException {
        try (InputStream inputStream = Day1.class.getClassLoader().getResourceAsStream("day2-input")) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            Day2 day2 = new Day2(reader);
//            System.out.println(day2.calculateSafeReports());
            System.out.println(day2.calculateSafeDampReports());
        }
    }
}

