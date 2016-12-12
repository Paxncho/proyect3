package animalcrush;

import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 * @author pencho
 */

public class FXAnimalCrushStage extends Stage{
    
    StackPane mainPane;
    Canvas paint;

    public FXAnimalCrushStage(){
        this.mainPane = new StackPane();

        iniciarJuego();
        
        Scene scene = new Scene(mainPane, 600, 600);
        super.setScene(scene);
    }
    
    private void iniciarJuego(){
        this.paint = new FXAnimalCrushCanvas(9, 9);
        //Adaptar el tamaño al canvas
        this.paint.widthProperty().bind(mainPane.widthProperty());
        this.paint.heightProperty().bind(mainPane.heightProperty());
        
        this.mainPane.getChildren().add(paint);
        super.setTitle("Animal Crush");
    }
    
    public void borrarJuego(){
        this.mainPane.getChildren().remove(this.paint);
    }
}
