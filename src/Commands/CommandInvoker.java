package Commands;

import java.util.HashMap;

public class CommandInvoker {
    private static final HashMap<String, Command> commandMap = new HashMap<>();

    public void register(String commandName, Command command) {
        commandMap.put(commandName, command);
    }

    public void executeCommand(String[] commandName) {
        try {
            Command command = commandMap.get(commandName[0]);
            command.execute(commandName);
            //System.out.println(commandName[0] + ":" + commandName[1]);
        } catch (IllegalStateException | NullPointerException ex) {
            System.out.println("Не существует команды " + commandName[0] + ". Для справки используйте – help");
        }
    }

    static HashMap<String, Command> getCommandMap() {
        return commandMap;
    }
}
