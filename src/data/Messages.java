package data;

public enum Messages {
    SelectFunction("Выберите функцию:"),
    LowerLimit("Введите нижний предел интегрирования:"),
    UpperLimit("Введите верхний предел интегрирования:"),
    AccuracyInput("Введите точность интегрирования:"),
    IllegalFormatMessage("Ошибка: Введен неверный формат числа"),
    IntegrableMessage("Невозможно получить значение интеграла с заданной точностью"),
    AccuracyExceptionMessage("Ошибка: Точность должна быть неотрицательным числом"),
    ZeroIntegral("a = b -> Интеграл равен 0"),
    Answer("\nОтвет: "),
    PartitionsNumber("Число разбиений: "),
    Error("Погрешность: "),
    NotFound("К сожалению невозможно найти значение интеграла на заданном промежутке [a, b]");

    private String message;

    Messages(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return message;
    }
}
