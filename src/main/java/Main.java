import computations.Data;
import console.InterruptCommandException;
import console.Introduction;

import java.util.NoSuchElementException;

public class Main {

    public static void main(String[] args) {
        boolean working = true;
        System.out.println(
                "Если вы захотите вернуться на предыдущий шаг, введите \"return\".\n" +
                "Если вы захотите начать ввод данных с нуля, введите \"cancel\".\n" +
                "Если вы захотите выйти из приложения, то введите \"exit\"."
        );
        while (working) {
            try {
                Introduction introduction = new Introduction();
                introduction.execute(new Data());
            } catch (NoSuchElementException e) {
                System.out.println("Завершение работы программы.");
                working = false;
            } catch (InterruptCommandException e) {
                System.out.println("Начинаем сначала...");
                continue;
            }
        }
    }

}
