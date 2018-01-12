package br.com.fatec.tg.mvp.notes.ui.addeditnote;

import android.support.annotation.NonNull;

import br.com.fatec.tg.mvp.notes.ui.common.BasePresenter;

public interface AddEditNoteContract {

    interface View {

        void showMenuActionDelete();

        void setTitle(@NonNull String title);

        void setDescription(@NonNull String description);

        void navigateToNotesScreen();
    }

    interface Presenter extends BasePresenter<View> {

        void onBackClicked(@NonNull String title, @NonNull String description);

        void onDeleteNoteClicked();
    }
}
