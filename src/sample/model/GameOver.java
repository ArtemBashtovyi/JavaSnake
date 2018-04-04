package sample.model;


import com.sun.javafx.tk.Toolkit;
import javafx.scene.control.Button;
import javafx.scene.control.Tooltip;

public class GameOver {
    private final static String gO = "Говнокод окончен!";

    private final static String replay  = "Давай еще!";

    private final static String NOT = "Выключите это дерьмо!!!!";


    public GameOver() {
        Button b = new Button(replay);
        Button n = new Button(NOT);
        Tooltip tooltip = new Tooltip(gO);
        b.setTooltip(tooltip);
        n.setTooltip(tooltip);
    }
}
