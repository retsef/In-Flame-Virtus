package Engine;

import GameObject.Player;
import java.io.IOException;
import java.net.MalformedURLException;

/**
 * 
 * @author Roberto
 * @Game Inizializza il gioco di per se
 */

public class Game implements Runnable{
    
    private int sleep = 10;
    private Thread thread;
    
    public Draw drawing;
    public static Player player;
    
    public Game(){
        this("Guy");
    }
    
    public Game(String pNamePlayer){
         this.player = new Player(pNamePlayer);
         this.drawing = new Draw();
    }
    
    /**
     * @run Esegue il gioco (parte attiva)
     */
    @Override
    public void run(){
    while(true){
             try {
                 this.player.update();
             } catch (MalformedURLException ex) { }
             try {
                 this.drawing.render();
             } catch (IOException | InterruptedException ex) { }
             try {
                 Thread.sleep(this.sleep);
             } catch (InterruptedException ex) { }
        }
    }
    
    
        /**
         * Avvia il thread del gioco
         */
        public void start() {
            stop();
            this.thread = new Thread(this);
            this.thread.start();
        }

        /**
         * Ferma il thread del gioco
         */
        public void stop() {
            if (this.thread != null && this.thread.isAlive()) {
                this.thread.interrupt();
            }
        }
    
   }