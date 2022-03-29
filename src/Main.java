import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
    private final String RED = "\u001B[31m";
    private final String GREEN = "\\u001B[32m";
    private final String YELLOW = "\u001B[33m";
    private final String CYAN = "\u001B[36m";
    private final String RESET = "\u001B[0m";
    Matrix matrix = new Matrix();
    MatrixWorker matrixWorker = new MatrixWorker();
    private int size;

    public static void main(String[] args) {
        new Main().run();
    }


    public void run() {
        printf(CYAN + "--------GAUSS-SEIDEL METHOD--------" + RESET);
        Scanner scanner = new Scanner(System.in);
        int a = 0;
        do {
            printf("\n----------------MENU----------------\n" + "Press 1, if you want input matrix \n:0\n");
            a = scanner.nextInt();
        } while (a != 1);
        switch (a) {
            case 1:
                matrix.setElements(parseMatrix());

        }
        showMatrix(size, matrix.getElements(), "\nMatrix: \n");
        LinearSystemSolver linearSystemSolver = new LinearSystemSolver();
        double[] ans = linearSystemSolver.findSolution(matrix.getElements());

        System.out.println(Arrays.toString(ans));
        double[] nev = gaussDiscrepancy(matrix.getElements(), ans);
        System.out.println(Arrays.toString(Arrays.stream(nev).toArray()));
        System.out.println("итераций: " + linearSystemSolver.getApproximation());
    }

    public double[] gaussDiscrepancy(double[][] matrix, double[] ans) {
        double[] vector = new double[size];
        double op = 0;
        for (int i = 0; i < size; ++i) {
            op = 0;
            for (int j = 0; j < size; ++j) {
                if (matrix[i][j] == 0)
                    continue;
                op = op + ans[j] * matrix[i][j];
            }
            vector[i] = matrix[i][size] - op;
        }
        return vector;
    }


    public double[][] parseMatrix() {
        int size = calculateSize();
        double[][] matrix = new double[size][size + 1];
        int a;
        Scanner scanner = new Scanner(System.in);
        do {
            printf("\nHow do you want to enter the matrix values?\n" +
                    "1 - Input from keyboard\n 2 - Input from file\n");
            a = scanner.nextInt();
        } while (a < 1 | a > 2);
        switch (a) {
            case 1:
                matrix = matrixWorker.parseMatrixFromKeyboardV2(System.in, matrix, size);
                for (double[] line : matrix) {
                    System.out.println(Arrays.toString(line));
                }
                break;
            case 2:
                matrix = matrixWorker.parseMatrixFromFile(size);
                break;

        }
        return matrix;
    }


    public int calculateSize() {
        int a;
        int size = 0;
        Scanner scanner = new Scanner(System.in);
        do {
            printf("\nHow do you want to enter the size?\n" +
                    "1 - Input from keyboard\n2 - Input from file\n");
            a = scanner.nextInt();
        } while (a < 1 | a > 2);
        switch (a) {
            case 1:
                printf("Input matrix size: \n");
                size = scanner.nextInt();
                if (size > 20) {
                    printf(RED + "Size should be less than 20!" + RESET);
                    calculateSize();
                }
                break;
            case 2:
                printf("Input the file path: \n");
                size = calculateSizeFromFile();
                if (size > 20 || size == 0) {
                    printf(RED + "Size should be more than 0 and less than 20!" + RESET);
                    calculateSize();
                }
                break;
        }
        this.size = size;
        return size;
    }

    public int calculateSizeFromFile() {
        int size = 0;
        Scanner scanner = new Scanner(System.in);
        String file = scanner.nextLine();
        try {
            Scanner scan = new Scanner(new File(file));
            size = scan.nextInt();
        } catch (FileNotFoundException e) {
            printf(RED + "Error when reading size from fileThe file is empty!\n" + RESET);
        }
        return size;
    }

    public void showMatrix(int size, double[][] matrix, String output) {
        if (matrix != null) {
            printf(output);
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size + 1; j++) {
                    matrix[i][j] += 0.0;
                }
            }
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size + 1; j++) {
                    System.out.format("%6.2f", matrix[i][j]);
                }
                System.out.println();
            }
            System.out.println();
        }
    }

    public void printf(String str, Object... objects) {
        System.out.printf(str, objects);
    }
}
