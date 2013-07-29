package Engine;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
/**
 * La classe gestisce l'iterazione dei tasti della tastiera
 * Funge da tramite tra l'utente che preme il tasto e il Player
 * @author roberto
 */
public class ButtonHandler extends KeyAdapter {
    /**
     * Costutto del ButtonHandler
     */
    public ButtonHandler() {
    }
    /**
     * Gestisce i pulsanti premuti
     * @param pkey Evento di tipo key
     */
    //This function will be used as soon as a key is released.
    @Override
    public void keyPressed(KeyEvent pkey) {
        switch (pkey.getKeyCode()) {
           //if you use WASD to move
           case KeyEvent.VK_W:
               Game.get_player().setUp(true);
               break;
           case KeyEvent.VK_S:
               Game.get_player().setDown(true);
               break;
           case KeyEvent.VK_A:
               Game.get_player().setLeft(true);
               break;
           case KeyEvent.VK_D:
               Game.get_player().setRight(true);
               break;
           case KeyEvent.VK_ESCAPE: //esc button
               System.out.println("ESC pressed");
               Instances.game.stop();
               break;
         }
    }   
    /**
     * Gestisce i pulsanti rilasciati
     * @param pkey Evento di tipo key
     */
    //This function will be used as soon as a key is released. they KeyEvent key we can use to determine what key we just released
    @Override
    public void keyReleased(KeyEvent pkey) {
        switch (pkey.getKeyCode()) {
           //if you use WASD to move
           case KeyEvent.VK_W:
               Game.get_player().setUp(false);
               break;
           case KeyEvent.VK_S:
               Game.get_player().setDown(false);
               break;
           case KeyEvent.VK_A:
               Game.get_player().setLeft(false);
               break;
           case KeyEvent.VK_D:
               Game.get_player().setRight(false);
               break;
         }
     }
}