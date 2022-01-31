package de.tum.in.ase.fop.view;

import de.tum.in.ase.fop.*;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class TickTackToeApplication extends Application implements View {

	private GridPane gridPane;
	private final Controller controller = new GameController();

	@Override
	public void put(int place, int type) {
		var symbol = type == Constants.MIN ? new Cross() : new Circle();
		gridPane.add(symbol, place % 3, place / 3);}

	@Override
	public void showWinner(int who) {
		String winner = switch (who) {
			case Constants.MIN -> "You Win!";
			case Constants.DRAW -> "Draw!";
			case Constants.MAX -> "Java Wins!";
			default -> "";
		};
		showAsyncAlert(winner);
	}

	@Override
	public void illegalMove(int place) {
		System.out.println("Illegal move: " + place);
	}

	@Override
	public void start(Stage stage) {
		Game game = new Game(this);
		controller.setup(game, this);

		VBox content = new VBox(20);
		content.setAlignment(Pos.CENTER);

		HBox hBox = new HBox(5);
		hBox.setAlignment(Pos.CENTER);

		gridPane = new GridPane();

		Button restartButton = new Button("Restart Game");
		restartButton.setOnAction(event -> {
			resetGame();
		});

		resetGame();

		content.getChildren().addAll(restartButton, gridPane);
		hBox.getChildren().add(content);

		var scene = new Scene(hBox);
		stage.setScene(scene);
		stage.setTitle("TicTakToe");
		stage.setWidth(300);
		stage.setHeight(300);
		stage.setOnCloseRequest(closeEvent -> {
			Platform.exit();
			System.exit(0);
		});
		stage.show();
	}

	private void resetGame() {
		controller.restart();
		gridPane.getChildren().clear();
		for (int row = 0; row < 3; row++) {
			for (int column = 0; column < 3; column++) {
				gridPane.add(createCell(3 * row + column), column, row);
			}
		}
	}

	private Node createCell(int number) {
		var button = new Button();
		button.setOnAction(actionEvent -> controller.checkMove(number));
		button.setFont(Font.font("Arial", FontWeight.BOLD, 20));
		button.setPrefWidth(50);
		button.setPrefHeight(50);
		return button;
	}

	private void showAsyncAlert(String message) {
		Platform.runLater(() -> {
			Alert alert = new Alert(Alert.AlertType.INFORMATION);
			alert.setHeaderText(message);
			alert.showAndWait();
		});
	}
}
