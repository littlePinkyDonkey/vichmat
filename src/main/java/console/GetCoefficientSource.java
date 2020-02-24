package console;

import computations.Data;

import java.util.Scanner;

public class GetCoefficientSource implements Command {

    public void execute(Data data) throws InterruptCommandException {
        System.out.println("Введите \"1\", чтобы ввести коэффициенты вручную. Введите \"2\", чтобы загрузить их из файла. Введите \"3\", чтобы сгенерировать случайные коэффициенты.");
        Command nextCommand = null;
        command: while (nextCommand == null) {
            Scanner scanner = Utils.getScanner();
            String answer = scanner.nextLine();

            if (Utils.isReturnCommand(answer)) {
                nextCommand = new GetAccuracyFromConsole();
                System.out.println("Возвращаемся на предыдущий шаг...");
                break;
            } else if (Utils.isInterruptCommand(answer)) {
                throw new InterruptCommandException();
            } else if (Utils.isExitCommand(answer)) {
                System.out.println("Завершение работы программы.");
                System.exit(0);
            }

            try {
                int answerNumber = Integer.parseInt(answer);
                switch (answerNumber) {
                    case 1:
                        nextCommand = new GetMatrixFromConsole();
                        break command;
                    case 2:
                        nextCommand = new GetMatrixFromFile();
                        break command;
                    case 3:
                        nextCommand = new GenerateMatrixCoefficients();
                        break command;
                }
            } catch (NumberFormatException e) {
                System.out.println("Неизвестный ответ, пожалуйста, введите один из вариантов ответа, предложенных выше.");
            }
            System.out.println("Неизвестный ответ, пожалуйста, введите один из вариантов ответа, предложенных выше.");
        }
        nextCommand.execute(data);
    }

}
