package GameObject;

public class Weapon {

    protected int Damage;
    protected int Range;
    private boolean attack;
    private int x,y;
    
    public Weapon() throws ValueErrorException {
        this(0,0);
    }
    
    public Weapon(int pDamage,int pRange) throws ValueErrorException {
        if (pDamage < 0 || pRange < 0)
            throw new ValueErrorException("Specifiche dell'arma errate");
        this.Damage = pDamage;
        this.Range = pRange;
        this.attack = false;
    }
    
    
    public void setDamage(int pDamage) {
        this.Damage = pDamage;
    }

    public void setRange(int pRange) {
        this.Range = pRange;
    }

    public void setAttack(boolean pAttack) {
        //to do action
        this.attack = pAttack;
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
