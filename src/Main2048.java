import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.scene.input.KeyEvent;
/**
 * Created by Oscar on 2016-09-04.
 */
public class Main2048 extends Application{
    public static GameBoard gameBoard;

    //Static variables
    private static int boardSizeX = 4;
    private static int boardSizeY = 4;
    private static int tileSize = 50;
    private static int left = 0;
    private static int right = 1;
    private static int up = 2;
    private static int down = 3;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        gameBoard = new GameBoard(boardSizeX, boardSizeY);
        initialize();
    }

    public static void initialize(){
        Stage primaryStage = new Stage();
        primaryStage.setX(300);
        primaryStage.setY(300);
        primaryStage.initStyle(StageStyle.DECORATED);
        primaryStage.setTitle("2048");
        Scene scene = new Scene(Layout.setUpGrid(),boardSizeX *tileSize+ 100,boardSizeY * tileSize + 100);
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                switch (event.getCode()) {

                    case W:    gameBoard.move(up); Layout.updateTiles(); break;
                    case S:    gameBoard.move(down); Layout.updateTiles(); break;
                    case D:    gameBoard.move(right); Layout.updateTiles(); break;
                    case A:    gameBoard.move(left); Layout.updateTiles(); break;
                }
            }
        });
        primaryStage.setScene(scene);
        primaryStage.setResizable(true);
        primaryStage.show();
    }

    public static GameBoard getGameBoard(){
        return gameBoard;
    }
    public static int getBoardSizeX() {
        return boardSizeX;
    }
    public static int getBoardSizeY() {
        return boardSizeY;
    }
    public static int getLeft(){
        return left;
    }
    public static int getDown() {
        return down;
    }
    public static int getRight() {
        return right;
    }
    public static int getUp() {
        return up;
    }
    public static int getTileSize() {
        return tileSize;
    }
}
