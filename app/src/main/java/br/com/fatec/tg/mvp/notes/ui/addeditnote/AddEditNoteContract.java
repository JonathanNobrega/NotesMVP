package br.com.fatec.tg.mvp.notes.ui.addeditnote;

import android.support.annotation.NonNull;

public interface AddEditNoteContract {

    interface View {

        void showMenuActionDelete();

        void hideMenuActionDelete();

        void showTitle(@NonNull String title);

        void showDescription(@NonNull String description);

        void navigateToNotesScreen();
    }

    interface Presenter {

        void setupNoteData();

        void saveNote(@NonNull String title, @NonNull String description);

        void onDeleteNoteClicked();
    }
}
