package GameObject;

import Utils.SpriteException;
import Utils.SpriteSheetLoader;
import Engine.*;
import Utils.Sound;
import Utils.SoundException;
import java.awt.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * La Classe crea un tipo Mob (Nemico/Monster)
 * @author roberto
 */
public class Mob {
    
    private int x,y;
    private int width,height;
    private int Life,Velocity,Point,Direction,Direction_final,Damage;
    private int type;
    private float angle;
    private SpriteSheetLoader MobWalk,MobWalk_shadow;
    private boolean up,down,right,left;
    private Rectangle Body;
    private boolean isMoving;
    private int Am,Anim_Mob;
    private Animation_mob animation_thread;
    private Sound mob_sound;
    
    public Mob() throws SpriteException, SoundException{
        this(2,200,2,1);
    }
    /**
     * Costrutto per la creazione di un Mob
     * @param pVelocity Velocita' di movimento
     * @param pPoint Punti da assegnare al player in caso di morte
     * @param pLife Punti vita del Mob
     * @param pDamage Potenza di attacco del Mob
     * @throws SpriteException Restituisce un Exception se gli sprite sono inaccesibili
     * @throws SoundException Restituisce un Exception se il Suono e' inaccessibile
     */
    public Mob(int pVelocity, int pPoint, int pLife, int pDamage) throws SpriteException, SoundException{
        this.Velocity = pVelocity;
        this.Point = pPoint;
        this.Life = pLife;
        this.Damage = pDamage;
        this.Direction = 0;
        this.Direction_final = 0;
        this.type = 0;
        this.height = 40;
        this.width = 40;
        this.Am = 0;
        this.Anim_Mob = 0;
        this.angle = 0;
        
        this.isMoving = true;
        
        this.Body = new Rectangle(this.x, this.y, this.width, this.height);
        this.animation_thread = new Animation_mob();
        
        this.mob_sound = new Sound("/sounds/Mob_Sound.wav");
        
        this.MobWalk = new SpriteSheetLoader(8,12,"/images/Mob.png");
        this.MobWalk_shadow = new SpriteSheetLoader(8,12,"/images/Actor.png");
        
        this.spawn();
    }
    /**
     * Il Costrutto permette al Mob di rincorrere il Player
     */
    private void move() {
        if(this.isMoving==true){
            //detect the position of the player and try to reatch
            if(Game.get_player().get_X() < this.x){
                this.x--;
                this.left = true;
                this.right = false;
                this.up = false;
                this.down = false;
            }else if(Game.get_player().get_X() > this.x){
                this.x++;
                this.right = true;
                this.left = false;
                this.up = false;
                this.down = false;
            }
            if(Game.get_player().get_Y() < this.y){
                this.y--;
                this.up = true;
                this.down = false;
                this.left = false;
                this.right = false;
            }else if(Game.get_player().get_Y() > this.y){
                this.y++;
                this.down = true;
                this.up = false;
                this.left = false;
                this.right = false;
            }
        } else {}
    }
    /**
     * Il Costrutto mantiene aggiornate le azioni e specifiche del Mob
     */
    public void update() {
      this.Body = new Rectangle(this.x, this.y, this.width, this.height);
      this.move();
    }
    /**
     * Il Costrutto ottiene l'immagine esatta da visualizzare in base alla direzione in cui si muove il Mob
     */
    private void getDirectionWalk() {
        if(this.down==true){
            if(this.Direction!=1){
                this.Direction = 1;
                this.Direction_final = (this.type + this.Direction);
            }
        }else if(this.left==true){
            if(this.Direction!=13){
                this.Direction = 13;
                this.Direction_final = (this.type + this.Direction);
            }
        }else if(this.right==true){
            if(this.Direction!=25){
                this.Direction = 25;
                this.Direction_final = (this.type + this.Direction);
            }
        }else if (this.up==true){
            if(this.Direction!=37){
                this.Direction = 37;
                this.Direction_final = (this.type + this.Direction);
            }
        } else {}
    }

    /**
     * Restituisce il danno da infliggere
     * @return Valore del danno
     */
    public int getDamage() {
        return this.Damage;
    }
    /**
     * Imposta l'ombra sotto i piedi del Mob
     * @return Sotto immagine contenente un alone per l'ombra
     * @throws SpriteException Restituisce un Exception se la sotto immagine e' errata
     */
    public Image Shadow() throws SpriteException{
       return this.MobWalk_shadow.paint(69);
   }
   /**
    * Imposta le immagini del Mob
    * @return Restituisce un immagine da affiliare al Mob
    * @throws SpriteException Restituisce un Exception se la sotto immagine e' errata
    */
    public Image Walk() throws SpriteException{
        while(true){
        this.getDirectionWalk();
        return this.MobWalk.paint(this.Direction_final);
        }
    }
    /**
     * Costrutto che serve in caso di danno al Mob
     */
    public void Damaged() {
        this.Life--;
        if (this.isDead()==true){
            try {
                this.mob_sound.play();
            } catch (SoundException ex) {
                Logger.getLogger(Mob.class.getName()).log(Level.SEVERE, null, ex);
            }
            this.spawn();
        }
    }
    /**
     * Il Costrutto verifica se il Mob e' andato in contatto con il Player
     * @param pPlayer Player da analizzare
     * @return Boolean per l'esito
     */
    private boolean isNearPlayer(Player pPlayer) {
        if(this.Body.intersects(pPlayer.get_Body()))
            return true;
        else
            return false;
    }
    /**
     * Il Costrutto permette al Mob di attaccare il Player
     * @return Boolean per l'esito
     */
    public boolean Attack() {
        if (this.isNearPlayer(Game.get_player())==true) {
            return true;
        }else{
            return false;
        }
    }
    /**
     * Restituisce i punti da assegnare al Player
     * @return valore dei punti
     */
    public int getPoint() {
        return this.Point;
    }
    /**
     * Il Costrutto ottiene il Corpo del Mob
     * @return Rectangle contenente il Mob
     */
    public Rectangle getBody() {
        return this.Body;
    }
    /**
     * Verifica se il Mob e' deceduto
     * @return Boolean per l'esito
     */
    public boolean isDead() {
        if (this.Life > 0)
            return false;
        else
            return true;
    }
    /**
     * Permette la rinascita del Mob in un punto casuale dello schermo
     */
    private void spawn(){
        this.x = (int)(Math.random() * Game.WIDTH);
        this.y = (int)(Math.random() * Game.HEIGTH);
        
        int phope = (int)(Math.random() * 4);
        this.type = 3*(phope);
    }

    /**
     * Setta la coordinata X del Mob
     * @param px Nuova coordinata X 
     */
    public void setX(int px) {
        this.x = px;
    }
    /**
     * Setta la coordinata Y del Mob
     * @param py Nuova coordinata Y
     */
    public void setY(int py) {
        this.y = py;
    }
    /**
     * Determina se il Mob deve muoversi o meno
     * @param pmove Boolean per impostare l'esito
     */
    public void setMoving(boolean pmove) {
        this.isMoving = pmove;
    }
    /**
     * Costrutto per switchare tra le varie sotto immagini per creare l'animazione del passo
     */
    public void moving() {
       if(this.isMoving != false){
            if (this.Am<1) {
               this.Am += 1;
             } else if (this.Am>=1) {
               this.Am = -1;
             }
            
            if(this.Am==0){
                this.Am=1;
            }
           /*
           int Am_1,Am_2,Am_3;
             Am_1=1;
             Am_2=-2;
             Am_3=1;
             
             if(this.Anim_Mob==0){
                 this.Anim_Mob++;
                 this.Am=Am_1;
             }else if(this.Anim_Mob==1){
                 this.Anim_Mob++;
                 this.Am=Am_2;
             }else if(this.Anim_Mob==2){
                 this.Anim_Mob=0;
                 this.Am=Am_3;
             }*/
                this.Direction_final += this.Am;
            } else {}
       
    }

    /**
     * Costrutto che disegna gli elementi che compongono un Mob
     * @param g Oggetto che contiene i metodi grafici
     * @throws SpriteException Restituisce un Exception se la sotto immagine e' errata
     */
    public void Draw(Graphics2D g) throws SpriteException{
        g.drawImage(this.Shadow(),this.x+1,this.y+5,this.width,this.height,null);
        g.drawImage(this.Walk(),this.x,this.y,this.width,this.height,null);
    }
    
    /**
     * Thread dell' animazione
     */
    private class Animation_mob implements Runnable {

        private long sleep_animation = 120;
        private Thread thread;

        public Animation_mob(){
            this.start();
        }
        
        @Override
        public void run() {
            try {
                while (true) {
                    moving();
                    //System.gc();
                    Thread.sleep(this.sleep_animation);
                }
            } catch (InterruptedException ex) { }
        }

        /**
         * Avvia il thread
         */
        public void start() {
            stop();
            thread = new Thread(this,"Animation Walk-Mob");
            thread.start();
        }

        /**
         * Ferma il thread
         */
        public void stop() {
            if (thread != null && thread.isAlive()) {
                thread.interrupt();
            }
        }
    }
    
}
