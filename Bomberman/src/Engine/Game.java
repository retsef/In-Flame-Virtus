package Engine;

import Engine.MenuScreen.Loading;
import Utils.SpriteException;
import GameObject.*;
import Utils.SoundException;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 * 
 * Inizializza il gioco e tutte le sue componenti
 * @author Roberto
 */

public class Game implements Runnable{
    
    /**
     * {@value} MAXMob Numero di Mob sullo schermo
     * {@value} WIDTH Larghezza della finestra di gioco
     * {@value} HEIGTH Altezza della finestra di gioco
     */
    private int sleep = 20;
    private Meccanics Meccanic;
    private boolean isStart,GameOver;
    public static final int MAXMob = 20;
    private Thread thread;
    public static final int WIDTH = 1240;
    public static final int HEIGTH = 700;
    
    private static Draw drawing;
    private static ArrayList<Mob> BundleMob;
    private static Player player;
    
    /**
     * Crea un tipo Game predefinito
     * @throws ValueErrorException Restituisce un Exception nel caso in cui valori degli elementi del gioco sono errati
     * @throws SpriteException Restituisce un Exception nel caso in cui gli sprite sono errati
     * @throws SoundException Restituisce un Exception nel caso in cui i suoni sono errati
     */
    public Game() throws ValueErrorException, SpriteException, SoundException{
        this("Guy");
    }
    /**
     * Crea un tipo Game
     * Esso contiene il gioco di per se'
     * @param pNamePlayer Nome del Player
     * @throws ValueErrorException Restituisce un Exception nel caso in cui valori degli elementi del gioco sono errati
     * @throws SpriteException Restituisce un Exception nel caso in cui gli sprite sono errati
     * @throws SoundException Restituisce un Exception nel caso in cui i suoni sono errati
     */
    public Game(String pNamePlayer) throws ValueErrorException, SpriteException, SoundException{
        this.GameOver = false;
        player = new Player(pNamePlayer);
        drawing = new Draw(Game.WIDTH,Game.HEIGTH,"In Flame Virtus");
        
        BundleMob = new ArrayList<>();
        for (int i = 0; i < this.MAXMob; i++){
            BundleMob.add(new Mob());
        }
        
        this.isStart = false;
        this.Meccanic = new Meccanics();
    }
    /**
     * Estrapola un elemento di tipo Meccanics
     * @return Un Meccanics
     */
    public Meccanics get_meccanics(){
        return this.Meccanic;
    }
    /**
     * Estrapola un elemento di tipo Draw
     * @return Un Draw
     */
    public static Draw get_draw(){
        return drawing;
    }
    /**
     * Estrapola un ArrayList di Mob
     * @return Un ArrayList di Mob
     */
    public static ArrayList<Mob> get_Bundle_Mob(){
        return BundleMob;
    }
    /**
     * Estrapola un elemento di tipo Player
     * @return Un Player
     */
    public static Player get_player(){
        return player;
    }
    /**
     * Impone lo stato di GameOver e la fine del gioco
     * @param pset Boolean per settare l'esito
     */
    public void setGameOver(boolean pset){
        this.GameOver = pset;
    }
    
    /**
     * @run Esegue il gioco (parte attiva)
     */
    @Override
    public void run(){
    while(true){
        try {
                 drawing.setFrameVisible(true);
                 player.update();
                 
                 for(int i = 0; i < BundleMob.size(); i++)
                    {
                        BundleMob.get(i).update();
                    }
                 
                 drawing.render();
                 
                    synchronized(this) { //monitor per la messa in attesa del thread
                       while(!this.isStart) {
                               wait();
                           }
                    }
                 if(this.GameOver){
                     this.stop();
                 }
                 Thread.sleep(this.sleep);
                 
        }catch (InterruptedException | IOException ex) { } }
    }
    
        /**
         * @start Avvia il thread del gioco
         */
        public void start(){
            stop();
            this.thread = new Thread(this,"Game");
            this.thread.start();
            this.iStart(true);
        }

        /**
         * @stop Ferma il thread del gioco
         */
        public void stop(){
            if (this.thread != null && this.thread.isAlive()) {
                this.Meccanic.stop();
                this.iStart(false);
                drawing.setFrameVisible(false);
                JOptionPane.showMessageDialog(null, get_player().getName() + " ha totalizzato " + get_player().getScore() +" punti :D");
                Loading.menu.setFrameVisible(true);
                this.thread.stop();
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
    
        /**
         * Setta lo stato del thread
         * @param pStart Boolean di esito
         */
        public void iStart(boolean pStart){
            this.isStart = pStart;
        }
        
        /**
         * Restituisce lo stato del thread
         * @return Boolean di esito
         */
        public boolean getisStart(){
            return this.isStart;
        }
   }
