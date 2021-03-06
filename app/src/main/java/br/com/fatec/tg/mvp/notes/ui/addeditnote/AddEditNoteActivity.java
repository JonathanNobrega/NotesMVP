package br.com.fatec.tg.mvp.notes.ui.addeditnote;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.EditText;

import br.com.fatec.tg.mvp.notes.R;
import br.com.fatec.tg.mvp.notes.data.repository.datasource.LocalNotesDataSource;
import butterknife.BindView;
import butterknife.ButterKnife;

public class AddEditNoteActivity extends AppCompatActivity implements AddEditNoteContract.View {

    private static final String EXTRA_NOTE_ID = "EXTRA_NOTE_ID";

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.edit_text_note_title)
    EditText editTextNoteTitle;
    @BindView(R.id.edit_text_note_description)
    EditText editTextNoteDescription;

    @Nullable
    private String noteId;
    private AddEditNoteContract.Presenter presenter;

    @NonNull
    public static Intent getStartIntent(@NonNull Context context,
                                        @Nullable String noteId) {
        Intent intent = new Intent(context, AddEditNoteActivity.class);
        intent.putExtra(EXTRA_NOTE_ID, noteId);

        return intent;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit_note);
        ButterKnife.bind(this);
        retrieveNoteId();
        setupToolbar();
        createPresenter();
    }

    @Override
    public void onBackPressed() {
        presenter.saveNote(noteId, editTextNoteTitle.getText().toString(),
                editTextNoteDescription.getText().toString());
    }

    /********** AddEditNoteContract.View **********/

    @Override
    public void showMenuActionDelete() {
        MenuItem menuActionDelete = toolbar.getMenu().findItem(R.id.add_edit_note_action_delete);
        menuActionDelete.setEnabled(true);
        menuActionDelete.setVisible(true);
    }

    @Override
    public void hideMenuActionDelete() {
        MenuItem menuActionDelete = toolbar.getMenu().findItem(R.id.add_edit_note_action_delete);
        menuActionDelete.setEnabled(false);
        menuActionDelete.setVisible(false);
    }

    @Override
    public void showTitle(@NonNull String title) {
        editTextNoteTitle.setText(title);
    }

    @Override
    public void showDescription(@NonNull String description) {
        editTextNoteDescription.setText(description);
    }

    @Override
    public void navigateToNotesScreen() {
        NavUtils.navigateUpFromSameTask(this);
    }

    /********** Methods **********/

    private void retrieveNoteId() {
        noteId = getIntent().getStringExtra(EXTRA_NOTE_ID);
    }

    private void createPresenter() {
        presenter = new AddEditNotePresenter(new LocalNotesDataSource(), this);
        presenter.setupNoteData(noteId);
    }

    private void setupToolbar() {
        toolbar.setNavigationOnClickListener(view -> presenter.saveNote(
                noteId,
                editTextNoteTitle.getText().toString(),
                editTextNoteDescription.getText().toString()));
        toolbar.inflateMenu(R.menu.activity_add_edit_note);
        toolbar.setOnMenuItemClickListener(menuItem -> {
            if (menuItem.getItemId() == R.id.add_edit_note_action_delete) {
                presenter.onDeleteNoteClicked(noteId);
            }
            return false;
        });
    }
}
