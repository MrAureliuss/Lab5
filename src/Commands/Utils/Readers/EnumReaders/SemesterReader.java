package Commands.Utils.Readers.EnumReaders;

import BasicClasses.Semester;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Считыватель Семестра.
 */
public class SemesterReader {
    public static boolean checkExist(String toContains) {
        return Arrays.stream(Semester.values()).anyMatch((semester) -> semester.name().equals(toContains));
    }

    public static Semester read(boolean canBeNull) {
        Scanner in = new Scanner(System.in);
        System.out.print("Введите страну из представленных(" + Arrays.asList(Semester.values()) + "): ");
        String toContains = in.nextLine().trim();

        if ((!checkExist(toContains)) && !canBeNull && !toContains.equals("") || !canBeNull && toContains.equals("") || canBeNull && !toContains.equals("")) {
            while (!checkExist(toContains)) {
                System.out.print("Вы ввели несуществующее значение из представленных. Попробуйте снова: ");
                toContains = in.nextLine().trim();
                checkExist(toContains);
            }
        }

        if (canBeNull && toContains.equals("")) { return null; }

        return Enum.valueOf(Semester.class, toContains);
    }
}
