import data.Functions;
import data.Messages;
import exceptions.AccuracyException;
import exceptions.IntegrableException;
import modules.InputModule;
import modules.OutputModule;

import java.util.InputMismatchException;

public class Main {
    private static double lowerLimit;
    private static double upperLimit;
    private static double accuracy;

    private static OutputModule outputModule = new OutputModule();
    private static InputModule inputModule = new InputModule();

    public static void main(String[] args) {
        while (true) {
            try {
                outputModule.printFunctions();

                Functions selectedFunction = inputModule.selectFunction();
                initParams();

                if(accuracy < 0) throw new AccuracyException(Messages.AccuracyExceptionMessage.toString());
                outputModule.printResult(selectedFunction, lowerLimit, upperLimit, accuracy);

            } catch (InputMismatchException | IllegalArgumentException e) {
                outputModule.print(Messages.IllegalFormatMessage.toString());
            } catch (IntegrableException | AccuracyException e){
                System.out.println(e.getMessage());
            }
        }
    }

    private static void initParams() {
        outputModule.print(Messages.LowerLimit.toString());
        lowerLimit = inputModule.getLowerLimit();

        outputModule.print(Messages.UpperLimit.toString());
        upperLimit = inputModule.getUpperLimit();

        outputModule.print(Messages.AccuracyInput.toString());
        accuracy = inputModule.getAccuracy();
    }
}
