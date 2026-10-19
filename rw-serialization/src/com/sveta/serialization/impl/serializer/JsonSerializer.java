package com.sveta.serialization.impl.serializer;

import com.google.gson.*;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import com.sveta.serialization.ObjectSerializer;
import com.sveta.serialization.impl.dto.carriagesDto.*;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class JsonSerializer implements ObjectSerializer {
    private final Gson gson = new GsonBuilder()
            .setPrettyPrinting()
            .registerTypeAdapter(LocalDateTime.class, new TypeAdapter<LocalDateTime>() {
                private final DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;

                @Override
                public void write(JsonWriter out, LocalDateTime value) throws IOException {
                    if (value == null) {
                        out.nullValue();
                    } else {
                        out.value(formatter.format(value));
                    }
                }

                @Override
                public LocalDateTime read(JsonReader in) throws IOException {
                    return LocalDateTime.parse(in.nextString(), formatter);
                }
            })

            .registerTypeAdapter(CarriageSerializationDto.class, (JsonDeserializer<CarriageSerializationDto>) (json, typeOfT, context) -> {
                JsonObject jsonObject = json.getAsJsonObject();

                if (jsonObject.has("coupes")) {
                    return context.deserialize(jsonObject, CoupeCarriageDto.class);
                } else if (jsonObject.has("hasHotKitchen")) {
                    return context.deserialize(jsonObject, DiningCarriageDto.class);
                } else if (jsonObject.has("seats") && jsonObject.has("baseCarriageWeightKg")) {
                    if (jsonObject.has("bicycles") || jsonObject.toString().contains("BICYCLE")) {
                        return context.deserialize(jsonObject, EconomyCarriageDto.class);
                    } else {
                        return context.deserialize(jsonObject, SeatedCarriageDto.class);
                    }
                }

                return context.deserialize(jsonObject, EconomyCarriageDto.class);
            })
            .create();

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