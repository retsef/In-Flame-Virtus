package GameObject;

import java.awt.Point;

public class Weapon {

    private int Damage;
    private boolean attack;
    private int x,y;
    private Point target;
    
    public Weapon() throws ValueErrorException {
        this(1);
    }
    
    public Weapon(int pDamage) throws ValueErrorException {
        if (pDamage < 0)
            throw new ValueErrorException("Specifiche dell'arma errate");
        this.Damage = pDamage;
        this.attack = false;
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

    public Point getPoint() {
        return this.target;
    }
    
    public void update() {
        this.target = new Point(this.getX()+14,this.getY()+14);
    }
    
    public int getX() {
        return  x;
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
