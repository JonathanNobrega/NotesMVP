package br.com.fatec.tg.mvp.notes.ui.addeditnote;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import br.com.fatec.tg.mvp.notes.data.entity.Note;
import br.com.fatec.tg.mvp.notes.data.repository.NoteRepository;

public class AddEditNotePresenter implements AddEditNoteContract.Presenter {

    @NonNull
    private final NoteRepository noteRepository;
    @NonNull
    private final AddEditNoteContract.View view;
    @Nullable
    private final String noteId;

    AddEditNotePresenter(@NonNull NoteRepository noteRepository,
                         @NonNull AddEditNoteContract.View view,
                         @Nullable String noteId) {
        this.noteRepository = noteRepository;
        this.view = view;
        this.noteId = noteId;
    }

    /********** AddEditNoteContract.Presenter **********/

    @Override
    public void setupNoteData() {
        if (noteId != null) {
            Note note = noteRepository.getNoteById(noteId);
            view.showMenuActionDelete();
            view.showTitle(note.getTitle());
            view.showDescription(note.getDescription());
        } else {
            view.hideMenuActionDelete();
        }
    }

    @Override
    public void saveNote(@NonNull String title, @NonNull String description) {
        if (!title.isEmpty() || !description.isEmpty()) {
            Note note = new Note(noteId == null ? "" : noteId, title, description);
            noteRepository.saveOrUpdateNote(note);
        }

        view.navigateToNotesScreen();
    }

    @Override
    public void onDeleteNoteClicked() {
        if (noteId != null) {
            noteRepository.deleteNoteById(noteId);
            view.navigateToNotesScreen();
        } else {
            throw new IllegalStateException("Cannot delete a null note");
        }
    }
}
