package Engine;

import Enviroment.Instances;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class ButtonHandler extends KeyAdapter {

    public ButtonHandler() {        
    }
    
    //This function will be used as soon as a key is released.
    @Override
    public void keyPressed(KeyEvent key) {
              switch (key.getKeyCode()) {
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
              }
    }   
    
    //This function will be used as soon as a key is released. they KeyEvent key we can use to determine what key we just released
    @Override
    public void keyReleased(KeyEvent key) {
        switch (key.getKeyCode()) {
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
        }
    }
    
    @Override
    public void keyTyped(KeyEvent key) { }
}