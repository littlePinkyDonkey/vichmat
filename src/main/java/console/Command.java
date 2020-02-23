package console;

public interface Command {

    void execute(Data data) throws InterruptCommandException;

}
