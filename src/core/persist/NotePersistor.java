package core.persist;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Order;

import common.entity.delivery.Delivery;
import common.entity.note.Note;
import common.entity.note.NoteType;
import common.manager.NoteManager;

public class NotePersistor extends Persistor implements NoteManager {

	@Override
	public void addNote(Note note) throws Exception {
		add(note);
	}

	@Override
	public Note getNote(int id) throws Exception {
		return (Note) get(Note.class, id);
	}

	// @Override
	// public List<Note> getNotes() throws Exception {
	// return getAll(Note.class);
	// }

	@SuppressWarnings("unchecked")
	@Override
	public List<Note> getNotes() throws Exception {
		Session session = HibernateUtil.startSession();
		Criteria criteria = session.createCriteria(Note.class);
		List<Note> notes = new ArrayList<Note>();
		try {
			notes = criteria.addOrder(Order.desc("date")).list();
		} catch (HibernateException ex) {
			ex.printStackTrace();
		} finally {
			session.close();
		}
		return notes;
	}

	@Override
	public void updateNote(Note note) throws Exception {
		update(note);
	}

	@Override
	public void deleteNote(Note note) throws Exception {
		remove(note);
	}

	@Override
	public void addNoteType(String name) throws Exception {
		add(new NoteType(name));
	}

	@Override
	public NoteType getNoteType(int id) throws Exception {
		return (NoteType) get(NoteType.class, id);
	}

	@Override
	public List<NoteType> getNoteTypes() throws Exception {
		return getAll(NoteType.class);
	}

	@Override
	public void updateNoteType(NoteType noteType) throws Exception {
		update(noteType);
	}

	@Override
	public void deleteNoteType(NoteType noteType) throws Exception {
		remove(noteType);
	}

}
