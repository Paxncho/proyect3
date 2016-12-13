package animalcrush;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 * @author pencho
 */

public class FXAnimalCrushStage extends Stage{
    
    private StackPane mainPane;
    private Canvas paint;

    public FXAnimalCrushStage(){
        this.mainPane = new StackPane();

        mainMenu();
        this.setMinHeight(300);
        this.setMinWidth(300);
        
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
            case 1: GamePane(9,9); break;
            case 2: mainMenu(); break;
        }
    }
    
    public void closeWindow(){
        this.close();
    }
    
    private void GamePane(int x, int y){
        BorderPane manPane = new BorderPane();
        HBox hbox = new HBox();
        
        hbox.setPadding(new Insets(15, 12, 85, 12));
        hbox.setSpacing(10);
        hbox.setStyle("-fx-background-color: #336699;");

        
        FXAnimalCrushCanvas canvas = new FXAnimalCrushCanvas(x, y);
        canvas.widthProperty().bind(mainPane.widthProperty());
        canvas.heightProperty().bind(mainPane.heightProperty());
        
        manPane.setTop(hbox);
        manPane.setCenter(canvas);
        
        this.mainPane.getChildren().add(manPane);
    }
}
