package Commands.Utils.Creaters;

import BasicClasses.*;
import Commands.Utils.Readers.EnumReaders.*;
import Commands.Utils.Readers.PrimitiveAndReferenceReaders.*;

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
}
