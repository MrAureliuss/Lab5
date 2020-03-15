package Commands.Utils.Readers.EnumReaders;

import BasicClasses.Color;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Считыватель цвета.
 */
public class ColorReader {
    public static boolean checkExist(String toContains) {
        return Arrays.stream(Color.values()).anyMatch((color) -> color.name().equals(toContains));
    }

    public static Color read(String messageForConsole, boolean canBeNull) {
        Scanner in = new Scanner(System.in);
        System.out.print(messageForConsole + " Выберите цвет из представленных(" + Arrays.asList(Color.values()) + "): ");
        String toContains = in.nextLine().trim();

        if ((!checkExist(toContains)) && !canBeNull && !toContains.equals("") || !canBeNull && toContains.equals("") || canBeNull && !toContains.equals("")) {
            while (!checkExist(toContains)) {
                System.out.print("Вы ввели несуществующее значение из представленных. Попробуйте снова: ");
                toContains = in.nextLine().trim();
                checkExist(toContains);
            }
        }

        if (canBeNull && toContains.equals("")) { return null; }

        return Enum.valueOf(Color.class, toContains);
    }
}
