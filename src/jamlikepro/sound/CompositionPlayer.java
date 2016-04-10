package jamlikepro.sound;

import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CompositionPlayer {
    private Boolean isPlaying;
    private Composition composition;
    private Boolean isLoop;

    private Runnable player = new Runnable() {
        @Override
        public void run() {
            try {
                isPlaying = true;
                while (isPlaying && isLoop) {
                    for (Iterator iterator = composition.iterateBeats(); iterator.hasNext();) {
                        Beat beat = (Beat) iterator.next();
                        Integer bpm = beat.getBpm();
                        Integer sixthPm = bpm * 4; // Количество шестнадцатых в минуту
                        Integer delay = (60 * 1000) / sixthPm; // Задержка

                        for (Iterator iterator1 = beat.iterateGroups(); iterator1.hasNext();) {

                            NoteGroup noteGroup = (NoteGroup) iterator1.next();
                            for (Iterator iterator2 = noteGroup.iterateNotes(); iterator2.hasNext();) {
                                if (!isPlaying) return;
                                Note note = (Note) iterator2.next();                                
                                SamplePlayer.getInstance().playSample(note.getSample());
                            }
                            Thread.sleep(delay);

                        }
                    }
                }
            } catch (Exception e) {

            }
        }
    };

    public void playComposition(Composition composition, boolean loop) {
        this.composition = composition;
        this.isLoop = loop;       
        new Thread(player).start();
    }
    
    public void stopPlaying() {
        isPlaying = false;
    }
}
