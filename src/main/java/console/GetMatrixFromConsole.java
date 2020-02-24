package console;

import computations.Data;
import computations.Solver;

import java.util.Scanner;

public class GetMatrixFromConsole implements Command {

    public void execute(Data data) throws InterruptCommandException {
        System.out.println("Введите коэффициенты и свободные члены системы. Чтобы узнать том, как должны быть представлены данные ввода, введите \"help\" или \"помощь\".\n" +
                "Чтобы ввести значения для предыдущей строки заново, введите \"back\" или \"назад\"\n" +
                "Чтобы ввести значения для следующей строки заново, введите \"next\" или \"вперед\"\n" +
                "Чтобы ввести данные для текущей строки заново, введите \"clear\" или \"очистить\"\n" +
                "Чтобы вывести текущие введенные значения, введите \"matrix\" или \"матрица\"\n" +
                "Чтобы завершить ввод данных, введите \"finish\" или \"завершить\"");
        Command nextCommand = null;
        Scanner scanner = Utils.getScanner();

        int n = data.getN();
        double[][] result = new double[n][n + 1];
        int i = 0;
        int numberLeft = n + 1;

        while (nextCommand == null) {
            System.out.println();
            if (numberLeft != 1) {
                System.out.println("Строка #" + (i + 1) + " (осталось ввести коэффициентов - " + (numberLeft - 1) + "; необходимо ввести свободный член уравнения):");
            } else {
                System.out.println("Строка #" + (i + 1) + " (необходимо ввести свободный член уравнения):");
            }


            String answer = scanner.nextLine();

            if (Utils.isReturnCommand(answer)) {
                nextCommand = new GetCoefficientSource();
                System.out.println("Возвращаемся на предыдущий шаг...");
                break;
            } else if (Utils.isInterruptCommand(answer)) {
                throw new InterruptCommandException();
            } else if (Utils.isExitCommand(answer)) {
                System.out.println("Завершение работы программы.");
                System.exit(0);
            } else if (Utils.isHelpCommand(answer)) {
                System.out.println(
                        "Ввод должен иметь следующий формат: \n" +
                        "a_1_1 a_1_2 ... a_1_n b_1 \n" +
                        "a_2_1 a_2_2 ... a_2_n b_2 \n" +
                        "... \n" +
                        "a_n_1 a_n_2 ... a_n_n b_n \n" +
                        "где a_i_j - это коэффичиент j-ой переменной i-ого уравнения,\n" +
                        "b_i - свободный член i-ого уравнения.\n" +
                        "Пример: \n" +
                        "1 0 2 \n" +
                        "0 1 4"
                );
                continue;
            } else if (Utils.isBackCommand(answer)) {
                if (i > 0) {
                    numberLeft = n + 1;
                    i--;
                    System.out.println("Вы переместились на строку #" + (i + 1) + ".");
                } else {
                    System.out.println("Это первая строка, нельзя переместиться на еще одну строку назад.");
                }
                continue;
            } else if (Utils.isNextCommand(answer)) {
                if (i < n) {
                    numberLeft = n + 1;
                    i++;
                    System.out.println("Вы переместились на строку #" + (i + 1) + ".");
                } else {
                    System.out.println("Это последняя строка, нельзя переместиться на еще одну строку вперед.");
                }
                continue;
            } else if (Utils.isClearCommand(answer)) {
                numberLeft = n + 1;
                System.out.println("Вы переместились к началу строки.");
            } else if (Utils.isMatrixCommand(answer)) {
                for (int j = 0; j < result.length; j++) {
                    for (int k = 0; k < result[0].length; k ++) {
                        System.out.printf("%+-20.10f ", result[j][k]);
                        if (k == n - 1) {
                            System.out.print("| ");
                        }
                    }
                    System.out.println();
                }
                continue;
            } else if (Utils.isFinishCommand(answer)) {
                System.out.println("Вы завершили ввод матрицы.");
                data.setMatrix(result);
                break;
            }

            answer = answer.trim();
            String[] answerParts = answer.split("\\s+");
            if (answerParts.length > numberLeft) {
                System.out.print("Вы ввели слишком много данных для текущей (" + (i + 1) + ") строки. Введите данные для этой строки еще раз.");
                continue;
            }

            for (int j = 0; j < answerParts.length; j++) {
                try {
                    String currentCoefficient = answerParts[j];
                    currentCoefficient = currentCoefficient.replace(",", ".");
                    result[i][(n + 1) - numberLeft] = Double.parseDouble(currentCoefficient);
                    if (currentCoefficient.trim().length() - (currentCoefficient.contains(".") ? 1 : 0) > 10) {
                        result[i][(n + 1) - numberLeft] = 0;
                        System.out.println("Значение #" + (j + 1) + " в строке #" + (i + 1) + " матрицы содержит больше 10 знаков. Это может сказаться на точности, пожалуйста, запишите это значение с меньшим числом знаков (сейчас это значение было обнулено).");
                    }
                    numberLeft--;
                } catch (NumberFormatException e) {
                    System.out.println("Введенное значение #" + (j + 1) + " не является десятичной дробью.");
                }
            }

            if (numberLeft == 0) {
                numberLeft = n + 1;
                if (i != n - 1) {
                    i++;
                } else {
                    System.out.println("Вы ввели все значения для последней строки расширенной матрицы\n" +
                            "Если вы хотите завершить ввод данных, введите \"finish\" или \"завершить\"");
                }
            }

        }
        if (nextCommand == null) {
            Solver solver = new Solver(data);
            solver.solve();
        } else {
            nextCommand.execute(data);
        }
    }

}
