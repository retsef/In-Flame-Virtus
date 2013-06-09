package GameObject;

import Engine.Game;
import Engine.InputErrorException;
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
    
    public Mob() throws ValueErrorException, InputErrorException, IOException {
        this(2,20,2);
    }
    
    public Mob(int pVelocity, int pPoint, int pLife) throws ValueErrorException, InputErrorException, IOException {
        this.Velocity = pVelocity;
        this.Point = pPoint;
        this.Life = pLife;
        this.Direction = 0;
        this.type = 0;
        this.isMoving = true;
        this.height = 40;
        this.width = 40;
        this.spawn();
        this.Body = new Rectangle(this.x, this.y, this.width, this.height);
        
        this.MobWalk = new SpriteSheetLoader(8,12,"/images/Mob.png");
        this.MobWalk_shadow = new SpriteSheetLoader(8,12,"/images/Actor.png");
    }
    
    private void move() {
        if(this.isMoving==true){
        //detect the position of the player and try to reatch
        if(Game.player.getX() < this.x){
        this.x--;
        this.left = true;
        this.right = false;
        this.up = false;
        this.down = false;
        }else if(Game.player.getX() > this.x){
        this.x++;
        this.right = true;
        this.left = false;
        this.up = false;
        this.down = false;
        }
        if(Game.player.getY() < this.y){
        this.y--;
        this.up = true;
        this.down = false;
        this.left = false;
        this.right = false;
        }else if(Game.player.getY() > this.y){
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
       return this.MobWalk.paint(this.Direction_type);
       }
   }
    
    public void Damaged() {
        this.Life--;
        if (this.isDead()==true){
            this.spawn();
        }
    }
    
    private boolean isNearPlayer() {
        if(this.Body.intersects(Game.player.Body))
            return true;
        else
            return false;
    }
    
    public boolean Attack() {
        if (this.isNearPlayer()==true)
            return true;
        else
            return false;
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
        int ptype = (int)(Math.random()*2);
    }
    
    public int getX() {
        return x;
    }

    public void setX(int px) {
        this.x = px;
    }

    public int getY() {
        return y;
    }

    public void setY(int py) {
        this.y = py;
    }
    
    public void setMoving(boolean pmove) {
        this.isMoving = pmove;
    }

    public void Draw(Graphics2D g) throws InputErrorException, IOException {
        
        g.drawImage(this.Shadow(),this.getX()+1,this.getY()+5,this.width,this.height,null);
        g.drawImage(this.Walk(),this.getX(),this.getY(),this.width,this.height,null);
    }
    
}
