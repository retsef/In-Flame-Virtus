package GameObject;

import Engine.InputErrorException;
import Engine.SpriteSheetLoader;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.MalformedURLException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class Player {
   //variables which we will use 
   private int x, y;
   private boolean  left, right, up, down;
   private String Name;
   private int Score;
   private int Life;
   private int Velocity;
   public Weapon Gun;
   public SpriteSheetLoader ActorWalk;
   public BufferedImage ActorWalk_static;
   public int Direction;
   
   public Player (String pName){
       this.Name = pName;
       this.Score = 0;
       this.Life = 3;
       this.Velocity = 2;
       //preset the propriety of the weapon
       //this.Gun.setDamage(1);
       //this.Gun.setRange(5);
       this.x = 0;
       this.y = 0;
       this.Direction=1;
   }
   
    public Image Shadow() throws InputErrorException, IOException{
       this.ActorWalk = new SpriteSheetLoader(8,12,"/GameObject/Actor.png");
       return this.ActorWalk.paint(69);
   }
   
   public Image Walk_static() throws InputErrorException, IOException{
       this.ActorWalk = new SpriteSheetLoader(8,12,"/GameObject/Actor.png");
       while(true){
       this.Walk();
       return this.ActorWalk.paint(this.Direction);
       }
   }
   
   //make player animation
   public void Walk() throws InputErrorException, IOException{
       if (this.down==true){
           this.Direction=1;
       } if (this.left==true){
           this.Direction=13;
       } if (this.up==true){
           this.Direction=37;
       } if (this.right==true){
           this.Direction=31;
       } if (this.left==true && this.up==true){
           this.Direction=16;
       } if (this.left==true && this.down==true){
           this.Direction=4;
       } if (this.right==true && this.up==true){
           this.Direction=40;
       } if (this.right==true && this.down==true){
           this.Direction=28;
       } else {}
   }
   
   public void spawn(int pX,int pY) throws InputErrorException{
       if (pX < 0 || pY < 0)
           throw new InputErrorException("coordinate di spawn non valide");
       this.x = pX;
       this.y = pY;
   }
   /*
   private void getDamage(Mob pMob) {
       if (pMob.Attack()==true)
           this.Life--;
   }*/
   
   private void increaseScore(Mob pMob) {
       if (pMob.isDead() == true) {
           this.Score += pMob.getPoint();
       }
   }
   
   //In this function we will do the required checking and updates
   public void update() throws MalformedURLException {
      this.move();
    }
   
   //This function will move the player according to its direction
   public void move() throws MalformedURLException{
      if(left){
         this.x -= this.Velocity;
      }if(right){
         this.x += this.Velocity;
      }if(up){
         this.y -= this.Velocity;
      }if(down){
         this.y += this.Velocity;
      }
   }
   
   //These 4 functions are able to set the direction
   public void setLeft (boolean pLeft  ){
      this.left  = pLeft; 
   }
   public void setUp   (boolean pUp   ){
      this.up    = pUp;   
   }
   public void setDown (boolean pDown ){
      this.down  = pDown; 
   }
   public void setRight(boolean pRight){
      this.right = pRight;
   }
   
   //This function will return X as an int.
   public int getX(){
      return this.x;
   }
   //And this function will return Y as an int.
   public int getY(){
      return this.y;
   }
   
}