import java.io.IOException;
import java.util.Scanner;
import Commands.Command;
import Commands.CommandInvoker;
import Commands.CommandReceiver;
import Commands.ConcreteCommands.*;

public class ConsoleManager {
    public void startInteractiveMode() throws IOException {
        CommandReceiver commandReceiver = new CommandReceiver();
        CommandInvoker commandInvoker = new CommandInvoker();

        commandInvoker.register("help", new Help(commandReceiver));
        commandInvoker.register("add", new Add(commandReceiver));

        try(Scanner scanner = new Scanner(System.in)) {
            while (scanner.hasNext()) {
                commandInvoker.executeCommand(scanner.nextLine());
            }
        }
    }
}
