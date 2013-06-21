package GameObject;

import Engine.InputErrorException;
import Engine.Instances;
import Engine.SpriteSheetLoader;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.geom.Line2D;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Weapon {

    private int Damage,x,y,Velocity;
    private boolean attack;
    private Point target;
    private Line2D Range;
    private SpriteSheetLoader Fireball;
    
    public Weapon() throws ValueErrorException {
        this(1);
    }
    
    public Weapon(int pDamage) throws ValueErrorException {
        if (pDamage < 0)
            throw new ValueErrorException("Specifiche dell'arma errate");
        this.Damage = pDamage;
        this.x = 0;
        this.y = 0;
        this.Velocity = 15;
        this.attack = false;
        this.target = new Point(0,0);
        this.Range = new Line2D.Float(new Point(this.x, this.y), this.target);
        try {
            this.Fireball = new SpriteSheetLoader(7,7,"/images/magic.png");
        } catch (IOException | InputErrorException ex) {
            Logger.getLogger(Weapon.class.getName()).log(Level.SEVERE, null, ex);
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

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }
    
    public void update() { //to use for weapon animation
        this.Range = new Line2D.Float(Instances.player.getPoint_position(), this.target);
        Reach();
    }
    
    public Image Fireball(int pint) throws InputErrorException {
        return this.Fireball.paint(pint);
    }
    
    public Image Fireball() throws InputErrorException {
        return this.Fireball.paint(8);
    }
    
    public void Draw(Graphics2D g) throws InputErrorException, IOException {
        if (this.attack == true)
            g.drawImage(this.Fireball(),this.x+14,this.y+14,28,28,null);
        else {
            this.x = Instances.player.getX();
            this.y = Instances.player.getY();
        }
    }
    
    public void Reach() {
        if(this.target.getX() < this.x){
        this.x -= this.Velocity;
        }else if(this.target.getX() > this.x){
        this.x += this.Velocity;
        }
        
        if(this.target.getY() < this.y){
        this.y -= this.Velocity;
        }else if(this.target.getY() > this.y){
        this.y += this.Velocity;
        }
    }
    
    
    
}
