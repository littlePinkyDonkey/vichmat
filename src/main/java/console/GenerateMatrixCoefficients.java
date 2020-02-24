package console;

import computations.Data;
import computations.Solver;

public class GenerateMatrixCoefficients implements Command {

    public void execute(Data data) throws InterruptCommandException {
        int n = data.getN();
        double[][] matrix = new double[n][n + 1];

        for (int i = 0; i < n; i++) {
            double sum = 0;
            for (int j = 0; j < n + 1; j++) {
                if (i != j) {
                    double coefficient = ((double)((int)Math.round(Math.random() * 1000))) / 100;
                    if (Math.random() > 0.5) {
                        coefficient *= -1;
                    }
                    matrix[i][j] = coefficient;
                    if (j != n) {
                        sum += Math.abs(coefficient);
                    }
                }
            }
            sum = ((double)((int)Math.round(sum * 100))) / 100;
            matrix[i][i] = sum + ((double)((int)Math.round(Math.random() * 1000))) / 100;
            matrix[i][i] = ((double)((int)Math.round(matrix[i][i] * 100))) / 100;
        }

        data.setMatrix(matrix);
        System.out.println("Сгенерированные коэффициенты и свободные члены:");
        for (int i = 0; i < data.getMatrix().length; i++) {
            for (int j = 0; j < data.getMatrix()[0].length; j ++) {
                System.out.printf("%+-7.2f ", data.getMatrix()[i][j]);
                if (j == n - 1) {
                    System.out.print("| ");
                }
            }
            System.out.println();
        }
        Solver solver = new Solver(data);
        solver.solve();
    }

}
