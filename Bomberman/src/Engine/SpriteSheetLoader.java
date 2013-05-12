package Engine;

import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

public class SpriteSheetLoader {
   
   BufferedImage spriteSheet;  
   int width;
   int height;
   int rows;
   int columns;
   BufferedImage[] sprites;
   
   public SpriteSheetLoader(int pwidth, int pheight, int prows, int pcolumns, String pLocation) throws IOException {
      this.width = pwidth;
      this.height = pheight;
      this.rows = prows;
      this.columns = pcolumns;
      this.sprites = new BufferedImage[this.rows * this.columns];
      this.spriteSheet = ImageIO.read(getClass().getResource(pLocation));
      
      for(int i = 0; i < this.rows; i++) {
         for(int j = 0; j < this.columns; j++) {
            this.sprites[(i * this.columns) + j] = this.spriteSheet.getSubimage(i * this.width, j * this.height, this.width, this.height);
         }
      }
      
   }

    public SpriteSheetLoader() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
   public BufferedImage paint(int pi) {
            return this.sprites[pi];
   }
}