package day13;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Reader {

    private static final Path PATH = Paths.get("./resources/Day13_values.txt");
    private static final long PRIZE_OFFSET = 10000000000000L; 

    private final List<int[][]> matrices; // Stores multiple 2x2 coefficient matrices
    private final List<long[]> results;   // Stores corresponding result vectors
    private long totalTokens = 0; // Tracks total tokens spent

    public Reader() {
        matrices = new ArrayList<>();
        results = new ArrayList<>();
    }

    public void readFromFile() {
        try (BufferedReader br = Files.newBufferedReader(PATH)) {
            String line;
            int[][] currentMatrix = new int[2][2];
            long[] currentResult = new long[2];

            while ((line = br.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty()) continue;

                if (line.startsWith("Button A:")) {
                    int[] values = parseButton(line);
                    currentMatrix[0][0] = values[0];
                    currentMatrix[1][0] = values[1];
                } else if (line.startsWith("Button B:")) {
                    int[] values = parseButton(line);
                    currentMatrix[0][1] = values[0];
                    currentMatrix[1][1] = values[1];
                } else if (line.startsWith("Prize:")) {
                    currentResult = parsePrize(line);
                    matrices.add(currentMatrix);
                    results.add(currentResult);
                    currentMatrix = new int[2][2];
                    currentResult = new long[2];
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private int[] parseButton(String line) {
        line = line.replace("Button A:", "").replace("Button B:", "").trim();
        String[] parts = line.split(", ");
        int dx = Integer.parseInt(parts[0].split("\\+")[1]);
        int dy = Integer.parseInt(parts[1].split("\\+")[1]);
        return new int[]{dx, dy};
    }

    private long[] parsePrize(String line) {
        line = line.replace("Prize:", "").trim();
        String[] parts = line.split(", ");
        long prizeX = Long.parseLong(parts[0].split("=")[1]) + PRIZE_OFFSET;
        long prizeY = Long.parseLong(parts[1].split("=")[1]) + PRIZE_OFFSET;
        return new long[]{prizeX, prizeY};
    }

    public long[] solveSystem(int[][] matrix, long[] result) {
        long determinant = (long) matrix[0][0] * matrix[1][1] - (long) matrix[0][1] * matrix[1][0];
        if (determinant == 0) {
            return null; // Matrix is not invertible
        }

        // Calculate adjugate and divide by determinant
        long adj00 = matrix[1][1];
        long adj01 = -matrix[0][1];
        long adj10 = -matrix[1][0];
        long adj11 = matrix[0][0];

        // Multiply adjugate by result vector
        long xNumerator = adj00 * result[0] + adj01 * result[1];
        long yNumerator = adj10 * result[0] + adj11 * result[1];

        // Ensure the solution divides cleanly (integers only)
        if (xNumerator % determinant != 0 || yNumerator % determinant != 0) {
            return null; // No integer solution
        }

        return new long[]{xNumerator / determinant, yNumerator / determinant};
    }

    public void printAllResults() {
        for (int i = 0; i < matrices.size(); i++) {
            int[][] matrix = matrices.get(i);
            long[] result = results.get(i);
            long[] solution = solveSystem(matrix, result);

            System.out.printf("Matrix %d:%n", i + 1);
            for (int[] row : matrix) {
                System.out.printf("%d %d%n", row[0], row[1]);
            }

            System.out.println("Result Vector:");
            System.out.printf("%d %d%n", result[0], result[1]);

            if (solution == null) {
                System.out.println("No possible answer.\n");
            } else {
                System.out.println("Solution (Button Presses):");
                System.out.printf("A = %d, B = %d%n", solution[0], solution[1]);

                // Calculate and accumulate token cost
                long tokenCost = solution[0] * 3 + solution[1] * 1;
                totalTokens += tokenCost;
                System.out.printf("Tokens Spent: %d%n%n", tokenCost);
            }
        }

        System.out.printf("Total Tokens Spent: %d%n", totalTokens);
    }

}
