package Engine;

import GameObject.WalkAnimation;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;


public class Draw{
   private JFrame frame;
   private Canvas canvas;

   private BufferStrategy bufferStrategy;

   public int WIDTH;
   public int HEIGHT;
   
   /**
    * @Draw inizializza la finestra di gioco
    */
   public Draw() {
       this(640,480,"MachineMan");
   }
   
   public Draw(int pwidth,int pheight,String pString){
      this.WIDTH = pwidth;
      this.HEIGHT = pheight;
      //Makes a new window, with the name " Basic game  ".
      frame = new JFrame(pString);
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
      canvas.setBackground(null);
      // This will add our buttonhandler to our program
      canvas.addKeyListener(new ButtonListener());
      }
   
   public void render() throws IOException, InterruptedException {
      Graphics2D g = (Graphics2D) bufferStrategy.getDrawGraphics();
      g.clearRect(0, 0, WIDTH, HEIGHT);
       try {
          try {
              renderPlayer(g);
          } catch (InputErrorException ex) {
              Logger.getLogger(Draw.class.getName()).log(Level.SEVERE, null, ex);
          }
       } catch (MalformedURLException ex) { }
      g.dispose();
      bufferStrategy.show();
   }
   
   protected void renderPlayer(Graphics2D g) throws MalformedURLException, IOException, InterruptedException, InputErrorException{
       g.drawImage(Game.player.Shadow(),Game.player.getX()+1,Game.player.getY()+5,40,40, null);
       g.drawImage(Game.player.Walk_static(),Game.player.getX(),Game.player.getY(),40,40, null);
       
      }
   }
