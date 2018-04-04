package sample.model;

import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;
import java.util.List;


public class Snake extends StackPane {

    private static Color color = new Color(0.9,0.0,0.0,1);

    private Side currentSide;

    private List<Rectangle> length = new ArrayList<>();

    private int positionX;
    private int positionY;

    public Snake(int positionX, int positionY) {

        this.positionX = positionX;
        this.positionY = positionY;

        addLength();
        System.out.println();
        System.out.println("Create Snake");
    }


    public int getPositionX() {
        return positionX;
    }

    public void setPositionX(int positionX) {
        this.positionX = positionX;
    }

    public int getPositionY() {
        return positionY;
    }

    public void setPositionY(int positionY) {
        this.positionY = positionY;
    }

    public void goUp() {
        this.currentSide = Side.UP;
    }

    public void goLeft() {
        this.currentSide = Side.LEFT;
    }

    public void goDown() {
        this.currentSide = Side.DOWN;
    }

    public void goRight() {
        this.currentSide = Side.RIGHT;
    }



    public Side getCurrentSide() {
        return this.currentSide;
    }

    public void addLength() {
        Rectangle rectangle = new Rectangle();
        rectangle.setFill(color);
        rectangle.setStroke(color);
        length.add(rectangle);

    }

    public int getLength() {
        return length.size();
    }
}
