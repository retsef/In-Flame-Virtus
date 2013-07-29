package Engine;

import Utils.SpriteException;
import GameObject.Mob;
import GameObject.Player;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * La Classe e' addetta al controllo delle regole e delle meccaniche di gioco
 * Essa ferma momentaneamente il gioco per esaminare che le regole base(o meccaniche) vengano rispettate e processate
 * @author roberto
 */

public class Meccanics implements Runnable{
    
    private int e,p,h;
    private Thread thread;
    private boolean isStart = false;
    /**
     * Crea un tipo Meccanics
     */
    public Meccanics(){
    }
    /**
     * Il metodo impedisce l'intersecarsi dei Mob
     * @param pMobClan Insieme di Mob nemici
     */
    public void isMobInteresect(ArrayList<Mob> pMobClan) {
        for (e=0;e<pMobClan.size();e++){
            for (p=0;p<pMobClan.size();p++){
                if (e==p){}
                else if(pMobClan.get(e).getBody().intersects(pMobClan.get(p).getBody())){
                    pMobClan.get(e).setMoving(false);
                } else {
                    pMobClan.get(e).setMoving(true);
                }
            }     
        }
    }
    
    /**
     * Il metodo controlla gli eventi dell'Environment
     * Qui troviamo i seguenti controlli:
     * -Il caso in cui un Mob ci attacca
     * -Il caso in cui noi attacchiamo un Mob
     * @param pMobClan Insieme di Mob nemici
     * @param pPlayer Il giocatore principale
     * @throws SpriteException Restituisce un Exception nel caso in cui ci fossero errori con gli sprite
     */
    public void EnvironmentAction(ArrayList<Mob> pMobClan, Player pPlayer) throws SpriteException {
        for (h=0;h<pMobClan.size();h++){
            pPlayer.Damage_a_Mob(pMobClan.get(h));
            pPlayer.get_Damage_from(pMobClan.get(h));
        }
    }
    
    /**
     * Il metodo controlla gli eventi correlati all'arma del Player
     * @param pPlayer Il giocatore principale
     *//*
    public void Environment_player(Player pPlayer){
        if(pPlayer.get_Glove().getAttack()){
            //System.out.println(pPlayer.get_Glove().get_count_Attack());
           for(int count=0;count<pPlayer.get_Glove().get_count_Attack();count++){
              pPlayer.BundleGlove.add(pPlayer.get_Glove());
              pPlayer.get_Glove().set_if_Fireball_moved(false);
           }
       }
    }
    */
    /**
     * Il metodo controlla gli eventi correlati al Player tra cui il movimento e l'attacco
     * @param pPlayer Il giocatore principale
     *//*
    public void Environment_playeraction(Player pPlayer){
        //System.out.println(pPlayer.BundleGlove.size());
       for (int g=0; g < pPlayer.BundleGlove.size(); g++){
           pPlayer.BundleGlove.get(g).update();
           if(!pPlayer.BundleGlove.get(g).get_if_Fireball_moved()){
               pPlayer.BundleGlove.remove(g);
               pPlayer.get_Glove().set_count_Attack(pPlayer.get_Glove().get_count_Attack()-1);
           }
       }
    }*/

    @Override
    public void run() {
        try {
            Instances.game.suspend();
            this.isMobInteresect(Game.get_Bundle_Mob());
            this.EnvironmentAction(Game.get_Bundle_Mob(), Game.get_player());
            //this.Environment_player(Game.get_player());
            //this.Environment_playeraction(Game.get_player());
            Instances.game.resume();
            } catch ( SpriteException ex) {
                Logger.getLogger(Meccanics.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
        /**
         * @start Avvia il thread
         */
        public void start() {
                this.thread = new Thread(this,"Meccanics");
                this.thread.start();
                this.iStart(true);
        }

        /**
         * @stop Ferma il thread
         */
        public void stop() {
            if (this.thread != null && this.thread.isAlive()) {
                this.thread.interrupt();
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
        
        public void iStart(boolean pStart){
            this.isStart = pStart;
        }
        
        public boolean getisStart(){
            return this.isStart;
        }
}
