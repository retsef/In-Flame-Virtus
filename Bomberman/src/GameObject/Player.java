package GameObject;

import java.net.MalformedURLException;

public class Player {
   //variables which we will use 
   private int x, y;
   private boolean  left, right, up, down;
   private String Name;
   private int Score;
   private int Life;
   private Weapon bomb;
   
   
   public Player () {
       this("Guy");
   }
   
   public Player (String pName) {
       this.Name = pName;
       this.Score = 0;
       this.Life = 3;
       this.bomb.setDamage(1);
       this.bomb.setRange(3);
   }
   
   private void increaseScore(Mob pMob) {
       if (pMob.isDead() == true) {
           this.Score += pMob.getPoint();
       }
   }
   
   //In this function we will do the required checking and updates
   public void update() throws MalformedURLException {
      move();
    }
   
   //This function will move the player according to its direction
   public void move() throws MalformedURLException{
      if(left){
         x--;
      }if(right){
         x++;
      }if(up){
         y--;
      }if(down){
         y++;
      }
   }
   
   //These 4 functions are able to set the direction
   public void setLeft (boolean newLeft  ){
      this.left  = newLeft; 
   }
   public void setUp   (boolean newUp   ){
      this.up    = newUp;   
   }
   public void setDown (boolean newDown ){
      this.down  = newDown; 
   }
   public void setRight(boolean newRight){
      this.right = newRight;
   }
   
   //This function will return X as an int.
   public int getX(){
      return x;
   }
   //And this function will return Y as an int.
   public int getY(){
      return y;
   }
}