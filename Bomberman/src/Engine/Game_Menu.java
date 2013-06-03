package Engine;

import GameObject.ValueErrorException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

/**
 *
 * @author Roberto
 */
public class Game_Menu implements ActionListener{
    private int WIDTH,HEIGHT;
    private JFrame Frame;
    private JPanel Panel;
    private JButton Start;
    private Game game; 
    
    public Game_Menu(){
        this(600,800);
    }
    
    public Game_Menu(int pWidth, int pHeight){
        this.WIDTH = pWidth;
        this.HEIGHT = pHeight;
        this.Frame = new JFrame("GAME MENU'");
        this.Panel = new JPanel(null);
        this.Frame.setVisible(true);
        this.Frame.setSize(this.HEIGHT,this.WIDTH);
        this.Panel = (JPanel) this.Frame.getContentPane();
        this.Frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.Panel.setLayout(null);
        this.Start = new JButton("Start");
        this.Panel.add(this.Start);
        this.Start.setBounds(20, 20, 100, 50);
        
        this.Start.addActionListener(this);
        try {
            this.game = new Game();
        } catch (    ValueErrorException | InputErrorException | IOException ex) {
            Logger.getLogger(Game_Menu.class.getName()).log(Level.SEVERE, null, ex);
        }
         
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        new Thread(this.game).start();
        this.Frame.setVisible(false);
    }
}
