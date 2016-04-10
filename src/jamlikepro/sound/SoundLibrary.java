/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jamlikepro.sound;

import jnr.ffi.LibraryLoader;

public class SoundLibrary {
    public static interface SoundLib {

        Integer Init();       
        //Integer AddSound(String name);
        void PlaySound(String name);
    }
    
    private static SoundLib instance = null;
    
    public static SoundLib getInstance() {
        if (instance == null) {
            instance = LibraryLoader.create(SoundLib.class).search("native").load("soundlibrary");
            Integer res = instance.Init();            
        }
        return instance;
    }
}
