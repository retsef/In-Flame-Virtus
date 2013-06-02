package GameObject;

import Engine.Game;
import Engine.InputErrorException;
import Engine.SpriteSheetLoader;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;

public class Player {
   //variables which we will use 
   private int x, y;
   private boolean  left, right, up, down;
   private String Name;
   private int Score;
   private int Life;
   private int Velocity;
   private boolean Damaged;
   public Weapon Gun;
   private SpriteSheetLoader ActorWalk;
   private BufferedImage ActorWalk_static;
   private Image Heart;
   private int Direction;
   
   public Player (String pName){
       this.Name = pName;
       this.Score = 0;
       this.Life = 3;
       this.Velocity = 4;
       this.Damaged = false;
       try {
           //preset the propriety of the weapon
           this.Gun = new Weapon(3,2);
       } catch (ValueErrorException ex) { }
       this.spawn();
       this.Direction=1;
       try {
           this.ActorWalk = new SpriteSheetLoader(8,12,"/GameObject/Actor.png");
           this.Heart = new ImageIcon(getClass().getResource("/GameObject/Heart.png")).getImage();
       } catch (IOException | InputErrorException ex) {
           Logger.getLogger(Player.class.getName()).log(Level.SEVERE, null, ex);
       }
       
   }
   
   public Image Heart() throws InputErrorException, IOException{
       return this.Heart;
   }
   
   public Image Shadow() throws InputErrorException, IOException{
       return this.ActorWalk.paint(69);
   }
   
   public Image Walk() throws InputErrorException, IOException{
       while(true){
       //this.getDirectionWalk();
       if (this.Damaged==true)
           return this.ActorWalk.paint(73);
       return this.ActorWalk.paint(this.Direction);
       }
   }
   /*
   //make player animation
   private void getDirectionWalk(){
       if (this.getDirection()>338 && this.getDirection()<23){
           this.Direction=31;
       } if (this.getDirection()>23 && this.getDirection()<=68){
           this.Direction=28;
       } if (this.getDirection()>68 && this.getDirection()<=113){
           this.Direction=1;
       } if (this.getDirection()>113 && this.getDirection()<=158){
           this.Direction=4;
       } if (this.getDirection()>158 && this.getDirection()<=203){
           this.Direction=13;
       } if (this.getDirection()>203 && this.getDirection()<=248){
           this.Direction=16;
       } if (this.getDirection()>248 && this.getDirection()<=293){
           this.Direction=37;
       } if (this.getDirection()>293 && this.getDirection()<=338){
           this.Direction=40;
       } else {}
   }
   
   public int getDirection(){
       double angle;
       angle = Math.atan2(this.Gun.getX()-this.x,this.Gun.getY()-this.y);
       System.out.println(Math.toDegrees(angle));
       return (int)angle;
   }*/
   
   public void getDamage(Mob pMob) {
       if (pMob.Attack()==true) {
           this.Life--;
           this.Damaged = true;
       }else{
           this.Damaged = false;
       }
   }
   
   private void increaseScore(Mob pMob) {
       if (pMob.isDead() == true) {
           this.Score += pMob.getPoint();
       }
   }
   
   //In this function we will do the required checking and updates
   public void update() throws MalformedURLException {
      this.move();
      if(this.Damaged==true)
          this.bounce();
    }
   
   //This function will move the player according to its direction
   private void move() throws MalformedURLException{
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
   
   public String getLife() {
       String strng;
       strng = "" + this.Life;
       return strng;
   }
   
   public String getScore() {
       String strng;
       strng = "" + this.Score;
       return strng;
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
   
   private void spawn(){
       this.x = Game.drawing.WIDTH/2; 
       this.y = Game.drawing.HEIGHT/2;
   }
   
   private void bounce(){
       this.x += (int)(Math.random()*50);
       this.y += (int)(Math.random()*50);
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