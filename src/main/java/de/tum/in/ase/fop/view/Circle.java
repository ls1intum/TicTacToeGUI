package de.tum.in.ase.fop.view;

import javafx.scene.canvas.Canvas;
import javafx.scene.paint.Color;

public class Circle extends Canvas {

    private static final double DEFAULT_SIZE = 50;

    public Circle() {
        double width = DEFAULT_SIZE;
        double height = DEFAULT_SIZE;
        widthProperty().set(width);
        heightProperty().set(height);
        getGraphicsContext2D().setFill(Color.WHITE);
        getGraphicsContext2D().fillRect(0, 0, width, height);
        getGraphicsContext2D().setFill(Color.RED);
        getGraphicsContext2D().fillOval(width / 10, height / 10, width - width / 5, height - height / 5);
    }
}
