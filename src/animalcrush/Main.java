package animalcrush;

import javafx.application.Application;
import javafx.stage.Stage;

/**
 * @author Pencho
 */

public class Main extends Application{

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        FXAnimalCrushStage stage = new FXAnimalCrushStage();
        stage.show();
    }
    
}
