package zoogame.animals;

public enum SizeClass {
    TINY,
    AVERAGE,
    BIG,
    MASSIVE;

    public String toString() {
        switch(this){
            case TINY:
                return "Tiny";
            case AVERAGE:
                return "Average";
            case BIG:
                return "Big";
            case MASSIVE:
                return "Massive";
            default:
                return "No size class defined";
        }
    }
}
