package Engine;

import java.awt.event.*;

public class MouseHandler extends MouseAdapter{
    
    public MouseHandler() {
    }

    @Override
    public void mousePressed(MouseEvent e) {
        switch (e.getButton()) {
            case MouseEvent.BUTTON1:
                Instances.player.get_Glove().setAttack(true);
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        switch (e.getButton()) {
            case MouseEvent.BUTTON1:
                Instances.player.get_Glove().setAttack(false);
        }
    }
    
    

    @Override
    public void mouseMoved(MouseEvent e) {
        Instances.player.get_Glove().setTarget(e.getPoint());
    }
    
    
    
    
}
