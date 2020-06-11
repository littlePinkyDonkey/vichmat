import computation.TrapezoidMethod;
import data.Function;
import exceptions.IntegrableException;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    private static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        while (true) {
            try {
                System.out.println("Выберите функцию:");
                for (int i = 0; i < Function.values().length; i++) {
                    System.out.println((i+1)+") "+Function.values()[i]);
                }

                String cmd = scanner.nextLine();
                Function f = Function.valueOf("F"+cmd);

                System.out.println("Нижний предел интегрирования");
                double a = Double.parseDouble(scanner.nextLine());

                System.out.println("Верхний предел интегрирования");
                double b = Double.parseDouble(scanner.nextLine());

                System.out.println("Точность интегрирования");
                double eps = Double.parseDouble(scanner.nextLine());

                if(eps<0){
                    System.out.println("Ошибка: Точность должна быть неотрицательным числом");
                }
                else if(f.isIntegrable(a, b)){
                    if(a==b) {
                        System.out.println("a = b -> Интеграл равен 0");
                    }else{
                        TrapezoidMethod trapezoidMethod = new TrapezoidMethod(f, a, b, eps);
                        double answer = trapezoidMethod.getIntegral();
                        System.out.println("\nОтвет: "+answer);
                        System.out.println("Число разбиений: "+trapezoidMethod.getTrapezoidsNumber());
                        System.out.println("Погрешность: "+trapezoidMethod.getError()+"\n");
                    }
                }else{
                    System.out.println("К сожалению невозможно найти значение интеграла на заданном промежутке [a, b]");
                }
            }catch (InputMismatchException | IllegalArgumentException e){
                System.out.println("Ошибка: Введен неверный формат числа");
            }catch (IntegrableException e){
                System.out.println(e.getMessage());
            }
        }
    }
}
