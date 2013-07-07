package Utils;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.Line;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 *
 * @author roberto
 */
public class Sound {
    
    private File soundFile;
    private Clip clip;
    private AudioInputStream audioInputStream;
    
    public Sound(String pLocation) throws LineUnavailableException, IOException, UnsupportedAudioFileException{
        this.soundFile = new File(pLocation);
        
        Line.Info linfo = new Line.Info(Clip.class);
        Line line = AudioSystem.getLine(linfo);
        this.clip = (Clip) line;
        this.audioInputStream = AudioSystem.getAudioInputStream(this.soundFile);
        this.clip.open(this.audioInputStream);
    }
    
    public void play(){
        this.clip.stop();
        this.clip.start();
    }
    
    
}
