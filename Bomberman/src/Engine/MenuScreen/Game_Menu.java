package Engine.MenuScreen;

import Engine.Game;
import Engine.InputErrorException;
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
public class Game_Menu {
    private int WIDTH,HEIGHT;
    private JFrame Frame;
    private Game game;
    private MenuPanel Menu;
    private SettingsPanel Settings;
    
    public Game_Menu(){
        this(600,800);
    }
    
    public Game_Menu(int pWidth, int pHeight){
        this.WIDTH = pWidth;
        this.HEIGHT = pHeight;
        this.Frame = new JFrame("MENU'");
        this.Menu = new MenuPanel();
        this.Settings = new SettingsPanel();
        this.Frame.setVisible(false);
        this.Frame.setSize(this.HEIGHT,this.WIDTH);
        //this.Menu.Panel = (JPanel) this.Frame.getContentPane();
        this.Frame.add(this.Menu.Panel);
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

            
            private JPanel Panel;
            private StartButton start;
            private SettingsButton settings;

            public MenuPanel() {
                this.Panel = new JPanel();
                this.Panel.setLayout(null);
                this.start = new StartButton();
                this.settings = new SettingsButton();
                this.Panel.add(this.start.button);
                this.Panel.add(this.settings.button);
                this.start.button.setBounds(20, 20, 100, 50);
                this.settings.button.setBounds(650,500,100,50);
            }

            private class StartButton implements ActionListener{

                public JButton button;

                public StartButton(){
                    this("Start");
                }

                public StartButton(String pTitle){
                    this.button = new JButton(pTitle);
                    this.button.addActionListener(this);
                }

                @Override
                public void actionPerformed(ActionEvent e) {
                    new Thread(game).start();
                    Frame.setVisible(false);
                    System.gc();
                }

            }

            private class SettingsButton implements ActionListener{

                public JButton button;

                public SettingsButton(){
                    this("Settings");
                }

                public SettingsButton(String pTitle){
                    this.button = new JButton(pTitle);
                    this.button.addActionListener(this);
                }

                @Override
                public void actionPerformed(ActionEvent e) {
                    Frame.remove(Menu.Panel);
                    Frame.dispose();
                    Frame.add(Settings.Panel);
                    Frame.setVisible(true);
                }

            }
            
        }
        
        private class SettingsPanel {
            private JPanel Panel;
            private InfoButton info;
            private BackButton back;

            public SettingsPanel() {
                this.Panel = new JPanel();
                this.Panel.setLayout(null);
                this.info = new InfoButton();
                this.back = new BackButton();
                this.Panel.add(this.info.button);
                this.Panel.add(this.back.button);
                this.back.button.setBounds(20, 20, 100, 50);
                this.info.button.setBounds(650,500,100,50);
            }

            private class InfoButton implements ActionListener{

                public JButton button;

                public InfoButton(){
                    this("Info");
                }

                public InfoButton(String pTitle){
                    this.button = new JButton(pTitle);
                    this.button.addActionListener(this);
                }

                @Override
                public void actionPerformed(ActionEvent e) {
                    System.out.println("pWork in progress");
                }

            }
            
            private class BackButton implements ActionListener{

                public JButton button;

                public BackButton(){
                    this("Back");
                }

                public BackButton(String pTitle){
                    this.button = new JButton(pTitle);
                    this.button.addActionListener(this);
                }

                @Override
                public void actionPerformed(ActionEvent e) {
                    Frame.remove(Settings.Panel);
                    Frame.dispose();
                    Frame.add(Menu.Panel);
                    Frame.setVisible(true);
                }

            }
        }
    
}
