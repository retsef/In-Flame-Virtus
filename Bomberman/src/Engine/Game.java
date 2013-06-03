package Engine;

import GameObject.Mob;
import GameObject.Player;
import GameObject.ValueErrorException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;

/**
 * 
 * @author Roberto
 * @Game Inizializza il gioco di per se
 */

public class Game implements Runnable{
    
    private int sleep = 10;
    private Thread thread;
    private boolean isStart;
    public static final int MAXMob = 15;
    
    public static Draw drawing;
    public static ArrayList<Mob> BundleMob;
    public static Player player;
    
    
    public Game() throws ValueErrorException, InputErrorException, IOException{
        this("Guy");
    }
    
    public Game(String pNamePlayer) throws ValueErrorException, InputErrorException, IOException{
        this.drawing = new Draw();
        
        this.player = new Player(pNamePlayer);
        
        this.BundleMob = new ArrayList<Mob>();
        for (int i = 0; i < this.MAXMob; i++){
        this.BundleMob.add(new Mob());
        }
        
        this.isStart = false;
         
    }
    
    /**
     * @run Esegue il gioco (parte attiva)
     */
    @Override
    public void run(){
    while(true){
             try {
                 this.drawing.setFrameVisible(true);
                 this.player.update();
                 
                 for(int i = 0; i < this.BundleMob.size(); i++)
                    {
                        this.BundleMob.get(i).update();
                        this.player.getDamage(this.BundleMob.get(i));
                    }
                 
             } catch (MalformedURLException ex) { }
             try {
                 this.drawing.render();
             } catch (IOException | InterruptedException ex) { }
             try {
                 System.gc();
                 Thread.sleep(this.sleep);
             } catch (InterruptedException ex) { }
        }
    }
    
        /**
         * @start Avvia il thread del gioco
         */
        public void start() {
            stop();
            
            this.thread = new Thread(this);
            this.thread.start();
            this.iStart(true);
        }

        /**
         * @stop Ferma il thread del gioco
         */
        public void stop() {
            if (this.thread != null && this.thread.isAlive()) {
                this.thread.interrupt();
                this.iStart(false);
                this.drawing.setFrameVisible(false);
            }
        }
        
        /**
         * @restart Riavvia il tread
         */
        public void restart() {
            this.stop();
            this.start();
        }
    
        public void iStart(boolean pStart){
            this.isStart = pStart;
        }
        
        public boolean getisStart(){
            return this.isStart;
        }
   }
