package computation;

import data.Functions;
import data.Messages;
import exceptions.IntegrableException;

public class TrapezoidMethod {
    private double a;
    private double b;
    private double eps;
    private Functions f;
    private double error;
    private double trapezoidsNumber;
    public static final int LIMIT = 50;

    public TrapezoidMethod(Functions f, double a, double b, double eps) {
        this.a = a;
        this.b = b;
        this.eps = eps;
        this.f = f;
    }

    public double getApproximatedIntegral(int n) {
        double h = (b-a)/n;
        double xPrevios = a;
        double xNext = 0;
        double S = 0;

        for (int i = 1; i <= n; i++) {
            xNext = xPrevios + h;
            S += (f.get(xNext)+f.get(xPrevios))*h/2;
            xPrevios = xNext;
        }

        return S;
    }
    public double getIntegral() throws IntegrableException {
        int counter = 0;
        int n = 2;
        double I1 = 0;
        double I2 = 0;
        I2 = getApproximatedIntegral(n);
        double accuracy = (Math.abs(I2-I1)/3);

        while(accuracy > eps && counter < LIMIT) {
            I1 = I2;
            n *= 2;
            I2 = getApproximatedIntegral(n);
            accuracy = (Math.abs(I2-I1)/3);
            counter++;
        }

        if(counter == LIMIT) throw new IntegrableException(Messages.IntegrableMessage.toString());
        this.trapezoidsNumber = n;
        this.error = accuracy;

        return I2;
    }

    public double getError() {
        return error;
    }

    public double getTrapezoidsNumber() {
        return trapezoidsNumber;
    }
}
