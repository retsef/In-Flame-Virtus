package Engine;

import Engine.MenuScreen.Loading;
import java.io.IOException;

public class Main{
     public static void main(String [] args) throws IOException{
        System.out.println("Application, started!");
        
        Loading ex = new Loading();
        new Thread(ex).start();
      }
}