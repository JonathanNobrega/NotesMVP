package br.com.fatec.tg.mvp.notes.ui.addeditnote;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import br.com.fatec.tg.mvp.notes.data.entity.Note;
import br.com.fatec.tg.mvp.notes.data.repository.NotesRepository;

public class AddEditNotePresenter implements AddEditNoteContract.Presenter {

    @NonNull
    private final NotesRepository notesRepository;
    @NonNull
    private final AddEditNoteContract.View view;

    AddEditNotePresenter(@NonNull NotesRepository notesRepository,
                         @NonNull AddEditNoteContract.View view) {
        this.notesRepository = notesRepository;
        this.view = view;
    }

    /********** AddEditNoteContract.Presenter **********/

    @Override
    public void setupNoteData(@Nullable String noteId) {
        if (noteId != null) {
            Note note = notesRepository.getNoteById(noteId);
            view.showMenuActionDelete();
            view.showTitle(note.getTitle());
            view.showDescription(note.getDescription());
        } else {
            view.hideMenuActionDelete();
        }
    }

    @Override
    public void saveNote(@Nullable String noteId, @NonNull String title, @NonNull String description) {
        if (!title.isEmpty() || !description.isEmpty()) {
            Note note = new Note(noteId == null ? "" : noteId, title, description);
            notesRepository.saveOrUpdateNote(note);
        }

        view.navigateToNotesScreen();
    }

    @Override
    public void onDeleteNoteClicked(@Nullable String noteId) {
        if (noteId != null) {
            notesRepository.deleteNoteById(noteId);
            view.navigateToNotesScreen();
        } else {
            throw new IllegalStateException("Cannot delete a null note");
        }
    }
}
