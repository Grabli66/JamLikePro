package jamlikepro.sound;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Beat {
    public static final int MAX_GROUP_COUNT = 16;
    public static final int DEFAULT_BPM = 120;
    
    private Integer bpm = DEFAULT_BPM;    
    private final ArrayList<NoteGroup> groups;
        
    public Integer getBpm() {
        return bpm;
    }

    public void setBpm(Integer bpm) {
        this.bpm = bpm;
    }    
    
    public Beat() {
        groups = new ArrayList<NoteGroup>(MAX_GROUP_COUNT);
        for (int i = 0; i < MAX_GROUP_COUNT; i++) {
            groups.add(new NoteGroup());
        }
    }
    
    public void addNote(int pos, String sample) {
        if ((pos < 0) || (pos > MAX_GROUP_COUNT)) return;                
        groups.get(pos).setNote(new Note(sample));        
    }
    
    public void removeNote(int pos, String sample) {
        if ((pos < 0) || (pos > MAX_GROUP_COUNT)) return;
        groups.get(pos).removeNote(sample);
    }
    
    public Iterator<NoteGroup> iterateGroups() {
        return groups.iterator();
    }

    public Integer size() {
        return groups.size();
    }

    public NoteGroup getGroup(Integer currentGroup) {
        return groups.get(currentGroup);
    }
}
