package Engine.MenuScreen;

import Engine.Game;
import Engine.InputErrorException;
import GameObject.ValueErrorException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.*;

/**
 *
 * @author Roberto
 */
public class Game_Menu {
    private int WIDTH,HEIGHT;
    private JFrame Frame;
    private Game game;
    private Menu_Panel Menu;
    private HowToPlay_Panel howToPlay;
    
    public Game_Menu(){
        this(700,1240);
    }
    
    public Game_Menu(int pWidth, int pHeight){
        this.WIDTH = pWidth;
        this.HEIGHT = pHeight;
        this.Frame = new JFrame("MENU'");
        this.Menu = new Menu_Panel();
        this.howToPlay = new HowToPlay_Panel();
        this.Frame.setVisible(false);
        this.Frame.setSize(this.HEIGHT,this.WIDTH);
        this.Frame.setResizable(false);
        this.Frame.add(this.Menu);
        this.Frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.Frame.setLocationRelativeTo(null);
        this.Frame.setBackground(null);

        try {
            this.game = new Game();
        } catch (    ValueErrorException | InputErrorException | IOException ex) {
            Logger.getLogger(Game_Menu.class.getName()).log(Level.SEVERE, null, ex);
        }
         
    }
    
        public void setFrameVisible(boolean b){
           if (b==true)
               Frame.setVisible(true);
           else
               Frame.setVisible(false);
        }
    
        
        private class Menu_Panel extends JPanel {

            private StartButton start;
            private HowToPlayButton HowtoPlay;
            private BufferedImage background;
            private JLabel backgroundl;

            public Menu_Panel() {
                setLayout(null);
                this.start = new StartButton();
                this.HowtoPlay = new HowToPlayButton();
                add(this.start);
                add(this.HowtoPlay);
                this.start.setBounds(20, 20, 100, 50);
                this.HowtoPlay.setBounds(650,500,100,50);
                try {
                    this.background = ImageIO.read(getClass().getResource("/images/notebook.png"));
                } catch (IOException ex) {
                    Logger.getLogger(Game_Menu.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                this.backgroundl = new JLabel(new ImageIcon(this.background));
                    this.backgroundl.setBounds(0, 0, this.background.getWidth(), this.background.getHeight());
                    add(this.backgroundl);
            }

            private class StartButton extends JButton implements ActionListener{
                
                public StartButton(){
                    this("Start");
                }

                public StartButton(String pTitle){
                    setText(pTitle);
                    addActionListener(this);
                }

                @Override
                public void actionPerformed(ActionEvent e) {
                    game.start();
                    Frame.setVisible(false);
                    System.gc();
                }

            }
            
            private class HowToPlayButton extends JButton implements ActionListener{

                public HowToPlayButton(){
                    this("How to Play");
                }

                public HowToPlayButton(String pTitle){
                    setText(pTitle);
                    addActionListener(this);
                }

                @Override
                public void actionPerformed(ActionEvent e) {
                    Frame.remove(Menu);
                    Frame.dispose();
                    Frame.add(howToPlay);
                    Frame.setVisible(true);
                }

            }
            
        }
        
        private class HowToPlay_Panel extends JPanel{
            
            private BackButton back;
            private BufferedImage background,note_arrow,note_wasd,scotch_note1,scotch_note2; 
            private JLabel backgroundl,note_wasdl,note_arrowl,scotch_note1l,scotch_note2l;
            private InfoButton info;
            
            public HowToPlay_Panel() {
                setLayout(null);
                setBackground(null);
                this.back = new BackButton();
                this.info = new InfoButton();
                this.info.setBounds(650,500,100,50);
                this.back.setBounds(20,20,100,50);
                add(this.back);
                add(this.info);
                try {
                    this.background = ImageIO.read(getClass().getResource("/images/notebook.png"));
                    this.note_wasd = ImageIO.read(getClass().getResource("/images/note_wasd.png"));
                    this.note_arrow = ImageIO.read(getClass().getResource("/images/note_arrow.png"));
                    this.scotch_note1 = ImageIO.read(getClass().getResource("/images/scotch_note.png"));
                    this.scotch_note2 = ImageIO.read(getClass().getResource("/images/scotch_note.png"));
                    
                } catch (IOException ex) {
                    Logger.getLogger(Game_Menu.class.getName()).log(Level.SEVERE, null, ex);
                }
                    
                    this.scotch_note1l = new JLabel(new ImageIcon(this.scotch_note1));
                    this.scotch_note1l.setBounds(250,110, this.scotch_note1.getWidth(), this.scotch_note1.getHeight());
                    add(this.scotch_note1l);
                
                    this.note_wasdl = new JLabel(new ImageIcon(this.note_wasd));
                    this.note_wasdl.setBounds(220,120, this.note_wasd.getWidth(), this.note_wasd.getHeight());
                    add(this.note_wasdl);
                
                    this.scotch_note2l = new JLabel(new ImageIcon(this.scotch_note2));
                    this.scotch_note2l.setBounds(250,390, this.scotch_note2.getWidth(), this.scotch_note2.getHeight());
                    add(this.scotch_note2l);
                
                    this.note_arrowl = new JLabel(new ImageIcon(this.note_arrow));
                    this.note_arrowl.setBounds(250,400, this.note_arrow.getWidth(), this.note_arrow.getHeight());
                    add(this.note_arrowl);
                    
                    this.backgroundl = new JLabel(new ImageIcon(this.background));
                    this.backgroundl.setBounds(0, 0, this.background.getWidth(), this.background.getHeight());
                    add(this.backgroundl);
                
            }
            
            private class BackButton extends JButton implements ActionListener{

                public BackButton(){
                    this("Back");
                }

                public BackButton(String pTitle){
                    setText(pTitle);
                    addActionListener(this);
                }

                @Override
                public void actionPerformed(ActionEvent e) {
                    Frame.remove(howToPlay);
                    Frame.dispose();
                    Frame.add(Menu);
                    Frame.setVisible(true);
                }
            
        }
            
            private class InfoButton extends JButton implements ActionListener{

                public InfoButton(){
                    this("Info");
                }

                public InfoButton(String pTitle){
                    setText(pTitle);
                    addActionListener(this);
                }

                @Override
                public void actionPerformed(ActionEvent e) {
                    System.out.println("pWork in progress");
                }

            }
        }
}
