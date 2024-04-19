package dev.infochem.transactionapi.include;
import com.google.gson.*;
import dev.infochem.transactionapi.LocalDateTimeTypeAdapter;
import dev.infochem.transactionapi.Session;

import java.io.*;
import java.time.LocalDate;
import java.util.Objects;


public class SessionImpl implements Session {
    private final Gson gson;
    private final File file;
    private Reader reader;
    private Writer writer;
    private boolean isClose = false;

    private String getFileExtension(File file) {
        String[] fileSeparate = file.getName().toLowerCase().split("\\.");
        return fileSeparate[fileSeparate.length-1];
    }
    private SessionImpl(String filePath) {
        gson = new GsonBuilder().registerTypeAdapter(LocalDate.class, new LocalDateTimeTypeAdapter())
                .setPrettyPrinting().create();
        file = new File(filePath);
        if (!file.exists() || !file.isFile() || !getFileExtension(file).equals("json")) {
            throw new IllegalArgumentException("Received file is not valid. File should exist and be not a directory and has \".json\" extension");
        }
    }

    @Override
    public File getFile() {
        return file;
    }

    @Override
    public void close() {
        try {
            isClose = true;
            reader.close();
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public boolean isClose() {
       return isClose;
    }

    @Override
    public <T> T readJson(Class<T> dataType) {
        try {
            reader = new BufferedReader(new FileReader(file));
            return gson.fromJson(reader, dataType);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void writeJson(JsonElement jsonElement) {
        try {
            writer = new BufferedWriter(new FileWriter(file));
            gson.toJson(jsonElement, writer);
            writer.flush();
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String toString() {
        return "SessionImpl{" +
                "gson=" + gson +
                ", file=" + file +
                ", reader=" + reader +
                ", writer=" + writer +
                ", isClose=" + isClose +
                '}';
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        SessionImpl session = (SessionImpl) object;
        return isClose == session.isClose && Objects.equals(gson, session.gson) && Objects.equals(file, session.file) && Objects.equals(reader, session.reader) && Objects.equals(writer, session.writer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(gson, file, reader, writer, isClose);
    }
}
