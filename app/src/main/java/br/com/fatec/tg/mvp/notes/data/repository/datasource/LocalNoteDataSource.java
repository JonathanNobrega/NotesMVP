package br.com.fatec.tg.mvp.notes.data.repository.datasource;

import android.support.annotation.NonNull;

import java.util.List;

import br.com.fatec.tg.mvp.notes.data.entity.Note;
import br.com.fatec.tg.mvp.notes.data.repository.NoteRepository;
import io.realm.Realm;

public class LocalNoteDataSource implements NoteRepository {

    @NonNull
    @Override
    public List<Note> getAllNotes() {
        return Realm.getDefaultInstance()
                .where(Note.class)
                .findAll();
    }

    @NonNull
    @Override
    public Note getNoteById(@NonNull String id) {
        return Realm.getDefaultInstance()
                .where(Note.class)
                .equalTo("id", id)
                .findFirst();
    }

    @Override
    public void saveNote(@NonNull Note note) {
        try (Realm realm = Realm.getDefaultInstance()) {
            realm.beginTransaction();
            realm.copyToRealmOrUpdate(note);
            realm.commitTransaction();
        }
    }
}
