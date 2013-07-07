package Engine;

import GameObject.*;
import java.io.IOException;
import java.util.ArrayList;

/**
 * 
 * @author Roberto
 * @Game Inizializza il gioco di per se
 */

public class Game implements Runnable{
    
    private int sleep = 20;
    private Meccanics Meccanic;
    private boolean isStart;
    public static final int MAXMob = 30;
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
    
    public Meccanics get_meccanics(){
        return this.Meccanic;
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
                 
                 for(int i = 0; i < Instances.BundleMob.size(); i++)
                    {
                        Instances.BundleMob.get(i).update();
                    }
                 
                 Instances.drawing.render();
                 
                    synchronized(this) {
                       while(!this.isStart) {
                               wait();
                           }
                    }
                 Thread.sleep(this.sleep);
                 
        }catch (InterruptedException | IOException ex) { } }
    }
    
        /**
         * @start Avvia il thread del gioco
         */
        public void start() {
            stop();
            this.thread = new Thread(this,"Game");
            this.thread.start();
            this.Meccanic.start();
            this.iStart(true);
        }

        /**
         * @stop Ferma il thread del gioco
         */
        public void stop() {
            if (this.thread != null && this.thread.isAlive()) {
                this.thread.interrupt();
                this.Meccanic.stop();
                this.iStart(false);
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
