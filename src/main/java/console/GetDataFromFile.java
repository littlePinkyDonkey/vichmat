package console;

import computations.Data;
import computations.Solver;

import java.io.FileNotFoundException;
import java.util.Scanner;

public class GetDataFromFile implements Command {

    public void execute(Data data) throws InterruptCommandException {
        System.out.println("Введите файл в следующем виде: file <путь до файла>. Чтобы узнать том, как должны быть представлены данные в файле, введите \"help\" или \"помощь\"");
        Command nextCommand = null;
        while (nextCommand == null) {
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
            } else if (Utils.isHelpCommand(answer)) {
                System.out.println(
                        "Файл должен иметь следующий формат: \n" +
                        "n \n" +
                        "accuracy \n" +
                        "a_1_1 a_1_2 ... a_1_n b_1 \n" +
                        "a_2_1 a_2_2 ... a_2_n b_2 \n" +
                        "... \n" +
                        "a_n_1 a_n_2 ... a_n_n b_n \n" +
                        "где n - размерность,\n" +
                        "accuracy - точность,\n" +
                        "a_i_j - это коэффичиент j-ой переменной i-ого уравнения,\n" +
                        "b_i - свободный член i-ого уравнения.\n" +
                        "Пример: \n" +
                        "2 \n" +
                        "0.0001 \n" +
                        "1 0 2 \n" +
                        "0 1 4"
                );
                continue;
            }

            answer = answer.trim();
            String[] answerParts = answer.split("\\s+");
            if (answerParts.length == 2 && answerParts[0].toLowerCase().equals("file")) {
                try {
                    data = Utils.readData(answerParts[1]);
                    break;
                } catch (FileNotFoundException e) {
                    System.out.println("По введенному пути не найден файл. Проверьте корректность введенного пути.");
                } catch (FileFormatException e) {
                    System.out.println(e.getMessage());
                }
            } else {
                System.out.println("Вы ввели путь до файла в неверном формате");
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
