package Engine;

import java.awt.Canvas;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.*;


public class Draw{
   private JFrame frame; //finestra
   private Canvas canvas; //tavolozza per graficare l'area di gioco
   private JPanel panel; // pannello da ancorare alla finestra

   private BufferStrategy bufferStrategy; //Questo è una classe in grado di gestire più immagini in contemporanea
   private Image Background; //Sfondo
   private Image Scope_img; //mirino
   private Font myFont; //Opzionale: se si vuole impostare un stile per i caratteri
   
   //Cursore invisibile
   private Cursor HIDDEN_CURSOR;
   private Rectangle Scope;
   
   public int WIDTH; // dimensioni finestra
   public int HEIGHT;
   
   /**
    * @Finestra_Gioco inizializza la finestra di gioco
    */
   public Draw() {
       this(800,600,"Titolo Finestra");
   }
   
   
   public Draw(int pwidth,int pheight,String pString){
      this.WIDTH = pwidth;
      this.HEIGHT = pheight;
      //Definiamo la finestra ed il pannello e la relativa dimensione
      this.frame = new JFrame(pString); //inizializziamo la finestra
      this.panel = (JPanel) this.frame.getContentPane(); // inizializziamo il pannello 
      this.panel.setPreferredSize(new Dimension(WIDTH, HEIGHT));
      this.panel.setLayout(null);
      //aggiungiamoci il pannello canvas
      this.canvas = new Canvas();
      this.canvas.setBounds(0, 0, WIDTH, HEIGHT);
      this.canvas.setIgnoreRepaint(true);
      this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      this.frame.pack();
      //ancoriamo la finestra
      this.frame.setResizable(false);
      this.frame.setVisible(false);
      this.frame.setLocationRelativeTo(null);
                             //this will add the canvas to our frame
      this.panel.add(this.canvas);
      this.canvas.createBufferStrategy(3);
      this.bufferStrategy = this.canvas.getBufferStrategy();
                            //This will make sure the canvas has focus, so that it can take input from mouse/keyboard
      this.canvas.requestFocus();
                             //this will set the background
      this.canvas.setBackground(null);
       try {
           this.Background = ImageIO.read(getClass().getResource("/images/Background.png"));
       } catch (IOException ex) {
           Logger.getLogger(Draw.class.getName()).log(Level.SEVERE, null, ex);
       }
      // This will add our command listner to our program
      this.canvas.addKeyListener(new ButtonHandler());
      this.canvas.addMouseListener(new MouseHandler()); //this will get the button of mouse
      this.canvas.addMouseMotionListener(new MouseHandler()); // this will get the movement of mouse
      //miscelatous
      this.myFont = new Font("Arial", Font.BOLD, 12);
      this.HIDDEN_CURSOR = Toolkit.getDefaultToolkit().createCustomCursor(
            new BufferedImage(3, 3, BufferedImage.TYPE_INT_ARGB), new Point(), "null");
      
      //Nascondo il cursore
      this.canvas.setCursor(this.HIDDEN_CURSOR);
   
      this.Scope = new Rectangle(Game.player.Gun.getX(),Game.player.Gun.getY(),28,28);
       try {
           this.Scope_img = ImageIO.read(getClass().getResource("/images/scope.png"));
       } catch (IOException ex) {
           Logger.getLogger(Draw.class.getName()).log(Level.SEVERE, null, ex);
       }
      }
   
   public void render() throws IOException, InterruptedException {
      Graphics2D g = (Graphics2D) this.bufferStrategy.getDrawGraphics();
      g.clearRect(0, 0, this.WIDTH, this.HEIGHT);
      //render of background
      g.drawImage(this.Background, 0, 0, null);
       try {
           //render life and score status
           g.drawImage(Game.player.Heart(),this.WIDTH-35,5,24,24,null);
           g.setFont(this.myFont);
           g.drawString(Game.player.getLife(),this.WIDTH-26,22);
           g.drawString(Game.player.getScore(),this.WIDTH-50,this.HEIGHT-30);
           
       } catch (InputErrorException ex) { }
       try {
           this.renderPlayer(g);
           this.renderMob(g);
           this.renderScope(g);
       } catch (InputErrorException ex) {
           Logger.getLogger(Draw.class.getName()).log(Level.SEVERE, null, ex);
       }
      g.dispose();
      this.bufferStrategy.show();
   }
   
   protected void renderPlayer(Graphics2D g) throws MalformedURLException, IOException, InterruptedException, InputErrorException{
       g.drawImage(Game.player.Shadow(),Game.player.getX()+1,Game.player.getY()+5,Game.player.getwidth(),Game.player.getheight(), null);
       g.drawImage(Game.player.Walk(),Game.player.getX(),Game.player.getY(),Game.player.getwidth(),Game.player.getheight(), null);
       
    }
   
   protected void renderScope(Graphics2D g) throws MalformedURLException, IOException, InterruptedException, InputErrorException {
       g.drawImage(this.Scope_img,Game.player.Gun.getX(),Game.player.Gun.getY(),28,28,null);
   }
   
   protected void renderMob(Graphics2D g) throws MalformedURLException, IOException, InterruptedException, InputErrorException{
       // Here we draw all the Mob.
        for(int i = 0; i < Game.BundleMob.size(); i++)
        {
            Game.BundleMob.get(i).Draw(g);
        }
    }
   
   public void setFrameVisible(boolean b){
       if (b==true)
           this.frame.setVisible(true);
       else
           this.frame.setVisible(false);
   }
   
   }
