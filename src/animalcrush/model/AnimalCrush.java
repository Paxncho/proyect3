package animalcrush.model;

import java.util.Random;

/**
 * @author pencho
 */


public class AnimalCrush {

    private Dimension world;
    private Animal[][] board;
    private int totalCandiesHorizontal;
    private int totalCandiesVertical;
    
    private boolean running;
    
    public AnimalCrush(int totalCandiesHorizontal, int totalCandiesVertical){
        this.world = new Dimension(totalCandiesHorizontal, totalCandiesVertical);
        
        this.totalCandiesHorizontal = totalCandiesHorizontal;
        this.totalCandiesVertical = totalCandiesVertical;
        this.board = new Animal[totalCandiesVertical][totalCandiesHorizontal];
        
        //Inicilizar los dulces
        for (int i = 0; i < totalCandiesVertical; i++){
            for (int j = 0; j < totalCandiesHorizontal; j++){
                this.board[i][j] = this.getRandomAnimal(j, i);
            }
        }
        
        this.running = false;        
    }
    
    public Dimension getWorld(){
        return this.world;
    }
    
    public boolean isRunning(){
        return this.running;
    }
    
    public void setRunning(boolean running){
        this.running = running;
    }
    
    public Animal[][] getBoard(){
        return this.board;
    }
    
    public int getBoardWidth(){
        return this.totalCandiesHorizontal;
    }
    
    public int getBoardHeight(){
        return this.totalCandiesVertical;
    }
    
    private Animal getRandomAnimal(int x, int y){
        Random random = new Random();
        AnimalType type = AnimalType.FOX;
        
        switch(random.nextInt()%6){
            case 0: type = AnimalType.HIPPOPOTAMUS; break;
            case 1: type = AnimalType.KOALA; break;
            case 2: type = AnimalType.TIGER; break;
            case 3: type = AnimalType.HEDGEHOG; break;
            case 4: type = AnimalType.PIG; break;
        }

        return new Animal(x, y, type);
    }
    
    public boolean checkMove(int x1, int y1, int x2, int y2){
        Boolean validMove = false;
        if (x2 - x1 != y2 - y1 && this.isRunning()){
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
            
            Animal aux = null;
            
            if (move != null){
                showBoard();
                switch(move){
                    case RIGHT:
                        //Intercambio los dulces
                        aux = this.board[y1][x1];
                        this.board[y1][x1] = this.board[y1][x1+1];
                        this.board[y1][x1+1] = aux;
                        showBoard();
                        
                        //Checkeo si el movimiento es valido
                        if (check3OnLine(x1, y1) || check3OnLine(x1+1, y1))
                            validMove = true;
                        //Caso contrario, devuelvo los dulces
                        else{
                            aux = this.board[y1][x1];
                            this.board[y1][x1] = this.board[y1][x1+1];
                            this.board[y1][x1+1] = aux;
                        }
                        
                        break;
                    case LEFT:
                        aux = this.board[y1][x1];
                        this.board[y1][x1] = this.board[y1][x1-1];
                        this.board[y1][x1-1] = aux;
                        showBoard();
                        
                        if (check3OnLine(x1, y1) || check3OnLine(x1-1, y1))
                            validMove = true;
                        else{
                            aux = this.board[y1][x1];
                            this.board[y1][x1] = this.board[y1][x1-1];
                            this.board[y1][x1-1] = aux;
                        }
                        
                        break;
                    case UP:
                        aux = this.board[y1][x1];
                        this.board[y1][x1] = this.board[y1-1][x1];
                        this.board[y1-1][x1] = aux;
                        showBoard();
                        
                        if (check3OnLine(x1, y1) || check3OnLine(x1, y1-1))
                            validMove = true;
                        else {
                            aux = this.board[y1][x1];
                            this.board[y1][x1] = this.board[y1-1][x1];
                            this.board[y1-1][x1] = aux;    
                        }
                        
                        break;
                    case DOWN:
                        aux = this.board[y1][x1];
                        this.board[y1][x1] = this.board[y1+1][x1];
                        this.board[y1+1][x1] = aux;
                        showBoard();
                        
                        if (check3OnLine(x1, y1) || check3OnLine(x1, y1+1))
                            validMove = true;
                        else {
                            aux = this.board[y1][x1];
                            this.board[y1][x1] = this.board[y1+1][x1];
                            this.board[y1+1][x1] = aux;    
                        }
                        break;
                }
                
                if (validMove){
                    refreshBoard();
                }
            }
        }
        
        return validMove;
    }
    
    private boolean check3OnLine(int x, int y){ //Revisa si hay dulces por eliminar
        boolean validMove = false;
        
        //Revisar vertical
        if (y > 0 && y < this.totalCandiesVertical-1 && x >=0 && x < totalCandiesHorizontal){
            if (this.board[y][x].getType().equals(this.board[y-1][x].getType()) && this.board[y][x].getType().equals(this.board[y+1][x].getType())){
                
                //Marcar actual
                this.board[y][x].setInLine(true);
                
                //Revisar vecinos en caso de que se junten más de 3 del mismo tipo, checkar si ya no he pasado por ellos
                if (!this.board[y-1][x].isInLine())
                    check3OnLine(x, y-1);
                if (!this.board[y+1][x].isInLine())
                    check3OnLine(x, y+1);
                
                //Marcar los que se van a eliminar
                this.board[y-1][x].setInLine(true);
                this.board[y+1][x].setInLine(true);
                validMove = true;
            }
        //Revisar Horizontal
        } else if (y >= 0 && y < this.totalCandiesVertical-1 && x > 0 && x < totalCandiesHorizontal){
            if (this.board[y][x].getType().equals(this.board[y][x-1].getType()) && this.board[y][x].getType().equals(this.board[y][x+1].getType())){
                
                this.board[y][x].setInLine(true);
                
                if (!this.board[y][x-1].isInLine())
                    check3OnLine(x-1, y);
                if (!this.board[y][x+1].isInLine())
                    check3OnLine(x+1, y);
            
                this.board[y][x-1].setInLine(true);
                this.board[y][x+1].setInLine(true);
                validMove = true;
            }
        }
        
        return validMove;
    }
    
    private void refreshBoard(){
        //Recorro el Array en busca de dulces por eliminar
        for (int i = 0; i < this.totalCandiesVertical; i++){
            for (int j = 0; j < this.totalCandiesHorizontal; j++){
                //Si mi dulce está en linea, debe ser eliminado
                if (this.board[i][j].isInLine()){
                    
                    //Mover todos los dulces hacía abajo
                    for (int k = i; k >= 0; k--){
                        if (k != 0){
                            this.board[i][j] = this.board[i-1][j];
                        } else {
                            //Crea un random
                            this.board[i][j] = this.getRandomAnimal(j, i);
                        }
                    }
                }
            }
        }
    }
    
    public void showBoard(){
        for (int i = 0; i < this.totalCandiesVertical; i++){
            for (int j = 0; j < this.totalCandiesHorizontal; j++){
                System.out.print(this.board[i][j].getType().ordinal());
            }
            System.out.println("");
        }
        System.out.println("---------------");
    }
}
