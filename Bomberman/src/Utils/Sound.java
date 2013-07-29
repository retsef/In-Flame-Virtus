package Utils;

import java.net.URL;
import javax.sound.sampled.*;
/**
 * La classe gestisce il Suono in formato WAV
 * @author roberto
 */
public class Sound {
    
    private AudioInputStream audioInputStream;
    private URL url;
    private Clip clip;
    private AudioFormat format;
    private DataLine.Info info;
    
    /**
     * Crea un nuovo Sound pronto per essere riprodotto
     * @param path Locazione del file WAV
     * @throws SoundException Restituisce un Exception nel caso in cui non si riesca a riprodurre il suono
     */
    public Sound(String path) throws SoundException{
            //URL pUrl = this.getClass().getResource(path);
            //this.soundFile = ;
            this.url = this.getClass().getResource(path);
            if (this.url == null) {
                throw new SoundException("Cannot read " + url.getPath());
            }
            //this.inputStream = new InputStream
            //this.audioInputStream = AudioSystem.getAudioInputStream(getClass().getResource(path));
            //this.clip = AudioSystem.getClip();
            //this.clip = AudioSystem.getClip();
        
    }
    /**
     * Permette di mandare in loop la traccia
     * @throws SoundException Restituisce un Exception nel caso in cui non si riesca a riprodurre il suono
     */
    public void loop() throws SoundException{
        this.play(-1);
    }
    /**
     * Permette di riprodurre una volta sola la traccia
     * @throws SoundException Restituisce un Exception nel caso in cui non si riesca a riprodurre il suono
     */
    public void play() throws SoundException{
        this.play(1);
    }
    /**
     * Permette di riprodurre un suono piu' volte
     * @param i Determina quante volte deve essere eseguita la riproduzione della traccia
     * @throws SoundException Restituisce un Exception nel caso in cui non si riesca a riprodurre il suono
     */
    public void play(int i) throws SoundException{
        try {
            if(this.clip!=null){
                this.stop();
            }
            this.init_Audio();
            if(i == -1){
                this.clip.loop(20);
            }else if(i == 1){
                this.clip.loop(0);
            } else {
                this.clip.loop(i);
            }
            //while(this.clip.getMicrosecondLength() != this.clip.getMicrosecondPosition()){ }
            //this.clip.close();
        }catch(Exception ex){
            throw new SoundException(this.clip + " " + this.audioInputStream,ex);
        }
    }
    /**
     * Permette di fermare l'esecuzione della traccia
     */
    public void stop() {
        this.clip.drain();
        this.clip.stop();
        this.clip.close();
        System.gc();
    }
    /**
     * Permette di generare un nuovo suono pronto per essere riprodotto
     * @throws SoundException Restituisce un Exception nel caso in cui non si riesca a riprodurre il suono
     */
    public void init_Audio() throws SoundException{
        try{
            System.gc();
            this.audioInputStream = AudioSystem.getAudioInputStream(this.url);
            this.format = this.audioInputStream.getFormat();
            this.info = new DataLine.Info(Clip.class, format);
            this.clip = (Clip)AudioSystem.getLine(this.info);
            //this.clip = AudioSystem.getClip();
            this.clip.open(this.audioInputStream);
        }catch(Exception ex){
            throw new SoundException("Cannot read input file " + ex.getStackTrace());
        }
    }
    
}