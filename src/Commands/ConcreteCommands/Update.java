package Commands.ConcreteCommands;

import Commands.Command;
import Commands.CommandReceiver;

public class Update extends Command {
    private final CommandReceiver commandReceiver;

    public Update (CommandReceiver commandReceiver) {
        this.commandReceiver = commandReceiver;
    }

    @Override
    protected void execute(String[] args) {
        commandReceiver.update(args[1]);
    }

    @Override
    protected void writeInfo() {
        System.out.println("Команда update. Синтаксис: update id – обновить значение элемента коллекции, id которого равен заданному.");
    }
}
