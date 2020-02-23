package console;

import java.util.Scanner;

public class Introduction implements Command {

    public void execute(Data data) throws InterruptCommandException {
        System.out.println("Введите \"1\", чтобы ввести размерность и точность вручную. Введите \"2\", чтобы загрузить все данные из файла. Введите \"3\", чтобы выйти из программы.");
        Command nextCommand = null;
        command: while (nextCommand == null) {
            Scanner scanner = Utils.getScanner();
            String answer = scanner.nextLine();

            if (Utils.isReturnCommand(answer)) {
                nextCommand = new Introduction();
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
                        nextCommand = new GetNFromConsole();
                        break command;
                    case 2:
                        nextCommand = new GetDataFromFile();
                        break command;
                    case 3:
                        System.out.println("Завершение работы программы.");
                        System.exit(0);
                        break command;
                }
            } catch (NumberFormatException e) {
                System.out.println("Вы ввели в качестве ответа не число.");
            }
            System.out.println("Неизвестный ответ, пожалуйста, введите один из вариантов ответа, предложенных выше.");
        }
        nextCommand.execute(data);
    }

}
