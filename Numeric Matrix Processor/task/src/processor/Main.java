package processor;

import java.util.Scanner;

import static processor.MatrixOperations.*;


public class Main {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int userChoice = -1;

        while (userChoice != 0) {
            printChoices();
            userChoice = scanner.nextInt();

            switch (userChoice) {
                case 1 : {
                    addAB();
                    break;
                }
                case 2 : {
                    multiplexMatrixByNumber();
                    break;
                }
                case 3 : {
                    multiplyTwoMatrices();
                    break;
                }
                case 4 : {
                    printTransposeChoices();
                    userChoice = scanner.nextInt();

                    chooseTransposition(userChoice);
                    break;
                }
                case 5 : {
                    calculateDeterminant();
                    break;
                }
                case 6 : {
                    inverseMatrix();
                    break;
                }
            }
        }
    }

    private static void printChoices() {
        System.out.println("1. Add matrices");
        System.out.println("2. Multiply matrix to a constant");
        System.out.println("3. Multiply matrices");
        System.out.println("4. Transpose matrix");
        System.out.println("5. Calculate a determinant");
        System.out.println("6. Inverse matrix");
        System.out.println("0. Exit");

        System.out.print("Your choice: ");
    }

    private static void printTransposeChoices() {
        System.out.println("1. Main diagonal");
        System.out.println("2. Side diagonal");
        System.out.println("3. Vertical line");
        System.out.println("4. Horizontal line");

        System.out.print("Your choice: ");
    }

    private static void chooseTransposition(int userChoice) {
        switch (userChoice) {
            case 1 : {
                transposeMainDiagonal();
                break;
            }
            case 2 : {
                transposeSideDiagonal();
                break;
            }
            case 3 : {
                transposeVerticalLine();
                break;
            }
            case 4 : {
                transposeHorizontalLine();
                break;
            }
        }
    }
}


