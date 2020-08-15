package processor;

import java.util.Scanner;

public class MatrixOperations {
    private static final Scanner scanner = new Scanner(System.in);

    static void addAB() {

        double[][] matrixA = enterMatrix("first");
        int rowA = matrixA.length;
        int columnA = matrixA[0].length;

        double[][] matrixB = enterMatrix("second");
        int rowB = matrixB.length;
        int columnB = matrixB[0].length;

        if (rowA == rowB && columnA == columnB) {

            double[][] matrixSumAB = new double[rowA][columnA];

            System.out.println("The sum result is:");

            for (int i = 0; i < rowA; i++) {
                for (int j = 0; j < columnA; j++) {
                    matrixSumAB[i][j] = matrixA[i][j] + matrixB[i][j];
                    System.out.print(matrixSumAB[i][j] + " ");
                }
                System.out.println();
            }
        } else {
            System.out.println("ERROR");
        }
    }

    static void multiplexMatrixByNumber() {
        double[][] matrix = enterMatrix("");

        System.out.print("Enter constant: ");
        int multiplier = scanner.nextInt();

        multiplicationByNumber(matrix, multiplier);

        System.out.println("The multiplication by number result is:");
        printMatrix(matrix);
    }

    private static void multiplicationByNumber(double[][] matrix, double multiplier) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                matrix[i][j] *= multiplier;
            }
        }
    }

    static void multiplyTwoMatrices() {
        double[][] matrixA = enterMatrix("first");
        int rowA = matrixA.length;
        int columnA = matrixA[0].length;

        double[][] matrixB = enterMatrix("second");
        int rowB = matrixB.length;
        int columnB = matrixB[0].length;

        if (columnA == rowB) {
            double[][] matrixMultiplyAB = new double[rowA][columnB];

            // resulted i j
            for (int i = 0; i < matrixMultiplyAB.length; i++) {
                for (int j = 0; j < matrixMultiplyAB[0].length; j++) {

                    double tmp = 0;
                    for (int k = 0; k < columnA; k++) {

                        tmp += matrixA[i][k] * matrixB[k][j];
                    }

                    matrixMultiplyAB[i][j] = tmp;
                    System.out.print(matrixMultiplyAB[i][j] + " ");
                }
                System.out.println();
            }

        } else {
            System.out.println("ERROR");
        }

        System.out.println("The multiplication result is:");
    }

    static void transposeMainDiagonal() {
        double[][] matrix = enterMatrix("");

        double[][] transposeMatrix = getTransposeMainDiagonal(matrix);

        printMatrix(transposeMatrix);
    }

    private static double[][] getTransposeMainDiagonal(double[][] matrix) {
        double[][] transposeMatrix = new double[matrix.length][matrix[0].length];

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                transposeMatrix[i][j] = matrix[j][i];
            }
        }

        return transposeMatrix;
    }

    static void transposeSideDiagonal() {
        double[][] matrix = enterMatrix("");

        double[][] transposeMatrix = new double[matrix.length][matrix[0].length];

        int columnIndex = 0;
        for (int i = matrix.length - 1; i >= 0; i--) {
            int rowIndex = 0;
            for (int j = matrix[0].length - 1; j >= 0; j--) {
                transposeMatrix[rowIndex][columnIndex] = matrix[i][j];
                rowIndex++;
            }
            columnIndex++;
        }

        printMatrix(transposeMatrix);
    }

    static void transposeVerticalLine() {
        double[][] matrix = enterMatrix("");

        double[][] transposeMatrix = new double[matrix.length][matrix[0].length];

        for (int i = 0; i < matrix.length; i++) {
            int rowIndex = 0;
            for (int j = matrix[0].length - 1; j >= 0; j--) {
                transposeMatrix[i][rowIndex] = matrix[i][j];
                rowIndex++;
            }
        }

        printMatrix(transposeMatrix);
    }

    static void transposeHorizontalLine() {
        double[][] matrix = enterMatrix("");

        double[][] transposeMatrix = new double[matrix.length][matrix[0].length];

        int columnIndex = 0;
        for (int i = matrix.length - 1; i >= 0; i--) {
            System.arraycopy(matrix[i], 0, transposeMatrix[columnIndex], 0, matrix[0].length);
            columnIndex++;
        }

        printMatrix(transposeMatrix);
    }

    static void calculateDeterminant() {
        double[][] matrix = enterMatrix("");

        if (matrix.length == matrix[0].length) {
            double determinant = matrixDet(matrix);
            System.out.println("The result is\n" + determinant);
        } else {
            System.out.println("Isn't square matrix!");
        }
    }

    // Вычисление определителя матрицы разложение по первой строке
    private static double matrixDet(double[][] matrix) {
        double det = 0;
        int degree = 1; // (-1)^(1+j) из формулы определителя

        // Условие выхода из рекурсии
        if (matrix.length == 1) {
            return matrix[0][0];

        } else if (matrix.length == 2) {
            return matrix[0][0] * matrix[1][1] - matrix[0][1] * matrix[1][0];

        } else {

            // Раскладываем по 0-ой строке, цикл бежит по столбцам
            for (int j = 0; j < matrix.length; j++) {
                //Удалить из матрицы i-ю строку и j-ый столбец
                //Результат в newMatrix
                double[][] newMatrix = getMatrixWithoutRowAndCol(matrix, 0, j);

                // Рекурсивный вызов
                // По формуле: сумма по j, (-1)^(1+j) * matrix[0][j] * minor_j (это и есть сумма из формулы)
                // где minor_j - дополнительный минор элемента matrix[0][j]
                // минор - это определитель матрицы без 0-ой строки и j-го столбца

                double minor_j = matrixDet(newMatrix);


                det += degree * matrix[0][j] * minor_j;
                //"Накручиваем" степень множителя
                degree *= -1;
            }
        }

        return det;
    }


    // Возвращает матрицу matrix без row-ой строки и col-того столбца, результат в newMatrix
    private static double[][] getMatrixWithoutRowAndCol(double[][] matrix, int row, int col) {
        double[][] newMatrix = new double[matrix.length - 1][matrix.length - 1];

        int offsetRow = 0; // Смещение индекса строки в матрице
        int offsetCol; // Смещение индекса столбца в матрице
        for (int i = 0; i < matrix.length - 1; i++) {
            // Пропустить row-ую строку
            if (i == row) {
                offsetRow = 1; // Как только встретили строку, которую надо пропустить, делаем смещение для исходной матрицы
            }

            offsetCol = 0; // Обнулить смещение столбца
            for (int j = 0; j < matrix.length - 1; j++) {
                // Пропустить col-ый столбец
                if (j == col) {
                    offsetCol = 1; // Встретили нужный столбец, проускаем его смещением
                }
                newMatrix[i][j] = matrix[i + offsetRow][j + offsetCol];
            }
        }

        return newMatrix;
    }

    static void inverseMatrix() {
        double[][] matrix = enterMatrix("");

        if (matrix.length == matrix[0].length) {
            double determinant = matrixDet(matrix);

            if (determinant != 0) {

                double[][] minorMatrix = new double[matrix.length][matrix.length];

                // make minor matrix
                getMinorMatrix(matrix, minorMatrix);

                // transpose it
                minorMatrix = getTransposeMainDiagonal(minorMatrix);

                // multiplication
                multiplicationByNumber(minorMatrix, 1 / determinant);

                printMatrix(minorMatrix);
            } else {
                System.out.println("Determinant is zero!");
            }

        } else {
            System.out.println("Isn't square matrix!");
        }

    }

    private static void getMinorMatrix(double[][] matrix, double[][] minorMatrix) {
        int degree; // (-1)^(1+j) из формулы определителя

        if (matrix.length == 1) {
            minorMatrix[0][0] = matrix[0][0];

        } else if (matrix.length == 2) {
            if (minorMatrix.length == 2) {
                minorMatrix[0][0] = matrix[1][1];
                minorMatrix[0][1] = matrix[1][0];
                minorMatrix[1][0] = matrix[0][1];
                minorMatrix[1][1] = matrix[0][0];
            }
        } else {
            for (int i = 0; i < matrix.length; i++) {
                for (int j = 0; j < matrix.length; j++) {
                    // Удалить из матрицы i-ю строку и j-ый столбец
                    // Результат в newMatrix
                    double[][] newMatrix = getMatrixWithoutRowAndCol(matrix, i, j);

                    // Рекурсивный вызов
                    // где minor_j - дополнительный минор элемента matrix[0][j]
                    // минор - это определитель матрицы без 0-ой строки и j-го столбца
                    double minor_j = matrixDet(newMatrix);

                    // change degree
                    degree = (int) Math.pow(-1, i + 1 + j + 1);

                    minorMatrix[i][j] = degree *  minor_j;
                }
            }
        }

    }

    private static double[][] enterMatrix(String name) {
        System.out.print("Enter size of " + name + " matrix: ");
        int row = scanner.nextInt();
        int column = scanner.nextInt();

        System.out.println("Enter " + name + " matrix: ");

        return fillMatrix(row, column);
    }

    private static double[][] fillMatrix(int row, int column) {

        double[][] matrix = new double[row][column];

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                matrix[i][j] = scanner.nextDouble();
            }
        }

        return matrix;
    }

    private static void printMatrix(double[][] matrix) {
        for (double[] row : matrix) {
            for (int j = 0; j < matrix[0].length; j++) {
                System.out.printf("%.2f ", row[j]);
            }
            System.out.println();
        }
    }
}
