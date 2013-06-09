package Engine.MenuScreen;

import Engine.MenuScreen.Game_Menu;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.*;

public class Loading implements Runnable {
    private JFrame Frame;
    private JPanel Panel;
    private Game_Menu menu;
    private Thread thread;
    private BufferedImage myPicture;
    private static final int sleep=300;
    private JLabel picLabel;
    
    public Loading() throws IOException {
        this.menu = new Game_Menu();
        this.Frame = new JFrame();
        this.Panel = new JPanel(null);
        this.Frame.setUndecorated(true);
        this.Frame.setVisible(true);
        this.Panel = (JPanel) this.Frame.getContentPane();
        
        this.myPicture = ImageIO.read(getClass().getResource("/images/Loading.png"));
        
        this.picLabel = new JLabel(new ImageIcon( myPicture ));
        this.picLabel.setBounds(0, 0, this.myPicture.getWidth(),this.myPicture.getHeight());
        this.Frame.setSize(this.myPicture.getWidth(),this.myPicture.getHeight());
        this.Panel.add( picLabel );
        this.Panel.setBackground(null);
        
        this.Frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.Panel.setLayout(null);
        this.Frame.setLocationRelativeTo(null);
    }

    @Override
    public void run() {
        for (int i=0;i<=5;i++){ 
            try {
                Thread.sleep(sleep);
            } catch (InterruptedException ex) {
                Logger.getLogger(Loading.class.getName()).log(Level.SEVERE, null, ex);
            }
        if (i==5){
            this.setFrameVisible(false);
            this.menu.setFrameVisible(true);
        }
        }
    }
    
    public void setFrameVisible(boolean b){
       if (b==true)
           this.Frame.setVisible(true);
       else
           this.Frame.setVisible(false);
   }
}
