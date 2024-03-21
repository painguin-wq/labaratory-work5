package dev.infochem.application.deserialize;

import com.google.gson.*;
import dev.infochem.application.model.Furnish;

import java.lang.reflect.Type;

public class FurnishDeserializer implements JsonDeserializer<Furnish> {
    @Override
    public Furnish deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        int ordinal = jsonElement.getAsInt();
        return Furnish.values()[ordinal];
    }
}
