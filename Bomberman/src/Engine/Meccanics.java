package Engine;

import GameObject.Mob;
import GameObject.Player;
import java.io.IOException;
import java.util.ArrayList;

public class Meccanics {
    
    private int e,p,h;
    
    public Meccanics(){
    }
    
    public void isMobInteresect(ArrayList<Mob> pMobClan) {
            for (p=0;p==pMobClan.size();p++){
                
                if(pMobClan.get(e).Body.intersects(pMobClan.get(p).Body)){
                    System.out.println("p="+p+" stopped from e="+e);
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
    
}
