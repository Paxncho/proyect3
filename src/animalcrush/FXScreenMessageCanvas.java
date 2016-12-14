package animalcrush;

import animalcrush.painter.Loader;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

/**
 * @author pencho
 */

public class FXScreenMessageCanvas extends Canvas{

    private GraphicsContext context;
    private FXAnimalCrushStage parent;
    
    public FXScreenMessageCanvas (FXAnimalCrushStage parent, int option){
        this.parent = parent;
        this.context = super.getGraphicsContext2D();
        this.context.clearRect(0, 0, this.getWidth(), this.getHeight());
        System.out.println("IM CREATED");
        switch (option){
            case 0: this.context.drawImage(Loader.getImage("youWin.png"), 0, 0, this.getWidth(), this.getHeight()); break;
            case 1: this.context.drawImage(Loader.getImage("youLose.png"), 0, 0, this.getWidth(), this.getHeight()); break;
        }
    }
}
