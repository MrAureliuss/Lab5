import Commands.Utils.JSON.ParserJson;

import java.io.IOException;

/**
 * Main class приложения
 */

public class Main {
    /**
     * Единая точка входа, как на сайте гос.услуг.
     */
    public static void main(String[] args) throws IOException {
        ParserJson.fromJsonToCollection();
        ConsoleManager consoleManager = new ConsoleManager();
        consoleManager.startInteractiveMode();
    }
}
