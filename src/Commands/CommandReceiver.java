package Commands;

import BasicClasses.StudyGroup;
import Collection.CollectionManager;
import Collection.CollectionUtils;
import Commands.ConcreteCommands.ExecuteScript;
import Commands.Utils.Creaters.ElementCreator;
import Commands.Utils.JSON.ParserJson;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

/**
 * Ресивер(получатель), описывает основную логику команд, при надобности делегирует ее консольному менеджеру.
 */
public class CommandReceiver {
    private final CommandInvoker commandInvoker;

    public CommandReceiver(CommandInvoker commandInvoker) {
        this.commandInvoker = commandInvoker;
    }

    public void help() {
        commandInvoker.getCommandMap().forEach((name, command) -> command.writeInfo());
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

    /**
     *
     * @param ID - апдейт элемента по ID.
     */
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

    /**
     *
     * @param ID - удаление по ID.
     */
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

    public void min_by_semester_enum() {
        CollectionManager.min_by_semester_enum();
    }

    public void maxByGroupAdmin() {
        CollectionManager.maxByGroupAdmin();
    }

    public void countByGroupAdmin() {
        CollectionManager.countByGroupAdmin(ElementCreator.createPerson());
    }

    /**
     *
     * @param path - Путь до файла, который будем считывать.
     */
    public void executeScript(String path) {
        String line;
        String command;
        ArrayList<String> parameters = new ArrayList<>();
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new BufferedInputStream(new FileInputStream(path)), StandardCharsets.UTF_8))) {
            while ((line = bufferedReader.readLine()) != null) {
                if (line.split(" ")[0].matches("add|update|remove_lower|remove_greater")) {
                    command = line;
                    for (int i = 0; i < 11; i++) {
                        if (line != null) {
                            line = bufferedReader.readLine();
                            parameters.add(line);
                        } else { System.out.println("Не хватает параметров для создания объекта."); break; }
                    }
                    StudyGroup studyGroup = ElementCreator.createScriptStudyGroup(parameters);
                    switch (command.split(" ")[0]) {
                        case "add":
                            CollectionManager.add(studyGroup);
                            break;
                        case "update":
                            CollectionManager.update(studyGroup, Integer.parseInt(command.split(" ")[1]));
                        case "remove_greater":
                            CollectionManager.remove_greater(studyGroup);
                            break;
                        case "remove_lower":
                            CollectionManager.remove_lower(studyGroup);
                            break;
                    }
                } else if (line.split(" ")[0].equals("execute_script")
                        && line.split(" ")[1].equals(ExecuteScript.getPath())) { System.out.println("Пресечена попытка рекурсивного вызова скрипта."); }
                else { commandInvoker.executeCommand(line.split(" ")); }
            }
        } catch (IOException e) {
            System.out.println("Ошибка! " + e.getMessage());
        }
    }

    /**
     * Сохранение коллекции
     */
    public void save() {
        ParserJson.collectionToJson();
    }
}
