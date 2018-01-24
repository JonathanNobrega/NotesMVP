package br.com.fatec.tg.mvp.notes.ui.notes;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.List;

import br.com.fatec.tg.mvp.notes.data.entity.Note;
import br.com.fatec.tg.mvp.notes.data.repository.NoteRepository;

public class NotesPresenter implements NotesContract.Presenter {

    @Nullable
    private NotesContract.View view;
    @NonNull
    private final NoteRepository noteRepository;

    NotesPresenter(@NonNull NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }

    @Override
    public void attachView(@NonNull NotesContract.View view) {
        this.view = view;
        loadNotes();
    }

    @Override
    public void onAddNoteClicked() {
        getViewOrThrow().navigateToAddNoteScreen();
    }

    @Override
    public void onNoteClicked(@NonNull Note note) {
        getViewOrThrow().navigateToNoteDetailsScreen(note);
    }

    @Override
    public void detachView() {
        view = null;
    }

    /********** Methods **********/

    @NonNull
    private NotesContract.View getViewOrThrow() {
        if (view != null) {
            return view;
        } else {
            throw new IllegalStateException("View not attached to presenter");
        }
    }

    private void loadNotes() {
        List<Note> notes = noteRepository.getAllNotes();
        getViewOrThrow().showNotes(notes);
        updatePlaceholderState(notes);
    }

    private void updatePlaceholderState(@NonNull List<Note> notes) {
        if (notes.isEmpty()) {
            getViewOrThrow().showPlaceholder();
        } else {
            getViewOrThrow().showMainView();
        }
    }
}
