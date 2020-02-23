package console;

public class GenerateMatrixCoefficients implements Command {

    public void execute(Data data) throws InterruptCommandException {
        int n = data.getN();
        double[][] matrix = new double[n][n + 1];

        int equalCoefficientNumber = (int) Math.random() * n;
        for (int i = 0; i < n; i++) {
            double sum = 0;
            for (int j = 0; j < n + 1; j++) {
                if (i != j) {
                    double coefficient = (double)((int)(Math.random() / 0.001)) / 100;
                    matrix[i][j] = coefficient;
                    if (j != n) {
                        sum += coefficient;
                    }
                }
            }
            sum = (double)((int)(Math.random() / 0.001)) / 100;
            if (i == equalCoefficientNumber) {
                matrix[i][i] = sum;
            } else {
                System.out.println(sum);
                matrix[i][i] = sum + (((int)(Math.random() / 0.1)) + 1);
                matrix[i][i] = (double)((int)(matrix[i][i] / 0.001)) / 100;
            }
        }

        data.setMatrix(matrix);
        System.out.println("Сгенерированные коэффициенты и свободные члены:");
        for (int i = 0; i < data.getMatrix().length; i++) {
            for (int j = 0; j < data.getMatrix()[0].length; j ++) {
                System.out.print(data.getMatrix()[i][j] + " ");
            }
            System.out.println();
        }
        Command nextCommand = null; //TODO solve
        nextCommand.execute(data);
    }

}
