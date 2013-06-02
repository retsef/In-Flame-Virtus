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
    
    private int Life;
    private int Velocity;
    private int Point;
    private int x,y;
    private SpriteSheetLoader MobWalk;
    private int Direction;
    private int TypeMob;
    private boolean up,down,right,left;
    public static int[] Bundle;
    
    public Mob() throws ValueErrorException, InputErrorException {
        this(2,20,2);
    }
    
    public Mob(int pVelocity, int pPoint, int pLife) throws ValueErrorException, InputErrorException {
        this.Velocity = pVelocity;
        this.Point = pPoint;
        this.Life = pLife;
        this.Direction = 0;
        this.spawn();
    }
    
    private void move() {
        //detect the position of the player and try to reatch
        if(Game.player.getX() < this.x){
        this.x--;
        this.left = true;
        this.right = false;
        }else if(Game.player.getX() > this.x){
        this.x++;
        this.right = true;
        this.left = false;
        }
        if(Game.player.getY() < this.y){
        this.y--;
        this.up = true;
        this.down = false;
        }else if(Game.player.getY() > this.y){
        this.y++;
        this.down = true;
        this.up = false;
        }
    }
    
    public void update() throws MalformedURLException {
      this.move();
    }
    
    public void getDirectionWalk() {
        if(Game.player.getY()==this.y && this.down==true){
            this.Direction = 1;
        }else if(Game.player.getX()==this.x && this.left==true){
            this.Direction = 13;
        }else if (Game.player.getX()==this.x && this.right==true){
            this.Direction = 25;
        }else if (Game.player.getY()==this.y && this.up==true){
            this.Direction = 37;
        }
    }

    public Image Shadow() throws InputErrorException, IOException{
       this.MobWalk = new SpriteSheetLoader(8,12,"/GameObject/Actor.png");
       return this.MobWalk.paint(69);
   }
   
   public Image Walk() throws InputErrorException, IOException{
       this.MobWalk = new SpriteSheetLoader(8,12,"/GameObject/Mob.png");
       while(true){
       this.getDirectionWalk();
       return this.MobWalk.paint(this.Direction);
       }
   }
    
    public void Damaged() {
        this.Life--;
    }
    
    public boolean Attack() {
        //if ((Game.player.getX()==Game.mob.getX())&&(Game.player.getY()==Game.mob.getY()))
        if (new Rectangle(Game.player.getX(), Game.player.getY(), 40, 40).intersects(new Rectangle(this.x,this.y,40,40)))
            return true;
        else
            return false;
    }
    
    public int getPoint() {
        return this.Point;
    }

    public boolean isDead() {
        if (this.Life > 0)
            return true;
        else
            return false;
    }
    
    private final void spawn(){
        this.x = (int)(Math.random() * Game.drawing.WIDTH);
        this.y = (int)(Math.random() * Game.drawing.HEIGHT);
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

    public void Draw(Graphics2D g) throws InputErrorException, IOException {
        g.drawImage(this.Shadow(),this.getX()+1,this.getY()+5,40,40,null);
        g.drawImage(this.Walk(),this.getX(),this.getY(),40,40,null);
    }
    
}
