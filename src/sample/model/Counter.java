package sample.model;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class Counter extends Label {

    private static final double WIDTH = 40;
    private static final double HEIGHT = 40;


    private int count;

    public Counter() {
        count = 0;
        super.setPrefSize(WIDTH,HEIGHT);
        super.setText(this.count + "");
        super.setPadding(new Insets(10,10,10,10));
        super.setGraphicTextGap(25);
        super.setTextFill(Color.WHITE);
        super.setFont(new Font(25));
        // не работает хрень
        super.parentToLocal(100,700);
    }

    private void setCount() {

    }

    public void counterPlus() {
        this.count = count+1;
        super.setText(this.count + "");
    }

    public int getCount() {
        return this.count;
    }
}
