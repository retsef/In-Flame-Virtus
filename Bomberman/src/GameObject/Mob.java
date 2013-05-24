package GameObject;

import Engine.Game;
import java.net.MalformedURLException;

public class Mob {
    
    private int Life;
    private int Velocity;
    private int Point;
    private int x,y;
    
    public Mob() throws ValueErrorException {
        this(1,20,2);
    }
    
    public Mob(int pVelocity, int pPoint, int pLife) throws ValueErrorException {
        this.Velocity = pVelocity;
        this.Point = pPoint;
        this.Life = pLife;
        this.x = 0;
        this.y = 0;
    }
    
    public void move() {
        //detect the position of the player and try to reatch
        if (Game.player.getX() > this.x) { //on x axes wen is on the left of the mob
            for (getX(); this.x>=Game.player.getX();this.x+=this.Velocity){
                if(Game.player.getY() > this.y) { //wen player is upper than mob
                    for (getY(); this.y>=Game.player.getY();this.y+=this.Velocity) {}
                } else if (Game.player.getY() < this.y){ //wen player is downer than mob
                    for (getY(); this.y<=Game.player.getX();this.y-=this.Velocity) {}
                }
            }
        }
        //viceversa
        if (Game.player.getX() < this.x) {
            for (getY(); this.x<=Game.player.getX();this.x-=this.Velocity){
                if(Game.player.getY() > this.y) {
                    for (getY(); this.y>=Game.player.getY();this.y+=this.Velocity) {}
                } else if (Game.player.getY() < this.y){
                    for (getY(); this.y<=Game.player.getX();this.y-=this.Velocity) {}
                }
            }
        }
            
    }
    
    public void update() throws MalformedURLException {
      this.move();
    }
    
    public boolean isDead() {
        if (this.Life > 0)
            return true;
        else
            return false;
    }
    /*
    public boolean Attack() {
        if ((Game.player.getX()==Game.mob.getX())&&(Game.player.getY()==Game.mob.getY()))
            return true;
        else
            return false;
    }*/

    public void Damaged() {
        this.Life--;
    }
    
    public int getPoint() {
        return this.Point;
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
    
}
