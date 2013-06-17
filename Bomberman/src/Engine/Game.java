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
    private Thread thread;
    private Meccanics Meccanic;
    private boolean isStart;
    public static final int MAXMob = 10;
    
    public static final int WIDTH = 800;
    public static final int HEIGTH = 600;
    
    
    
    
    public Game() throws ValueErrorException, InputErrorException, IOException{
        this("Guy");
    }
    
    public Game(String pNamePlayer) throws ValueErrorException, InputErrorException, IOException{
        
        Instances.player = new Player(pNamePlayer);
        Instances.drawing = new Draw(Game.WIDTH,Game.HEIGTH,"Shot them all!");
        
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
                 
                 for(int i = 0; i < Instances.BundleMob.size(); i++)
                    {
                        
                        Instances.BundleMob.get(i).update();
                        this.Meccanic.EnvironmentAction(Instances.BundleMob,Instances.player);
                        this.Meccanic.isMobInteresect(Instances.BundleMob);
                        
                    }
                 
             } catch (MalformedURLException ex) { } catch (IOException ex) {
            Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InputErrorException ex) {
            Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
        }
             try {
                 Instances.drawing.render();
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
                Instances.drawing.setFrameVisible(false);
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
