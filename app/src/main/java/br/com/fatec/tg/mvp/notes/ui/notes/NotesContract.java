package br.com.fatec.tg.mvp.notes.ui.notes;

import android.support.annotation.NonNull;

import java.util.List;

import br.com.fatec.tg.mvp.notes.data.entity.Note;
import br.com.fatec.tg.mvp.notes.ui.common.BasePresenter;

public interface NotesContract {

    interface View {

        void showMainView();

        void showPlaceholder();

        void showNotes(@NonNull List<Note> notes);

        void addNoteToList(@NonNull Note note);

        void removeNoteFromList(@NonNull Note note);

        void navigateToAddNoteScreen();

        void navigateToNoteDetailsScreen(@NonNull Note note);
    }

    interface Presenter extends BasePresenter<View> {

        void onAddNoteClicked();

        void onNoteClicked(@NonNull Note note);

        void onNoteCreated(@NonNull Note note);

        void onNoteDeleted(@NonNull Note note);
    }
}
