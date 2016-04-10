package jamlikepro.sound;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Optional;

public class NoteGroup {

    private final ArrayList<Note> notes;

    public NoteGroup() {
        notes = new ArrayList<>();
    }

    public void setNote(Note note) {
        if (notes.stream().anyMatch(x -> x.getSample().equals(note.getSample()))) {
            return;
        }
        notes.add(note);
    }

    public Iterator<Note> iterateNotes() {
        return notes.iterator();
    }

    void removeNote(String sample) {
        Optional<Note> note = notes.stream().filter(x -> x.getSample().equals(sample)).findFirst();
        if (note.isPresent()) notes.remove(note.get());
    }
}
