package pokemon;

public interface BattleAction {
    public default void basicAttack(Pokemon enemy){

    }
    public default void specialAttack(Pokemon enemy){

    }
    
    public default void elementalAttack(Element element, Pokemon enemy){

    }
    
    public default boolean tryToEscape(){
        return false;

    }
}