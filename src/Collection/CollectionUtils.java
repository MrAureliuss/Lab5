package Collection;

import BasicClasses.StudyGroup;

public class CollectionUtils {
    public static boolean checkExist(Integer ID) {
        for (StudyGroup studyGroup:CollectionManager.getLinkedList()) {
            return studyGroup.getId().equals(ID);
        }
        return false;
    }
}
