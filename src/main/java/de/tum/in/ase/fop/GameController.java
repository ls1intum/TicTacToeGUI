package de.tum.in.ase.fop;

import de.tum.in.ase.fop.view.View;

public class GameController implements Controller {
	private Model game;
	private View view;

	public void setup(Model model, View view) {
		this.game = model;
		this.view = view;
	}

	public void checkMove(int place) {
		if (game.isMovePossible(place)) {
			game.makePlayerMove(place);
		} else {
			view.illegalMove(place);
		}
	}

	public void restart() {
		game = new Game(view);
	}
}




