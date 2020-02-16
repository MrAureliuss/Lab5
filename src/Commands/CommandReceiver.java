package Commands;

import BasicClasses.Country;
import Collection.CollectionManager;
import Commands.Utils.Creaters.ElementCreater;
import Commands.Utils.Readers.EnumReaders.*;
import Commands.Utils.Readers.PrimitiveAndReferenceReaders.*;

public class CommandReceiver {
    public void help() {
        CommandInvoker.getCommandMap().forEach((name, command) -> command.writeInfo());
    }

    public void info() { // добавить класс
        CollectionManager.getInfo();
    }

    public void show() {  // добавить класс
        CollectionManager.show();
    }

    public void add() {
        CollectionManager.add(ElementCreater.createStudyGroup());
    }

    public void update(String ID) {
        Integer groupId;
        try {
            groupId = Integer.parseInt(ID);
            CollectionManager.update(ElementCreater.createStudyGroup(), groupId);
        } catch (NumberFormatException e) {
            System.out.println("Команда не выполнена. Вы ввели некорректный аргумент.");
        }
    }

}
