package Commands.Utils.Readers.EnumReaders;

import BasicClasses.FormOfEducation;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Считыватель формы обучения.
 */
public class FormOfEducationReader {
    public static boolean checkExist(String toContains) {
        return Arrays.stream(FormOfEducation.values()).anyMatch((formOfEducation) -> formOfEducation.name().equals(toContains));
    }

    public static FormOfEducation read(boolean canBeNull) {
        Scanner in = new Scanner(System.in);
        System.out.print("Введите страну из представленных(" + Arrays.asList(FormOfEducation.values()) + "): ");
        String toContains = in.nextLine().trim();

        if ((!checkExist(toContains)) && !canBeNull && !toContains.equals("") || !canBeNull && toContains.equals("") || canBeNull && !toContains.equals("")) {
            while (!checkExist(toContains)) {
                System.out.print("Вы ввели несуществующее значение из представленных. Попробуйте снова: ");
                toContains = in.nextLine().trim();
                checkExist(toContains);
            }
        }

        if (canBeNull && toContains.equals("")) { return null; }

        return Enum.valueOf(FormOfEducation.class, toContains);
    }
}
