package console;

import computations.Data;

import java.util.Scanner;

public class GetNFromConsole implements Command {

    public void execute(Data data) throws InterruptCommandException {
        System.out.println("Введите n - размерность матрицы СЛАУ (минимальное значение: 1. максимальное значение: 20).");
        Command nextCommand = null;
        while (nextCommand == null) {
            Scanner scanner = Utils.getScanner();
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
            }

            try {
                int n = Integer.parseInt(answer);
                if (n < 1) {
                    System.out.println("Введенное n меньше 1. Пожалуйста, введите целое n, попадающее в диапазон от 1 до 20 включительно.");
                    continue;
                } else if (n > 20) {
                    System.out.println("Ввденное n больше 20. Пожалуйста, введите целое n, попадающее в диапазон от 1 до 20 включительно.");
                    continue;
                }
                data.setN(n);
                nextCommand = new GetAccuracyFromConsole();
            } catch (NumberFormatException e) {
                System.out.println("Вы ввели в качестве ответа не целое число.");
            }
        }
        nextCommand.execute(data);
    }

}
