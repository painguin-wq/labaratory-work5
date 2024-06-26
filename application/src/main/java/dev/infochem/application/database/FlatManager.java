package dev.infochem.application.database;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonSerializer;
import dev.infochem.application.deserialize.FlatDeserializer;
import dev.infochem.application.model.Flat;
import dev.infochem.application.serialize.FlatSerializer;
import dev.infochem.transactionapi.DataManager;
import dev.infochem.transactionapi.Session;
import dev.infochem.transactionapi.SessionFactory;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayDeque;
import java.util.stream.Collectors;

/**
 * The type Flat manager.
 */
public class FlatManager implements DataManager<ArrayDeque<Flat>> {
    private final File DATABASE_FILE;
    private final LocalDateTime creationDate = LocalDateTime.now();
    private final JsonSerializer<Flat> serializer = new FlatSerializer();
    private final JsonDeserializer<Flat> deserializer = new FlatDeserializer();
    private ArrayDeque<Flat> flats;

    private Long sequenceID = null;

    private FlatManager(String pathToDatabase){
        DATABASE_FILE = new File(pathToDatabase);
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
        flats = readData();
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }
    @Override
    public ArrayDeque<Flat> getData() {
       return flats;
    }

    private ArrayDeque<Flat> readData() {
        Session session = SessionFactory.get(DATABASE_FILE);
        JsonArray flatsArray = session.readJson(JsonArray.class);
        ArrayDeque<Flat> flatArrayDeque = new ArrayDeque<>();
        if (flatsArray != null && !flatsArray.isEmpty()) {
            for (int i = 0; i < flatsArray.size(); i++) {
                Flat flat = deserializer.deserialize(flatsArray.get(i), Flat.class, null);
                flat.setId(i);
                flatArrayDeque.add(flat);
            }
        }
        return sortFlats(flatArrayDeque);
    }

    public ArrayDeque<Flat> sortFlats(ArrayDeque<Flat> flats) {
        return flats.stream().sorted((flat1, flat2) -> Float.compare(flat1.getArea(), flat2.getArea())).collect(Collectors.toCollection(ArrayDeque::new));
    }

    public void resolveIds() {
        ArrayDeque<Flat> flats = new ArrayDeque<>();
        sequenceID = 0L;
        ArrayDeque<Flat> flatsCopy = this.flats.clone();
        this.flats = null;
        for (Flat flat : flatsCopy) {
            flat.setId(generateID());
            flats.add(flat);
        }
        this.flats = flats;
     }

    @Override
    public void saveData(ArrayDeque<Flat> flats) {
        Session session = SessionFactory.create(DATABASE_FILE);
        JsonArray jsonArray = new JsonArray();
        ArrayDeque<Flat> flatsNew = sortFlats(flats);
        for (Flat flat : flatsNew) {
            jsonArray.add(serializer.serialize(flat, Flat.class, null));
        }
        session.writeJson(jsonArray);
        this.flats = flatsNew;
    }

    @Override
    public long generateID() {
        if (flats == null) {
            return generateSequenceID();
        }
       if (getData().isEmpty()) {
           return 0;
       } else {
           return flats.getLast().getId() + 1;
       }
    }

    private long generateSequenceID() {
        if (sequenceID == null) {
            sequenceID = 0L;
            return sequenceID;
        } else {
            return ++sequenceID;
        }
    }
}
