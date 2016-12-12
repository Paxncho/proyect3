
package candycrush.model;

import candycrush.model.Animal;

/**
 * @author Pencho
 */

public class Board {
    Animal[][] candies;
    
    public Board(int m, int n){
        candies = new Animal[m][n];
    }
}
