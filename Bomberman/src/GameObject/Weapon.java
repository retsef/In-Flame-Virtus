package GameObject;

import Engine.*;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.geom.Line2D;
import java.io.IOException;

public class Weapon {

    private int Damage;
    private int x,y;
    private boolean attack,Fireball_moved;
    private Point target;
    private Line2D View_Range;
    private SpriteSheetLoader Fireball;
    
    public Weapon() throws ValueErrorException {
        this(1);
    }
    
    public Weapon(int pDamage) throws ValueErrorException {
        if (pDamage < 0)
            throw new ValueErrorException("Specifiche dell'arma errate");
        this.Damage = pDamage;
        
        //value start
        this.x = 0;
        this.y = 0;
        this.attack = false;
        this.Fireball_moved = false;
        this.target = new Point(0,0);
        
        this.View_Range = new Line2D.Float(new Point(this.x, this.y), this.target);
        try {
            this.Fireball = new SpriteSheetLoader(7,7,"/images/magic.png");
        } catch (IOException | InputErrorException ex) { }
    }
    
    public void update() { //to use for weapon animation
        this.View_Range = new Line2D.Float(Instances.player.getPoint_position(), this.target);
        this.Reach();
    }
    
    public Image Fireball(int pint) throws InputErrorException {
        return this.Fireball.paint(pint);
    }
    
    public Image Fireball() throws InputErrorException {
        return this.Fireball.paint(8);
    }
    
    public void Draw(Graphics2D g) throws InputErrorException, IOException {
        if (this.Fireball_moved == true){
                g.drawImage(this.Fireball(),this.x+14,this.y+14,28,28,null);
        }
    }
    
    public void Reach() {
        if(this.attack){
            this.Fireball_moved=true;
            while(this.x==this.target.getX()&&this.y==this.target.getY()){
                if(this.target.getX() < this.x){
                this.x--;
                }else if(this.target.getX() > this.x){
                this.x++;
                }

                if(this.target.getY() < this.y){
                this.y--;
                }else if(this.target.getY() > this.y){
                this.y++;
                }
            }
        }else{
            this.Fireball_moved=false;
            this.x = Instances.player.getX();
            this.y = Instances.player.getY();
        }
    }
    
    public void setDamage(int pDamage) {
        this.Damage = pDamage;
    }

    public void setAttack(boolean pAttack) {
        this.attack = pAttack;
    }
    
    public boolean getAttack() {
        return this.attack;
    }

    public Point getTarget() {
        return this.target;
    }
    
    public void setTarget(Point pPoint) {
        this.target = pPoint;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }
    
    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }
    
    
}
