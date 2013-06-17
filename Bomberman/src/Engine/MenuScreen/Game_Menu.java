package Engine.MenuScreen;

import Engine.Game;
import Engine.InputErrorException;
import GameObject.ValueErrorException;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

/**
 *
 * @author Roberto
 */
public class Game_Menu {
    private int WIDTH,HEIGHT;
    private JFrame Frame;
    private Game game;
    private MenuPanel Menu;
    private SettingsPanel Settings;
    private HowToPlay howToPlay;
    
    public Game_Menu(){
        this(600,800);
    }
    
    public Game_Menu(int pWidth, int pHeight){
        this.WIDTH = pWidth;
        this.HEIGHT = pHeight;
        this.Frame = new JFrame("MENU'");
        this.Menu = new MenuPanel();
        this.Settings = new SettingsPanel();
        this.howToPlay = new HowToPlay();
        this.Frame.setVisible(false);
        this.Frame.setSize(this.HEIGHT,this.WIDTH);
        this.Frame.setResizable(false);
        this.Frame.add(this.Menu);
        this.Frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.Frame.setLocationRelativeTo(null);

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
    
        
        private class MenuPanel extends JPanel {

            private StartButton start;
            private SettingsButton settings;

            public MenuPanel() {
                setLayout(null);
                this.start = new StartButton();
                this.settings = new SettingsButton();
                add(this.start);
                add(this.settings);
                this.start.setBounds(20, 20, 100, 50);
                this.settings.setBounds(650,500,100,50);
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

            private class SettingsButton extends JButton implements ActionListener{
                
                public SettingsButton(){
                    this("Settings");
                }

                public SettingsButton(String pTitle){
                    setText(pTitle);
                    addActionListener(this);
                }

                @Override
                public void actionPerformed(ActionEvent e) {
                    Frame.remove(Menu);
                    Frame.add(Settings);
                    
                    Frame.dispose();
                    Frame.setVisible(true);
                }

            }
            
        }
        
        private class SettingsPanel extends JPanel{
            
            private InfoButton info;
            private BackButton back;
            private HowToPlayButton Howtoplay;

            public SettingsPanel() {
                setLayout(null);
                this.info = new InfoButton();
                this.back = new BackButton();
                this.Howtoplay = new HowToPlayButton();
                add(this.info);
                add(this.back);
                add(this.Howtoplay);
                this.back.setBounds(20, 20, 100, 50);
                this.info.setBounds(650,500,100,50);
                this.Howtoplay.setBounds(510, 500, 120, 50);
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
                    Frame.remove(Settings);
                    Frame.dispose();
                    Frame.add(howToPlay);
                    Frame.setVisible(true);
                }

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
                    Frame.remove(Settings);
                    Frame.dispose();
                    Frame.add(Menu);
                    Frame.setVisible(true);
                }

            }
        }
        
        private class HowToPlay extends JPanel{
            
            private BackButton back;
            private Image W,S,A,D,up,down,left,right; 
            
            public HowToPlay(){
                setLayout(null);
                this.back = new BackButton();
                add(this.back);
                this.back.setBounds(20, 20, 100, 50);
                
                this.W = new ImageIcon("/images/w.png").getImage();
                this.A = new ImageIcon("/images/a.png").getImage();
                this.S = new ImageIcon("/images/s.png").getImage();
                this.D = new ImageIcon("/images/d.png").getImage();
                this.up = new ImageIcon("/images/up_key.png").getImage();
                this.down = new ImageIcon("/images/down_key.png").getImage();
                this.left = new ImageIcon("/images/left_key.png").getImage();
                this.right = new ImageIcon("/images/right_key.png").getImage();
                
                
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
                    Frame.add(Settings);
                    Frame.setVisible(true);
                }
            
        }
            
        }
}
