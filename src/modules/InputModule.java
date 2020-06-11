package modules;

import java.util.Scanner;
import data.Functions;

public class InputModule {
    private Scanner scanner = new Scanner(System.in);

    public Functions selectFunction() {
        return Functions.valueOf("F" + scanner.nextLine());
    }

    public double getLowerLimit() {
        return Double.parseDouble(scanner.nextLine());
    }

    public double getUpperLimit() {
        return Double.parseDouble(scanner.nextLine());
    }

    public double getAccuracy() {
        return Double.parseDouble(scanner.nextLine());
    }

}
