package de.tum.in.ase.fop.view;

import javafx.scene.canvas.Canvas;
import javafx.scene.paint.Color;

public class Cross extends Canvas {

	private static final double DEFAULT_SIZE = 50;

	public Cross() {
		double width = DEFAULT_SIZE;
		double height = DEFAULT_SIZE;
		widthProperty().set(width);
		heightProperty().set(height);
		double[] x0 = { width / 10, width - width / 5, width - width / 10, width / 5 };
		double[] y = { height / 5, height - height / 10, height - height / 5, height / 10 };
		getGraphicsContext2D().setFill(Color.WHITE);
		getGraphicsContext2D().fillRect(0, 0, width, height);
		getGraphicsContext2D().setFill(Color.BLUE);
		getGraphicsContext2D().fillPolygon(x0, y, 4);
		double[] x1 = { width - width / 10, width / 5, width / 10, width - width / 5 };
		getGraphicsContext2D().fillPolygon(x1, y, 4);
	}
}
