package zoogame.animals;

public enum AnimalType {
    MAMMAL,
    BIRD,
    REPTILE,
    INSECT;

    public String toString() {
        switch(this){
            case MAMMAL:
                return "Mammal";
            case BIRD:
                return "Bird";
            case INSECT:
                return "Insect";
            case REPTILE:
                return "Reptile";
            default:
                return "No type defined";
        }
    }
}
