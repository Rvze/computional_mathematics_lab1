public class LinearSystemSolver {

    double EPS = 0.0000012;
    int approximation = 1;


    public double[] findSolution(double[][] matrix) {
        return findSolution(matrix, EPS);
    }

    public double[] findSolution(double[][] matrix, double eps) {
        int size = matrix.length;
        double[] previousValue = new double[size];
        for (int i = 0; i < size; i++) {
            previousValue[i] = 0.0;
        }
        if (diagonal(size, matrix)) {
            while (true) {
                double[] currentValue = new double[size];

                for (int i = 0; i < size; i++) {
                    currentValue[i] = matrix[i][size];

                    for (int j = 0; j < size; j++) {
                        if (j < i)
                            currentValue[i] -= matrix[i][j] * currentValue[j];
                        if (j > i)
                            currentValue[i] -= matrix[i][j] * previousValue[j];
                    }
                    currentValue[i] /= matrix[i][i];
                }
                double error = 0.0;
                for (int i = 0; i < size; i++) {
                    error = Math.abs(currentValue[i] - previousValue[i]);
                }
                if (error < eps)
                    break;
                previousValue = currentValue;
                approximation++;
            }
            return previousValue;
        } else
            System.out.println("Не выполняется преобладание диагоналей");
        return previousValue;
    }

    public int getApproximation() {
        return approximation;
    }


    private boolean diagonal(int size, double[][] matrix) {
        int i, j, k = 0;
        double sum;
        for (i = 0; i < size; i++) {
            sum = 0;
            for (j = 0; j < size; j++) {
                sum = sum + Math.abs(matrix[i][j]);
                sum = sum - Math.abs(matrix[i][i]);
                if (sum > matrix[i][i]) {
                    System.out.println(matrix[i][i] + " < " + sum);
                } else {
                    k++;
                    System.out.println(matrix[i][i] + " > " + sum);
                }
            }
        }
        return k != size * 3;
    }

    private boolean diagonalDominating(int size, double[][] matrix) {
        double sum = 0;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (i != j) {
                    sum += Math.abs(matrix[i][j]);
                    System.out.println(matrix[i][j] + " <>" + sum);
                }
            }
        }
        for (int i = 0; i < size; i++) {
            if (Math.abs(matrix[i][i]) < sum) {
                System.out.println(matrix[i][i] + "<> " + sum);
                break;
            }
        }

        return true;
    }



}
