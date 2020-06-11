package data;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public enum Functions {
    F1("1/x", "1/x"){
        @Override
        public boolean isIntegrable(double a, double b) {
            return (a<=0 && 0<=b) || (b<=0 && 0<=a);
        }
    },
    F2("(Math.sin(x))/x", "sin(x)/x"){
        @Override
        public boolean isIntegrable(double a, double b) {
            return (a<=0 && 0<=b) || (b<=0 && 0<=a);
        }
    },
    F3("x*x*x*x+3*x*x+6*x+2", "x^3+3x^2+6x+2"),
    F4("Math.pow(2, 1/x)", "2^(1/x)"){
        @Override
        public boolean isIntegrable(double a, double b) {
            return (a<=0 && 0<b) || (b<=0 && 0<a);
        }
    };

    private String signature;
    private ScriptEngine engine;
    private String function;

    Functions(String function, String signature){
        this.signature = signature;
        this.function = function;
        ScriptEngineManager manager = new ScriptEngineManager();
        this.engine = manager.getEngineByName("JavaScript");
    }

    public double get(double x) {
        String equation = this.function.replaceAll("x", String.valueOf(x));
        try {
            return (double)this.engine.eval(equation);
        } catch (ScriptException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public boolean isIntegrable(double a, double b){
        return true;
    }

    @Override
    public String toString() {
        return signature;
    }
}
