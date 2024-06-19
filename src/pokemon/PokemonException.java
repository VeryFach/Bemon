package pokemon;


public class PokemonException extends Exception{
    
    private int errorCode;
    
    PokemonException(Exception e){
        super(e);
    }
    
    public PokemonException(String message) {
        super(message);
    }
    
    public PokemonException(String message, Throwable cause) {
        super(message, cause);
    }
    
    public String getDetailedMessage() {
        String detailedMessage = super.getMessage();
        if (errorCode != 0) {
            detailedMessage += " (Error Code: " + errorCode + ")";
        }
        return detailedMessage;
    }
}
