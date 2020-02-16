package Commands;

import Collection.CollectionManager;
import Collection.CollectionUtils;
import Commands.Utils.Creaters.ElementCreater;

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
            if (CollectionUtils.checkExist(groupId)) { CollectionManager.update(ElementCreater.createStudyGroup(), groupId); }
            else {System.out.println("Элемента с таким ID нет в коллекции.");}
        } catch (NumberFormatException e) {
            System.out.println("Команда не выполнена. Вы ввели некорректный аргумент.");
        }
    }

}
