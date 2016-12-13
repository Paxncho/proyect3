package animalcrush.model;

import animalcrush.FXAnimalCrushCanvas;
import java.util.Random;

/**
 * @author pencho
 */


public class AnimalCrush {

    private Dimension world;
    private Animal[][] board;
    private int animalsHorizontal;
    private int animalsVertical;
    private int score;
    private int multiplier;
    
    private int left;
    private int right;
    private int bottom;
    
    private FXAnimalCrushCanvas canvas;
    
    public AnimalCrush(int totalCandiesHorizontal, int totalCandiesVertical){
        this.score = 0;
        this.multiplier = 1;
        this.world = new Dimension(totalCandiesHorizontal, totalCandiesVertical);
        
        this.animalsHorizontal = totalCandiesHorizontal;
        this.animalsVertical = totalCandiesVertical;
        this.board = new Animal[totalCandiesVertical][totalCandiesHorizontal];
        
        //Inicilizar los dulces
        for (int i = 0; i < totalCandiesVertical; i++){
            for (int j = 0; j < totalCandiesHorizontal; j++){
                int k = (i + j/2)%9;
                switch(k){
                    case 0: case 6: this.board[i][j] = new Animal(j, i, AnimalType.CHICK); break;
                    case 1: case 7: this.board[i][j] = new Animal(j, i, AnimalType.FOX); break;
                    case 2: case 8: this.board[i][j] = new Animal(j, i, AnimalType.HEDGEHOG); break;
                    case 3: this.board[i][j] = new Animal(j, i, AnimalType.TIGER); break;
                    case 4: this.board[i][j] = new Animal(j, i, AnimalType.KOALA); break;
                    case 5: this.board[i][j] = new Animal(j, i, AnimalType.PIG); break;
                }                
            }
        }
    }
    
    public Dimension getWorld(){
        return this.world;
    }
    
    public Animal[][] getBoard(){
        return this.board;
    }
    
    public int getBoardWidth(){
        return this.animalsHorizontal;
    }
    
    public int getBoardHeight(){
        return this.animalsVertical;
    }
    
    public int getScore(){
        return this.score;
    }
    
    public void setScore(int score){
        this.score = score;
    }
    
    public void resetMultiplicator(){
        this.multiplier = 1;
    }
    
    private AnimalType getRandomAnimalType(){
        Random random = new Random();
        AnimalType type = AnimalType.FOX;
        
        switch(random.nextInt()%6){
            case 0: type = AnimalType.CHICK; break;
            case 1: type = AnimalType.KOALA; break;
            case 2: type = AnimalType.TIGER; break;
            case 3: type = AnimalType.HEDGEHOG; break;
            case 4: type = AnimalType.PIG; break;
        }

        return type;
    }
    
    //Reviso si el movimiento es válido
    public boolean checkMove(int x1, int y1, int x2, int y2, FXAnimalCrushCanvas canvas){
        this.canvas = canvas;
        boolean validMove = false;
        boolean Move1;
        boolean Move2;
        Movement move = null;
        if (x2 - x1 >= 1){
            move = Movement.RIGHT;
        } else if (x2 - x1 <= -1){
            move = Movement.LEFT;
        } else if (y2 - y1 >= 1){
            move = Movement.DOWN;
        } else if (y2 - y1 <= -1){
            move = Movement.UP;
        }

        AnimalType aux = null;

        if (move != null){
            validMove = true;
            switch(move){
                case RIGHT:
                    //Intercambio los dulces
                    aux = this.board[y1][x1].getType();
                    this.board[y1][x1].setType(this.board[y1][x1+1].getType());
                    this.board[y1][x1+1].setType(aux);
                    
                    Move1 = checkLine(x1, y1);
                    Move2 = checkLine(x1+1, y1);
                    //Checkeo si el movimiento es valido
                    if (Move1 || Move2)
                        validMove = true;
                    //Caso contrario, devuelvo los dulces
                    else{
                        aux = this.board[y1][x1].getType();
                        this.board[y1][x1].setType(this.board[y1][x1+1].getType());
                        this.board[y1][x1+1].setType(aux);
                    }

                    break;
                case LEFT:
                    aux = this.board[y1][x1].getType();
                    this.board[y1][x1].setType(this.board[y1][x1-1].getType());
                    this.board[y1][x1-1].setType(aux);

                    Move1 = checkLine(x1, y1);
                    Move2 = checkLine(x1-1, y1);
                    
                    if (Move1 || Move2)
                        validMove = true;
                    else{
                        aux = this.board[y1][x1].getType();
                        this.board[y1][x1].setType(this.board[y1][x1-1].getType());
                        this.board[y1][x1-1].setType(aux);
                    }

                    break;
                case UP:
                    aux = this.board[y1][x1].getType();
                    this.board[y1][x1].setType(this.board[y1-1][x1].getType());
                    this.board[y1-1][x1].setType(aux);

                    Move1 = checkLine(x1, y1);
                    Move2 = checkLine(x1, y1-1);
                    
                    if (Move1 || Move2)
                        validMove = true;
                    else {
                        aux = this.board[y1][x1].getType();
                        this.board[y1][x1].setType(this.board[y1-1][x1].getType());
                        this.board[y1-1][x1].setType(aux);
                    }

                    break;
                case DOWN:
                    aux = this.board[y1][x1].getType();
                    this.board[y1][x1].setType(this.board[y1+1][x1].getType());
                    this.board[y1+1][x1].setType(aux);

                    Move1 = checkLine(x1, y1);
                    Move2 = checkLine(x1, y1+1);
                    
                    if (Move1 || Move2)
                        validMove = true;
                    else {
                        aux = this.board[y1][x1].getType();
                        this.board[y1][x1].setType(this.board[y1+1][x1].getType());
                        this.board[y1+1][x1].setType(aux);
                    }
                    break;
            }

            if (validMove){
                this.refreshBoard();
                this.multiplier++;
            }
        }        
        return validMove;
    }
    
    private boolean checkLine(int x, int y){
        boolean line = false;
        int j = x;
        int index1 = 0;
        int index2 = 0;
        
        
        //Index1 Fila
        while(j >= 0){
            if(j == 0){
                index1 = j;
            }else if (!this.board[y][j].getType().equals(this.board[y][j-1].getType())){
                index1 = j;
                break;
            }
            
            j--;
        }
        
        j = x;
        //Index2 Fila
        while(j < this.animalsHorizontal){
            if(j == this.animalsHorizontal-1){
                index2 = j;
            }else if (!this.board[y][j].getType().equals(this.board[y][j+1].getType())){
                index2 = j;
                break;
            }
            
            j++;
        }
        
        if((index2 -  index1) > 1){
            for(int i = index1; i <= index2; i++)
                this.board[y][i].setInLine(true);
            line = true;
        }
        
        j = y;
        //Index1 Columna
        while(j >= 0){
            if(j == 0){
                index1 = j;
            }else if (!this.board[j][x].getType().equals(this.board[j-1][x].getType())){
                index1 = j;
                break;
            }
            
            j--;
        }
        
        j = y;
        //Index2 Columna
        while(j < this.animalsVertical){
            if(j == this.animalsVertical-1){
                index2 = j;
            }else if (!this.board[j][x].getType().equals(this.board[j+1][x].getType())){
                index2 = j;
                break;
            }
            
            j++;
        }
        
        if((index2 -  index1) > 1){
            for(int i = index1; i <= index2; i++)
                this.board[i][x].setInLine(true);
            line = true;
        }
        
        return line;
    }
    
    //Recorro el Array en busca de dulces por eliminar
    private void refreshBoard(){
        this.left = 9;
        this.right = 0;
        this.bottom = 0;
        
        for (int i = 0; i < this.animalsVertical; i++){
            for (int j = 0; j < this.animalsHorizontal; j++){
                
                //Si mi dulce está en linea, debe ser eliminado
                if (this.board[i][j].isInLine()){
                    //Añadir puntaje
                    this.score += 10 * this.multiplier;
                    
                    //Mover todos los dulces hacía abajo
                    for (int k = i; k >= 0; k--){
                        if (k != 0){
                            this.board[k][j].setType(this.board[k-1][j].getType());
                        } else {
                            //Si mi dulce está hasta arriba, creo un random
                            this.board[k][j].setType(this.getRandomAnimalType());
                        }
                    }                    
                    this.board[i][j].setInLine(false);
                }
            }
        }
        System.out.println("PUNTAJE: " + this.score);
    }
    
    private void checkNewBoard(){
        boolean runs = false;
        
        for (int i = 0; i < this.animalsVertical; i++){
            for (int j = 0; j < this.animalsHorizontal; j++){
                if(checkLine(j, i))
                    runs = true;
            }
        }
        if (runs)
            this.refreshBoard();
    }
}
