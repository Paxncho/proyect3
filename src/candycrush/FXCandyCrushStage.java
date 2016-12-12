package candycrush;

import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 * @author pencho
 */

public class FXCandyCrushStage extends Stage{

    public FXCandyCrushStage(){
        StackPane mainPane = new StackPane();
        
        FXCandyCrushCanvas paint = new FXCandyCrushCanvas();
        mainPane.getChildren().add(paint);
        
        //Adaptar el tamaño al canvas
        paint.widthProperty().bind(mainPane.widthProperty());
        paint.heightProperty().bind(mainPane.heightProperty());
        
        Scene scene = new Scene(mainPane, 600, 600);
        super.setScene(scene);
        super.setTitle("Candy Crush");
        
    }
}
