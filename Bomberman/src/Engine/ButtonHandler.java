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
               Game.player.setUp(true);
               break;
           case KeyEvent.VK_DOWN:
               Game.player.setDown(true);
               break;
           case KeyEvent.VK_LEFT:
               Game.player.setLeft(true);
               break;
           case KeyEvent.VK_RIGHT:
               Game.player.setRight(true);
               break;
           //if you use WASD to move
           case KeyEvent.VK_W:
               Game.player.setUp(true);
               break;
           case KeyEvent.VK_S:
               Game.player.setDown(true);
               break;
           case KeyEvent.VK_A:
               Game.player.setLeft(true);
               break;
           case KeyEvent.VK_D:
               Game.player.setRight(true);
               break;
         }
    }   
    
    //This function will be used as soon as a key is released. they KeyEvent key we can use to determine what key we just released
    @Override
    public void keyReleased(KeyEvent pkey) {
        switch (pkey.getKeyCode()) {
           case KeyEvent.VK_UP:
               Game.player.setUp(false);
               break;
           case KeyEvent.VK_DOWN:
               Game.player.setDown(false);
               break;
           case KeyEvent.VK_LEFT:
               Game.player.setLeft(false);
               break;
           case KeyEvent.VK_RIGHT:
               Game.player.setRight(false);
               break;
           //if you use WASD to move
           case KeyEvent.VK_W:
               Game.player.setUp(false);
               break;
           case KeyEvent.VK_S:
               Game.player.setDown(false);
               break;
           case KeyEvent.VK_A:
               Game.player.setLeft(false);
               break;
           case KeyEvent.VK_D:
               Game.player.setRight(false);
               break;
         }
     }
}