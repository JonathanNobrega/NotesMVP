package br.com.fatec.tg.mvp.notes.ui.addeditnote;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import br.com.fatec.tg.mvp.notes.data.entity.Note;
import br.com.fatec.tg.mvp.notes.data.repository.NoteRepository;
import br.com.fatec.tg.mvp.notes.util.Database;

public class AddEditNotePresenter implements AddEditNoteContract.Presenter {

    @NonNull
    private final NoteRepository noteRepository;
    @Nullable
    private final Note noteBeingEdited;
    @Nullable
    private AddEditNoteContract.View view;

    AddEditNotePresenter(@NonNull NoteRepository noteRepository, @Nullable String noteId) {
        this.noteRepository = noteRepository;

        if (noteId != null) {
            noteBeingEdited = noteRepository.getNoteById(noteId);
        } else {
            noteBeingEdited = null;
        }
    }

    /********** AddEditNoteContract.Presenter **********/

    @Override
    public void attachView(@NonNull AddEditNoteContract.View view) {
        this.view = view;
        setNoteDataToView();
    }

    @Override
    public void onBackClicked(@NonNull String title, @NonNull String description) {
        if (!title.isEmpty() || !description.isEmpty()) {
            if (noteBeingEdited != null) {
                noteBeingEdited.setTitle(title);
                noteBeingEdited.setDescription(description);
                noteRepository.saveOrUpdateNote(noteBeingEdited);
            } else {
                Note note = new Note(Database.generateId(), title, description);
                noteRepository.saveOrUpdateNote(note);
            }
        }

        getViewOrThrow().navigateToNotesScreen();
    }

    @Override
    public void onDeleteNoteClicked() {
        if (noteBeingEdited != null) {
            noteRepository.deleteNoteById(noteBeingEdited.getId());
            getViewOrThrow().navigateToNotesScreen();
        }
    }

    @Override
    public void detachView() {
        view = null;
    }

    /********** Methods **********/

    @NonNull
    private AddEditNoteContract.View getViewOrThrow() {
        if (view != null) {
            return view;
        } else {
            throw new IllegalStateException("View not attached to presenter");
        }
    }

    private void setNoteDataToView() {
        if (noteBeingEdited != null) {
            getViewOrThrow().showMenuActionDelete();
            getViewOrThrow().setTitle(noteBeingEdited.getTitle());
            getViewOrThrow().setDescription(noteBeingEdited.getDescription());
        }
    }
}
