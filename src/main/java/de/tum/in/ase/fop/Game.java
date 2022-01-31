package de.tum.in.ase.fop;

import de.tum.in.ase.fop.view.View;

public class Game implements Model {
    private PlayGround playGround;
    private GameTreeNode gameTreeNode;
    private final View view;

    public Game(View view) {
        this.view = view;
        playGround = new PlayGround();
    }

    private void initTree() {
        GameTreeNode.nodeCount = 0;
        gameTreeNode = new GameTreeNodeA(playGround);
        System.out.println("Generate tree... (" + GameTreeNode.nodeCount + " nodes)");
    }

    private void makeMove(int place) {
        if (view != null) {
            view.put(place, playGround.getPlayerToMove());
        }
        playGround = playGround.makeMove(place);
        if (isFinished()) {
            if (view != null) {
                view.showWinner(playGround.winner());
            }
        }
    }

    public void makeBestMove() {
        initTree();
        makeMove(gameTreeNode.getBestMove());
    }

    public void makePlayerMove(int place) {
        makeMove(place);
        if (!isFinished()) {
            makeBestMove();
        }
    }

    public boolean isMovePossible(int place) {
        return playGround.isMovePossible(place);
    }

    public boolean isFinished() {
        return !playGround.getMoves().hasNext();
    }

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        Game game = new Game(null);
        for (int i = 0; i < 9; i++) {
            if (!game.isFinished()) {
                game.makeBestMove();
                System.out.println(game.playGround);
            }
            else {
                System.out.println("No more moves!");
            }
        }
        long end = System.currentTimeMillis();
        System.out.println("\nEfficiency: " + (end - start) + "ms");
    }
}
