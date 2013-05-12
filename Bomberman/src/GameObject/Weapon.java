package GameObject;

public class Weapon extends Item {

    protected int Damage;
    protected int Range;
    
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

    private void place() {
        
    }
    
    @Override
    public void obtain() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void use() {
        
    }
    
}
