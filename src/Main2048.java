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
    public static int boardSizeX = 4;
    public static int boardSizeY = 4;
    public static int tileSize = 50;

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

                    case W:    gameBoard.moveUp(); Layout.updateTiles(); break;
                    case S:     gameBoard.moveDown(); Layout.updateTiles(); break;
                    case D:     gameBoard.moveRight(); Layout.updateTiles(); break;
                    case A:      gameBoard.moveLeft(); Layout.updateTiles(); break;
                }
            }
        });
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

}
