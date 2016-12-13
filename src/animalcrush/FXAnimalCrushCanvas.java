package animalcrush;

import animalcrush.model.AnimalCrush;
import animalcrush.model.Dimension;
import animalcrush.painter.Painter;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;


/**
 * @author pencho
 */

public class FXAnimalCrushCanvas extends Canvas implements EventHandler, ChangeListener{

    private AnimalCrush animalCrush;
    private final GraphicsContext context;
    private boolean firstSelected;
    private int firstX;
    private int firstY;
    private int lastX;
    private int lastY;

    public FXAnimalCrushCanvas(int x, int y){
        this.animalCrush = new AnimalCrush(x, y);
        this.context = super.getGraphicsContext2D();

        this.addEventHandler(MouseEvent.MOUSE_PRESSED, this);
        this.addEventHandler(MouseEvent.MOUSE_DRAGGED, this);

        this.widthProperty().addListener(this);
        this.heightProperty().addListener(this);

        this.firstSelected = false;
        this.firstX = -1;
        this.firstY = -1;
    }

    @Override
    public void handle(Event event){

        Dimension window = new Dimension(this.getWidth(), this.getHeight());

        if (event.getEventType() == MouseEvent.MOUSE_PRESSED){
            MouseEvent me = (MouseEvent) event;
            
            if (!firstSelected){
                this.firstX = (int) Painter.xToWorld(me.getX(), this.animalCrush.getWorld(), window);
                this.firstY = (int) Painter.yToWorld(me.getY(), this.animalCrush.getWorld(), window);
                this.firstSelected = true;
                
            } else {
                this.lastX = (int) Painter.xToWorld(me.getX(), this.animalCrush.getWorld(), window);
                this.lastY = (int) Painter.yToWorld(me.getY(), this.animalCrush.getWorld(), window);
                this.firstSelected = false;

                if ((Math.abs(this.lastX - this.firstX) <= 1 && this.lastY == this.firstY) || (Math.abs(this.lastY - this.firstY) <= 1 && this.lastX == this.firstX)){
                    this.checkMove();
                }
                
                this.firstX = -1;
                this.firstY = -1;
            }
        } else if (event.getEventType() == MouseEvent.MOUSE_DRAGGED){
            MouseEvent me = (MouseEvent) event;
            this.lastX = (int) Painter.xToWorld(me.getX(), this.animalCrush.getWorld(), window);
            this.lastY = (int) Painter.yToWorld(me.getY(), this.animalCrush.getWorld(), window);

            if (this.firstSelected){
                if ((Math.abs(this.lastX - this.firstX) >= 1 && this.lastY == this.firstY) || (Math.abs(this.lastY - this.firstY) >= 1 && this.lastX == this.firstX)){
                    this.checkMove();
                    this.firstSelected = false;
                    this.firstX = -1;
                    this.firstY = -1;
                }
            }
        }
        draw();
    }

    @Override
    public void changed(ObservableValue ov, Object t, Object t1){
        this.draw();
    }

    public void draw(){
        this.context.clearRect(0, 0, this.getWidth(), this.getHeight());

        if (this.animalCrush != null){
            Painter.paint(this.animalCrush, this.context, this.animalCrush.getWorld(), new Dimension(this.getWidth(), this.getHeight()), this.firstY, this.firstX);
        }
    }

    private boolean checkMove(){
        return this.animalCrush.checkMove(this.firstX, this.firstY, this.lastX, this.lastY, this);
    }
}
