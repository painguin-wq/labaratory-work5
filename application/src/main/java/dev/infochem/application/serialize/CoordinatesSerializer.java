package dev.infochem.application.serialize;

import com.google.gson.*;
import dev.infochem.application.model.Coordinates;

import java.lang.reflect.Type;

public class CoordinatesSerializer implements JsonSerializer<Coordinates> {
    @Override
    public JsonElement serialize(Coordinates coordinates, Type type, JsonSerializationContext jsonSerializationContext) {
        JsonObject object = new JsonObject();
        object.add("x", new JsonPrimitive(coordinates.getX()));
        object.add("y", new JsonPrimitive(coordinates.getY()));
        return object;
    }
}
