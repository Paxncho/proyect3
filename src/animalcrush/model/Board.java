
package animalcrush.model;

import animalcrush.model.Animal;

/**
 * @author Pencho
 */

public class Board {
    Animal[][] candies;
    
    public Board(int m, int n){
        candies = new Animal[m][n];
    }
}
