package br.com.fatec.tg.mvp.notes.ui.notes;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.widget.LinearLayout;
import android.widget.ViewFlipper;

import java.util.ArrayList;
import java.util.List;

import br.com.fatec.tg.mvp.notes.R;
import br.com.fatec.tg.mvp.notes.data.entity.Note;
import br.com.fatec.tg.mvp.notes.data.repository.datasource.LocalNoteDataSource;
import br.com.fatec.tg.mvp.notes.ui.addeditnote.AddEditNoteActivity;
import butterknife.BindInt;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class NotesActivity extends AppCompatActivity implements
        NotesContract.View,
        NotesAdapter.ItemListener {

    @BindView(R.id.view_flipper_note_content)
    ViewFlipper viewFlipperMainContent;
    @BindView(R.id.linear_layout_note_placeholder_container)
    LinearLayout linearLayoutPlaceholderContainer;
    @BindView(R.id.recycler_view_notes_list)
    RecyclerView recyclerViewNotes;
    @BindInt(R.integer.notes_columns_count)
    int columnsNumber;

    private NotesContract.Presenter presenter;
    private NotesAdapter mNoteAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes);
        ButterKnife.bind(this);
        createPresenter();
        setupRecyclerView();
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.attachView(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        presenter.detachView();
    }

    /********** Click events **********/

    @OnClick(R.id.fab_note_add_note)
    void onAddNoteClicked() {
        presenter.onAddNoteClicked();
    }

    /********** NotesContract.View **********/

    @Override
    public void showMainView() {
        viewFlipperMainContent.setDisplayedChild(
                viewFlipperMainContent.indexOfChild(recyclerViewNotes));
    }

    @Override
    public void showPlaceholder() {
        viewFlipperMainContent.setDisplayedChild(
                viewFlipperMainContent.indexOfChild(linearLayoutPlaceholderContainer));
    }

    @Override
    public void showNotes(@NonNull List<Note> notes) {
        mNoteAdapter.setData(notes);
    }

    @Override
    public void addNoteToList(@NonNull Note note) {
        mNoteAdapter.addItem(note);
    }

    @Override
    public void removeNoteFromList(@NonNull Note note) {
        mNoteAdapter.removeItem(note);
    }

    @Override
    public void navigateToAddNoteScreen() {
        startActivity(AddEditNoteActivity.getStartIntent(this));
    }

    @Override
    public void navigateToNoteDetailsScreen(@NonNull Note note) {
        startActivity(AddEditNoteActivity.getStartIntent(this, note.getId()));
    }

    /********** NotesAdapter.ItemListener **********/

    @Override
    public void onNoteClicked(@NonNull Note note) {
        presenter.onNoteClicked(note);
    }

    /********** Methods **********/

    private void createPresenter() {
        presenter = new NotesPresenter(new LocalNoteDataSource());
    }

    private void setupRecyclerView() {
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(
                columnsNumber, StaggeredGridLayoutManager.VERTICAL);
        mNoteAdapter = new NotesAdapter(new ArrayList<>(), this);
        recyclerViewNotes.setLayoutManager(layoutManager);
        recyclerViewNotes.setAdapter(mNoteAdapter);
    }
}
