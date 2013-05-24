package GameObject;

public class Weapon {

    protected int Damage;
    protected int Range;
    public boolean attack;
    public int x,y;
    
    public Weapon() {
        this.Damage = 0;
        this.Range = 0;
    }
    
    public void setDamage(int pDamage) {
        this.Damage = pDamage;
    }

    public void setRange(int pRange) {
        this.Range = pRange;
    }

    public void setAttack(boolean pAttack) {
        this.attack = pAttack;
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
