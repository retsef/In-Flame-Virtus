package GameObject;

/**
 * L'Exception gestisce i volori di parametri errati
 * @author roberto
 */
public class ValueErrorException extends Exception {

    public ValueErrorException() {
        super("Valore immesso non valido");
    }

    public ValueErrorException(String pMessage) {
        super(pMessage);
    }
}
