package br.com.fatec.tg.mvp.notes.ui.notes;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import br.com.fatec.tg.mvp.notes.R;
import br.com.fatec.tg.mvp.notes.data.repository.datasource.LocalNoteDataSource;

public class NotesActivity extends AppCompatActivity {

    private LocalNoteDataSource localNoteDataSource = new LocalNoteDataSource();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes);
    }
}
