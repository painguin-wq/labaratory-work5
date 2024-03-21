package dev.infochem.transactionapi;

public interface DataManager<T> {
    T getData();
    void saveData(T data);
    long generateID();
}
