package dev.infochem.transactionapi;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.io.File;

public interface Session {
    File getFile();
    void close();

    boolean isClose();
    <T> T readJson(Class<T> dataType);
    void writeJson(JsonElement jsonObject);
}
