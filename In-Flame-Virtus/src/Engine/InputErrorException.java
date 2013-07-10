package Engine;

public class InputErrorException extends Exception {
    
    public InputErrorException() {
        super("Valore immesso non valido");
    }
    
    public InputErrorException(String pMessage) {
        super(pMessage);
    }
    
}
