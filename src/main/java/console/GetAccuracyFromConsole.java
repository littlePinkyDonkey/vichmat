package console;

import java.util.Scanner;

public class GetAccuracyFromConsole implements Command {

    public void execute(Data data) throws InterruptCommandException {
        System.out.println("Введите точность, с которой будет находиться решение СЛАУ (десятичная дробь, которая не меньше 0).");
        Command nextCommand = null;
        while (nextCommand == null) {
            Scanner scanner = Utils.getScanner();
            String answer = scanner.nextLine();

            if (Utils.isReturnCommand(answer)) {
                nextCommand = new GetNFromConsole();
                System.out.println("Возвращаемся на предыдущий шаг...");
                break;
            } else if (Utils.isInterruptCommand(answer)) {
                throw new InterruptCommandException();
            } else if (Utils.isExitCommand(answer)) {
                System.out.println("Завершение работы программы.");
                System.exit(0);
            }

            answer.replace(',', '.');
            try {
                double accuracy = Double.parseDouble(answer);
                if (accuracy < 0) {
                    System.out.println("Введенная точность меньше 0. Пожалуйста, введите точность, которая будет больше или равна 0.");
                    continue;
                }
                data.setAccuracy(accuracy);
                nextCommand = new GetCoefficientSource();
            } catch (NumberFormatException e) {
                System.out.println("Вы ввели в качестве ответа не число.");
            }
        }
        nextCommand.execute(data);
    }

}
