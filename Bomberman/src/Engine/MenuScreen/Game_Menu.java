package Engine.MenuScreen;

import Engine.Game;
import Utils.SpriteException;
import Engine.Instances;
import GameObject.ValueErrorException;
import Utils.Sound;
import Utils.SoundException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.*;

/**
 * Realizza la finestra del menu contenente:
 * -Pannello delle istruzioni
 * -Pannello delle informazioni
 * -Schermata principale
 * @author Roberto
 */
public class Game_Menu {
    private int WIDTH,HEIGHT;
    private JFrame Frame;
    private Menu_Panel Menu;
    private HowToPlay_Panel howToPlay;
    private Info_Panel Info;
    private Sound Menu_sound;
    /**
     * Crea una finestra di grandezza predefinita
     */
    public Game_Menu(){
        this(700,1240);
    }
    /**
     * Realizza la Finestra principale
     * @param pWidth Larchezza della finestra
     * @param pHeight Altezza della finestra
     */
    public Game_Menu(int pWidth, int pHeight){
        this.WIDTH = pWidth;
        this.HEIGHT = pHeight;
        this.Frame = new JFrame("MENU'");
        this.Menu = new Menu_Panel();
        this.howToPlay = new HowToPlay_Panel();
        this.Info = new Info_Panel();
        this.Frame.setVisible(false);
        this.Frame.setSize(this.HEIGHT,this.WIDTH);
        this.Frame.setResizable(false);
        this.Frame.add(this.Menu);
        this.Frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.Frame.setLocationRelativeTo(null);
        this.Frame.setBackground(null);

        try {
            Instances.game = new Game();
            //this.Menu_sound = new Sound("/sounds/End_to_the_War.wav");
            this.Menu_sound = new Sound("/sounds/Furiously.wav");
        } catch (ValueErrorException | SpriteException | SoundException ex) {
            Logger.getLogger(Game_Menu.class.getName()).log(Level.SEVERE, null, ex);
        }
         
    }
    /**
     * Impone la decisione di mostrare la finestra o no
     * @param b Boolena che impone l'esito
     */
    public void setFrameVisible(boolean b){
       if (b==true){
           Frame.setVisible(true);
           try {
               this.Menu_sound.loop();
           } catch (SoundException ex) {
               Logger.getLogger(Game_Menu.class.getName()).log(Level.SEVERE, null, ex);
           }
       }else{
           Frame.setVisible(false);
           //this.Menu_sound.stop();
       }
    }

    /**
     * Crea il pannello contenente il menu' principale
     */
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
            this.start.setBounds(-120, -100, 630, 480);
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

            this.start.setIcon(new ImageIcon(getClass().getResource("/images/start_button.png")) );
            this.start.setBorder(null);
            this.start.setContentAreaFilled(false);

            this.title_label = new JLabel(new ImageIcon(this.title));
            this.title_label.setBounds(0, 0, this.title.getWidth(), this.title.getHeight());
            add(this.title_label);

            this.background_label = new JLabel(new ImageIcon(this.background));
            this.background_label.setBounds(0, 0, this.background.getWidth(), this.background.getHeight());
            add(this.background_label);

        }

        /**
         * Crea il pulsante start
         * Avvia il gioco alla pressione
         */
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
                Game.get_player().setName(JOptionPane.showInputDialog("Inserisci il nome del Player"));
                Instances.game.start();
                Frame.setVisible(false);
                System.gc();
            }

        }

        /**
         * Crea il pulsante per accedere alle istruzioni
         */
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

    /**
     * Pannello con le istruzioni
     */
    private class HowToPlay_Panel extends JPanel{

        private BackButton back;
        private BufferedImage background; 
        private JLabel background_label;
        private InfoButton info;

        public HowToPlay_Panel() {
            setLayout(null);
            setBackground(null);

            this.back = new BackButton();
            this.back.setBounds(-90,110,290,90);
            this.back.setIcon(new ImageIcon(getClass().getResource("/images/ribbon.png")) );
            this.back.setBorder(null);
            this.back.setContentAreaFilled(false);
            add(this.back);

            this.info = new InfoButton();
            this.info.setBounds(370,420,250,250);
            this.info.setIcon(new ImageIcon(getClass().getResource("/images/Info.png")));
            this.info.setBorder(null);
            this.info.setContentAreaFilled(false);
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

        /**
         * Tasto indietro
         * Riporta alla schermata precedente
         */
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

        /**
         * Tasto Informazioni
         * Rimanda al pannello informativo
         */
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
                Frame.remove(howToPlay);
                Frame.dispose();
                Frame.add(Info);
                Frame.setVisible(true);
            }

        }
    }
    /**
     * Pannello informativo
     */
    private class Info_Panel extends JPanel{

        private BackButton back;
        private BufferedImage background,Jake_Dog,Info_dev; 
        private JLabel background_label,Jake_Dog_label,Info_dev_label;
        
        public Info_Panel() {
            setLayout(null);
            setBackground(null);

            this.back = new BackButton();
            this.back.setBounds(-90,110,290,90);
            this.back.setIcon(new ImageIcon(getClass().getResource("/images/ribbon.png")) );
            this.back.setBorder(null);
            this.back.setContentAreaFilled(false);
            add(this.back);
            
            
            
            try {
                this.background = ImageIO.read(getClass().getResource("/images/notebook.png"));
                this.Jake_Dog = ImageIO.read(getClass().getResource("/images/jakedog-wizard.png"));
                this.Info_dev = ImageIO.read(getClass().getResource("/images/Info_dev.png"));
            } catch (IOException ex) {
                Logger.getLogger(Game_Menu.class.getName()).log(Level.SEVERE, null, ex);
            }

            this.Jake_Dog_label = new JLabel(new ImageIcon(this.Jake_Dog));
            this.Jake_Dog_label.setBounds(180, 120, 475, 475);
            add(this.Jake_Dog_label);
            
            this.Info_dev_label = new JLabel(new ImageIcon(this.Info_dev));
            this.Info_dev_label.setBounds(635, 120, 500, 500);
            add(this.Info_dev_label);
            
            this.background_label = new JLabel(new ImageIcon(this.background));
            this.background_label.setBounds(0, 0, this.background.getWidth(), this.background.getHeight());
            add(this.background_label);
        }
        /**
         * Tasto indietro
         * Riporta alla schermata precedente
         */
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
                Frame.remove(Info);
                Frame.dispose();
                Frame.add(howToPlay);
                Frame.setVisible(true);
            }

        }
    }
}
