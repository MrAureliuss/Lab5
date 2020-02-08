import java.io.IOException;
import java.util.Scanner;

public class ConsoleManager {
    public void startInteractiveMode() throws IOException {
        try(Scanner scanner = new Scanner(System.in)) {
            while (scanner.hasNext()) {
                System.out.println(scanner.nextLine());
            }
        }
    }
}
