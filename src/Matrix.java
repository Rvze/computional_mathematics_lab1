public class Matrix {
    private double[][] elements;
    private int size;

    public Matrix(double[][] elements, int size) {
        this.elements = elements;
        this.size = size;
    }

    public Matrix(){

    }

    public void showMatrix() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size + 1; j++) {
                System.out.format("%5.1f", elements[i][j]);
            }
            System.out.println();
        }
    }

    public double[][] getElements() {
        return elements;
    }

    public void setElements(double[][] elements) {
        this.elements = elements;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
}
