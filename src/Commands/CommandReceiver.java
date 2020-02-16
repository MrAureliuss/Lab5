package Commands;

import BasicClasses.Country;
import Commands.Utils.Readers.EnumReaders.*;
import Commands.Utils.Readers.PrimitiveAndReferenceReaders.*;

public class CommandReceiver {
    public void help() {
        CommandInvoker.getCommandMap().forEach((name, command) -> command.writeInfo());
    }

    public void info() { // добавить класс

    }

    public void show() {  // добавить класс

    }

    public void add() {
        String name = StringReader.read("Введите имя группы: ", false);
        Integer x = RefIntReader.read("Введите X: ", false, 531, "MAX");
        float y = PrimitiveFloatReader.read("Введите Y: ", -653f, "MIN");
        Country country = CountryReader.read(false);
    }

}
