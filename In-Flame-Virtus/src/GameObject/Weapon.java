package GameObject;

import Utils.SpriteSheetLoader;
import Engine.*;
import java.awt.*;
import java.io.IOException;

public class Weapon {

    private int Damage,Velocity,Counter;
    private int x,y,mem_target_x,mem_target_y,height,width;
    private boolean attack,Fireball_moved;
    private float Direction;
    private Point target;
    private Rectangle Body;
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
        this.width = 28;
        this.height = 28;
        this.mem_target_x = 0;
        this.mem_target_y = 0;
        
        this.Velocity = 2;
        this.Direction = 0;
        this.Counter = 0;
        
        this.attack = false;
        this.Fireball_moved = false;
        this.target = new Point(0,0);
        this.Body = new Rectangle(this.x, this.y, this.width, this.height);
        
        try {
            this.Fireball = new SpriteSheetLoader(7,7,"/images/magic.png");
        } catch (IOException | InputErrorException ex) { }
    }
    
    public void update() { //to use for weapon animation
        this.Body = new Rectangle(this.x, this.y, this.width, this.height);
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
                g.drawImage(this.Fireball(),this.x,this.y,this.width,this.height,null);
        }
    }
    
    public void Reach() {
        if(this.attack){
            this.Fireball_moved=true;
            this.set_Target_mem(this.target);
            this.x = Instances.player.get_X();
            this.y = Instances.player.get_Y();
            this.Direction = (this.mem_target_y/this.mem_target_x);
        }else if(this.is_Reached()){
            this.Fireball_moved=false;
        }
        //if(!this.is_Reached()){
            if(this.mem_target_x < this.x){
                this.x -= this.Velocity;
            }else if(this.mem_target_x > this.x){
                this.x += this.Velocity;
            }
            if(this.mem_target_y < this.y){
                this.y -= this.Velocity;
            }else if(this.mem_target_y > this.y){
                this.y += this.Velocity;
            }
        //}
    }
    
    private boolean is_Reached() {
        if(this.x==this.mem_target_x&&this.y==this.mem_target_y) {
            return true;
        } else {
            return false;
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
    
    public int get_count_Attack() {
        return this.Counter;
    }
    
    public void set_count_Attack(int pCount) {
        this.Counter = pCount;
    }
    
    public boolean get_if_Fireball_moved(){
        return this.Fireball_moved;
    }
    
    public void set_if_Fireball_moved(boolean pMove){
        this.Fireball_moved = pMove;
    }

    public Point getTarget() {
        return this.target;
    }
    
    public void setTarget(Point pPoint) {
        this.target = pPoint;
    }
    
    public void set_Target_mem(Point pPoint) {
        this.mem_target_x = (int)pPoint.getX();
        this.mem_target_y = (int)pPoint.getY();
    }
    
    public Rectangle getBody() {
        return this.Body;
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
