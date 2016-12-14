package animalcrush;

import animalcrush.model.Dimension;
import animalcrush.painter.Loader;
import animalcrush.painter.Painter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;

/**
 * @author Pencho
 */

public class FXMainMenuCanvas extends Canvas implements EventHandler, ChangeListener{

    private GraphicsContext context;
    private int buttonSelected;
    private FXAnimalCrushStage parent;
    
    FXMainMenuCanvas(FXAnimalCrushStage parent){
        this.parent = parent;
        this.context = super.getGraphicsContext2D();
        this.buttonSelected = -1;
        this.draw();
        
        this.addEventHandler(MouseEvent.MOUSE_PRESSED, this);
        this.addEventHandler(MouseEvent.MOUSE_MOVED, this);
        
        this.widthProperty().addListener(this);
        this.heightProperty().addListener(this);
    }
    
    @Override
    public void handle(Event event){
        if (event.getEventType() == MouseEvent.MOUSE_MOVED){
            Dimension window = new Dimension(this.getWidth(), this.getHeight());
            Dimension world = new Dimension(600, 600);
            MouseEvent me = (MouseEvent) event;
            int x = (int) Painter.xToWorld(me.getX(), world, window);
            int y = (int) Painter.yToWorld(me.getY(), world, window);
            
            if (x > 50 && x < 250){
                if(y > 205 && y < 255)
                    this.buttonSelected = 1;
                else if (y > 305 && y < 355)
                    this.buttonSelected = 2;
                else if (y > 405 && y < 455)
                    this.buttonSelected = 3;
                else if (y > 505 && y < 555)
                    this.buttonSelected = 4;
                else
                    this.buttonSelected = -1;
            } else
                this.buttonSelected = -1;
            
        }
        
        if (event.getEventType() == MouseEvent.MOUSE_PRESSED){
            switch(this.buttonSelected){
                case 1: {
                    try{
                        parent.changeCanvas(1);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(FXMainMenuCanvas.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } break;
                case 2: break;
                case 3: break;
                case 4: parent.closeWindow(); break;
            }
        }
        
        this.draw();
    }

    @Override
    public void changed(ObservableValue ov, Object t, Object t1){
        this.draw();
    }
    
    private void draw(){
        this.context.clearRect(0, 0, this.getWidth(), this.getHeight());
        this.context.drawImage(Loader.getImage("background.jpg"), 0, 0, this.getWidth(), this.getHeight());
        this.context.drawImage(Loader.getImage("mainMenu.png"), 0, 0, this.getWidth(), this.getHeight());
        this.context.drawImage(Loader.getImage("playButton.png"), 0, 0, this.getWidth(), this.getHeight());
        this.context.drawImage(Loader.getImage("aboutButton.png"), 0, 0, this.getWidth(), this.getHeight());
        this.context.drawImage(Loader.getImage("rankingButton.png"), 0, 0, this.getWidth(), this.getHeight());
        this.context.drawImage(Loader.getImage("exitButton.png"), 0, 0, this.getWidth(), this.getHeight());
        switch(this.buttonSelected){
            case 1: this.context.drawImage(Loader.getImage("playButtonSelected.png"), 0, 0, this.getWidth(), this.getHeight()); break;
            case 3: this.context.drawImage(Loader.getImage("aboutButtonSelected.png"), 0, 0, this.getWidth(), this.getHeight()); break;
            case 2: this.context.drawImage(Loader.getImage("rankingButtonSelected.png"), 0, 0, this.getWidth(), this.getHeight()); break;
            case 4: this.context.drawImage(Loader.getImage("exitButtonSelected.png"), 0, 0, this.getWidth(), this.getHeight()); break;
        }
    }
}
