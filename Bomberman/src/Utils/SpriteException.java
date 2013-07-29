package Utils;

/**
 * L'exception gestisce le anomalie correlate agli Sprite
 * @author roberto
 */
public class SpriteException extends Exception {
    
    public SpriteException() {
        super("Impossibile preparare lo Sprite");
    }
    
    public SpriteException(String pMessage) {
        super(pMessage);
    }
    
}
