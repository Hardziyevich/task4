package by.hardziyevich.task4.service;

public class IdGenerator {

    private static long id;

    private IdGenerator() {}

    public static long generateId() {
        return id++;
    }
}
