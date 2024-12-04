package aoc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicLong;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day3 {

    final BufferedReader bufferedReader;
    private final Pattern pattern = Pattern.compile("mul\\([0-9][0-9]?[0-9]?,[0-9][0-9]?[0-9]?\\)");
    private final Pattern allPatterns = Pattern.compile(
            "(?<mul>mul\\([0-9][0-9]?[0-9]?,[0-9][0-9]?[0-9]?\\))|(?<do>do\\(\\))|(?<dont>don't\\(\\))"
    );
    private boolean doMultiply = true;

    public Day3(BufferedReader bufferedReader) {
        this.bufferedReader = bufferedReader;
    }

    public long multiply() {
        AtomicLong atomicLong = new AtomicLong();
        bufferedReader.lines().forEach(line -> atomicLong.addAndGet(doTheThing(line)));
        return atomicLong.get();
    }

    public long multiply2() {
        AtomicLong atomicLong = new AtomicLong();
        bufferedReader.lines().forEach(line -> {
            atomicLong.addAndGet(doTheThing2(line));
        });
        return atomicLong.get();
    }

    private long doTheThing2(String line) {
        Matcher matcher = allPatterns.matcher(line);
        long total = 0;

        int matchCount = 0;
        while (matcher.find()) {
            matchCount++;
            if (matcher.group("do") != null) {
                doMultiply = true;
            } else if (matcher.group("dont") != null) {
                doMultiply = false;
            } else if (matcher.group("mul") != null) {
                if (doMultiply) {
                    String group = matcher.group();
                    String subGroup = group.substring(4, group.length() - 1);
                    String[] split = subGroup.split(",");
                    total += Long.parseLong(split[0]) * Long.parseLong(split[1]);
                }
            }
        }
        System.out.println(matchCount);
        return total;
    }

    private long doTheThing(String line) {
        Matcher matcher = pattern.matcher(line);
        long total = 0;
        while (matcher.find()) {
            String group = matcher.group();
            String subGroup = group.substring(4, group.length() - 1);
            String[] split = subGroup.split(",");
            total += Long.parseLong(split[0]) * Long.parseLong(split[1]);
        }
        return total;
    }

    public static void main(String[] args) throws IOException {

        try (InputStream inputStream = Day3.class.getClassLoader().getResourceAsStream("day3-input")) {
            assert inputStream != null;
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            Day3 day3 = new Day3(reader);
//            System.out.println(day3.multiply());
            System.out.println(day3.multiply2());
        }
    }
}
