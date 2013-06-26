package GameObject;

import Engine.*;
import java.awt.*;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import javax.swing.ImageIcon;

public class Player {
   //variables which we will use 
   private int x,y;
   private int width,height;
   private boolean  left, right, up, down;
   private String Name;
   private int Score,Life,Velocity,Direction,Direction_final,Range;
   private boolean isDamaged,isMoving;
   public Weapon Glove;
   private SpriteSheetLoader ActorWalk;
   private Image Heart;
   public Rectangle Body;
   private int Ap;
   private Animation_player animation_thread;
   //public ArrayList<Weapon> BundleGlove; //shadow glove
   private static int MAXGlove=10;
   
   public Player (String pName){
       this.Name = pName;
       this.Score = 0;
       this.Life = 30;
       this.Velocity = 4;
       this.isDamaged = false;
       this.isMoving = false;
       this.height = 40;
       this.width = 40;
       this.x = 0;
       this.y = 0;
       
       this.Direction=1;
       this.Direction_final=0;
       this.Ap = 0;
       
       this.Body = new Rectangle(this.x, this.y, this.width, this.height);
       this.animation_thread = new Animation_player();
       try {
           //preset the propriety of the weapon
           this.Glove = new Weapon(1);
           /*this.BundleGlove = new ArrayList<>();
             for (int i = 0; i < this.MAXGlove; i++){
                 this.BundleGlove.add(new Weapon(1));
             }*/
       } catch (ValueErrorException ex) { }
       
       try {
           this.ActorWalk = new SpriteSheetLoader(8,12,"/images/Actor.png");
           this.Heart = new ImageIcon(getClass().getResource("/images/Heart.png")).getImage();
       } catch (IOException | InputErrorException ex) { }
       
       this.spawn();
   }
   
   public Image Heart() throws InputErrorException, IOException{
       return this.Heart;
   }
   
   public Image Shadow() throws InputErrorException, IOException{
       return this.ActorWalk.paint(69);
   }
   
   public Image Walk() throws InputErrorException, IOException{
       while(true){
       this.animation_thread.start();
       this.getDirectionWalk(); //contrast with animation thread
       if (this.isDamaged==true) {
           return this.ActorWalk.paint(73);
       }
       return this.ActorWalk.paint(this.Direction_final);
       }
   }
   
   public void moving() {
       if(this.isMoving != false){
            if (this.Ap<1) {
               this.Ap += 1;
             } else if (this.Ap>=1) {
               this.Ap -= 2;
             }
               this.Direction_final += this.Ap;
            } else {}
   }
   
   //make player rotation animation
   public void getDirectionWalk(){
       if ((this.getDirection()>338 && this.getDirection()<=0) || (this.getDirection()>0 && this.getDirection()<=23) ){
           this.Direction=1;
       } if (this.getDirection()>23 && this.getDirection()<=68){
           this.Direction=28;
       } if (this.getDirection()>68 && this.getDirection()<=113){
           this.Direction=25;
       } if (this.getDirection()>113 && this.getDirection()<=158){
           this.Direction=40;
       } if ((this.getDirection()>158 && this.getDirection()<=180) || (this.getDirection()>180 &&this.getDirection()<=203)){
           this.Direction=37;
       } if (this.getDirection()>203 && this.getDirection()<=248){
           this.Direction=16;
       } if (this.getDirection()>248 && this.getDirection()<=293){
           this.Direction=13;
       } if (this.getDirection()>293 && this.getDirection()<=338){
           this.Direction=4;
       } else {
           this.Direction_final = this.Direction;
       }
   }
   
   public int getDirection(){
       double angle;
       angle = Math.toDegrees(Math.atan2(this.Glove.getTarget().getX()-(this.x+16),this.Glove.getTarget().getY()-(this.y+16)));
       if (angle < 0){
           angle += 360;
       }
       return (int)angle;
   }
   
   public void get_Damage_from(Mob pMob) {
       if (pMob.Attack() == true) {
           this.bounce();
           this.Life -= pMob.getDamage();
       }else{ }
   }
   
   public void Damage_a_Mob(Mob pMob) throws IOException, InputErrorException {
       if(this.Glove.getAttack()==true && pMob.getBody().contains(new Point(this.Glove.getX(),this.Glove.getY()))){
           this.Glove.setX(this.x);
           this.Glove.setY(this.y);
           pMob.Damaged();
           this.increaseScore(pMob);
       } else {}
   }
   
   private void increaseScore(Mob pMob) {
       if (pMob.isDead() == true) {
           this.Score += pMob.getPoint();
       }
   }
   
   //In this function we will do the required checking and updates
   public void update() throws MalformedURLException {
      this.move();
      this.Body = new Rectangle(this.x, this.y, this.width, this.height);
      this.Glove.update();
    }
   
   //This function will move the player according to its direction
   //up-down e left-rigth separati per i movimenti diagonali
   private void move() throws MalformedURLException{
      if(left){
         this.x -= this.Velocity;
         this.isMoving=true;
      }else if(right){
         this.x += this.Velocity;
         this.isMoving=true;
      }else{
         this.isMoving=false;
      }
      
      if(up){
         this.y -= this.Velocity;
         this.isMoving=true;
      }else if(down){
         this.y += this.Velocity;
         this.isMoving=true;
      }else{
         this.isMoving=false;
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
       this.x = Game.WIDTH/2; 
       this.y = Game.HEIGTH/2;
   }
   
   private void bounce(){
       this.x += (int)(Math.random()*3);
       this.y += (int)(Math.random()*3);
   }
   
   //This function will return X as an int.
   public int getX(){
      return this.x;
   }
   //And this function will return Y as an int.
   public int getY(){
      return this.y;
   }
   
   public Point getPoint_position() {
       return new Point(this.x, this.y);
   }

    public int getheight() {
        return this.height;
    }

    public int getwidth() {
        return this.width;
    }
   
    public void Draw(Graphics2D g) throws InputErrorException, IOException{
       g.drawImage(this.Shadow(),this.x+1,this.y+5,this.width,this.height, null);
       g.drawImage(this.Walk(),this.x,this.y,this.width,this.height, null);
    }
    
   /**
     * Thread dell' animazione
     */
    private class Animation_player implements Runnable {

        private int sleep = 7000;
        private Thread thread;

        @Override
        public void run() {
            try {
                while (true) {
                    moving();
                    Thread.sleep(this.sleep);
                }
            } catch (InterruptedException ex) {
            }
        }

        /**
         * Avvia il thread
         */
        public void start() {
            stop();
            thread = new Thread(this,"Animation Walk-Player");
            thread.start();
        }

        /**
         * Ferma il thread
         */
        public void stop() {
            if (thread != null && thread.isAlive()) {
                thread.interrupt();
            }
        }
    }
    
}

