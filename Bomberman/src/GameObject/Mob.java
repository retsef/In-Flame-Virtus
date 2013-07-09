package GameObject;

import Utils.SpriteSheetLoader;
import Engine.*;
import Utils.Sound;
import Utils.SoundException;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Mob {
    
    private int x,y;
    private int width,height;
    private int Life,Velocity,Point,Direction,Direction_final,Damage;
    private int type;
    private float angle;
    private SpriteSheetLoader MobWalk,MobWalk_shadow;
    private boolean up,down,right,left;
    private Rectangle Body;
    private boolean isMoving;
    private int Am;
    private Animation_mob animation_thread;
    private Sound mob_sound;
    
    public Mob() throws ValueErrorException, InputErrorException, IOException{
        this(2,20,2,1);
    }
    
    public Mob(int pVelocity, int pPoint, int pLife, int pDamage) throws ValueErrorException, InputErrorException, IOException{
        this.Velocity = pVelocity;
        this.Point = pPoint;
        this.Life = pLife;
        this.Damage = pDamage;
        this.Direction = 0;
        this.Direction_final = 0;
        this.type = 0;
        this.height = 40;
        this.width = 40;
        this.Am = 0;
        this.angle = 0;
        
        this.isMoving = true;
        
        this.Body = new Rectangle(this.x, this.y, this.width, this.height);
        this.animation_thread = new Animation_mob();
        
        try {
            this.mob_sound = new Sound("/sounds/Mob_Sound.wav");
        } catch (SoundException ex) {
            Logger.getLogger(Mob.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        this.MobWalk = new SpriteSheetLoader(8,12,"/images/Mob.png");
        this.MobWalk_shadow = new SpriteSheetLoader(8,12,"/images/Actor.png");
        
        this.spawn();
    }
    
    private void move() {
        if(this.isMoving==true){
            //detect the position of the player and try to reatch
            if(Instances.player.get_X() < this.x){
                this.x--;
                this.left = true;
                this.right = false;
                this.up = false;
                this.down = false;
            }else if(Instances.player.get_X() > this.x){
                this.x++;
                this.right = true;
                this.left = false;
                this.up = false;
                this.down = false;
            }
            if(Instances.player.get_Y() < this.y){
                this.y--;
                this.up = true;
                this.down = false;
                this.left = false;
                this.right = false;
            }else if(Instances.player.get_Y() > this.y){
                this.y++;
                this.down = true;
                this.up = false;
                this.left = false;
                this.right = false;
            }
        } else {}
    }
    
    public void update() throws MalformedURLException {
      this.Body = new Rectangle(this.x, this.y, this.width, this.height);
      this.move();
    }
    
    private void getDirectionWalk() {
        if(this.down==true){
            if(this.Direction!=1){
                this.Direction = 1;
                this.Direction_final = (this.type + this.Direction);
            }
        }else if(this.left==true){
            if(this.Direction!=13){
                this.Direction = 13;
                this.Direction_final = (this.type + this.Direction);
            }
        }else if(this.right==true){
            if(this.Direction!=25){
                this.Direction = 25;
                this.Direction_final = (this.type + this.Direction);
            }
        }else if (this.up==true){
            if(this.Direction!=37){
                this.Direction = 37;
                this.Direction_final = (this.type + this.Direction);
            }
        } else {}
    }

    public int getDamage() {
        return this.Damage;
    }
    
    public Image Shadow() throws InputErrorException, IOException{
       return this.MobWalk_shadow.paint(69);
   }
   
    public Image Walk() throws InputErrorException, IOException{
        while(true){
        this.getDirectionWalk();
        return this.MobWalk.paint(this.Direction_final);
        }
    }
    
    public void Damaged() {
        this.Life--;
        if (this.isDead()==true){
            this.mob_sound.play();
            this.spawn();
        }
    }
    
    private boolean isNearPlayer(Player pPlayer) {
        if(this.Body.intersects(pPlayer.get_Body()))
            return true;
        else
            return false;
    }
    
    public boolean Attack() {
        if (this.isNearPlayer(Instances.player)==true) {
            return true;
        }else{
            return false;
        }
    }
    
    public int getPoint() {
        return this.Point;
    }

    public Rectangle getBody() {
        return this.Body;
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
               this.Am = -1;
             }
            
            if(this.Am==0){
                this.Am=1;
            }
            
               this.Direction_final += this.Am;
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

        private long sleep_animation = 120;
        private Thread thread;

        public Animation_mob(){
            this.start();
        }
        
        @Override
        public void run() {
            try {
                while (true) {
                    moving();
                    Thread.sleep(this.sleep_animation);
                }
            } catch (InterruptedException ex) { }
        }

        /**
         * Avvia il thread
         */
        public void start() {
            stop();
            thread = new Thread(this,"Animation Walk-Mob");
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
