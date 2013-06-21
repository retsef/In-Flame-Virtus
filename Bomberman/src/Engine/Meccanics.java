package Engine;

import GameObject.Mob;
import GameObject.Player;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Meccanics implements Runnable{
    
    private int e,p,h;
    
    public Meccanics(){
    }
    
    public void isMobInteresect(ArrayList<Mob> pMobClan) {
            for (p=0;p==pMobClan.size();p++){
                if(pMobClan.get(e).Body.intersects(pMobClan.get(p).Body)){
                    pMobClan.get(e).setMoving(false);
                } else {
                    pMobClan.get(e).setMoving(true);
                }
            }     
        }
    
    public void EnvironmentAction(ArrayList<Mob> pMobClan, Player pPlayer) throws IOException, InputErrorException {
        for (h=0;h<pMobClan.size();h++){
            pPlayer.Damage_a_Mob(pMobClan.get(h));
            pPlayer.get_Damage_from(pMobClan.get(h));
        }
    }

    @Override
    public void run() {
            try {
                Instances.game.suspend();
                this.isMobInteresect(Instances.BundleMob);
                this.EnvironmentAction(Instances.BundleMob, Instances.player);
                Instances.game.resume();
                } catch (    IOException | InputErrorException ex) {
                    Logger.getLogger(Meccanics.class.getName()).log(Level.SEVERE, null, ex);}
    }
    
}
