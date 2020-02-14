package Commands;

public class CommandReceiver {
    public void help() {
        CommandInvoker.getCommandMap().forEach((name, command) -> command.writeInfo());
    }

    public void info() { // добавить класс

    }

    public void show() {  // добавить класс

    }

    public void add() {

    }

}
