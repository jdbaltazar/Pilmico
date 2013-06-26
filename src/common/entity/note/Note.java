package common.entity.note;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import common.entity.profile.Account;

@Entity
public class Note {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private int id;

	@Column(name = "date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date date;

	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "author")
	private Account author;

	@Column(name = "description")
	private String description;

	@Column(name = "tag")
	private String tag;

	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "note_type")
	private NoteType noteType;

	public Note() {
		super();
	}

	public Note(Date date, Account author, String description, NoteType noteType) {
		super();
		this.date = date;
		this.author = author;
		this.description = description;
		this.noteType = noteType;
	}

	public Note(Date date, Account author, String description, String tag, NoteType noteType) {
		super();
		this.date = date;
		this.author = author;
		this.description = description;
		this.tag = tag;
		this.noteType = noteType;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Account getAuthor() {
		return author;
	}

	public void setAuthor(Account author) {
		this.author = author;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public NoteType getNoteType() {
		return noteType;
	}

	public void setNoteType(NoteType noteType) {
		this.noteType = noteType;
	}

	public String toString() {
		return description;
	}

}
