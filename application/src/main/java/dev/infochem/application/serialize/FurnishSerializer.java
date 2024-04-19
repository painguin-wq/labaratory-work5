package dev.infochem.application.serialize;

import com.google.gson.*;
import dev.infochem.application.model.Furnish;

import java.lang.reflect.Type;

/**
 * The type Furnish serializer.
 */
public class FurnishSerializer implements JsonSerializer<Furnish> {
    @Override
    public JsonElement serialize(Furnish furnish, Type type, JsonSerializationContext jsonSerializationContext) {
        return new JsonPrimitive(furnish.ordinal());
    }
}
