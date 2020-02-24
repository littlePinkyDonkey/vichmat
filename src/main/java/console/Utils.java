package console;

import computations.Data;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class Utils {

    private static Scanner scanner = new Scanner(System.in);

    public static Scanner getScanner() {
        return scanner;
    }

    public static boolean isReturnCommand(String line) {
        line = line.trim().toLowerCase();
        if (line.equals("return") || line.equals("вернуться")) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean isInterruptCommand(String line) {
        line = line.trim().toLowerCase();
        if (line.equals("cancel") || line.equals("отменить")) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean isExitCommand(String line) {
        line = line.trim().toLowerCase();
        if (line.equals("exit") || line.equals("выйти")) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean isHelpCommand(String line) {
        line = line.trim().toLowerCase();
        if (line.equals("help") || line.equals("помощь")) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean isBackCommand(String line) {
        line = line.trim().toLowerCase();
        if (line.equals("back") || line.equals("назад")) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean isNextCommand(String line) {
        line = line.trim().toLowerCase();
        if (line.equals("next") || line.equals("вперед")) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean isClearCommand(String line) {
        line = line.trim().toLowerCase();
        if (line.equals("clear") || line.equals("очистить")) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean isMatrixCommand(String line) {
        line = line.trim().toLowerCase();
        if (line.equals("matrix") || line.equals("матрица")) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean isFinishCommand(String line) {
        line = line.trim().toLowerCase();
        if (line.equals("finish") || line.equals("завершить")) {
            return true;
        } else {
            return false;
        }
    }


    public static Data readData(String filePath) throws FileNotFoundException, FileFormatException {
        Data result = new Data();

        File file = new File(filePath);
        if (!file.exists()) {
            throw new FileNotFoundException();
        }
        FileReader fileReader = new FileReader(file);
        Scanner scanner = new Scanner(fileReader);

        int n = readN(scanner);
        result.setN(n);
        double accuracy = readAccuracy(scanner);
        result.setAccuracy(accuracy);
        double[][] matrix = readMatrix(n, scanner);
        result.setMatrix(matrix);

        return result;
    }

    public static int readN(Scanner scanner) throws FileFormatException {
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] coefficientsString = line.split("\\s+");
            if (line.replaceAll("\\s", "").length() == 0) {
                continue;
            } else if (coefficientsString.length == 1) {
                try {
                    int n = Integer.parseInt(coefficientsString[0]);
                    if (n < 1) {
                        throw new FileFormatException("Введенное n меньше 1. Пожалуйста, введите целое n, попадающее в диапазон от 1 до 20 включительно.");
                    } else if (n > 20) {
                        throw new FileFormatException("Ввденное n больше 20. Пожалуйста, введите целое n, попадающее в диапазон от 1 до 20 включительно.");
                    }
                    return n;
                } catch (NumberFormatException e) {
                    throw new FileFormatException("В файле найден неизвестный ответ для размерности. Запишите в качестве размерности одно целое число не меньшее 1, и не большее 20.");
                }
            } else {
                throw new FileFormatException("В строке с размерностью должно быть указано только одно целое число - сама размерность. Исправьте файл (подробнее: введите \"help\").");
            }
        }
        throw new FileFormatException("Не найдена размерность. Исправьте файл (подробнее: введите \"help\").");
    }

    public static double readAccuracy(Scanner scanner) throws FileFormatException {
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] coefficientsString = line.split("\\s+");
            if (line.replaceAll("\\s", "").length() == 0) {
                continue;
            } else if (coefficientsString.length == 1) {
                try {
                    coefficientsString[0] = coefficientsString[0].replace(",", ".");
                    if (coefficientsString[0].contains(".") && coefficientsString[0].split("\\.")[1].length() > 12) {
                        System.out.println("В файле содержится точность, у которой больше 12 знаков после запятой. Это может сказаться на точности, пожалуйста, запишите точность с меньшим числом знаков после запятой.");
                        continue;
                    }
                    double accuracy = Double.parseDouble(coefficientsString[0]);
                    if (accuracy <= 0) {
                        System.out.println();
                        throw new FileFormatException("Введенная точность меньше или равна 0. Пожалуйста, введите точность, которая будет больше 0.");
                    }
                    return accuracy;
                } catch (NumberFormatException e) {
                    throw new FileFormatException("В файле найден неизвестный ответ для точности. Запишите в качестве точности одну десятичную дробь, большую 0");
                }
            } else {
                throw new FileFormatException("В строке с точностью должна быть указано только одна десятичная дробь - сама точность. Исправьте файл (подробнее: введите \"help\").");
            }
        }
        throw new FileFormatException("Не найдена точность. Исправьте файл (подробнее: введите \"help\").");
    }

    public static double[][] readMatrix(int n, String filePath) throws FileNotFoundException, FileFormatException {
        File file = new File(filePath);
        if (!file.exists()) {
            throw new FileNotFoundException();
        }
        FileReader fileReader = new FileReader(file);
        Scanner scanner = new Scanner(fileReader);

        return readMatrix(n, scanner);
    }

    public static double[][] readMatrix(int n, Scanner scanner) throws FileFormatException {
        double[][] result = new double[n][n+1];
        int i = 0;
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] coefficientsString = line.split("\\s+");
            if (line.replaceAll("\\s", "").length() == 0) {
                continue;
            } else if (i >= n) {
                throw new FileFormatException("В файле указаны коэффициенты большего числа уравнений, чем размерность матрицы.");
            } else if (coefficientsString.length < n + 1) {
                throw new FileFormatException("В строке #" + (i + 1) + " матрицы введено меньше данных, чем требуется.");
            } else if (coefficientsString.length > n + 1) {
                throw new FileFormatException("В строке #" + (i + 1) + " матрицы введено больше данных, чем требуется.");
            }
            for (int j = 0; j < n + 1; j++) {
                try {
                    String currentCoefficient = coefficientsString[j];
                    currentCoefficient = currentCoefficient.replace(",", ".");
                    if (currentCoefficient.contains(".") && currentCoefficient.split("\\.")[1].length() > 10) {
                        throw new FileFormatException("Значение #" + (j + 1) + " в строке #" + (i + 1) + " матрицы содержит больше 10 знаков после запятой. Это может сказаться на точности, пожалуйста, запишите это значение с меньшим числом знаков после запятой.");
                    }
                    result[i][j] = Double.parseDouble(currentCoefficient);
                } catch (NumberFormatException e) {
                    throw new FileFormatException("Значение #" + (j + 1) + " в строке #" + (i + 1) + " матрицы не является десятичной дробью.");
                }
            }
            i++;
        }
        if (i != n) {
            throw new FileFormatException("В файле указаны коэффициенты меньшего числа уравнений, чем размерность матрицы.");
        }
        return result;
    }

}
