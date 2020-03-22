package Commands.Utils.JSON;

import BasicClasses.StudyGroup;
import Collection.CollectionManager;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.TypeAdapter;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.time.ZonedDateTime;
import java.util.List;

/**
 * Класс, отвечающий за сохранение и загрузку JSON.
 */
public class ParserJson {
    private static String filePath = System.getenv("WORK_FILE_PATH");
    private static GsonBuilder builder = new GsonBuilder();
    private static Gson gson = builder
            .registerTypeAdapter(ZonedDateTime.class, new TypeAdapter<ZonedDateTime>() {
                @Override
                public void write(JsonWriter out, ZonedDateTime value) throws IOException {
                    out.value(value.toString());
                }

                @Override
                public ZonedDateTime read(JsonReader in) throws IOException {
                    return ZonedDateTime.parse(in.nextString());
                }
            })
            .serializeNulls()
            .setPrettyPrinting()
            .enableComplexMapKeySerialization()
            .create();

    public static void collectionToJson() {
        try (FileWriter writer = new FileWriter(filePath)) {
            gson.toJson(CollectionManager.getLinkedList(), writer);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void fromJsonToCollection() {
        if (filePath != null) {
            try (Reader reader = new FileReader(filePath)) {
                CollectionManager.initList();
                List<StudyGroup> studyGroups = gson.fromJson(reader, new TypeToken<List<StudyGroup>>(){}.getType());
                if (studyGroups.size() > 0) for (StudyGroup studyGroup: studyGroups) { CollectionManager.addJsonObject(studyGroup); }

            } catch (IOException e) {
                System.out.println(e.getMessage());
            } catch (SecurityException e) {
                System.out.println("Недостаточно прав для открытия файла.");
            } catch (NullPointerException e) {
                System.out.println("В файле нет объектов");
            } catch (com.google.gson.JsonSyntaxException e) {
                System.out.println("Ошибка в содержании файла " + e.getMessage());
            }
        } else { System.out.println("Переменная окружения не выставлена"); }
    }
}
