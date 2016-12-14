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
    private int index = 0;
    private int message = 0;

    public FXAnimalCrushStage(){
        this.mainPane = new StackPane();

        mainMenu();
        this.setMinHeight(300);
        this.setMinWidth(300);
        
        super.setTitle("Animal Crush");
        Scene scene = new Scene(mainPane, 600, 600);
        super.setScene(scene);
    }
    
    private void mainMenu(){
        this.index = 0;
        this.paint = new FXMainMenuCanvas(this);
        this.paint.widthProperty().bind(mainPane.widthProperty());
        this.paint.heightProperty().bind(mainPane.heightProperty());
        this.mainPane.getChildren().add(paint);
    }
    
    public void changeCanvas(int i) throws InterruptedException{
        this.mainPane.getChildren().remove(this.paint);
        switch(i){
            case 1: playGame(); break;
            case 2: mainMenu(); break;
            case 3: showMessage(); break;
        }
    }
    
    public void closeWindow(){
        this.close();
    }
    
    private void playGame(){
        BorderPane manPane = new BorderPane();
        HBox hbox = new HBox();
        
        hbox.setPadding(new Insets(15, 12, 85, 12));
        hbox.setSpacing(10);
        hbox.setStyle("-fx-background-color: #336699;");

        
        FXAnimalCrushCanvas canvas = new FXAnimalCrushCanvas(this.index, this);
        canvas.widthProperty().bind(mainPane.widthProperty());
        canvas.heightProperty().bind(mainPane.heightProperty());
        
        manPane.setTop(hbox);
        manPane.setCenter(canvas);
        
        this.mainPane.getChildren().add(manPane);
    }
    
    public void youWin() throws InterruptedException{
        this.index++;
        this.message = 0;
        this.changeCanvas(3);
    }
    
    public void youLose() throws InterruptedException{
        this.message = 1;
        this.changeCanvas(3);
    }
    
    private void showMessage() throws InterruptedException{
        this.paint = new FXScreenMessageCanvas(this, this.message);
        this.paint.widthProperty().bind(mainPane.widthProperty());
        this.paint.heightProperty().bind(mainPane.heightProperty());
        this.mainPane.getChildren().add(paint);
        Thread.sleep(5000);
        switch(this.message){
            case 0: this.changeCanvas(1); break;
            case 1: this.changeCanvas(2); break;
        }        
    }
}
