package CommandPattern;

public abstract class Command {

    public abstract void execute(Account account);

    public abstract void undo();

    public abstract void redo();

    @Override
    public abstract String toString();

}