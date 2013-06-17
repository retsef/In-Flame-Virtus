package Engine;

import java.awt.event.*;

public class MouseHandler extends MouseAdapter{
    
    public MouseHandler() {
    }

    @Override
    public void mouseClicked(MouseEvent pMouse) {
        switch (pMouse.getButton()) {
            case MouseEvent.BUTTON1:
                Instances.player.Gun.setAttack(true);
                break;
        }
    }
    
    //this function set fire action to the wapon when mouse button is pressed
    @Override
    public void mousePressed(MouseEvent pMouse) {
        this.mouseClicked(pMouse);
    }

    //that relase the fire action
    @Override
    public void mouseReleased(MouseEvent pMouse) {
        switch (pMouse.getButton()) {
            case MouseEvent.BUTTON1:
                Instances.player.Gun.setAttack(false);
                break;
        }
    }
    
    @Override
    public void mouseEntered(MouseEvent pMouse) {
        super.mouseEntered(pMouse); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        mouseMoved(e); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseMoved(MouseEvent pMouse) {
        Instances.player.Gun.setTarget(pMouse.getPoint());
    }
    
    
    
    
}
