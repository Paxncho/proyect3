package candycrush;

import candycrush.model.CandyCrush;
import candycrush.model.Dimension;
import candycrush.painter.Loader;
import candycrush.painter.Painter;
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

public class FXCandyCrushCanvas extends Canvas implements EventHandler, ChangeListener{

    private CandyCrush candyCrush;
    private final GraphicsContext context;
    private int firstX;
    private int firstY;
    private int lastX;
    private int lastY;
    
    
    public FXCandyCrushCanvas(){
        this.candyCrush = new CandyCrush(9, 9);
        this.context = super.getGraphicsContext2D();
        
        this.addEventHandler(MouseEvent.MOUSE_PRESSED, this);
        this.addEventHandler(MouseEvent.MOUSE_DRAGGED, this);
        
        this.widthProperty().addListener(this);
        this.heightProperty().addListener(this);
        
        /*Timeline timer = new Timeline(new KeyFrame(Duration.millis(30), this));
        timer.setCycleCount(Animation.INDEFINITE);
        timer.play();*/
    }

    @Override
    public void handle(Event event) {
        
        Dimension window = new Dimension(this.getWidth(), this.getHeight());
        
        if (event.getEventType() == MouseEvent.MOUSE_PRESSED){
            MouseEvent me = (MouseEvent)event;
            this.firstX = (int)Painter.xToWorld(me.getX(), this.candyCrush.getWorld(), window);
            this.firstY = (int)Painter.yToWorld(me.getY(), this.candyCrush.getWorld(), window);
            this.lastX = this.firstX;
            this.lastY = this.firstY;
            this.candyCrush.setRunning(true);
            
        } else if (event.getEventType() == MouseEvent.MOUSE_DRAGGED){
            MouseEvent me = (MouseEvent)event;
            this.lastX = (int)Painter.xToWorld(me.getX(), this.candyCrush.getWorld(), window);
            this.lastY = (int)Painter.yToWorld(me.getY(), this.candyCrush.getWorld(), window);
            
            if (this.checkMove()){
                this.draw();
                this.candyCrush.setRunning(false);
            }
        }
        
    }

    @Override
    public void changed(ObservableValue ov, Object t, Object t1) {
        this.draw();
    }

    private void draw(){
        this.context.clearRect(0, 0, this.getWidth(), this.getHeight());
        
        //Setea un wallpaper po :c
        this.context.drawImage(Loader.getImage("wallpaper.jpg"), this.getWidth(), this.getHeight());
        
        if (this.candyCrush != null){
            Painter.paint(this.candyCrush, this.context, this.candyCrush.getWorld(), new Dimension(this.getWidth(), this.getHeight()));
        }
    }
        
    private boolean checkMove(){
        if (this.lastX < this.candyCrush.getBoardWidth() && this.firstX < this.candyCrush.getBoardWidth() && this.firstY < this.candyCrush.getBoardHeight() && this.lastY < this.candyCrush.getBoardHeight())
            return this.candyCrush.checkMove(this.firstX, this.firstY, this.lastX, this.lastY);
        return false;
    }
}
