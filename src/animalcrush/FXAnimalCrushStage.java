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

        mainMenu();
        
        super.setTitle("Animal Crush");
        Scene scene = new Scene(mainPane, 600, 600);
        super.setScene(scene);
    }
    
    private void playGame(){
        this.paint = new FXAnimalCrushCanvas(9, 9);
        //Adaptar el tamaño al canvas
        this.paint.widthProperty().bind(mainPane.widthProperty());
        this.paint.heightProperty().bind(mainPane.heightProperty());
        
        this.mainPane.getChildren().add(paint);
    }
    
    private void mainMenu(){
        this.paint = new FXMainMenuCanvas(this);
        this.paint.widthProperty().bind(mainPane.widthProperty());
        this.paint.heightProperty().bind(mainPane.heightProperty());
        this.mainPane.getChildren().add(paint);
    }
    
    public void changeCanvas(int i){
        this.mainPane.getChildren().remove(this.paint);
        switch(i){
            case 1: playGame(); break;
            case 2: mainMenu(); break;
        }
    }
    
    public void closeWindow(){
        this.close();
    }
}
