/**
 * TO DO LIST:
 * Replantear todo el algoritmo para que intercambie y genere Tipos, no Animales
 * Crear el Algoritmo que checkee movimientos posibles
 * 
 * Crear las Ventanas
 * Vincular todo
 * Pulir
 * Crear algoritmo que genere buenos niveles random (Caso contrario, tenerlos pre-hechos)
*/
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
