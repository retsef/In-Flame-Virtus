package GameObject;

import java.awt.Point;

public class Weapon {

    private int Damage;
    private boolean attack;
    private Point target;
    
    public Weapon() throws ValueErrorException {
        this(1);
    }
    
    public Weapon(int pDamage) throws ValueErrorException {
        if (pDamage < 0)
            throw new ValueErrorException("Specifiche dell'arma errate");
        this.Damage = pDamage;
        this.attack = false;
        this.target = new Point(0,0);
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
    
    public void setTarget(Point pPoint) {
        this.target = pPoint;
    }
    
}
