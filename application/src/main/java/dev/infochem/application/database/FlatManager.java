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
import java.util.*;
import java.util.stream.Collectors;

public class FlatManager implements DataManager<ArrayDeque<Flat>> {
    private static final File DATABASE_FILE = new File("/Users/stanislavserbakov/Desktop/Java/lab5/application/src/main/resources/database.json");
    private final JsonSerializer<Flat> serializer = new FlatSerializer();
    private final JsonDeserializer<Flat> deserializer = new FlatDeserializer();
    private ArrayDeque<Flat> flats = new ArrayDeque<>();

    private FlatManager(){
        flats = readData();
    }

    static {
        if (!DATABASE_FILE.exists()) {
            try {
                if (!DATABASE_FILE.createNewFile()) {
                    throw new IOException("Database file failed to create");
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        SessionFactory.create(DATABASE_FILE);
    }
    @Override
    public ArrayDeque<Flat> getData() {
       return flats.clone();
    }

    private ArrayDeque<Flat> readData() {
        Session session = SessionFactory.get(DATABASE_FILE);
        JsonArray flatsArray = session.readJson(JsonArray.class);
        ArrayDeque<Flat> flatArrayDeque = new ArrayDeque<>();
        if (flatsArray != null && !flatsArray.isEmpty()) {
            for (JsonElement flatElement : flatsArray) {
                flatArrayDeque.add(deserializer.deserialize(flatElement, Flat.class, null));
            }
        }
        return sortFlats(flatArrayDeque);
    }

    private ArrayDeque<Flat> sortFlats(ArrayDeque<Flat> flats) {
        return flats.stream().sorted((flat1, flat2) -> Float.compare(flat1.getArea(), flat2.getArea())).collect(Collectors.toCollection(ArrayDeque::new));
    }

    @Override
    public void saveData(ArrayDeque<Flat> flats) {
        if (!flats.equals(getData())) {
            Session session = SessionFactory.create(DATABASE_FILE);
            JsonArray jsonArray = new JsonArray();
            ArrayDeque<Flat> flatsNew = sortFlats(flats);
            for (Flat flat : flatsNew) {
                jsonArray.add(serializer.serialize(flat, Flat.class, null));
            }
            session.writeJson(jsonArray);
            this.flats = flatsNew;
        }
    }

    @Override
    public long generateID() {
       if (getData().isEmpty()) {
           return 1;
       } else {
           return getData().getLast().getId() + 1;
       }
    }
}
