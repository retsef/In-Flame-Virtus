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
    
    private int sleep = 20;
    private Thread thread;
    private Meccanics Meccanic;
    private boolean isStart;
    public static final int MAXMob = 10;
    
    public static final int WIDTH = 800;
    public static final int HEIGTH = 600;
    
    public static Draw drawing;
    public static ArrayList<Mob> BundleMob;
    public static Player player;
    
    
    public Game() throws ValueErrorException, InputErrorException, IOException{
        this("Guy");
    }
    
    public Game(String pNamePlayer) throws ValueErrorException, InputErrorException, IOException{
        
        Game.player = new Player(pNamePlayer);
        Game.drawing = new Draw(Game.WIDTH,Game.HEIGTH,"Shot them all!");
        
        Game.BundleMob = new ArrayList<>();
        for (int i = 0; i < Game.MAXMob; i++){
            Game.BundleMob.add(new Mob());
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
                 Game.drawing.setFrameVisible(true);
                 Game.player.update();
                 
                 for(int i = 0; i < Game.BundleMob.size(); i++)
                    {
                        
                        Game.BundleMob.get(i).update();
                        this.Meccanic.EnvironmentAction();
                        this.Meccanic.isMobInteresect();
                        
                    }
                 
             } catch (MalformedURLException ex) { } catch (IOException ex) {
            Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InputErrorException ex) {
            Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
        }
             try {
                 Game.drawing.render();
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
                Game.drawing.setFrameVisible(false);
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
