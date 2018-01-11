package br.com.fatec.tg.mvp.notes.ui.notes;

import android.support.annotation.NonNull;

import br.com.fatec.tg.mvp.notes.data.entity.Note;
import br.com.fatec.tg.mvp.notes.data.repository.NoteRepository;

public class NotesPresenter implements NotesContract.Presenter {

    private NoteRepository noteRepository;
    private NotesContract.View view;

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
        view.navigateToAddNoteScreen();
    }

    @Override
    public void onNoteClicked(@NonNull Note note) {
        view.navigateToNoteDetailsScreen(note);
    }

    @Override
    public void onNoteCreated(@NonNull Note note) {
        view.addNoteToList(note);
        updatePlaceholderState();
    }

    @Override
    public void onNoteDeleted(@NonNull Note note) {
        view.removeNoteFromList(note);
        updatePlaceholderState();
    }

    @Override
    public void detachView() {
        view = null;
    }

    /********** Methods **********/

    private void loadNotes() {
        view.showNotes(noteRepository.getAllNotes());
        updatePlaceholderState();
    }

    private void updatePlaceholderState() {
        if (noteRepository.hasAnyNotes()) {
            view.showMainView();
        } else {
            view.showPlaceholder();
        }
    }
}
