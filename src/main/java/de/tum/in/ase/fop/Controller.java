package de.tum.in.ase.fop;

import de.tum.in.ase.fop.view.View;

public interface Controller {
    void checkMove(int place);
    void restart();
    void setup(Model model, View view);
}
