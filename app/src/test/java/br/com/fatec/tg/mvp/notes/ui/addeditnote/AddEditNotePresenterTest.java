package br.com.fatec.tg.mvp.notes.ui.addeditnote;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import br.com.fatec.tg.mvp.notes.data.entity.Note;
import br.com.fatec.tg.mvp.notes.data.repository.NoteRepository;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class AddEditNotePresenterTest {

    private static Note NOTE = new Note("qz1r25fee2", "Title 1", "Some description");

    @InjectMocks
    private AddEditNotePresenter presenter;
    @Mock
    private NoteRepository noteRepository;
    @Mock
    private AddEditNoteContract.View view;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void setupNoteData_showsNoteDataAndMenu() {
        when(noteRepository.getNoteById(any())).thenReturn(NOTE);
        presenter.setupNoteData(NOTE.getId());
        verify(noteRepository).getNoteById(NOTE.getId());
        verify(view).showMenuActionDelete();
        verify(view).showTitle(NOTE.getTitle());
        verify(view).showDescription(NOTE.getDescription());
    }

    @Test
    public void setupNoteData_nullNote_hidesMenu() {
        presenter.setupNoteData(null);
        verify(view).hideMenuActionDelete();
    }

    @Test
    public void saveNote_emptyTitleAndDescription_navigatesToNotesScreen() {
        presenter.saveNote(NOTE.getId(), "", "");
        verify(view).navigateToNotesScreen();
    }

    @Test
    public void saveNote_navigatesToNotesScreen() {
        presenter.saveNote(NOTE.getId(), NOTE.getTitle(), NOTE.getDescription());
        verify(noteRepository).saveOrUpdateNote(any());
        verify(view).navigateToNotesScreen();
    }

    @Test
    public void deleteNote_navigatesToNotesScreen() {
        presenter.onDeleteNoteClicked(NOTE.getId());
        verify(noteRepository).deleteNoteById(NOTE.getId());
        verify(view).navigateToNotesScreen();
    }

    @Test(expected = IllegalStateException.class)
    public void deleteNote_nullNoteId_throwsException() {
        presenter.onDeleteNoteClicked(null);
    }
}
