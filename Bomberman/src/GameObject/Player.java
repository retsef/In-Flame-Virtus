package GameObject;

import Utils.SpriteException;
import Utils.SpriteSheetLoader;
import Engine.*;
import java.awt.*;
import javax.swing.ImageIcon;

/**
 * Crea un tipo Player
 * Il Player e' il protagonista del gioco
 * @author roberto
 */

public class Player {
    //variables which we will use 
    private int x,y;
    private int width,height;
    private boolean  left, right, up, down;
    private String Name;
    private int Score,Life,Velocity,Direction,Direction_mem,Direction_final;
    private boolean isDamaged,isMoving;
    private Weapon Glove;
    private SpriteSheetLoader ActorWalk;
    private Image Heart;
    private Rectangle Body;
    private int Ap,Anim_player;
    private Animation_player animation_thread;
    //public ArrayList<Weapon> BundleGlove; //shadow glove
    /**
     * Crea un Player
     * @param pName Nome del giocatore
     * @throws SpriteException Restituisce un Exception se gli sprite sono errati
     */
    public Player (String pName) throws SpriteException{
        this.Name = pName;
        this.Score = 0;
        this.Life = 30;
        this.Velocity = 4;
        this.isDamaged = false;
        this.isMoving = false;
        this.height = 40;
        this.width = 40;

        this.Direction=1;
        this.Direction_mem=this.Direction;
        this.Direction_final=0;
        this.Ap = 0;
        this.Anim_player = 0;

        this.Body = new Rectangle(this.x, this.y, this.width, this.height);
        this.animation_thread = new Animation_player();
        try {
            //preset the propriety of the weapon
            this.Glove = new Weapon(1);
            //this.BundleGlove = new ArrayList<>();
        } catch (ValueErrorException ex) { }

        this.ActorWalk = new SpriteSheetLoader(8,12,"/images/Actor.png");
        this.Heart = new ImageIcon(getClass().getResource("/images/Heart.png")).getImage();

        this.spawn();
    }

    /**
     * Imagine gi un cuore per indicare i punti vita del player
     * @return L'immagine di un cuore
     */
    public Image Heart() {
        return this.Heart;
    }

    /**
     * Ottiene l'immagine dell'ombra sotto i piedi del Player
     * @return Sotto immagine contenente un alone per l'ombra
     * @throws SpriteException Restituisce un Exception se la sotto immagine e' errata
     */
    public Image Shadow() throws SpriteException{
        return this.ActorWalk.paint(69);
    }

    /**
     * Ottiene l'immagine del player mentre cammina
     * @return Sotto immagine contenente il player mentre cammina
     * @throws SpriteException Restituisce un Exception se la sotto immagine e' errata
     */
    public Image Walk() throws SpriteException{
        while(true){
        this.get_Walk_Direction();
        if (this.isDamaged) {
            return this.ActorWalk.paint(73);
        }
        return this.ActorWalk.paint(this.Direction_final);
        }
    }

    /**
     * Permette di girare il player in direzione del puntatore
     */
    //make player rotation animation
    private void get_Walk_Direction(){
        if ((this.get_Direction()>338 && this.get_Direction()<=0) || (this.get_Direction()>0 && this.get_Direction()<=23) ){
            if (this.Direction!=1){
                this.Direction=1;
                this.Direction_final = this.Direction;
            }
        } else if (this.get_Direction()>23 && this.get_Direction()<=68){
            if (this.Direction!=28){
                this.Direction=28;
                this.Direction_final = this.Direction;
            }
        } else if (this.get_Direction()>68 && this.get_Direction()<=113){
            if (this.Direction!=25){
                this.Direction=25;
                this.Direction_final = this.Direction;
            }
        } else if (this.get_Direction()>113 && this.get_Direction()<=158){
            if (this.Direction!=40){
                this.Direction=40;
                this.Direction_final = this.Direction;
            }
        } else if ((this.get_Direction()>158 && this.get_Direction()<=180) || (this.get_Direction()>180 &&this.get_Direction()<=203)){
            if (this.Direction!=37){
                this.Direction=37;
                this.Direction_final = this.Direction;
            }
        } else if (this.get_Direction()>203 && this.get_Direction()<=248){
            if (this.Direction!=16){
                this.Direction=16;
                this.Direction_final = this.Direction;
            }
        } else if (this.get_Direction()>248 && this.get_Direction()<=293){
            if (this.Direction!=13){
                this.Direction=13;
                this.Direction_final = this.Direction;
            }
        } else if (this.get_Direction()>293 && this.get_Direction()<=338){
            if (this.Direction!=4){
                this.Direction=4;
                this.Direction_final = this.Direction;
            }
        } else {
        }
    }

    /**
     * Restituisce l'angolo di visione del player rispetto al puntatore del mouse
     * @return Angolo del vettore player-puntatore
     */
    private int get_Direction(){
        double angle;
        angle = Math.toDegrees(Math.atan2(this.Glove.getTarget().getX()-(this.x+16),this.Glove.getTarget().getY()-(this.y+16)));
        if (angle < 0){
            angle += 360;
        }
        return (int)angle;
    }

    /**
     * Rileva se si e' stati attaccati da un Mob e ne provoca conseguenze
     * @param pMob Mob attaccante
     */
    public void get_Damage_from(Mob pMob) {
        if (pMob.Attack()) {
            this.bounce();
            this.Life -= pMob.getDamage();
        }else{ }
    }

    /**
     * Rileva se si ha attaccato un Mob e ne provoca le conseguenze
     * @param pMob Mob attaccato
     * @throws SpriteException Restituisce un Exception se la sotto immagine e' errata
     */
    public void Damage_a_Mob(Mob pMob) throws SpriteException {
        if(pMob.getBody().intersects(this.Glove.getBody())){
            pMob.Damaged();
            this.Glove.setX(-30);
            this.Glove.setY(-30);
            this.Glove.set_Target_mem(new Point(-30,-30));
            this.increaseScore(pMob);
        } else {}
    }

    /**
     * Incrementa il punteggio in base al Mob colpito
     * @param pMob Mob attaccato
     */
    private void increaseScore(Mob pMob) {
        if (pMob.isDead()) {
            this.Score += pMob.getPoint();
        }
    }

    /**
     * Il Costrutto mantiene aggiornate le azioni e specifiche del Player
     */
    //In this function we will do the required checking and updates
    public void update(){
       this.move();
       this.Body = new Rectangle(this.x, this.y, this.width, this.height);
       this.Glove.update();
       Instances.game.get_meccanics().run();
       this.isDead();
       
     }
/*
    private void Shadow_Glove(){ //bundle of weapon trowed
        if(this.Glove.getAttack()){
            this.BundleGlove.add(this.Glove);
        }
    }
*/   
    /**
     * La funzione permette al Player di muoversi
     */
    //up-down e left-rigth separati per i movimenti diagonali
    private void move(){
       if(up){
          this.y -= this.Velocity;
          this.isMoving=true;
       }else if(down){
          this.y += this.Velocity;
          this.isMoving=true;
       }else{
           this.isMoving=false;
       }
       
       if(left){
          this.x -= this.Velocity;
          this.isMoving=true;
       }else if(right){
          this.x += this.Velocity;
          this.isMoving=true;
       }else{
          this.isMoving=false;
       }
    }
    /**
     * Estrapola i punti Vita del Player
     * @return Life in String
     */
    public String getLife() {
        String strng;
        strng = "" + this.Life;
        return strng;
    }
    /**
     * Estrapola il Punteggio del Player
     * @return Score in String
     */
    public String getScore() {
        String strng;
        strng = "" + this.Score;
        return strng;
    }
    /**
     * Estrapola il nome del Player
     * @return Nome in String
     */
    public String getName() {
        String strng;
        strng = "" + this.Name;
        return strng;
    }

    //These 4 functions are able to set the direction
    /**
     * Impone al Player di andare a sinistra
     * @param pLeft Boolean per impostare l'esito
     */
    public void setLeft (boolean pLeft){
       this.left  = pLeft; 
    }
    /**
     * Impone al Player di andare su
     * @param pUp Boolean per impostare l'esito
     */
    public void setUp   (boolean pUp){
       this.up    = pUp;   
    }
    /**
     * Impone al Player di andare giu
     * @param pDown Boolean per impostare l'esito
     */
    public void setDown (boolean pDown){
       this.down  = pDown; 
    }
    /**
     * Impone al Player di andare a destra
     * @param pRight Boolean per impostare l'esito
     */
    public void setRight(boolean pRight){
       this.right = pRight;
    }

    /**
     * Imposta il nome del player
     * @param pString Stringa contenente il nome
     */
    public void setName(String pString){
        if(pString!=null){
        this.Name = pString;
        }
    }
    
    /**
     * Permette la nascita sul terreno di gioco
     */
    private void spawn(){
        this.x = Game.WIDTH/2; 
        this.y = Game.HEIGTH/2;
    }
    /**
     * Simula lo spostamento dovuto al colpo ricevuto
     */
    private void bounce(){
        this.x += (int) ( ((-1)^((int)Math.random()*2)) *(Math.random()*40));
        this.y += (int) ( ((-1)^((int)Math.random()*2)) *(Math.random()*40));
    }

    /**
     * Restituisce la coordinata X del Player
     * @return Coordinata X
     */
    public int get_X(){
       return this.x;
    }
    /**
     * Restituisce la coordinata Y del Player
     * @return Coordinata Y
     */
    public int get_Y(){
       return this.y;
    }

    /**
     * Restituisce l'altezza del Player
     * @return Altezza
     */
     public int get_height() {
         return this.height;
     }
     /**
     * Restituisce la larghezza del Player
     * @return Larghezza
     */
     public int get_width() {
         return this.width;
     }
     /**
      * Restituisce il corpo del player
      * @return Rectangle contenente il corpo
      */
     public Rectangle get_Body(){
         return this.Body;
     }

     /**
      * Restituisce la Weapon del Player
      * @return Weapon
      */
     public Weapon get_Glove(){
         return this.Glove;
     }
     /**
     * Costrutto che disegna gli elementi che compongono un Player
     * @param g Oggetto che contiene i metodi grafici
     * @throws SpriteException Restituisce un Exception se la sotto immagine e' errata
     */
     public void Draw(Graphics2D g) throws SpriteException{
        g.drawImage(this.Shadow(),this.x+1,this.y+5,this.width,this.height, null);
        g.drawImage(this.Walk(),this.x,this.y,this.width,this.height, null);
     }

     /**
     * Costrutto per switchare tra le varie sotto immagini per creare l'animazione del passo
     */
     public void moving() {
         if(this.isMoving){
             if (this.Ap<1) {
                 //this.Anim_player = 1;
                 this.Ap += 1;
             } else if (this.Ap>=1) {
                 //this.Anim_player = -1;
                 this.Ap = -1;
             }

             if (this.Ap==0){
                 this.Ap=1;
             }
             /*
             int Ap_1,Ap_2,Ap_3;
             Ap_1=1;
             Ap_2=-2;
             Ap_3=1;
             
             
             if (this.Direction_mem!=this.Direction){
                this.Anim_player=0;
             }else if(this.Anim_player==0){
                 this.Anim_player++;
                 this.Ap=Ap_1;
             }else if(this.Anim_player==1){
                 this.Anim_player++;
                 this.Ap=Ap_2;
             }else if(this.Anim_player==2){
                 this.Anim_player=0;
                 this.Ap=Ap_3;
             }
             
             //System.out.println(this.Direction_final+","+this.Ap);
             this.Direction_mem=this.Direction;*/
             this.Direction_final += this.Ap;
             } else {}
     }

     /**
      * Verifica la morte del Player
      */
     public void isDead(){
         if (this.Life<=0)
             Instances.game.setGameOver(true);
     }
     /**
      * Thread dell' animazione
      */
     private class Animation_player implements Runnable {

         private long sleep_animation = 100;
         private Thread thread;

         public Animation_player(){
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
             } catch (InterruptedException ex) {
             }
         }

         /**
          * Avvia il thread
          */
         public void start() {
             stop();
             thread = new Thread(this,"Animation Walk-Player");
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

