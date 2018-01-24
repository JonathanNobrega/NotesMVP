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
    public void attachView_loadAllNotes_showNotes() {
        when(noteRepository.getAllNotes()).thenReturn(NOTES);
        presenter.attachView(view);
        verify(noteRepository).getAllNotes();
        verify(view).showNotes(any());
        verify(view).showMainView();
    }

    @Test
    public void attachView_emptyNotes_showsPlaceholder() {
        when(noteRepository.getAllNotes()).thenReturn(Collections.emptyList());
        presenter.attachView(view);
        verify(noteRepository).getAllNotes();
        verify(view).showPlaceholder();
    }

    @Test
    public void onAddNoteClicked_navigatesToAddNoteScreen() {
        presenter.attachView(view);
        presenter.onAddNoteClicked();
        verify(view).navigateToAddNoteScreen();
    }

    @Test
    public void onNoteClicked_navigatesToNoteDetailsScreen() {
        Note note = NOTES.get(0);
        presenter.attachView(view);
        presenter.onNoteClicked(note);
        verify(view).navigateToNoteDetailsScreen(note);
    }

    @Test(expected = IllegalStateException.class)
    public void detachView_callsView_throwsException() {
        presenter.detachView();
        presenter.onAddNoteClicked();
    }
}
