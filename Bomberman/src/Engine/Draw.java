package Engine;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;
import java.io.IOException;
import java.net.MalformedURLException;
import javax.swing.*;


public class Draw{
   private JFrame frame;
   private Canvas canvas;

   private BufferStrategy bufferStrategy;

   private int WIDTH;
   private int HEIGHT;
   
   /**
    * @Draw inizializza la finestra di gioco
    */
   public Draw() {
       this(640,480);
   }
   
   public Draw(int pwidth,int pheight){
      this.WIDTH = pwidth;
      this.HEIGHT = pheight;
      //Makes a new window, with the name " Basic game  ".
      frame = new JFrame("Basic Game");
      JPanel panel = (JPanel) frame.getContentPane();
      panel.setPreferredSize(new Dimension(WIDTH, HEIGHT));
      panel.setLayout(null);
      
      canvas = new Canvas();
      canvas.setBounds(0, 0, WIDTH, HEIGHT);
      canvas.setIgnoreRepaint(true);
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.pack();
                             //this will make the frame not re-sizable
      frame.setResizable(false);
      frame.setVisible(true);
                             //this will add the canvas to our frame
      panel.add(canvas);
      canvas.createBufferStrategy(2);
      bufferStrategy = canvas.getBufferStrategy();
                            //This will make sure the canvas has focus, so that it can take input from mouse/keyboard
      canvas.requestFocus();
                             //this will set the background to black
      canvas.setBackground(Color.LIGHT_GRAY);
      // This will add our buttonhandler to our program
      canvas.addKeyListener(new ButtonHandler());
      }
   
   void render() throws IOException, InterruptedException {
      Graphics2D g = (Graphics2D) bufferStrategy.getDrawGraphics();
      g.clearRect(0, 0, WIDTH, HEIGHT);
       try {
           render(g);
       } catch (MalformedURLException ex) { }
      g.dispose();
      bufferStrategy.show();
   }
   
   protected void render(Graphics2D g) throws MalformedURLException, IOException, InterruptedException{ 
         //g.drawImage(Instances.player.SOMETHING_HERE(),Instances.player.getX(),Instances.player.getY(), 35,35, null);
      }
   }
