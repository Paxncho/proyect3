package animalcrush.model;

/**
 * @author Pencho
 */

public enum AnimalType{
    FOX("fox.png"),
    HEDGEHOG("hedgehog.png"),
    KOALA("koala.png"),
    CHICK("chick.png"),
    PIG("pig.png"),
    TIGER("tiger.png");

    private final String filename;

    private AnimalType(String filename){
        this.filename = filename;
    }

    public String getFilename(){
        return this.filename;
    }
}
