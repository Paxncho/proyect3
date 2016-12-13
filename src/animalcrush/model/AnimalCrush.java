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
    private int score;
    private int multiplier;
    
    private int left;
    private int right;
    private int bottom;
    
    public AnimalCrush(int totalCandiesHorizontal, int totalCandiesVertical){
        this.score = 0;
        this.multiplier = 1;
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
    }
    
    public Dimension getWorld(){
        return this.world;
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
    
    public int getScore(){
        return this.score;
    }
    
    public void setScore(int score){
        this.score = score;
    }
    
    public void resetMultiplicator(){
        this.multiplier = 1;
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
    
    //Reviso si el movimiento es válido
    public boolean checkMove(int x1, int y1, int x2, int y2){
        Boolean validMove = false;
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
            validMove = true;
            switch(move){
                case RIGHT:
                    //Intercambio los dulces
                    aux = this.board[y1][x1];
                    this.board[y1][x1] = this.board[y1][x1+1];
                    this.board[y1][x1+1] = aux;

                    //Checkeo si el movimiento es valido
                    /*if (check3OnLine(x1, y1) || check3OnLine(x1+1, y1))
                        validMove = true;
                    //Caso contrario, devuelvo los dulces
                    else{
                        aux = this.board[y1][x1];
                        this.board[y1][x1] = this.board[y1][x1+1];
                        this.board[y1][x1+1] = aux;
                    }*/

                    break;
                case LEFT:
                    aux = this.board[y1][x1];
                    this.board[y1][x1] = this.board[y1][x1-1];
                    this.board[y1][x1-1] = aux;

                    /*if (check3OnLine(x1, y1) || check3OnLine(x1-1, y1))
                        validMove = true;
                    else{
                        aux = this.board[y1][x1];
                        this.board[y1][x1] = this.board[y1][x1-1];
                        this.board[y1][x1-1] = aux;
                    }*/

                    break;
                case UP:
                    aux = this.board[y1][x1];
                    this.board[y1][x1] = this.board[y1-1][x1];
                    this.board[y1-1][x1] = aux;

                    /*if (check3OnLine(x1, y1) || check3OnLine(x1, y1-1))
                        validMove = true;
                    else {
                        aux = this.board[y1][x1];
                        this.board[y1][x1] = this.board[y1-1][x1];
                        this.board[y1-1][x1] = aux;    
                    }*/

                    break;
                case DOWN:
                    aux = this.board[y1][x1];
                    this.board[y1][x1] = this.board[y1+1][x1];
                    this.board[y1+1][x1] = aux;

                    /*if (check3OnLine(x1, y1) || check3OnLine(x1, y1+1))
                        validMove = true;
                    else {
                        aux = this.board[y1][x1];
                        this.board[y1][x1] = this.board[y1+1][x1];
                        this.board[y1+1][x1] = aux;    
                    }*/
                    break;
            }

            if (validMove){
                this.refreshBoard();
                this.multiplier++;
            }
        }        
        return validMove;
    }
    
    //Revisa si hay dulces por eliminar, si los hay, llama a onLine()
    private boolean check3OnLine(int x, int y){
        boolean validMove = false;
        
        //Revisar vertical cuando el y está en 0
        if (y == 0 && x >=0 && x < totalCandiesHorizontal){
            //Revisar y, y+1, y+2
            if(this.onLine(x, y, 1, 0, 2, 0))
                validMove = true;
            
        //Revisar vertical y = 1
        } else if (y == 1 && x >=0 && x < totalCandiesHorizontal){
            if (this.onLine(x, y, 1, 0, 2, 0))
                validMove = true;
            //Revisar y-1, y, y+1
            else if (this.onLine(x, y, -1, 0, 1, 0))
                validMove = true;
            
        //Revisar vertical en los casos intermedio
        } else if (y > 0 && y < this.totalCandiesVertical-1 && x >=0 && x < totalCandiesHorizontal){
            if (this.onLine(x, y, -1, 0, 1, 0))
                validMove = true;
            else if (this.onLine(x, y, 1, 0, 2, 0))
                validMove = true;
            //Revisar y-2, y-1, y
            else if (this.onLine(x, y, -1, 0, -2, 0))
                validMove = true;
            
        //Revisar vertical max - 1
        } else if (y == this.totalCandiesVertical - 2 && x >= 0 & x < totalCandiesHorizontal){
            if (this.onLine(x, y, -1, 0, 1, 0))
                validMove = true;
            else if (this.onLine(x, y, -1, 0, -2, 0))
                validMove = true;
            
        //Revisar vertical cuando el y es el máximo
        } else if (y == this.totalCandiesVertical - 1 && x >= 0 & x < totalCandiesHorizontal){
            if (this.onLine(x, y, -1, 0, -2, 0))
                validMove = true;
        }
        
        //Revisar horizontal cuando x es 0
        if (y >= 0 && y < this.totalCandiesVertical -1 && x == 0){
            //Revisar x, x+1, x+2   
            if (this.onLine(x, y, 0, 1, 0, 2))
                validMove = true;

        //Revisar horizontal cuando x es 1
        } else if (y >= 0 && y < this.totalCandiesVertical -1 && x == 1){
            if (this.onLine(x, y, 0, 1, 0, 2))
                validMove = true;
            //Revisar x-1, x, x+1
            else if (this.onLine(x, y, 0, -1, 0, 1))
                validMove = true;
            
        //Revisar horizontal en los casos intermedios
        } else if (y >= 0 && y < this.totalCandiesVertical && x > 0 && x < totalCandiesHorizontal - 1){
            if (this.onLine(x, y, 0, 1, 0, 2))
                validMove = true;
            else if (this.onLine(x, y, 0, -1, 0, 1))
                validMove = true;
            //Revisar x-2, x-1, x
            else if (this.onLine(x, y, 0, -1, 0, -2))
                validMove = true;

        //Revisar horizontal cuando x es el máximo - 1
        } else if (y >= 0 && y < this.totalCandiesVertical -1 && x == totalCandiesHorizontal - 2){
            if (this.onLine(x, y, 0, -1, 0, 1))
                validMove = true;
            else if (this.onLine(x, y, 0, -1, 0, -2))
                validMove = true;

        //Revisar horizontal cuando x es el máximo
        } else if (y >= 0 && y < this.totalCandiesVertical -1 && x == totalCandiesHorizontal - 1){
            if (this.onLine(x, y, 0, -1, 0, -2))
                validMove = true;
        }
        
        return validMove;
    }
    
    //Recorro el Array en busca de dulces por eliminar
    private void refreshBoard(){
        this.left = 9;
        this.right = 0;
        this.bottom = 0;
        for (int i = 0; i < this.totalCandiesVertical; i++){
            for (int j = 0; j < this.totalCandiesHorizontal; j++){
                
                //Si mi dulce está en linea, debe ser eliminado
                if (this.board[i][j].isInLine()){
                    //Añadir puntaje
                    this.score += 10 * this.multiplier;
                    //Mover todos los dulces hacía abajo
                    for (int k = i; k >= 0; k--){
                        if (k != 0){
                            this.board[i][j] = this.board[i-1][j];
                        } else {
                            //Si mi dulce está hasta arriba, creo un random
                            this.board[i][j] = this.getRandomAnimal(j, i);
                        }
                    }
                    //Guardar coordenadas para posterior revisión
                    if (j < this.left)
                        this.left = j;
                    if (j > this.right)
                        this.right = j;
                    if (i > this.bottom)
                        this.bottom = i;
                }
            }
        }
        
        if (this.left != 9 && this.right != 0 && this.bottom != 0){
            checkNewBoard();
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
    
    //Marco dulces en linea
    private boolean onLine(int x, int y, int a, int b, int c, int d){
        //Verifico si estan en linea
        if(this.board[y][x].getType().equals(this.board[y+a][x+b].getType()) && this.board[y][x].getType().equals(this.board[y+c][x+d].getType())){

            //Marco el actual
            this.board[y][x].setInLine(true);
            
                //Si los vecinos no están marcados, revisarlos
                if (!this.board[y+a][x+b].isInLine())
                    check3OnLine(x+b, y+a);
                if (!this.board[y+c][x+d].isInLine())
                    check3OnLine(x+d, y+c);
                
                //Marcar vecinos
                this.board[y+a][x+b].setInLine(true);
                this.board[y+c][x+d].setInLine(true);
                return true;
        }
        return false;
    }
    
    private void checkNewBoard(){
        boolean runs = false;
        for (int i = 0; i < this.bottom; i++){
            for (int j = this.left; j < this.right; j++){
                if (check3OnLine(j, i))
                    runs = true;
            }
        }
        if (runs){
            this.multiplier++;
            refreshBoard();
        }
    }
}
