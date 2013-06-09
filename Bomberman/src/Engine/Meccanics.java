package Engine;

import java.io.IOException;

public class Meccanics {
    
    public Meccanics(){
    }
    
    public void isMobInteresect() {
        for (int e=0;e==Game.BundleMob.size();e++){
            for (int p=0;p==Game.BundleMob.size();p++){
                
                if(Game.BundleMob.get(e).Body.intersects(Game.BundleMob.get(p).Body)){
                    System.out.println("p="+p+" stopped from e="+e);
                    Game.BundleMob.get(e).setMoving(false);
                } else {
                    Game.BundleMob.get(e).setMoving(true);
                }
            }     
        }
    }
    
    public void EnvironmentAction() throws IOException, InputErrorException {
        for (int h=0;h<Game.BundleMob.size();h++){
            Game.player.DamageMob(Game.BundleMob.get(h));
            Game.player.getDamage(Game.BundleMob.get(h));
        }
    }
    
}
