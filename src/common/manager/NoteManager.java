package common.manager;

import java.util.List;

import common.entity.note.Note;
import common.entity.note.NoteType;

public interface NoteManager {

	public void addNote(Note note) throws Exception;

	public Note getNote(int id) throws Exception;

	public List<Note> getNotes() throws Exception;

	public void updateNote(Note note) throws Exception;

	public void deleteNote(Note note) throws Exception;

	public void addNoteType(String name) throws Exception;

	public NoteType getNoteType(int id) throws Exception;

	public List<NoteType> getNoteTypes() throws Exception;

	public void updateNoteType(NoteType noteType) throws Exception;

	public void deleteNoteType(NoteType noteType) throws Exception;

}
