package Collection;

import BasicClasses.*;
import java.time.ZonedDateTime;
import java.util.LinkedList;

public class CollectionManager {
    private static LinkedList<StudyGroup> linkedList;
    private static ZonedDateTime creationDate;

    public static void initList() {
        if (linkedList == null) { linkedList = new LinkedList<>(); creationDate = ZonedDateTime.now(); }
    }

    static LinkedList<StudyGroup> getLinkedList() {
        return linkedList;
    }

    public static void add(StudyGroup studyGroup) {
        linkedList.add(studyGroup);
    }

    public static void getInfo() {
        System.out.println("Тип коллекции – " + linkedList.getClass().getName());
        System.out.println("Дата инициализации коллекции – " + creationDate);
        System.out.println("Количество элементов в коллекции – " + linkedList.size());
        System.out.println("_________________________________________________________\n");
    }

    public static void show() {
        linkedList.forEach(studyGroup -> {
            System.out.println("ID элемента коллекции – " + studyGroup.getId());
            System.out.println("Название группы – " + studyGroup.getName());
            System.out.println("Координата X – " + studyGroup.getCoordinates().getX());
            System.out.println("Координата Y – " + studyGroup.getCoordinates().getY());
            System.out.println("Количество студентов в группе – " + studyGroup.getStudentsCount());
            System.out.println("Форма обучения – " + studyGroup.getFormOfEducation());
            System.out.println("Номер семестра – " + studyGroup.getSemesterEnum());
            System.out.println("Имя админа группы – " + studyGroup.getGroupAdmin().getName());
            System.out.println("Рост админа группы – " + studyGroup.getGroupAdmin().getHeight());
            System.out.println("Цвет глаз админа группы – " + studyGroup.getGroupAdmin().getEyeColor());
            System.out.println("Цвет волос админа группы – " + studyGroup.getGroupAdmin().getHairColor());
            System.out.println("Национальность админа группы – " + studyGroup.getGroupAdmin().getNationality());
            System.out.println("_________________________________________________________\n");
        });
    }

    public static void update(StudyGroup groupToUpdate, Integer elementId) {
        linkedList.forEach(studyGroup -> {
            if (studyGroup.getId().equals(elementId)) {
                studyGroup.setName(groupToUpdate.getName());
                studyGroup.setCoordinates(groupToUpdate.getCoordinates());
                studyGroup.setStudentsCount(groupToUpdate.getStudentsCount());
                studyGroup.setFormOfEducation(groupToUpdate.getFormOfEducation());
                studyGroup.setSemesterEnum(groupToUpdate.getSemesterEnum());
                studyGroup.setGroupAdmin(groupToUpdate.getGroupAdmin());
            }
            else {
                System.out.println("Элемента с " + elementId + " не существует.");
            }
        });
    }
}
