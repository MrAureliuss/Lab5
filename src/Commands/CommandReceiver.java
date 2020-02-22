package Commands;

import Collection.CollectionManager;
import Collection.CollectionUtils;
import Commands.Utils.Creaters.ElementCreator;

public class CommandReceiver {
    public void help() {
        CommandInvoker.getCommandMap().forEach((name, command) -> command.writeInfo());
    }

    public void info() { // добавить класс
        CollectionManager.getInfo();
    }

    public void show() {
        CollectionManager.show();
    }

    public void add() {
        CollectionManager.add(ElementCreator.createStudyGroup());
        System.out.println("Элемент добавлен в коллекцию.");
    }

    public void update(String ID) {
        Integer groupId;
        try {
            groupId = Integer.parseInt(ID);
            if (CollectionUtils.checkExist(groupId)) { CollectionManager.update(ElementCreator.createStudyGroup(), groupId); }
            else {System.out.println("Элемента с таким ID нет в коллекции.");}
        } catch (NumberFormatException e) {
            System.out.println("Команда не выполнена. Вы ввели некорректный аргумент.");
        }
    }

    public void remove_by_id(String ID) {
        Integer groupId;
        try {
            groupId = Integer.parseInt(ID);
            if (CollectionUtils.checkExist(groupId)) {
                CollectionManager.remove_by_id(groupId);
                System.out.println("Элемент с ID " + groupId + " успешно удален из коллекции.");
            } else {System.out.println("Элемента с таким ID нет в коллекции.");}
        } catch (NumberFormatException e) {
            System.out.println("Команда не выполнена. Вы ввели некорректный аргумент.");
        }
    }

    public void clear() {
        CollectionManager.clear();
        System.out.println("Коллекция успешно очищена.");
    }

    public void exit() {
        System.out.println("Завершение работы программы.");
        System.exit(0);
    }

    public void head() {
        CollectionManager.head();
    }

    public void remove_greater() {
        CollectionManager.remove_greater(ElementCreator.createStudyGroup());
    }

    public void remove_lower() {
        CollectionManager.remove_lower(ElementCreator.createStudyGroup());
    }
}
