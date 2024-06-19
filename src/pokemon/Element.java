package pokemon;

public enum Element {
    FIRE, WIND, WATER, ICE, EARTH;

    public boolean isStrongerThan(Element other) {
        switch (this) {
            case FIRE:
                return other == ICE;
            case WIND:
                return other == EARTH;
            case WATER:
                return other == FIRE;
            case ICE:
                return other == WIND;
            case EARTH:
                return other == WATER;
            default:
                return false;
        }
    }
}