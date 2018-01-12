package br.com.fatec.tg.mvp.notes.util;

import android.support.annotation.NonNull;

import java.util.UUID;

public class Database {

    @NonNull
    public static String generateId() {
        return System.currentTimeMillis() + "-" + UUID.randomUUID();
    }
}
