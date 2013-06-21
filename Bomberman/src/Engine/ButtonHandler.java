package Engine;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class ButtonHandler extends KeyAdapter {
    
    public ButtonHandler() {
    }
    
    //This function will be used as soon as a key is released.
    @Override
    public void keyPressed(KeyEvent pkey) {
        switch (pkey.getKeyCode()) {
           //if you use arrowkey to move
           case KeyEvent.VK_UP:
               Instances.player.setUp(true);
               break;
           case KeyEvent.VK_DOWN:
               Instances.player.setDown(true);
               break;
           case KeyEvent.VK_LEFT:
               Instances.player.setLeft(true);
               break;
           case KeyEvent.VK_RIGHT:
               Instances.player.setRight(true);
               break;
           //if you use WASD to move
           case KeyEvent.VK_W:
               Instances.player.setUp(true);
               break;
           case KeyEvent.VK_S:
               Instances.player.setDown(true);
               break;
           case KeyEvent.VK_A:
               Instances.player.setLeft(true);
               break;
           case KeyEvent.VK_D:
               Instances.player.setRight(true);
               break;
           case KeyEvent.VK_ESCAPE: //esc button
               Instances.game.stop();
               break;
         }
    }   
    
    //This function will be used as soon as a key is released. they KeyEvent key we can use to determine what key we just released
    @Override
    public void keyReleased(KeyEvent pkey) {
        switch (pkey.getKeyCode()) {
           case KeyEvent.VK_UP:
               Instances.player.setUp(false);
               break;
           case KeyEvent.VK_DOWN:
               Instances.player.setDown(false);
               break;
           case KeyEvent.VK_LEFT:
               Instances.player.setLeft(false);
               break;
           case KeyEvent.VK_RIGHT:
               Instances.player.setRight(false);
               break;
           //if you use WASD to move
           case KeyEvent.VK_W:
               Instances.player.setUp(false);
               break;
           case KeyEvent.VK_S:
               Instances.player.setDown(false);
               break;
           case KeyEvent.VK_A:
               Instances.player.setLeft(false);
               break;
           case KeyEvent.VK_D:
               Instances.player.setRight(false);
               break;
           case KeyEvent.VK_ESCAPE: //esc button
               Instances.game.stop();
               break;
         }
     }
}