package br.com.fatec.tg.mvp.notes.ui.notes;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import br.com.fatec.tg.mvp.notes.data.entity.Note;
import br.com.fatec.tg.mvp.notes.data.repository.NoteRepository;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class NotesPresenterTest {

    private static List<Note> NOTES;

    @InjectMocks
    private NotesPresenter presenter;
    @Mock
    private NoteRepository noteRepository;
    @Mock
    private NotesContract.View view;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        NOTES = Arrays.asList(new Note("qz1r25fee2", "Title 1", "Some description"),
                new Note("rzr6dfa3e6", "Title 2", "Another description"));
    }

    @Test
    public void loadNotes_showsNotes() {
        when(noteRepository.getAllNotes()).thenReturn(NOTES);
        presenter.loadNotes();
        verify(noteRepository).getAllNotes();
        verify(view).showNotes(NOTES);
        verify(view).showMainView();
    }

    @Test
    public void loadNotes_emptyResult_showsPlaceholder() {
        when(noteRepository.getAllNotes()).thenReturn(Collections.emptyList());
        presenter.loadNotes();
        verify(noteRepository).getAllNotes();
        verify(view).showPlaceholder();
    }

    @Test
    public void onAddNoteClicked_navigatesToAddNoteScreen() {
        presenter.onAddNoteClicked();
        verify(view).navigateToAddNoteScreen();
    }

    @Test
    public void onNoteClicked_navigatesToNoteDetailsScreen() {
        Note note = NOTES.get(0);
        presenter.onNoteClicked(note);
        verify(view).navigateToNoteDetailsScreen(note);
    }
}
