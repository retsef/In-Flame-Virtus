package GameObject;

import Utils.SpriteException;
import Utils.SpriteSheetLoader;
import Engine.*;
import java.awt.*;

/**
 * Realizza un tipo Wweapon (arma)
 * @author roberto
 */
public class Weapon {

    private int Damage,Velocity,Counter;
    private int x,y,mem_target_x,mem_target_y,target_x,target_y,height,width;
    private boolean attack,Fireball_moved;
    private float Direction;
    private Point target;
    private Rectangle Body;
    private SpriteSheetLoader Fireball;
    
    public Weapon() throws ValueErrorException, SpriteException {
        this(1);
    }
    
    /**
     * Crea un Weapon (arma)
     * @param pDamage Valore del danno dell'arma
     * @throws ValueErrorException Restituisce errore nel caso in cui il valore immesso per l'attacco non sia valido
     * @throws SpriteException Restituisce errore nel caso in cui ci siano problemi con il caricamento degli sprite
     */
    public Weapon(int pDamage) throws ValueErrorException, SpriteException {
        if (pDamage < 0)
            throw new ValueErrorException("Specifiche dell'arma errate");
        this.Damage = pDamage;
        
        //value start
        this.width = 28;
        this.height = 28;
        
        this.x = 0;
        this.y = 0;
        this.mem_target_x = 0;
        this.mem_target_y = 0;
        
        this.Velocity = 6;
        this.Direction = 0;
        this.Counter = 0;
        
        this.attack = false;
        this.Fireball_moved = false;
        this.target = new Point(0,0);
        this.Body = new Rectangle(this.x, this.y, this.width, this.height);
        
        this.Fireball = new SpriteSheetLoader(7,7,"/images/magic.png");
    }
    
    /**
     * Mantiene aggiornato il corpo del proiettile e la traiettoria
     */
    public void update() { //to use for weapon animation
        this.Body = new Rectangle(this.x, this.y, this.width, this.height);
        this.Reach();
    }
    
    /**
     * Restituisce la Fireball(Magia/proiettile)
     * @param pint indice dello sprite per l'immagine della magia
     * @return la sottoimmagine dello sprite
     * @throws SpriteException Nel caso in cui non esista la sottoimmagine a cui si vuole accedere
     */
    public Image Fireball(int pint) throws SpriteException {
        return this.Fireball.paint(pint);
    }
    
    /**
     * Restituisce la Fireball(Magia/proiettile)
     * @return la sottoimmagine dello sprite
     * @throws SpriteException Nel caso in cui non esista la sottoimmagine a cui si vuole accedere
     */
    public Image Fireball() throws SpriteException {
        return this.Fireball.paint(8);
    }
    
    /**
     * Permette di disegnare la Fireball(Magia/proiettile)
     * @param g 
     * @throws SpriteException Nel caso in cui non esista la sottoimmagine a cui si vuole accedere
     */
    public void Draw(Graphics2D g) throws SpriteException {
        if (this.Fireball_moved == true){
                g.drawImage(this.Fireball(),this.x,this.y,this.width,this.height,null);
        }
    }
    
    /**
     * Mantiene la traiettoria del proiettile quando si effettua un attacco
     */
    public void Reach() {
        if(this.attack){
            this.Fireball_moved=true;
            this.set_Target_mem(this.target);
            this.x = Game.get_player().get_X();
            this.y = Game.get_player().get_Y();
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
    
    /**
     * Controlla se il proiettile ha colpito il bersaglio
     * @return boolean a seconda dell'esito
     */
    private boolean is_Reached() {
        if(this.x==this.mem_target_x&&this.y==this.mem_target_y) {
            return true;
        } else {
            return false;
        }
    }
    
    /**
     * Imposta il valore di attacco dell'arma
     * @param pDamage valore di attacco dell'arma
     * @throws ValueErrorException Restituisce un Exception nel caso in cui il valore immesso sia errato o minore/uguale a zero
     */
    public void setDamage(int pDamage) throws ValueErrorException{
        if (pDamage<=0){
            throw new ValueErrorException("Valore di attacco dell'arma non valido");
        }
        this.Damage = pDamage;
    }

    /**
     * Verifica se si sta attaccando cen l'arma
     * @param pAttack boolean per settare lo stato di attacco
     */
    public void setAttack(boolean pAttack){
        this.attack = pAttack;
    }
    /**
     * Restituisce lo stato attivo dell'arma
     * @return boolean per sapere se si sta attaccando o no
     */
    public boolean getAttack() {
        return this.attack;
    }
    /**
     * Il costrutto rileva se il proiettile e' in movimento o no
     * @return Restituisce lo stato riguardo il movimento del proiettile
     */
    public boolean get_if_Fireball_moved(){
        return this.Fireball_moved;
    }
    
    /**
     * Il costrutto setta lo stato riguardo al movimento del proiettile
     * @param pMove Boolean per settare lo stato del movimento
     */
    public void set_if_Fireball_moved(boolean pMove){
        this.Fireball_moved = pMove;
    }

    /**
     * Ottiene il target (bersaglio)
     * @return Un Point contente la posizione del target
     */
    public Point getTarget() {
        return this.target;
    }
    /**
     * Setta il target (bersaglio)
     * @param pPoint Un Point contente la posizione del target
     */
    public void setTarget(Point pPoint) {
        this.target = pPoint;
    }
    /**
     * Permette di mantenere in memeoria la posizione del target
     * @param pPoint Un Point contente la posizione memorizzata del target
     */
    public void set_Target_mem(Point pPoint) {
        this.mem_target_x = pPoint.x;
        this.mem_target_y = pPoint.y;
    }
    /**
     * Restituisce il corpo del proiettile
     * @return Rectangle contenente il corpo del proiettile
     */
    public Rectangle getBody() {
        return this.Body;
    }

    /**
     * Restituisce la coordinata X del proiettile
     * @return Coordinata X
     */
    public int getX() {
        return this.x;
    }

    /**
     * Restituisce la coordinata Y del proiettile
     * @return Coordinata Y
     */
    public int getY() {
        return this.y;
    }
    /**
     * Setta la coordinata X del Proiettile
     * @param x Nuova coordinata X 
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * Setta la coordinata Y del Proiettile
     * @param y Nuova coordinata Y
     */
    public void setY(int y) {
        this.y = y;
    }
    
    
}
