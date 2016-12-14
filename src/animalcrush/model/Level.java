package animalcrush.model;

import java.util.Random;

/**
 * @author pencho
 */

public class Level {
    
    static public Animal[][] generateLevel(int index){
        Animal[][] board;
        
        switch(index){
            //case 0: board = getLevel0(); break; //5 - 4 colores
            /*case 1: board = getLevel1(); break; //5 - 4 colores
            case 2: board = getLevel2(); break; //6 - 4 colores
            case 3: board = getLevel3(); break; //6 - 4 colores
            case 4: board = getLevel4(); break; //7 - 5 colores
            case 5: board = getLevel5(); break; //7 - 5 colores
            case 6: board = getLevel6(); break; //8 - 5 colores
            case 7: board = getLevel7(); break; //8 - 5 colores
            case 8: board = getLevel8(); break; //9 - 6 colores
            case 9: board = getLevel9(); break; //9 - 6 colores*/
            default: board = getRandomLevel(index); break;
        }
        
        return board;
    }
    
    static public int getSizeLevel(int index){
        switch(index){
            case 0: case 1: return 5;
            case 2: case 3: return 6;
            case 4: case 5: return 7;
            case 6: case 7: return 8;
            case 8: case 9: return 9;
            default: return 0;
        }
    }
    
    static private Animal[][] getLevel0(){
        Animal[][] board = new Animal[5][5];
        
        for (int i = 0; i < 5; i++){
            for (int j = 0; j < 5; j++){
                int k = (i + j/2)%8;
                switch(k){
                    case 0: case 4: board[i][j] = new Animal(j, i, AnimalType.CHICK); break;
                    case 1: case 5: board[i][j] = new Animal(j, i, AnimalType.FOX); break;
                    case 2: case 6: board[i][j] = new Animal(j, i, AnimalType.HEDGEHOG); break;
                    case 3: case 7: board[i][j] = new Animal(j, i, AnimalType.TIGER); break;
                }
            }
        }        
        return board;
    }
    
    static private Animal[][] getRandomLevel(int index){
        Random random = new Random();
        Animal[][] board = new Animal[getSizeLevel(index)][getSizeLevel(index)];
        for (int i = 0; i < getSizeLevel(index); i++){
            for (int j = 0; j < getSizeLevel(index); j++){
                board[i][j] = new Animal(j, i, AnimalType.FOX);
                switch(index){
                    case 0: case 1: case 2: case 3:      
                        switch(random.nextInt()%4){
                            case 0: board[i][j] = new Animal(j, i, AnimalType.CHICK); break;
                            case 1: board[i][j] = new Animal(j, i, AnimalType.KOALA); break;
                            case 2: board[i][j] = new Animal(j, i, AnimalType.TIGER); break;
                        }
                        break;
                    case 4: case 5: case 6: case 7:
                        switch(random.nextInt()%5){
                            case 0: board[i][j] = new Animal(j, i, AnimalType.CHICK); break;
                            case 1: board[i][j] = new Animal(j, i, AnimalType.KOALA); break;
                            case 2: board[i][j] = new Animal(j, i, AnimalType.TIGER); break;
                            case 3: board[i][j] = new Animal(j, i, AnimalType.HEDGEHOG); break;
                        }
                        break;
                    case 8: case 9:
                        switch(random.nextInt()%6){
                            case 0: board[i][j] = new Animal(j, i, AnimalType.CHICK); break;
                            case 1: board[i][j] = new Animal(j, i, AnimalType.KOALA); break;
                            case 2: board[i][j] = new Animal(j, i, AnimalType.TIGER); break;
                            case 3: board[i][j] = new Animal(j, i, AnimalType.HEDGEHOG); break;
                            case 4: board[i][j] = new Animal(j, i, AnimalType.PIG); break;
                        }
                        break;
                }
            }
        }
        return board;
    }

    static public int getMovesCount(int index){
        switch(index){
            case 0: return 20;
            case 1: return 18;
            case 2: return 16;
            case 3: return 14;
            case 4: return 12;
            case 5: return 14;
            case 6: return 12;
            case 7: return 14;
            case 8: return 16;
            case 9: return 18;
            default: return 0;
        }
    }

    static public int getScoreGoal(int index){
        switch(index){
            case 0: return 500;
            case 1: return 10000;
            case 2: return 15000;
            case 3: return 20000;
            case 4: return 25000;
            case 5: return 30000;
            case 6: return 35000;
            case 7: return 40000;
            case 8: return 45000;
            case 9: return 50000;
            default: return 0;
        }
    }
}
