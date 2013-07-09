package Engine;

import GameObject.Mob;
import GameObject.Player;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Meccanics implements Runnable{
    
    private int e,p,h;
    private Thread thread;
    private boolean isStart = false;
    
    public Meccanics(){
    }
    
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
    
    public void EnvironmentAction(ArrayList<Mob> pMobClan, Player pPlayer) throws IOException, InputErrorException {
        for (h=0;h<pMobClan.size();h++){
            pPlayer.Damage_a_Mob(pMobClan.get(h));
            pPlayer.get_Damage_from(pMobClan.get(h));
        }
    }
    
    public void Environment_player(Player pPlayer){
        if(pPlayer.get_Glove().getAttack()){
            System.out.println(pPlayer.get_Glove().get_count_Attack());
           for(int count=0;count<pPlayer.get_Glove().get_count_Attack();count++){
              pPlayer.BundleGlove.add(pPlayer.get_Glove());
              pPlayer.get_Glove().set_if_Fireball_moved(false);
           }
       }
    }
    
    public void Environment_playeraction(Player pPlayer){
        System.out.println(pPlayer.BundleGlove.size());
       for (int g=0; g < pPlayer.BundleGlove.size(); g++){
           pPlayer.BundleGlove.get(g).update();
           if(!pPlayer.BundleGlove.get(g).get_if_Fireball_moved()){
               pPlayer.BundleGlove.remove(g);
               pPlayer.get_Glove().set_count_Attack(pPlayer.get_Glove().get_count_Attack()-1);
           }
       }
    }

    @Override
    public void run() {
        try {
            Instances.game.suspend();
            //this.isMobInteresect(Instances.BundleMob);
            this.EnvironmentAction(Instances.BundleMob, Instances.player);
            this.Environment_player(Instances.player);
            this.Environment_playeraction(Instances.player);
            Instances.game.resume();
            } catch (    IOException | InputErrorException ex) {
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
