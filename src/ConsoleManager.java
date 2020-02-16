import java.io.IOException;
import java.util.Scanner;

import Collection.CollectionManager;
import Commands.CommandInvoker;
import Commands.CommandReceiver;
import Commands.ConcreteCommands.*;
import Collection.CollectionManager;

public class ConsoleManager {
    public void startInteractiveMode() throws IOException {
        CommandReceiver commandReceiver = new CommandReceiver();
        CommandInvoker commandInvoker = new CommandInvoker();
        CollectionManager.initList();

        commandInvoker.register("help", new Help(commandReceiver));
        commandInvoker.register("add", new Add(commandReceiver));
        commandInvoker.register("info", new Info(commandReceiver));
        commandInvoker.register("show", new Show(commandReceiver));
        commandInvoker.register("update", new Update(commandReceiver));

        try(Scanner scanner = new Scanner(System.in)) {
            while (scanner.hasNext()) {
                commandInvoker.executeCommand(scanner.nextLine().split(" "));
            }
        }
    }
}
