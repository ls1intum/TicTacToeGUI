package de.tum.in.ase.fop;

interface Model {
    void    makePlayerMove(int place);
    void    makeBestMove();
    boolean isMovePossible(int place);
    boolean isFinished();
}
