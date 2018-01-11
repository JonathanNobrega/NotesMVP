package br.com.fatec.tg.mvp.notes.util;

import java.util.UUID;

public class Database {

    public static String generateId() {
        return System.currentTimeMillis() + "-" + UUID.randomUUID();
    }
}
