package modules;

import computation.TrapezoidMethod;
import data.Functions;
import data.Messages;
import exceptions.IntegrableException;

public class OutputModule {

    public void printFunctions() {
        print(Messages.SelectFunction.toString());
        for (int i = 0; i < Functions.values().length; i++) {
            System.out.printf("%s) %s\n", (i+1), Functions.values()[i]);
        }
    }

    public void print(String outputString) {
        System.out.println(outputString);
    }

    public void printResult(Functions selectedFunction, double lowerLimit, double upperLimit, double accuracy) throws IntegrableException {
        if(selectedFunction.isIntegrable(lowerLimit, upperLimit)){
            if(lowerLimit == upperLimit) {
                print(Messages.ZeroIntegral.toString());
            } else {
                TrapezoidMethod trapezoidMethod
                        = new TrapezoidMethod(selectedFunction, lowerLimit, upperLimit, accuracy);
                double answer = trapezoidMethod.getIntegral();

                print(Messages.Answer.toString() + answer);
                print(Messages.PartitionsNumber.toString() + trapezoidMethod.getTrapezoidsNumber());
                print(Messages.Error.toString() + trapezoidMethod.getError() + "\n");
            }
        }else{
            print(Messages.NotFound.toString());
        }
    }
}
