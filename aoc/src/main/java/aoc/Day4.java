package aoc;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Day4 {
    final BufferedReader bufferedReader;
    private final char[][] array;

    public Day4(BufferedReader bufferedReader) {
        this.bufferedReader = bufferedReader;
        final List<char[]> lines = new ArrayList<>();
        bufferedReader.lines().forEach(line -> {
                    char[] charArray = line.toCharArray();
                    lines.add(charArray);
                }
        );
        array = lines.toArray(new char[lines.size()][]);
    }

    public int findXmasCount() {
        return findWordCount(array);
    }

    public int findXmasCrossCount() {
        return findCrosses(array);
    }

    private int findWordCount(char[][] array) {
        int numberOfRows = array.length;
        int rowWidth = array[0].length;
        int count = 0;

        for (int i = 0; i < numberOfRows; i++) {
            for (int j = 0; j < rowWidth; j++) {
                count += search(array, i, j);
            }
        }
        return count;
    }

    private int findCrosses(char[][] array) {
        int numberOfRows = array.length;
        int rowWidth = array[0].length;
        int count = 0;

        for (int i = 0; i < numberOfRows; i++) {
            for (int j = 0; j < rowWidth; j++) {
                count += searchForCross(array, i, j);
            }
        }
        return count;
    }

    private int searchForCross(char[][] array, int row, int column) {
        if (array[row][column] != 'A') {
            return 0;
        }

        int count = 0;
        try {
            if (isCross(row, column)) {
                count++;
            }
        } catch (Exception ignored) {
        }
        return count;
    }

    private boolean isCross(int row, int col) {
        boolean matches = true;
        if ((array[row - 1][col - 1] != 'M' || array[row + 1][col + 1] != 'S') && (array[row - 1][col - 1] != 'S' || array[row + 1][col + 1] != 'M')) {
            matches = false;
        } else {
            if ((array[row - 1][col + 1] != 'M' || array[row + 1][col - 1] != 'S') && (array[row - 1][col + 1] != 'S' || array[row + 1][col - 1] != 'M')) {
                matches = false;
            }
        }
        return matches;
    }

    private int search(char[][] array, int row, int column) {
        String word = "XMAS";
        if (array[row][column] != word.charAt(0)) {
            return 0;
        }
        int count = 0;

        int[][] directions = {
                {-1, -1}, {-1, 0}, {-1, 1}, {0, -1},
                {0, 1}, {1, -1}, {1, 0}, {1, 1}
        };

        for (int[] dir : directions) {
            int k;
            int currentX = row + dir[0];
            int currentY = column + dir[1];

            for (k = 1; k < word.length(); k++) {
                if (isOutOfBounds(array, currentX, currentY)) {
                    break;
                }
                if (array[currentX][currentY] != word.charAt(k)) {
                    break;
                }
                currentY += dir[1];
                currentX += dir[0];
            }

            if (k == word.length()) {
                count++;
            }
        }

        return count;
    }

    private static boolean isOutOfBounds(char[][] array, int currentX, int currentY) {
        return currentX >= array.length || currentX < 0 || currentY < 0 || currentY >= array[0].length;
    }

    public static void main(String[] args) throws Exception {
        try (InputStream inputStream = Day4.class.getClassLoader().getResourceAsStream("day4-input")) {
            assert inputStream != null;
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            Day4 day4 = new Day4(reader);
            System.out.println(day4.findXmasCount());
            System.out.println(day4.findXmasCrossCount());
        }

    }
}
