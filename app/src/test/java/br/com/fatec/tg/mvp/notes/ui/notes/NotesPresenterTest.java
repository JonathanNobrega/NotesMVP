package br.com.fatec.tg.mvp.notes.ui.notes;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import br.com.fatec.tg.mvp.notes.data.entity.Note;
import br.com.fatec.tg.mvp.notes.data.repository.NotesRepository;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class NotesPresenterTest {

    private static final List<Note> EMPTY_NOTES = new ArrayList<>();

    private static final List<Note> NOTES = Arrays.asList(
            new Note("qz1r25fee2", "Title 1", "Some description"),
            new Note("rzr6dfa3e6", "Title 2", "Another description"));

    @InjectMocks
    private NotesPresenter presenter;
    @Mock
    private NotesRepository notesRepository;
    @Mock
    private NotesContract.View view;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void loadNotes_showsNotes() {
        when(notesRepository.getAllNotes()).thenReturn(NOTES);
        presenter.loadNotes();
        verify(notesRepository).getAllNotes();
        verify(view).showNotes(NOTES);
        verify(view).showMainView();
    }

    @Test
    public void loadNotes_emptyNotes_showsPlaceholder() {
        when(notesRepository.getAllNotes()).thenReturn(EMPTY_NOTES);
        presenter.loadNotes();
        verify(notesRepository).getAllNotes();
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
