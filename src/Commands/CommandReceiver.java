package Commands;

import Commands.Utils.ConsoleReaders;

public class CommandReceiver {
    public void help() {
        CommandInvoker.getCommandMap().forEach((name, command) -> command.writeInfo());
    }

    public void info() { // добавить класс

    }

    public void show() {  // добавить класс

    }

    public void add() {
        String name = ConsoleReaders.stringReader("Введите имя группы: ", false);
    }

}
