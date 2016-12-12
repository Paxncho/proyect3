package animalcrush.model;

/**
 * @author Pencho
 */

public class Animal{

    private AnimalType type;
    private int x;
    private int y;
    private boolean inLine;

    public Animal(int x, int y, AnimalType type){
        this.x = x;
        this.y = y;
        this.type = type;
        this.inLine = false;
    }

    public AnimalType getType(){
        return type;
    }

    public void setType(AnimalType type){
        this.type = type;
    }

    public int getX(){
        return x;
    }

    public void setX(int x){
        this.x = x;
    }

    public int getY(){
        return y;
    }

    public void setY(int y){
        this.y = y;
    }
    
    public boolean isInLine(){
        return this.inLine;
    }
    
    public void setInLine(boolean inLine){
        this.inLine = inLine;
    }
    
}
