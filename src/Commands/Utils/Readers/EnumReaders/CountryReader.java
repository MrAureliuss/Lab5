package Commands.Utils.Readers.EnumReaders;

import BasicClasses.Country;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Считыватель страны.
 */
public class CountryReader {
    public static boolean checkExist(String toContains) {
        return Arrays.stream(Country.values()).anyMatch((country) -> country.name().equals(toContains));
    }

    public static Country read(String messageForConsole, boolean canBeNull) {
        Scanner in = new Scanner(System.in);
        System.out.print(messageForConsole + " Выберите страну из представленных(" + Arrays.asList(Country.values()) + "): ");
        String toContains = in.nextLine().trim();

        if ((!checkExist(toContains)) && !canBeNull && !toContains.equals("") || !canBeNull && toContains.equals("") || canBeNull && !toContains.equals("")) {
            while (!checkExist(toContains)) {
                System.out.print("Вы ввели несуществующее значение из представленных. Попробуйте снова: ");
                toContains = in.nextLine().trim();
                checkExist(toContains);
            }
        }

        if (canBeNull && toContains.equals("")) { return null; }

        return Enum.valueOf(Country.class, toContains);
    }
}
