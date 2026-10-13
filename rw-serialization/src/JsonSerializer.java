import com.google.gson.Gson;
import com.sveta.serialization.ObjectSerializer;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.Reader;
import java.io.Writer;

public class JsonSerializer implements ObjectSerializer {
    private final Gson gson = new Gson();

    @Override
    public void save(Object object, String filePath) throws Exception {
        try (Writer writer = new FileWriter(filePath)) {
            gson.toJson(object, writer);
        }
    }

    @Override
    public <T> T load(String filePath, Class<T> clazz) throws Exception {
        try (Reader reader = new FileReader(filePath)) {
            return gson.fromJson(reader, clazz);
        }
    }
}