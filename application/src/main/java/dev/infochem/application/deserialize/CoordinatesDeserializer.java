package dev.infochem.application.deserialize;

import com.google.gson.*;
import dev.infochem.application.model.Coordinates;

import java.lang.reflect.Type;

public class CoordinatesDeserializer implements JsonDeserializer<Coordinates> {
    @Override
    public Coordinates deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        JsonObject jsonObject = jsonElement.getAsJsonObject();
        return new Coordinates(jsonObject.get("x").getAsLong(), jsonObject.get("y").getAsInt());
    }
}
