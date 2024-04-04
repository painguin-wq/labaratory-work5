package dev.infochem.application.deserialize;

import com.google.gson.*;
import dev.infochem.application.database.FileManagerFactory;
import dev.infochem.application.model.Flat;
import dev.infochem.application.model.House;

import java.lang.reflect.Type;
import java.time.LocalDate;

public class FlatDeserializer implements JsonDeserializer<Flat> {
    @Override
    public Flat deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        JsonObject jsonObject = jsonElement.getAsJsonObject();
        Flat flat = new Flat();
        flat.setId(FileManagerFactory.create().generateID());
        flat.setName(jsonObject.get("name").getAsString());
        flat.setCoordinates(new CoordinatesDeserializer().deserialize(jsonObject.get("coordinates"), type, jsonDeserializationContext));
        flat.setCreationDate(LocalDate.parse(jsonObject.get("creationDate").getAsString()));
        flat.setArea(jsonObject.get("area").getAsLong());
        if (jsonObject.get("numberOfRooms") != null){
            flat.setNumberOfRooms(jsonObject.get("numberOfRooms").getAsLong());
        }
        flat.setTimeToMetroByTransport(jsonObject.get("timeToMetroByTransport").getAsFloat());
        flat.setFurnish(new FurnishDeserializer().deserialize(jsonObject.get("furnish"), type, jsonDeserializationContext));
        House house = new HouseDeserializer().deserialize(jsonObject.get("house"), type, jsonDeserializationContext);
        if (house != null) {
            flat.setHouse(house);
        }
        return flat;
    }
}
