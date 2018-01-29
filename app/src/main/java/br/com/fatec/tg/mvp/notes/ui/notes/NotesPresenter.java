package br.com.fatec.tg.mvp.notes.ui.notes;

import android.support.annotation.NonNull;

import java.util.List;

import br.com.fatec.tg.mvp.notes.data.entity.Note;
import br.com.fatec.tg.mvp.notes.data.repository.NotesRepository;

public class NotesPresenter implements NotesContract.Presenter {

    @NonNull
    private final NotesRepository notesRepository;
    @NonNull
    private final NotesContract.View view;

    NotesPresenter(@NonNull NotesRepository notesRepository, @NonNull NotesContract.View view) {
        this.notesRepository = notesRepository;
        this.view = view;
    }

    /********** NotesContract.Presenter **********/

    @Override
    public void loadNotes() {
        List<Note> notes = notesRepository.getAllNotes();
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
