package sample.model;

import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;


public class Cell extends StackPane {

    private static final int CELL_WIDTH = 20;
    private static final int CELL_HEIGHT = 20;

    private Color emptyColor = new Color(0.1294, 0.5216, 0.5333, 0.8);
    private Color appleColor = new Color(1.0,0,0,1);
    private Color withSnakeColor = new Color(0.0,1.0,0,1);

    private  Rectangle rectangle;

    /** check how long will be lightTime
     *   0 - emptyCell;
     *   > 0 - snakeCell;
     *   -1 - appleCell;
     */

    private int lightTime;


    public Cell(int x, int y) {

        // Create cell with properties
        int positionX = x * CELL_WIDTH;
        int positionY = y * CELL_HEIGHT;
        setTranslateY(positionY);
        setTranslateX(positionX);
        rectangle = new Rectangle(CELL_WIDTH,CELL_HEIGHT);
        //rectangle.setStroke(Color.BLACK);

        clearCell();

    }


    private void clearCell() {
        rectangle.setFill(emptyColor);

        getChildren().add(rectangle);
    }

    // Change light time for snake
    public void changeLightTime(int lightTime) {
        this.lightTime = lightTime;
    }

    public int getLightTime() {
        return this.lightTime;
    }


    // For every second update
    public void update(boolean isDecrement) {
        if (isDecrement && this.lightTime > 0) {
            this.lightTime--;
        }
        setColor();
    }

    // Show Color
    public void setColor() {
        Color color;
        getChildren().clear();
        if(this.lightTime > 0) {
            color = withSnakeColor;
        } else if (this.lightTime == 0) {
            color = emptyColor;
        } else {
            color = appleColor;
        }
        rectangle.setFill(color);
        getChildren().add(rectangle);
    }




}

