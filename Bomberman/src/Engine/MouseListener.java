package Engine;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MouseListener extends MouseAdapter{
    
    public MouseListener() {
    }

    @Override
    public void mouseClicked(MouseEvent pMouse) {
        switch (pMouse.getButton()) {
            case MouseEvent.BUTTON1:
                Game.player.Gun.setAttack(true);
        }
    }

    //this function set fire action to the wapon when mouse button is pressed
    @Override
    public void mousePressed(MouseEvent pMouse) {
        switch (pMouse.getButton()) {
            case MouseEvent.BUTTON1:
                Game.player.Gun.setAttack(true);
        }
    }

    //that relase the fire action
    @Override
    public void mouseReleased(MouseEvent pMouse) {
        switch (pMouse.getButton()) {
            case MouseEvent.BUTTON1:
                Game.player.Gun.setAttack(false);
        }
    }
    
    public void mousegetX(MouseEvent pMouse) {
        Game.player.Gun.setX(pMouse.getX());
    }
    
    public void mousegetY(MouseEvent pMouse) {
        Game.player.Gun.setY(pMouse.getY());
    }

    @Override
    public void mouseEntered(MouseEvent pMouse) {
        super.mouseEntered(pMouse); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
