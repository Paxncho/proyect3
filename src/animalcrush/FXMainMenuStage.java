package animalcrush;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * @author pencho
 */

public class FXMainMenuStage extends Stage implements EventHandler{

    private Button play;
    private Button ranking;
    private Button about;
    private Button exit;
    
    public FXMainMenuStage(){
        
        BorderPane mainPane = new BorderPane();
        VBox centralPane = new VBox(20);
        
        this.play = new Button("Play");
        this.play.setPrefWidth(100);
        this.ranking = new Button("Ranking");
        this.ranking.setPrefWidth(100);
        this.about = new Button("About");
        this.about.setPrefWidth(100);
        this.exit = new Button("Exit");
        this.exit.setPrefWidth(100);
        
        centralPane.getChildren().addAll(this.play, this.ranking, this.about, this.exit);
        
        mainPane.setCenter(centralPane);
        
        this.play.setLayoutX(300);
        this.ranking.setLayoutX(300);
        this.about.setLayoutX(300);
        this.exit.setLayoutX(300);
        
        this.play.setOnAction(this);
        this.ranking.setOnAction(this);
        this.about.setOnAction(this);
        this.exit.setOnAction(this);
        
        super.setTitle("Animal Crush");
        Scene scene = new Scene(mainPane, 600, 600);
        super.setScene(scene);
    }

    @Override
    public void handle(Event event){
        
    }
    
}
