package aoc;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;

public class Day6 {
    final BufferedReader bufferedReader;
    final char[][] grid;
    final int[] up = new int[]{0, -1};
    final int[] down = new int[]{0, 1};
    final int[] right = new int[]{1, 0};
    final int[] left = new int[]{-1, 0};
    int[] direction = up;
    int currentX;
    int currentY;
    final Set<String> visitedCoords = new HashSet<>();

    public Day6(BufferedReader bufferedReader) {
        this.bufferedReader = bufferedReader;
        final List<char[]> lines = new ArrayList<>();
        bufferedReader.lines().forEach(line -> {
                    char[] charArray = line.toCharArray();
                    lines.add(charArray);
                }
        );
        grid = lines.toArray(new char[lines.size()][]);
        findStart();
    }

    private void findStart() {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == '^') {
                    currentX = j;
                    currentY = i;
                }
            }
        }
    }

    public long part1() {
        while (true) {
            currentX += direction[0];
            currentY += direction[1];
            if (areDone(grid, currentY, currentX)) {
                break;
            }
            visitedCoords.add(currentX + "," + currentY);
            direction = maybeChangeDirection(direction, grid, currentY, currentX);
        }
        return visitedCoords.size() + 1;
    }

    public int part2() {
        // brute force - put obstacle in each place and run simulation
        int sum = 0;
        for (int y = 0; y < grid.length; y++) {
            for (int x = 0; x < grid[y].length; x++) {
                direction = up;
                char[][] copy = Arrays.copyOf(grid, grid.length);
                int xPos = currentX;
                int yPos = currentY;
                if (copy[y][x] == '.') {
                    copy[y][x] = '#';
                } else continue;
                if (inLoop(copy, xPos, yPos)) {
                    System.out.println("In loop: row=" + y + " col=" + x);
                    ++sum;
                }
                copy[y][x] = '.';
            }
        }
        return sum;
    }

    private boolean inLoop(char[][] copy, int x, int y) {
        int count = 0;
        while (true) {
            x += direction[0];
            y += direction[1];
            if (areDone(copy, y, x)) {
                break;
            }
            if (++count > 10000) {
                return true;
            }
            direction = maybeChangeDirection(direction, copy, y, x);
        }
        return false;
    }

    private int[] maybeChangeDirection(int[] direction, char[][] gridz, int y, int x) {
        while (gridz[y + direction[1]][x + direction[0]] == '#') {
            direction = rotateDirection(direction);
        }
        return direction;
    }

    private int[] rotateDirection(int[] direction) {
        if (direction == right) {
            return down;
        } else if (direction == down) {
            return left;
        } else if (direction == left) {
            return up;
        } else if (direction == up) {
            return right;
        }
        throw new RuntimeException("Somethin wrong");
    }

    private boolean areDone(char[][] gridz, int y, int x) {
        return y >= gridz.length - 1 || x >= gridz[0].length - 1
                || y == 0 || x == 0;
    }

    public static void main(String[] args) throws Exception {
        try (InputStream inputStream = Day6.class.getClassLoader().getResourceAsStream("day6-input")) {
            assert inputStream != null;
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            Day6 day6 = new Day6(reader);
//            System.out.println(day6.part1());
            System.out.println(day6.part2());
        }
    }
}
