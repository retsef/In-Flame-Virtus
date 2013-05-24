package GameObject;

public class ValueErrorException extends Exception {

    public ValueErrorException() {
        super("Valore immesso non valido");
    }

    public ValueErrorException(String pMessage) {
        super(pMessage);
    }
}
