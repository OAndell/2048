import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import java.util.ArrayList;

/**
 * Created by Oscar on 2016-09-04.
 */
public class Layout{
    public static ArrayList<ArrayList<Label>> labelList;

    public static GridPane setUpGrid(){
        labelList = createTiles();
        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(50,50,50,50));
        gridPane.setHgap(2);
        gridPane.setVgap(2);
        for (int i = 0; i < Main2048.boardSizeY; i++) {
            for (int j = 0; j < Main2048.boardSizeX; j++) {
                gridPane.add(labelList.get(i).get(j), j , i);
            }
        }
        return gridPane;
    }
    /**
     * Updates the layout and gets the new values from gameBoard tiles
     */
    public static void updateTiles(){
        for (int i = 0; i < labelList.size(); i++) {
            for (int j = 0; j < labelList.get(i).size(); j++) {
                if(Main2048.gameBoard.getValueOfTile(j,i) == 0){
                    labelList.get(i).get(j).setText(" ");
                }
                else {
                    labelList.get(i).get(j).setText(String.valueOf(Main2048.gameBoard.getValueOfTile(j, i)));
                }
                updateLabelColor(labelList.get(i).get(j), Main2048.gameBoard.getValueOfTile(j, i));
            }
        }
    }

    private static ArrayList<ArrayList<Label>> createTiles(){
        ArrayList<ArrayList<Label>> tiles = new ArrayList<>();
        for (int i = 0; i < Main2048.boardSizeY; i++) {
            ArrayList<Label> column = new ArrayList<Label>();
            for (int j = 0; j < Main2048.boardSizeX; j++) {
                Label label = new Label();
                if(Main2048.gameBoard.getValueOfTile(j,i) == 0){
                    label.setText(" ");
                }
                else {
                    label.setText(String.valueOf(Main2048.gameBoard.getValueOfTile(j,i)));
                }
                label.setStyle(Main2048.tileCSS + "-fx-background-color: #F3F1F1;");
                label.setMinSize(Main2048.tileSize,Main2048.tileSize);
                label.setMaxSize(Main2048.tileSize,Main2048.tileSize);
                label.setAlignment(Pos.CENTER);
                column.add(label);
            }
            tiles.add(column);
        }
        return tiles;
    }

    private static void updateLabelColor(Label label , int value){
        if(value == 0){
            label.setStyle(Main2048.tileCSS + "-fx-background-color: #F2F2F2;");
        }
        else if(value == 2){
            label.setStyle(Main2048.tileCSS + "-fx-background-color: #E9E9E9;");
        }
        else if(value == 4){
            label.setStyle(Main2048.tileCSS + "-fx-background-color: #D6C29F;");
        }
        else if(value == 8){
            label.setStyle(Main2048.tileCSS + "-fx-background-color: #E88937;");
        }
        else if(value == 16){
            label.setStyle(Main2048.tileCSS + "-fx-background-color: #CF511A;");
        }
        else if(value == 32){
            label.setStyle(Main2048.tileCSS + "-fx-background-color: #C93333;");
        }
        else if(value == 64){
            label.setStyle(Main2048.tileCSS + "-fx-background-color: #FF0000;");
        }
        else if(value > 64){
            label.setStyle(Main2048.tileCSS + "-fx-background-color: #FFE205;");
        }
    }


}
