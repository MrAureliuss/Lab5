package Commands.ConcreteCommands;

import Commands.Command;
import Commands.CommandReceiver;

public class ExecuteScript extends Command {
    private final CommandReceiver commandReceiver;

    public ExecuteScript(CommandReceiver commandReceiver) {
        this.commandReceiver = commandReceiver;
    }

    @Override
    protected void execute(String[] args) {
        if (args.length == 2) { commandReceiver.update(args[1]); }
        else { System.out.println("Некорректное количество аргументов. Для справки напишите help."); }
    }

    @Override
    protected void writeInfo() {
        System.out.println("Команда execute_script. Синтаксис: execute_script file_name – считать и исполнить скрипт из указанного файла. " +
                "В скрипте содержатся команды в таком же виде, в котором их вводит пользователь в интерактивном режиме.");
    }
}