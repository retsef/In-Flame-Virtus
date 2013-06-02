package Engine;

import java.awt.event.*;

public class MouseHandler extends MouseAdapter{
    
    public MouseHandler() {
    }

    @Override
    public void mouseClicked(MouseEvent pMouse) {
        switch (pMouse.getButton()) {
            case MouseEvent.BUTTON1:
                Game.player.Gun.setAttack(true);
                System.out.println("Attack true");
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
                Game.player.Gun.setAttack(false);
                break;
        }
    }
    /*
    public void mousegetX(MouseEvent pMouse) {
        Game.player.Gun.setX(pMouse.getX());
        System.out.print(pMouse.getX());
    }
    
    public void mousegetY(MouseEvent pMouse) {
        Game.player.Gun.setY(pMouse.getY());
        System.out.print(pMouse.getY());
    }
    */
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
        Game.player.Gun.setX(pMouse.getX());
        Game.player.Gun.setY(pMouse.getY());
    }
    
    
    
    
}
