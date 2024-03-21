package dev.infochem.application.database;

import com.google.gson.*;
import dev.infochem.application.deserialize.FlatDeserializer;
import dev.infochem.application.model.Flat;
import dev.infochem.application.serialize.FlatSerializer;
import dev.infochem.transactionapi.DataManager;
import dev.infochem.transactionapi.Session;
import dev.infochem.transactionapi.SessionFactory;

import java.io.File;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.Collections;
import java.util.HashSet;

public class FlatManager implements DataManager<ArrayDeque<Flat>> {
    private static final File DATABASE_FILE = new File("/Users/stanislavserbakov/Desktop/Java/lab5/application/src/main/resources/database.json");
    private final JsonSerializer<Flat> serializer = new FlatSerializer();
    private final JsonDeserializer<Flat> deserializer = new FlatDeserializer();

    private final HashSet<Long> flatIds = new HashSet<>();

    private FlatManager(){}

    static {
        if (!DATABASE_FILE.exists()) {
            try {
                if (!DATABASE_FILE.createNewFile()) {
                    throw new IOException("Database file failed to create");
                };
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        SessionFactory.create(DATABASE_FILE);
    }
    @Override
    public ArrayDeque<Flat> getData() {
        ArrayDeque<Flat> flats = new ArrayDeque<>();
        Session session = SessionFactory.get(DATABASE_FILE);
        JsonArray flatsArray = session.readJson(JsonArray.class);
        if (flatsArray != null){
            for (JsonElement flatElement : flatsArray) {
                flats.add(deserializer.deserialize(flatElement, Flat.class, null));
            }
            flatIds.clear();
            flats.stream().map(Flat::getId).forEach(flatIds::add);
        }
        return flats;
    }

    @Override
    public void saveData(ArrayDeque<Flat> flats) {
        flatIds.clear();
        flats.stream().map(Flat::getId).forEach(flatIds::add);
        Session session = SessionFactory.get(DATABASE_FILE);
        JsonArray jsonArray = new JsonArray();
        for (Flat flat : flats) {
            jsonArray.add(serializer.serialize(flat, JsonObject.class, null));
        }
        System.out.println(jsonArray);
        session.writeJson(jsonArray);
    }

    @Override
    public long generateID() {
        long id;
        if (!flatIds.isEmpty()) {
            id = Collections.max(flatIds) + 1;
            while (flatIds.contains(id)) {
                id++;
            }
        } else {
            id = 1;
        }
        return id;
    }
}
