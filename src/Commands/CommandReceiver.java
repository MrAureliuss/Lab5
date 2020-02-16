package Commands;

import Commands.Utils.Creaters.ElementCreater;
import Collection.CollectionManager;

public class CommandReceiver {
    public void help() {
        CommandInvoker.getCommandMap().forEach((name, command) -> command.writeInfo());
    }

    public void info() {
        CollectionManager.getInfo();
    }

    public void show() {
        CollectionManager.show();
    }

    public void add() {
        CollectionManager.add(ElementCreater.createStudyGroup());
    }

    public void update() {

    }

}
