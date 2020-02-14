package Commands.ConcreteCommands;

import Commands.Command;
import Commands.CommandReceiver;

public class Add extends Command {
    private final CommandReceiver commandReceiver;

    public Add(CommandReceiver commandReceiver) {
        this.commandReceiver = commandReceiver;
    }

    @Override
    protected void execute() {
        commandReceiver.add();
    }

    @Override
    protected void writeInfo() {
        System.out.println("Команда add – добавить новый элемент в коллекцию.");
    }
}
