package de.tum.in.ase.fop;

public class GameTreeNodeC extends GameTreeNode {

    public GameTreeNodeC(PlayGround playGround) {
        this(playGround, Constants.MIN, Constants.MAX);
    }

    private GameTreeNodeC(PlayGround playGround, int goalMin, int goalMax) {
        super(playGround);

        // have I lost already?
        if (playGround.winner() != Constants.NONE) {
            value = playGround.winner();
            return;
        }

        // no more moves --> no winner
        var moves = playGround.getMoves();
        if (!moves.hasNext()) {
            value = Constants.DRAW;
            return;
        }

        value = -2 * type;
        while (moves.hasNext()) {
            int move = moves.next();
            children[move] = new GameTreeNodeC(playGround.makeMove(move), goalMin, goalMax);
            if (type == Constants.MIN && children[move].value < value || type == Constants.MAX && children[move].value > value) {
                value = children[move].value;
                bestMove = move;
                // update goals
                if (type == Constants.MIN && goalMax > value) {
                    goalMax = value;
                }
                if (type == Constants.MAX && goalMin < value) {
                    goalMin = value;
                }
                // leave if goal is reached
                if (type == Constants.MIN && value <= goalMin) {
                    return;
                }
                if (type == Constants.MAX && value >= goalMax) {
                    return;
                }
            }
        }
    }
}
