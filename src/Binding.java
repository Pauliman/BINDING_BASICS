import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;


/**
 * This class demonstrates simple bindings between a 2D shape and
 * a two control views(Sliders).
 */
public class Binding extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        GridPane root = new GridPane();
        root.alignmentProperty().setValue(Pos.CENTER);
        root.setVgap(10);
        root.setHgap(10);
        Label label_h = new Label("HEIGHT");
        Label label_w = new Label("WIDTH");
        Slider slider_h = new Slider(1, 100,10);
        slider_h.setShowTickMarks(true);
        slider_h.setMajorTickUnit(10);
        slider_h.increment();
        Slider slider_w = new Slider(1,100,10);
        slider_w.setShowTickMarks(true);
        slider_w.setMajorTickUnit(10);
        slider_w.increment();
        VBox lboxl = new VBox(10,label_h, slider_h);
        lboxl.setBackground(getBackground(200,150,50));
        lboxl.setAlignment(Pos.CENTER);
        VBox lboxr = new VBox(10, label_w, slider_w);
        lboxr.setBackground(getBackground(100,150,50));
        lboxr.setAlignment(Pos.CENTER);
        HBox lowerBox = new HBox(10, lboxl, lboxr);
        lowerBox.setAlignment(Pos.CENTER);
        lowerBox.setPrefSize(300,150);
        Rectangle rect = new Rectangle(100,25,10,10);
        rect.setStroke(Color.WHITE);
        rect.setStrokeWidth(2);
        rect.heightProperty().bind(slider_h.valueProperty());           //In unidirectional binding the property on which the bind()-method is called is slave to the argument's property.
        rect.widthProperty().bind(slider_w.valueProperty());
        Pane group = new Pane(rect);                                // Must be a simple pane for the Rectangle position values to have an effect
        HBox upperBox = new HBox(0, group);
        upperBox.setPrefSize(300, 150);
        upperBox.setBackground(getBackground(50,50,50));
        root.add(upperBox, 0,0);
        root.add(lowerBox,0,1);
        primaryStage.setScene(new Scene(root));
        primaryStage.setTitle("Binding");
        primaryStage.show();
    } // end of start()

    private Background getBackground(Integer r, Integer g, Integer b){
        Paint paint = Paint.valueOf("#" + Integer.toHexString(r) + Integer.toHexString(g) + Integer.toHexString(b));
        CornerRadii radii = new CornerRadii(10, false);
        Insets insets = new Insets(0);
        BackgroundFill bf = new BackgroundFill(paint, radii, insets);
        Background bg = new Background(bf);
        return bg;
    }
} //end of class
