package aoc;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.concurrent.atomic.AtomicLong;

public class Day1 {

    private final Queue<Integer> priorityQueue1 = new PriorityQueue<>();
    private final Queue<Integer> priorityQueue2 = new PriorityQueue<>();

    private final List<Integer> list1 = new ArrayList<>();
    private final List<Integer> list2 = new ArrayList<>();

    private final BufferedReader bufferedReader;

    public Day1(final BufferedReader bufferedReader) {
        this.bufferedReader = bufferedReader;
    }

    public int calculateTotalDistance() {
        bufferedReader.lines().forEach(line -> {
            String[] split = line.split(" {3}");
            priorityQueue1.add(Integer.parseInt(split[0]));
            priorityQueue2.add(Integer.parseInt(split[1]));
        });

        int total = 0;
        while (!priorityQueue1.isEmpty() && !priorityQueue2.isEmpty()) {
            final Integer poll1 = priorityQueue1.poll();
            final Integer poll2 = priorityQueue2.poll();
            total += Math.abs(poll1 - poll2);
        }
        return total;
    }

    public long calculateSimilarityScore() {
        bufferedReader.lines().forEach(line -> {
            String[] split = line.split(" {3}");
            list1.add(Integer.parseInt(split[0]));
            list2.add(Integer.parseInt(split[1]));
        });

        final AtomicLong similarityScore = new AtomicLong(0);
        list1.forEach(blah -> {
            final long count = list2.stream().filter(blahblah -> blahblah.equals(blah)).count();
            similarityScore.addAndGet(count * blah);
        });
        return similarityScore.get();
    }

    public static void main(String[] args) throws Exception {
        try (InputStream inputStream = Day1.class.getClassLoader().getResourceAsStream("day1-input")) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            final Day1 day1 = new Day1(reader);
//            System.out.println(day1.calculateTotalDistance());
            System.out.println(day1.calculateSimilarityScore());
        }

    }
}
