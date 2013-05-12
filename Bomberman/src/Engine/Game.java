package Engine;

import Enviroment.Instances;
import java.io.IOException;
import java.net.MalformedURLException;

public class Game implements Runnable{
    Draw drawing = new Draw();
    
    @Override
    public void run(){
    while(true){
    try {
        Instances.player.update();
        try {
            try {
                drawing.render();
            } catch (InterruptedException ex) { }
        } catch (IOException ex) { }
        try {Thread.sleep(10);} catch (InterruptedException e) {e.printStackTrace();}
       } catch (MalformedURLException ex) { }
      }
   }
}