package jamlikepro.sound;

import java.io.File;
import java.util.HashMap;

public class SamplePlayer {
    private static SamplePlayer instance = null;
    
    public static SamplePlayer getInstance() {
        if (instance == null) {
            instance = new SamplePlayer();
        }
        return instance;
    }
    
    private final HashMap<String, Integer> samples;

    private SamplePlayer() {
        samples = new HashMap<>();
        loadSamples();
    }

    /*
    * Загружает все сэмплы с диска в память
     */
    private void loadSamples() {
       /* File[] files = new File("samples").listFiles();

        for (File file : files) {
            String name = file.getName();
            String path = "samples/" + name;
            Integer idx = SoundLibrary.getInstance().AddSound(path);
            samples.put(name, idx);
        }*/
    }
    
    public void playSample(String name) {
       // Integer idx = samples.get(name);
        SoundLibrary.getInstance().PlaySound(name);
    }
}
