import java.io.*;
import java.util.Scanner;

public class MatrixWorker {
    double[][] matrix;


    public double[][] parseMatrixFromKeyboard(int size) {
        System.out.println("Input values: \n");
        matrix = new double[size][size + 1];
        Scanner scanner = new Scanner(System.in);
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size + 1; j++) {
                System.out.println("matrix[" + i + "][" + j + "]:");
                matrix[i][j] = scanner.nextDouble();

            }
        }
        return matrix;
    }

    public double[][] parseMatrixFromKeyboardV2(InputStream is, double[][] matrix, int size) {
        Scanner scanner = new Scanner(System.in);
        for (int i = 0; i < size; ++i) {
            for (int j = 0; j < size+1; ++j) {
                matrix[i][j] = scanner.nextDouble();
            }
        }
        return matrix;
    }



    public double[][] parseMatrixFromFile(int size) {
        System.out.println("\nInput the file name:\n");
        Scanner scanner = new Scanner(System.in);
        matrix = new double[size][size + 1];
        String row;

        String filename = scanner.nextLine();
        try {
            Scanner file = new Scanner(new File(filename));
            int lineCount = 0;
            int i = 0;
            while (i < size) {
                row = file.nextLine().trim().replaceAll(",", ".");
                if (row.isEmpty())
                    continue;
                String[] num = row.split("(\\s++)");
                if (num.length != size + 1)
                    throw new RuntimeException();
                else {
                    for (int j = 0; j < size + 1; j++) {
                        matrix[lineCount][j] = Double.parseDouble(num[j]);
                    }
                    lineCount++;
                }
                i++;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("No such file or directory");
        }
        return matrix;
    }
}
