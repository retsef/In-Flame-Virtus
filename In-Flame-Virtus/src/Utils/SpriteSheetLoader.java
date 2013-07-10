package Utils;

import Engine.InputErrorException;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 * La classe SpriteSheetLoader, scompatta e organizza immagini accorpate in gliglie
 * @author roberto
 */

public class SpriteSheetLoader {
   
   private BufferedImage spriteSheet;  
   private int width;
   private int height;
   private int rows;
   private int columns;
   private BufferedImage[] sprites;
   
   /**
    * @SpriteSheetLoader scompata un'immagine in in sotto immagini
    * @param prows Righe della griglia
    * @param pcolums Colonne della griglia
    * @param pLocation Indirizzo della locazione dell'immagine
    * @throws IOException Nel caso in cui si è impossibilitati a leggere l'immagine
    */
   
   public SpriteSheetLoader(int prows, int pcolums, String pLocation) throws IOException,InputErrorException {
      if (prows <=0) {
          throw new InputErrorException("Numero di colonne della griglia non valida");
      }
      if (pcolums<=0) {
          throw new InputErrorException("Numero di righe della griglia non valida");
      }
      
      this.rows = prows;
      this.columns = pcolums;
      this.sprites = new BufferedImage[this.rows * this.columns];
      this.spriteSheet = ImageIO.read(getClass().getResource(pLocation));
      this.height = (int)(this.spriteSheet.getTileHeight() / this.rows);
      this.width = (int)(this.spriteSheet.getTileWidth() / this.columns);
      
      for(int i = 0; i < this.rows; i++) {
         for(int j = 0; j < this.columns; j++) {
             this.sprites[(i * this.columns) + j] = this.spriteSheet.getSubimage(j * this.width, i * this.height, this.width, this.height);
         }
      }
      
   }
   
   /**
    * Preleva una delle sotto-immagini scompattate dalla griglia
    * @param pi Locazione del singolo elemento della griglia di immagini (pi = righe * colonne)
    * @return Restituisce un singolo elemento della griglia di immagini 
    */
   public BufferedImage paint(int pi) throws InputErrorException {
       if (pi < 0 || pi > (this.rows * this.columns))
           throw new InputErrorException("la sotto immagine a cui cerchi di accedere non esiste: n" + pi);     
       return this.sprites[pi];
   }
    /**
     * @getWidth Larghezza della sotto immagine
     * @getHeight Altezza della sotto immagine
     */
    public int getWidth() {
        return this.width;
    }

    public int getHeight() {
        return this.height;
    }
   
}