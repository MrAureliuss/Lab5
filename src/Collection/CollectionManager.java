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
        linkedList.forEach(CollectionUtils::display);
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
        });
    }

    public static void remove_by_id(Integer groupId) {
        linkedList.forEach(studyGroup -> {
            if (studyGroup.getId().equals(groupId)) { linkedList.remove(studyGroup); }
        });
    }

    public static void clear() {
        linkedList.clear();
    }

    public static void head() {
        if (linkedList.size() > 0) { CollectionUtils.display(linkedList.getFirst()); }
        else { System.out.println("Коллекция пуста."); }
    }
}
