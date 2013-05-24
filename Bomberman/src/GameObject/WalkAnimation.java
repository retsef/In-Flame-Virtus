package GameObject;

import Engine.Game;
import Engine.InputErrorException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Roberto
 */
public class WalkAnimation implements Runnable {

    public WalkAnimation(){
    }
    
    @Override
    public void run() {
         while(true){
             try {
                 try {
                     Game.player.Walk();
                 } catch (InputErrorException | IOException ex) {
                     Logger.getLogger(WalkAnimation.class.getName()).log(Level.SEVERE, null, ex);
                 }
                 Thread.sleep(5000);
             } catch (InterruptedException ex) {
                 Logger.getLogger(WalkAnimation.class.getName()).log(Level.SEVERE, null, ex);
             }
         
         }           
    }
    
}
