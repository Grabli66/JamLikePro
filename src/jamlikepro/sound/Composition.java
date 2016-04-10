package jamlikepro.sound;

import java.util.ArrayList;
import java.util.Iterator;

public class Composition {
    private final ArrayList<Beat> beats;
    
    public Composition() {
        beats = new ArrayList<>();
    }
    
    public void addBeat(Beat beat) {
        beats.add(beat);
    }
    
    public Beat getBeat(int idx) {
        return beats.get(idx);
    }
    
    public int size() {
        return beats.size();
    }
    
    public Iterator<Beat> iterateBeats() {
        return beats.iterator();
    }
}
