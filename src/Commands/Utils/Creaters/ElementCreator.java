package Commands.Utils.Creaters;

import BasicClasses.*;
import Commands.Utils.Readers.EnumReaders.ColorReader;
import Commands.Utils.Readers.EnumReaders.CountryReader;
import Commands.Utils.Readers.EnumReaders.FormOfEducationReader;
import Commands.Utils.Readers.EnumReaders.SemesterReader;
import Commands.Utils.Readers.PrimitiveAndReferenceReaders.PrimitiveFloatReader;
import Commands.Utils.Readers.PrimitiveAndReferenceReaders.PrimitiveIntReader;
import Commands.Utils.Readers.PrimitiveAndReferenceReaders.RefIntReader;
import Commands.Utils.Readers.PrimitiveAndReferenceReaders.StringReader;

import java.util.ArrayList;

/**
 * Классб содержащий методы для создания группы и человека.
 */
public class ElementCreator {
    public static StudyGroup createStudyGroup() {
        String name = StringReader.read("Введите имя группы: ", false);
        Integer x = RefIntReader.read("Введите X: ", false, 531, "MAX");
        float y = PrimitiveFloatReader.read("Введите Y: ", -653f, "MIN");
        Integer studentsCount = RefIntReader.read("Введите количество студентов: ", false, 0, "MIN");
        FormOfEducation formOfEducation = FormOfEducationReader.read(true);
        Semester semester = SemesterReader.read(false);

        return new StudyGroup(name, new Coordinates(x, y), studentsCount, formOfEducation, semester, createPerson());
    }

    public static Person createPerson() {
        String groupAdminName = StringReader.read("Введите имя админа группы: ", false);
        int height = PrimitiveIntReader.read("Введите рост админа группы: ", 0, "MIN");
        Color eyeColor = ColorReader.read("Введите цвет глаз Админа группы.", false);
        Color hairColor = ColorReader.read("Введите цвет волос Админа группы", false);
        Country nationality = CountryReader.read("Введите национальность Админа группы", false);

        return new Person(groupAdminName, height, eyeColor, hairColor, nationality);
    }

    public static StudyGroup createScriptStudyGroup(ArrayList<String> parameters) {
        if (validateArray(parameters)) {
            FormOfEducation formOfEducation = null;
            if (!parameters.get(4).isEmpty()) { formOfEducation = FormOfEducation.valueOf(parameters.get(4)); }
            return new StudyGroup(parameters.get(0),
                    new Coordinates(Integer.parseInt(parameters.get(1)), Float.parseFloat(parameters.get(2))),
                    Integer.parseInt(parameters.get(3)),
                    formOfEducation,
                    Semester.valueOf(parameters.get(5)),
                    new Person(parameters.get(6), Integer.parseInt(parameters.get(7)), Color.valueOf(parameters.get(8)), Color.valueOf(parameters.get(9)), Country.valueOf(parameters.get(10))));
        } else { System.out.println("Один из параметров не соответствует требованиям."); }

        return null;
    }

    private static boolean validateArray(ArrayList<String> parameters) {
        try {
            return !parameters.get(0).isEmpty()
                    && Integer.parseInt(parameters.get(1)) < 511
                    && Float.parseFloat(parameters.get(2)) > -653f
                    && Integer.parseInt(parameters.get(3)) > 0
                    && (FormOfEducationReader.checkExist(parameters.get(4)) || parameters.get(4).isEmpty())
                    && !parameters.get(5).isEmpty()
                    && !parameters.get(6).isEmpty()
                    && Integer.parseInt(parameters.get(7)) > 0
                    && ColorReader.checkExist(parameters.get(8))
                    && ColorReader.checkExist(parameters.get(9))
                    && CountryReader.checkExist(parameters.get(10));

        } catch (NumberFormatException ex) { return false; }
    }
}
