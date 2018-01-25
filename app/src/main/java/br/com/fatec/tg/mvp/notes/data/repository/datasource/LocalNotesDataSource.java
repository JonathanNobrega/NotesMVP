package br.com.fatec.tg.mvp.notes.data.repository.datasource;

import android.support.annotation.NonNull;

import java.util.List;

import br.com.fatec.tg.mvp.notes.data.entity.Note;
import br.com.fatec.tg.mvp.notes.data.repository.NotesRepository;
import br.com.fatec.tg.mvp.notes.util.Database;
import io.realm.Realm;

public class LocalNotesDataSource implements NotesRepository {

    @NonNull
    @Override
    public List<Note> getAllNotes() {
        Realm realm = Realm.getDefaultInstance();
        return realm.copyFromRealm(
                realm.where(Note.class)
                        .findAll()
        );
    }

    @NonNull
    @Override
    public Note getNoteById(@NonNull String id) {
        Realm realm = Realm.getDefaultInstance();
        return realm.copyFromRealm(
                realm.where(Note.class)
                        .equalTo("id", id)
                        .findFirst()
        );
    }

    @Override
    public void saveOrUpdateNote(@NonNull Note note) {
        try (Realm realm = Realm.getDefaultInstance()) {
            realm.beginTransaction();
            if (note.getId().isEmpty()) note.setId(Database.generateId());
            realm.copyToRealmOrUpdate(note);
            realm.commitTransaction();
        }
    }

    @Override
    public void deleteNoteById(@NonNull String noteId) {
        Realm.getDefaultInstance().executeTransaction(realm ->
                realm.where(Note.class)
                        .equalTo("id", noteId)
                        .findFirst()
                        .deleteFromRealm()
        );
    }
}
