import java.io.IOException;
import java.util.Scanner;

import Collection.CollectionManager;
import Commands.CommandInvoker;
import Commands.CommandReceiver;
import Commands.ConcreteCommands.*;

class ConsoleManager {
    void startInteractiveMode() throws IOException {
        CommandInvoker commandInvoker = new CommandInvoker();
        CommandReceiver commandReceiver = new CommandReceiver(commandInvoker);
        CollectionManager.initList();

        commandInvoker.register("help", new Help(commandReceiver));
        commandInvoker.register("add", new Add(commandReceiver));
        commandInvoker.register("info", new Info(commandReceiver));
        commandInvoker.register("show", new Show(commandReceiver));
        commandInvoker.register("update", new Update(commandReceiver));
        commandInvoker.register("remove_by_id", new RemoveByID(commandReceiver));
        commandInvoker.register("clear", new Clear(commandReceiver));
        commandInvoker.register("exit", new Exit(commandReceiver));
        commandInvoker.register("head", new Head(commandReceiver));
        commandInvoker.register("remove_greater", new RemoveGreater(commandReceiver));
        commandInvoker.register("remove_lower", new RemoveLower(commandReceiver));
        commandInvoker.register("min_by_semester_enum", new MinBySemesterEnum(commandReceiver));
        commandInvoker.register("max_by_group_admin", new MaxByGroupAdmin(commandReceiver));
        commandInvoker.register("count_by_group_admin", new CountByGroupAdmin(commandReceiver));
        commandInvoker.register("execute_script", new ExecuteScript(commandReceiver));

        try(Scanner scanner = new Scanner(System.in)) {
            while (scanner.hasNextLine()) {
                commandInvoker.executeCommand(scanner.nextLine().split(" "));
            }
        }
    }
}
