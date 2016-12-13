package animalcrush.painter;

import animalcrush.model.Animal;
import animalcrush.model.AnimalCrush;
import animalcrush.model.AnimalType;
import animalcrush.model.Dimension;
import javafx.scene.canvas.GraphicsContext;

/**
 * @author pencho
 */

public class Painter {

    static public void paint(AnimalCrush game, GraphicsContext context, Dimension world, Dimension window, int i1, int j1){
        
        Animal[][] board = game.getBoard();
        for (int i = 0; i < game.getBoardHeight(); i++){
            for (int j = 0; j < game.getBoardWidth(); j++){
                if( i == i1 && j == j1 ){
                    Painter.draw(board[i][j], context, world, window);
                    double x = Painter.xToWindow(board[i][j].getX(), world, window);
                    double y = Painter.yToWindow(board[i][j].getY(), world, window);
                    double width = Painter.xToWindow(.9, world, window);
                    double height = Painter.yToWindow(.9, world, window);
                    context.drawImage(Loader.getImage("marked.png"), x, y, width, height);
                } else {
                    Painter.draw(board[i][j], context, world, window);
                }
            }
        }
    }
    
    static private void draw(Animal candy, GraphicsContext context, Dimension world, Dimension window)
    {
        double x = Painter.xToWindow(candy.getX(), world, window);
        double y = Painter.yToWindow(candy.getY(), world, window);
        double width = Painter.xToWindow(1, world, window);
        double height = Painter.yToWindow(1, world, window);
        
        if(candy.getType() != AnimalType.WHITE)
            context.drawImage(Loader.getImage(candy.getType().getFilename()), x, y, width, height);
    }
    
    static public double xToWindow(double x, Dimension world, Dimension window){
        return x*window.getWidth()/world.getWidth();
    }
    
    static public double yToWindow(double y, Dimension world, Dimension window){
        return y*window.getHeight()/world.getHeight();
    }
    
    static public double xToWorld(double x, Dimension world, Dimension window){
        return x*world.getWidth()/window.getWidth();
    }
    
    static public double yToWorld(double y, Dimension world, Dimension window){
        return y*world.getHeight()/window.getHeight();
    }
}
