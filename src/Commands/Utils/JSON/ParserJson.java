package Commands.Utils.JSON;

import BasicClasses.StudyGroup;
import Collection.CollectionManager;
import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.lang.reflect.Type;
import java.time.ZonedDateTime;
import java.util.List;

public class ParserJson {
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
        try (FileWriter writer = new FileWriter("staff.json")) {
            gson.toJson(CollectionManager.getLinkedList(), writer);
            CollectionManager.getLinkedList().clear();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void fromJsonToCollection() {
        try (Reader reader = new FileReader("staff.json")) {
            CollectionManager.initList();
            List<StudyGroup> studyGroups = gson.fromJson(reader, new TypeToken<List<StudyGroup>>(){}.getType());
            for (StudyGroup studyGroup: studyGroups) { CollectionManager.add(studyGroup); }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
