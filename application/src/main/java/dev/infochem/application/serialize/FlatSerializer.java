package dev.infochem.application.serialize;

import com.google.gson.*;
import dev.infochem.application.model.Flat;

import java.lang.reflect.Type;

public class FlatSerializer implements JsonSerializer<Flat> {
    @Override
    public JsonElement serialize(Flat flat, Type type, JsonSerializationContext jsonSerializationContext) {
        JsonObject object = new JsonObject();
        object.add("name", new JsonPrimitive(flat.getName()));
        object.add("coordinates", new CoordinatesSerializer().serialize(flat.getCoordinates(), type, jsonSerializationContext));
        object.add("creationDate", new JsonPrimitive(flat.getCreationDate().toString()));
        object.add("area", new JsonPrimitive(flat.getArea()));
        if (flat.getNumberOfRooms() == null) {
            object.add("numberOfRooms",null);
        } else {
            object.add("numberOfRooms", new JsonPrimitive(flat.getNumberOfRooms()));
        }
        object.add("numberOfBathrooms", new JsonPrimitive(flat.getNumberOfBathrooms()));
        object.add("timeToMetroByTransport", new JsonPrimitive(flat.getTimeToMetroByTransport()));
        object.add("furnish", new FurnishSerializer().serialize(flat.getFurnish(), type, jsonSerializationContext));
        if (flat.getHouse() == null) {
            object.add("house", null);
        } else {
            object.add("house", new HouseSerializer().serialize(flat.getHouse(), type, jsonSerializationContext));
        }
        return object;
    }
}
