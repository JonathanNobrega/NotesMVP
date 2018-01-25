package br.com.fatec.tg.mvp.notes.ui.notes;

import android.support.annotation.NonNull;

import java.util.List;

import br.com.fatec.tg.mvp.notes.data.entity.Note;
import br.com.fatec.tg.mvp.notes.data.repository.NoteRepository;

public class NotesPresenter implements NotesContract.Presenter {

    @NonNull
    private final NoteRepository noteRepository;
    @NonNull
    private final NotesContract.View view;

    NotesPresenter(@NonNull NoteRepository noteRepository, @NonNull NotesContract.View view) {
        this.noteRepository = noteRepository;
        this.view = view;
    }

    @Override
    public void loadNotes() {
        List<Note> notes = noteRepository.getAllNotes();
        view.showNotes(notes);
        updatePlaceholderState(notes);
    }

    @Override
    public void onAddNoteClicked() {
        view.navigateToAddNoteScreen();
    }

    @Override
    public void onNoteClicked(@NonNull Note note) {
        view.navigateToNoteDetailsScreen(note);
    }

    /********** Methods **********/

    private void updatePlaceholderState(@NonNull List<Note> notes) {
        if (notes.isEmpty()) {
            view.showPlaceholder();
        } else {
            view.showMainView();
        }
    }
}
