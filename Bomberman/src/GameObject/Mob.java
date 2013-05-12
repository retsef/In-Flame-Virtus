package GameObject;

public class Mob {
    
    private int Life;
    private int Velocity;
    private int Point;
    
    public Mob() {
        this(1, 20);
    }
    
    public Mob(int pVelocity, int pPoint) {
        this.Velocity = pVelocity;
        this.Point = pPoint;
        this.Life = 1;
    }
    
    public boolean isDead() {
        if (this.Life > 0)
            return true;
        else
            return false;
    }

    public int getPoint() {
        return this.Point;
    }
    
}
