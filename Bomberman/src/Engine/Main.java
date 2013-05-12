package Engine;

public class Main{
     public static void main(String [] args){
        System.out.println("Application, started!");
        Game ex = new Game();               //Creates new object of type Game, and name it ex.
        new Thread(ex).start();            //Start this so called ex object. 
      }
}