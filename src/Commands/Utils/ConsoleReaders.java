package Commands.Utils;

import java.util.Scanner;

public class ConsoleReaders {
    public static String stringReader(String messageForConsole, boolean canBeNull) {
        Scanner in = new Scanner(System.in);
        System.out.print(messageForConsole);
        String readString = in.nextLine();
        if(!canBeNull && readString.equals("")) {
            while (readString.equals("")) {
                System.out.print("Данное поле не может быть пустым. " + messageForConsole);
                readString = in.nextLine();
            }
        }

        if(canBeNull && readString.equals("")) { readString = null; }
        return readString;
    }

    public static Integer integerReader(String messageForConsole, boolean canBeNull) {
        Scanner in = new Scanner(System.in);
        System.out.print(messageForConsole);
        Integer readInt = in.nextInt();
        if(!canBeNull && readInt.toString().equals("")) {
            while (readInt.toString().equals("")) {
                System.out.print("Данное поле не может быть пустым. " + messageForConsole);
                readInt = in.nextInt();
            }
        }

        if(canBeNull && readInt.toString().equals("")) { readInt = null; }
        return readInt;
    }

    public static float floatReader(String messageForConsole) {
        Scanner in = new Scanner(System.in);
        System.out.print(messageForConsole);
        float readFloat = in.nextFloat();
        if(String.valueOf(readFloat).equals("")) {
            while (String.valueOf(readFloat).equals("")) {
                System.out.print("Данное поле не может быть пустым. " + messageForConsole);
                readFloat = in.nextInt();
            }
        }

        return readFloat;
    }

    public static Enum enumReader(String messageForConsole, boolean canBeNull, Enum enumForRead) {

    }
}
