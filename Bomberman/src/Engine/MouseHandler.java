package Engine;

import java.awt.event.*;
/**
 * La classe gestisce l'iterazione del mouse
 * Funge da tramite tra l'utente che muove il mouse e il Player
 * @author roberto
 */
public class MouseHandler extends MouseAdapter{
    /**
     * Costrutto del MouseHandler
     */
    public MouseHandler() {
    }

    /**
     * Gestisce i pulsanti del mouse alla pressione
     * @param e Evento di tiop Mouse
     */
    @Override
    public void mousePressed(MouseEvent e) {
        switch (e.getButton()) {
            case MouseEvent.BUTTON1:
                Game.get_player().get_Glove().setAttack(true);
        }
    }

    /**
     * Gestisce i pulsanti del mouse al rilascio
     * @param e Evento di tiop Mouse
     */
    @Override
    public void mouseReleased(MouseEvent e) {
        switch (e.getButton()) {
            case MouseEvent.BUTTON1:
                Game.get_player().get_Glove().setAttack(false);
        }
    }

    /**
     * Aggiorna la posizione del puntatore
     * @param e Evento di tiop Mouse
     */
    @Override
    public void mouseMoved(MouseEvent e) {
        Game.get_player().get_Glove().setTarget(e.getPoint());
    }
    /*
    @Override
    public void mouseClicked(MouseEvent e) {
        Game.get_player().get_Glove().set_count_Attack(e.getClickCount());
    }
    */
}
