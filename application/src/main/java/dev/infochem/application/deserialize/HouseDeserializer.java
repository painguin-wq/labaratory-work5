package dev.infochem.application.deserialize;

import com.google.gson.*;
import dev.infochem.application.model.House;

import java.lang.reflect.Type;

/**
 * The type House deserializer.
 */
public class HouseDeserializer implements JsonDeserializer<House> {
    @Override
    public House deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        JsonObject jsonObject = jsonElement.getAsJsonObject();
        House house = new House();
        if (jsonObject.get("name") != null) {
            house.setName(jsonObject.get("name").getAsString());
        }
        house.setYear(jsonObject.get("year").getAsInt());
        house.setNumberOfLifts(jsonObject.get("numberOfLifts").getAsInt());
        return house;
    }
}
