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

        Command help = new Help(commandReceiver);
        commandInvoker.register("help", help);

        try(Scanner scanner = new Scanner(System.in)) {
            while (scanner.hasNext()) {
                commandInvoker.executeCommand(scanner.nextLine());
            }
        }
    }
}
