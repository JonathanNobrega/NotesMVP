package br.com.fatec.tg.mvp.notes.ui.addeditnote;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

public interface AddEditNoteContract {

    interface View {

        void showMenuActionDelete();

        void hideMenuActionDelete();

        void showTitle(@NonNull String title);

        void showDescription(@NonNull String description);

        void navigateToNotesScreen();
    }

    interface Presenter {

        void setupNoteData(@Nullable String noteId);

        void saveNote(@Nullable String noteId, @NonNull String title, @NonNull String description);

        void onDeleteNoteClicked(@Nullable String noteId);
    }
}
