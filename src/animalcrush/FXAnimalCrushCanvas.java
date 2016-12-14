package animalcrush;

import animalcrush.model.AnimalCrush;
import animalcrush.model.Dimension;
import animalcrush.painter.Loader;
import animalcrush.painter.Painter;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
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
    
    private Insets margin;
    private Dimension window;
    private FXAnimalCrushStage parent;

    public FXAnimalCrushCanvas(int index, FXAnimalCrushStage parent){
        System.out.println("INDEX " + index);
        this.parent = parent;
        this.animalCrush = new AnimalCrush(index);
        this.context = super.getGraphicsContext2D();

        this.addEventHandler(MouseEvent.MOUSE_PRESSED, this);
        this.addEventHandler(MouseEvent.MOUSE_DRAGGED, this);

        this.widthProperty().addListener(this);
        this.heightProperty().addListener(this);

        this.firstSelected = false;
        this.firstX = -1;
        this.firstY = -1;
        
        this.margin = new Insets(25);
    }

    @Override
    public void handle(Event event){

        this.window = new Dimension(this.getWidth() - this.margin.getLeft() - this.margin.getRight(), this.getHeight() - this.margin.getBottom() - this.margin.getTop());

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

        this.context.drawImage(Loader.getImage("background.jpg"), 0, 0, this.getWidth(), this.getHeight());
        
        if (this.animalCrush != null){
            this.window = new Dimension(this.getWidth() - this.margin.getLeft() - this.margin.getRight(), this.getHeight() - this.margin.getBottom() - this.margin.getTop());
            Painter.paint(this.animalCrush, this.context, this.animalCrush.getWorld(), this.window, this.firstY, this.firstX, this.margin);
        }
    }

    private void checkMove(){
        this.animalCrush.resetMultiplicator();
        this.animalCrush.checkMove(this.firstX, this.firstY, this.lastX, this.lastY);
        
        if (this.animalCrush.checkWin()){
            this.parent.youWin();
            
        } else if (this.animalCrush.checkLose()){
            this.parent.youLose();
        }
    }
}
