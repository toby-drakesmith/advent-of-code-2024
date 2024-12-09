package aoc;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;

public class Day5 {
    final BufferedReader bufferedReader;
    final Map<Integer, List<Integer>> rules = new HashMap<>();
    final List<Row> rows = new ArrayList<>();

    public Day5(BufferedReader bufferedReader) throws Exception {
        this.bufferedReader = bufferedReader;
        String s;
        while ((s = bufferedReader.readLine()) != null) {
            if (s.contains("|")) {
                String[] split = s.split("\\|");
                rules.computeIfAbsent(Integer.parseInt(split[0]), map -> new ArrayList<>()).add(Integer.parseInt(split[1]));
            } else if (!s.isEmpty()) {
                List<Integer> row = new ArrayList<>();
                Arrays.stream(s.split(",")).map(Integer::parseInt).forEach(row::add);
                rows.add(new Row(row));
            }
        }
    }

    public int part1() {
        int sum = 0;
        for (Row row : rows) {
            AtomicBoolean valid = new AtomicBoolean(true);
            rules.keySet().forEach(key -> {
                if (row.updates.contains(key)) {
                    if (row.isInvalid(key, rules.get(key))) {
                        valid.set(false);
                    }
                }
            });
            if (valid.get()) {
                sum += row.middle();
            }
        }
        return sum;
    }

    public int part2() {
        int sum = 0;
        for (Row row : rows) {
            AtomicBoolean valid = new AtomicBoolean(true);
            rules.keySet().forEach(key -> {
                if (row.updates.contains(key) && row.isInvalid(key, rules.get(key))) {
                    valid.set(false);
                }
            });
            if (!valid.get()) {
                row.makeValid(rules);
                sum += row.middle();
            }
        }
        return sum;
    }

    public static void main(String[] args) throws Exception {
        try (InputStream inputStream = Day5.class.getClassLoader().getResourceAsStream("day5-input")) {
            assert inputStream != null;
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            Day5 day5 = new Day5(reader);
            System.out.println(day5.part1());
            System.out.println(day5.part2());
        }
    }
}

class Row {
    final List<Integer> updates;

    Row(List<Integer> updates) {
        this.updates = updates;
    }

    public int middle() {
        return updates.get(updates.size() / 2);
    }

    public boolean isInvalid(Integer key, List<Integer> integers) {
        for (Integer integer : integers) {
            if (updates.contains(integer) && updates.indexOf(integer) < updates.indexOf(key)) {
                return true;
            }
        }
        return false;
    }

    public void makeValid(Map<Integer, List<Integer>> rules) {
        updates.sort((o1, o2) -> {
            if (o1.equals(o2)) {
                return 0;
            }
            if (rules.containsKey(o1) && rules.get(o1).contains(o2)) {
                return -1;
            }
            return 1;
        });
    }

    @Override
    public String toString() {
        return "Row{" +
                "updates=" + updates +
                '}';
    }
}


