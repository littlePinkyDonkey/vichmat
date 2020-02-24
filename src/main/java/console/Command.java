package console;

import computations.Data;

public interface Command {

    void execute(Data data) throws InterruptCommandException;

}
