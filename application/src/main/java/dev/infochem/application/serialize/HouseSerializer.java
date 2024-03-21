package dev.infochem.application.serialize;

import com.google.gson.*;
import dev.infochem.application.model.House;

import java.lang.reflect.Type;

public class HouseSerializer implements JsonSerializer<House> {
    @Override
    public JsonElement serialize(House house, Type type, JsonSerializationContext jsonSerializationContext) {
        JsonObject object = new JsonObject();
        if (house.getName() == null) {
            object.add("name", null);
        } else {
            object.add("name", new JsonPrimitive(house.getName()));
        }
        object.add("year", new JsonPrimitive(house.getYear()));
        object.add("numberOfLifts", new JsonPrimitive(house.getNumberOfLifts()));
        return object;
    }
}
