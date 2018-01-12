package br.com.fatec.tg.mvp.notes.data.repository;

import android.support.annotation.NonNull;

import java.util.List;

import br.com.fatec.tg.mvp.notes.data.entity.Note;

public interface NoteRepository {

    @NonNull
    List<Note> getAllNotes();

    @NonNull
    Note getNoteById(@NonNull String id);

    boolean hasAnyNotes();

    void saveNote(@NonNull Note note);

    void deleteNote(@NonNull Note note);
}
