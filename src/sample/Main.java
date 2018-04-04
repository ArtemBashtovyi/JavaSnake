package sample;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.util.Duration;
import sample.model.Cell;
import sample.model.Counter;
import sample.model.GameOver;
import sample.model.Snake;





/* created by @Felix */
public class Main extends Application {

    private static final double SPEED = 0.20;
    private static final double MAIN_SCREEN_WIDTH = 600;
    private static final double MAIN_SCREEN_HEIGHT = 600;

    private static final int SNAKE_START_POSITION_X = 10;
    private static final int SNAKE_START_POSITION_Y = 10;

    // count cells
    private int xCells = (int) (MAIN_SCREEN_WIDTH/20);
    private int yCells = (int) (MAIN_SCREEN_HEIGHT/20);

    private Counter counter;
    private Cell[][] cells = new Cell[xCells][yCells];
    private Group group = new Group();
    private Scene scene;

    private Snake snake;

    @Override
    public void start(Stage primaryStage){

        primaryStage.setTitle("Hello World");

        scene = new Scene(generateField(),MAIN_SCREEN_WIDTH,MAIN_SCREEN_HEIGHT);


        generateSnake();
        generateApple();
        // keyboard listener
        scene.setOnKeyPressed(event -> {
            switch (event.getCode()) {
                case UP :    snake.goUp();     break;
                case LEFT :  snake.goLeft();   break;
                case RIGHT : snake.goRight();  break;
                case DOWN :  snake.goDown();   break;
            }
        });

        primaryStage.setScene(scene);
        primaryStage.show();

        startTimer();
    }


    public static void main(String[] args) {

        launch(args);
    }

    // Create Game Field
    private Group generateField() {
        counter = new Counter();
        for (int i = 0; i < cells.length;i++) {
            for (int j = 0; j < cells[i].length;j++) {

                cells[i][j] = new Cell(i,j);
                group.getChildren().add(cells[i][j]);
            }
        }
        group.getChildren().add(counter);
        return group;
    }

    private void generateSnake() {

        //create Snake and set cells light
        snake = new Snake(SNAKE_START_POSITION_X,SNAKE_START_POSITION_Y);
        snake.addLength();
        snake.addLength();
        snake.goUp();

        cells[SNAKE_START_POSITION_X][SNAKE_START_POSITION_Y]
                .changeLightTime(snake.getLength());
        cells[SNAKE_START_POSITION_X][SNAKE_START_POSITION_Y]
                .setColor();

        cells[SNAKE_START_POSITION_X][SNAKE_START_POSITION_Y+1]
                .changeLightTime(snake.getLength());
        cells[SNAKE_START_POSITION_X][SNAKE_START_POSITION_Y+1]
                .setColor();


    }

    private void generateApple() {
        int positionX = 1;
        int positionY = 19;
        int randomX = positionX + (int) (Math.random() * positionY);
        int randomY = positionX + (int) (Math.random() * positionY);

        cells[randomX][randomY].changeLightTime(-1);
    }

    // When snake eat apple
    private void changeLengthSnake(Cell cell) {
        if (cell.getLightTime() == -1) {
            // add length for shake
            snake.addLength();
            cell.changeLightTime(snake.getLength());
            // generate new Apple
            generateApple();
            //counter
            counter.counterPlus();
        }
    }

    // Moving snake
    private void changeSnakePosition(int x,int y) throws ArrayIndexOutOfBoundsException{

        changeLengthSnake(cells[x][y]);
        cells[x][y].changeLightTime(snake.getLength()+1);

        updateField();
    }

    // Update GUI
    private void updateField()  {

        // GUI
        group.getChildren().clear();

        // Matrix
        for (Cell[] cell : cells) {
            for (Cell aCell : cell) {
                // Тут нужно абдейтить всю матрицу
                aCell.update(true);
                group.getChildren().add(aCell);
            }
        }
        group.getChildren().add(counter);
    }

    // Snake speed - хоча НІ!
    private void startTimer() {
        Timeline everySecondsWorker = new Timeline(new KeyFrame(Duration.seconds(SPEED),
                event ->  snakeGo()));

        everySecondsWorker.setCycleCount(Timeline.INDEFINITE);
        everySecondsWorker.play();
    }

    // Control turns
    private void snakeGo() {

        int x = snake.getPositionX();
        int y = snake.getPositionY();

        switch (snake.getCurrentSide()) {
            case UP :
                snake.setPositionY(y);
                snake.setPositionY(snake.getPositionY() - 1);
            break;

            case LEFT:
                snake.setPositionX(x);
                snake.setPositionX(snake.getPositionX() - 1);
            break;

            case RIGHT:
                snake.setPositionX(x);
                snake.setPositionX(snake.getPositionX() + 1);
            break;

            case DOWN:
                snake.setPositionY(y);
                snake.setPositionY(snake.getPositionY() + 1);
        }

        // Clear field for every second
        try {
            changeSnakePosition(snake.getPositionX(), snake.getPositionY());
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("GAME OVER");
            gameOver();
        }
    }


    private void gameOver() {
        GameOver g = new GameOver();
    }



}
