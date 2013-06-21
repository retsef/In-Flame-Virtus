package Engine;

import GameObject.Mob;
import GameObject.Player;
import GameObject.ValueErrorException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * 
 * @author Roberto
 * @Game Inizializza il gioco di per se
 */

public class Game implements Runnable{
    
    private int sleep = 10;
    private Meccanics Meccanic;
    private boolean isStart;
    public static final int MAXMob = 20;
    private Thread thread;
    public static final int WIDTH = 1240;
    public static final int HEIGTH = 700;
    
    
    
    
    public Game() throws ValueErrorException, InputErrorException, IOException{
        this("Guy");
    }
    
    public Game(String pNamePlayer) throws ValueErrorException, InputErrorException, IOException{
        
        Instances.player = new Player(pNamePlayer);
        Instances.drawing = new Draw(Game.WIDTH,Game.HEIGTH,"In Flame Virtus");
        
        Instances.BundleMob = new ArrayList<>();
        for (int i = 0; i < this.MAXMob; i++){
            Instances.BundleMob.add(new Mob());
        }
        
        this.isStart = false;
        this.Meccanic = new Meccanics();
    }
    
    /**
     * @run Esegue il gioco (parte attiva)
     */
    @Override
    public void run(){
    while(true){
        try {
                 Instances.drawing.setFrameVisible(true);
                 Instances.player.update();
                 
                 this.Meccanic.run();
                 
                 for(int i = 0; i < Instances.BundleMob.size(); i++)
                    {
                        Instances.BundleMob.get(i).update();
                    }
                 
                 Instances.drawing.render();
                 System.gc();
                 Thread.sleep(this.sleep);
                        synchronized(this) {
                           while(!this.isStart) {
                                   wait();
                               }
                        }
        }catch (InterruptedException | IOException ex) {
        Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);}
        }
    }
    
        /**
         * @start Avvia il thread del gioco
         */
        public void start() {
            stop();
            this.thread = new Thread(this,"Game");
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
                //Instances.drawing.setFrameVisible(false);
                //Instances.menu.setFrameVisible(true);
            }
        }
        
        /**
         * @restart Riavvia il tread
         */
        public void restart() {
            this.stop();
            this.start();
        }
        
        /**
         * @suspend Ferma momentaneamente il tread
         */
        public void suspend() {
            this.isStart = false;
        }
        
        /**
         * @resume Il tread riprende l'esecuzione
         */
        synchronized void resume() {
           this.isStart = true;
            notify();
        }
    
        public void iStart(boolean pStart){
            this.isStart = pStart;
        }
        
        public boolean getisStart(){
            return this.isStart;
        }
   }
