package Engine;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class SpriteSheetLoader {
   
   private BufferedImage spriteSheet;  
   private int width;
   private int height;
   private int rows;
   private int columns;
   private BufferedImage[] sprites;
   
   /**
    * @SpriteSheetLoader scompata un'immagine in in sotto immagini
    * @param pwidth: larghezza della sotto immagine
    * @param pheight: altezza della sotto immagine
    * @param prows: righe della griglia
    * @param pcolums: colonne della griglia
    * @param pLocation: indirizzo della locazione dell'immagine
    * @throws IOException 
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
      //this.spriteSheet = Resources.getImage(pLocation);
      this.height = (int)(this.spriteSheet.getTileHeight() / this.rows);
      this.width = (int)(this.spriteSheet.getTileWidth() / this.columns);
      
      for(int i = 0; i < this.rows; i++) {
         for(int j = 0; j < this.columns; j++) {
            //this.sprites[(j * this.rows) + i] = this.spriteSheet.getSubimage(i * this.width, j * this.height, this.width, this.height);
             this.sprites[(i * this.columns) + j] = this.spriteSheet.getSubimage(j * this.width, i * this.height, this.width, this.height);
         }
      }
      
   }
    
   public BufferedImage paint(int pi) throws InputErrorException {
       if (pi < 0 || pi > (this.rows * this.columns))
           throw new InputErrorException("la sotto immagine a cui cerchi di accedere non esiste");     
       return this.sprites[pi];
   }

    public int getWidth() {
        return this.width;
    }

    public int getHeight() {
        return this.height;
    }
   
}