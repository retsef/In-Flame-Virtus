package Engine.MenuScreen;

import Engine.Game;
import Engine.InputErrorException;
import Engine.Instances;
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
            Instances.game = new Game();
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
            private BufferedImage background,title;
            private JLabel background_label,title_label;

            public Menu_Panel() {
                setLayout(null);
                this.start = new StartButton();
                this.HowtoPlay = new HowToPlayButton();
                add(this.start);
                add(this.HowtoPlay);
                this.start.setBounds(20, 20, 200, 200);
                this.HowtoPlay.setBounds(960,480,300,200);
                try {
                    this.background = ImageIO.read(getClass().getResource("/images/Actor_background.jpg"));
                    this.title = ImageIO.read(getClass().getResource("/images/Game_title.png"));
                } catch (IOException ex) {
                    Logger.getLogger(Game_Menu.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                this.HowtoPlay.setIcon(new ImageIcon(getClass().getResource("/images/book.png")) );
                this.HowtoPlay.setBorder(null);
                this.HowtoPlay.setContentAreaFilled(false);
                
                this.start.setIcon(new ImageIcon(getClass().getResource("/images/Fireball.png")) );
                this.start.setBorder(null);
                this.start.setContentAreaFilled(false);
                
                this.title_label = new JLabel(new ImageIcon(this.title));
                this.title_label.setBounds(0, 0, this.title.getWidth(), this.title.getHeight());
                add(this.title_label);
                
                this.background_label = new JLabel(new ImageIcon(this.background));
                this.background_label.setBounds(0, 0, this.background.getWidth(), this.background.getHeight());
                add(this.background_label);
                
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
                    Instances.game.start();
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
            private BufferedImage background; 
            private JLabel background_label;
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
                    this.background = ImageIO.read(getClass().getResource("/images/notebook_guide.png"));
                    
                } catch (IOException ex) {
                    Logger.getLogger(Game_Menu.class.getName()).log(Level.SEVERE, null, ex);
                }
                    
                    this.background_label = new JLabel(new ImageIcon(this.background));
                    this.background_label.setBounds(0, 0, this.background.getWidth(), this.background.getHeight());
                    add(this.background_label);
                
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
