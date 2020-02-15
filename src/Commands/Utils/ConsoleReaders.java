package Commands.Utils;

import BasicClasses.Country;

import java.util.Arrays;
import java.util.Scanner;

public class ConsoleReaders {
    public static String stringReader(String messageForConsole, boolean canBeNull) {
        Scanner in = new Scanner(System.in);
        System.out.print(messageForConsole);
        String readString = in.nextLine().trim();

        while(!canBeNull && readString.equals("")) {
            System.out.print("Данное поле не может быть пустым. " + messageForConsole);
            readString = in.nextLine().trim();
        }

        if(canBeNull && readString.equals("")) { readString = null; }
        return readString;
    }

    public static Integer integerReader(String messageForConsole, boolean canBeNull) {
        Scanner in = new Scanner(System.in);
        System.out.print(messageForConsole);
        Integer readInt = in.nextInt();

        while(!canBeNull && readInt.toString().equals("")) {
            System.out.print("Данное поле не может быть пустым. " + messageForConsole);
            readInt = in.nextInt();
        }

        if(canBeNull && readInt.toString().equals("")) { readInt = null; }
        return readInt;
    }

    public static float floatReader(String messageForConsole) {
        Scanner in = new Scanner(System.in);
        System.out.print(messageForConsole);
        float readFloat = in.nextFloat();

        while(String.valueOf(readFloat).equals("")) {
            System.out.print("Данное поле не может быть пустым. " + messageForConsole);
            readFloat = in.nextInt();
        }

        return readFloat;
    }

    public static Country countryReader(boolean canBeNull) {
        Scanner in = new Scanner(System.in);
        System.out.print("Введите страну из представленных(" + Arrays.asList(Country.values()) + "): ");
        String toContains = in.nextLine().trim();

        while (Arrays.stream(Country.values()).noneMatch((country) -> country.name().equals(toContains))) {
            System.out.print("Вы ввели несуществующее значение из представленных. Попробуйте снова: ");
            toContains = in.nextLine().trim();
            
        }

        return Enum.valueOf(Country.class, toContains);
    }
}
