package GameObject;

import Engine.Game;
import Engine.InputErrorException;
import Engine.Instances;
import Engine.SpriteSheetLoader;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.io.IOException;
import java.net.MalformedURLException;

public class Mob {
    
    private int x,y;
    private int width,height;
    private int Life,Velocity,Point,Direction;
    private int Direction_type,type;
    private SpriteSheetLoader MobWalk,MobWalk_shadow;
    private boolean up,down,right,left;
    public Rectangle Body;
    private boolean isMoving;
    private int Am;
    private Animation_mob animation_thread;
    private boolean isIntersectWithPlayer;
    
    public Mob() throws ValueErrorException, InputErrorException, IOException {
        this(2,20,2);
    }
    
    public Mob(int pVelocity, int pPoint, int pLife) throws ValueErrorException, InputErrorException, IOException {
        this.Velocity = pVelocity;
        this.Point = pPoint;
        this.Life = pLife;
        this.Direction = 0;
        this.type = 0;
        this.height = 40;
        this.width = 40;
        this.Am = 0;
        this.isIntersectWithPlayer = false;
        
        this.isMoving = true;
        
        this.Body = new Rectangle(this.x, this.y, this.width, this.height);
        //this.animation_thread = new Animation_mob();
        
        this.MobWalk = new SpriteSheetLoader(8,12,"/images/Mob.png");
        this.MobWalk_shadow = new SpriteSheetLoader(8,12,"/images/Actor.png");
        
        this.spawn();
    }
    
    private void move() {
        if(this.isMoving==true){
        //detect the position of the player and try to reatch
        if(Instances.player.getX() < this.x){
        this.x--;
        this.left = true;
        this.right = false;
        this.up = false;
        this.down = false;
        }else if(Instances.player.getX() > this.x){
        this.x++;
        this.right = true;
        this.left = false;
        this.up = false;
        this.down = false;
        }
        if(Instances.player.getY() < this.y){
        this.y--;
        this.up = true;
        this.down = false;
        this.left = false;
        this.right = false;
        }else if(Instances.player.getY() > this.y){
        this.y++;
        this.down = true;
        this.up = false;
        this.left = false;
        this.right = false;
        }
    } else {}
    }
    
    public void update() throws MalformedURLException {
      this.move();
      this.Body = new Rectangle(this.x, this.y, this.width, this.height);
      this.isNearPlayer(Instances.player);
    }
    
    private void getDirectionWalk() {
        if(this.down==true){
            this.Direction = 1;
        }if(this.left==true){
            this.Direction = 13;
        }if (this.right==true){
            this.Direction = 25;
        }if (this.up==true){
            this.Direction = 37;
        }
    }

    public Image Shadow() throws InputErrorException, IOException{
       return this.MobWalk_shadow.paint(69);
   }
   
   public Image Walk() throws InputErrorException, IOException{
       while(true){
       this.getDirectionWalk();
       
       this.Direction_type = (this.type + this.Direction);
       
       //this.animation_thread.start();
       return this.MobWalk.paint(this.Direction_type);
       }
   }
    
    public void Damaged() {
        this.Life--;
        if (this.isDead()==true){
            this.spawn();
        }
    }
    
    private void isNearPlayer(Player pPlayer) {
        if(this.Body.intersects(pPlayer.Body))
            this.isIntersectWithPlayer = true;
        else
            this.isIntersectWithPlayer = false;
    }
    
    public boolean Attack() {
        if (this.isIntersectWithPlayer==true) {
            return true;
        }else{
            return false;
        }
    }
    
    public int getPoint() {
        return this.Point;
    }

    public boolean isDead() {
        if (this.Life > 0)
            return false;
        else
            return true;
    }
    
    private void spawn(){
        this.x = (int)(Math.random() * Game.WIDTH);
        this.y = (int)(Math.random() * Game.HEIGTH);
        
        int phope = (int)(Math.random() * 4);
        this.type = 3*(phope);
    }

    public void setX(int px) {
        this.x = px;
    }

    public void setY(int py) {
        this.y = py;
    }
    
    public void setMoving(boolean pmove) {
        this.isMoving = pmove;
    }
    public void moving() {
       if(this.isMoving != false){
            if (this.Am<1) {
               this.Am += 1;
             } else if (this.Am>=1) {
               this.Am -= 2;
             }
               this.Direction_type += this.Am;
            } else {}
       
    }

    public void Draw(Graphics2D g) throws InputErrorException, IOException {
        
        g.drawImage(this.Shadow(),this.x+1,this.y+5,this.width,this.height,null);
        g.drawImage(this.Walk(),this.x,this.y,this.width,this.height,null);
    }
    
    /**
     * Thread dell' animazione
     */
    private class Animation_mob implements Runnable {

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
            thread = new Thread(this);
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
