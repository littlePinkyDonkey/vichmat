package exceptions;

public class IntegrableException extends Exception {
    @Override
    public String getMessage() {
        return "Невозможно получить значение интеграла с заданной точностью";
    }
}
