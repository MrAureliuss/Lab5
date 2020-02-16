package Commands.ConcreteCommands;

import Commands.Command;
import Commands.CommandReceiver;

public class Help extends Command {
    private final CommandReceiver commandReceiver;

    public Help (CommandReceiver commandReceiver) {
        this.commandReceiver = commandReceiver;
    }

    @Override
    protected void execute(String[] args) {
        commandReceiver.help();
    }

    @Override
    protected void writeInfo() {
        System.out.println("Команда help – получить справку по доступным командам.");
    }
}
