
import java.util.ArrayList;
import java.util.Random;



/**
 * Created by Oscar on 2016-09-04.
 */
public class GameBoard{

    private ArrayList<ArrayList<Integer>> tiles = new ArrayList<ArrayList<Integer>>();

    private int boardsizeX;
    private int boardsizeY;
    private int left;
    private int right;
    private int up;
    private int down;
    private int emptyTile = 0;
    private Random rnd = new Random();

    public GameBoard(int boardsizeX,int boardsizeY){
        this.boardsizeX = boardsizeX;
        this.boardsizeY = boardsizeY;
        left = Main2048.getLeft();
        right = Main2048.getRight();
        up = Main2048.getUp();
        down = Main2048.getDown();
        initialize();
        generateNewTile();
    }


    private void initialize(){
        for(int i= 0; i < boardsizeY; i++){
            ArrayList<Integer> column = new ArrayList<Integer>();
            for(int j = 0; j < boardsizeX; j++){
                column.add(emptyTile);
            }
            tiles.add(column);
        }
    }

    private void generateNewTile(){
        boolean tileAdded = false;
        if(!hasLost()) {
            while (!tileAdded) {
                int rndX = rnd.nextInt(boardsizeX);
                int rndY = rnd.nextInt(boardsizeY);
                if (tiles.get(rndY).get(rndX) == emptyTile) {
                    int rndNewValue = rnd.nextInt(2);
                    if (rndNewValue == 1) {
                        tiles.get(rndY).set(rndX, 2);
                    } else {
                        tiles.get(rndY).set(rndX, 4);
                    }
                    tileAdded = true;
                }
            }
        }
    }

    private boolean hasLost(){
        for (int i = 0; i < tiles.size(); i++) {
            for (int j = 0; j < tiles.get(i).size(); j++) {
                if(tiles.get(i).get(j) == emptyTile){
                    return false;
                }
            }
        }
        return true;
    }



    public void move(int direction){
        if(direction == left || direction == right){
            for (int i = tiles.size() - 1; -1 < i; i--) {
                moveRow(direction, i);
            }
        }
        if(direction == up || direction == down){
            for (int col = boardsizeX - 1; -1 < col; col--) {
                moveCol(direction, col);
            }
        }
        generateNewTile();
    }

    private void moveCol(int direction, int col){
        if(direction == up){
            for (int i = 0; i < boardsizeY-1; i++) {
                int nextTile = i + 1;
                if (isSameNumber((tiles.get(i).get(col)), tiles.get(nextTile).get(col))) {
                    tiles.get(i).set(col, tiles.get(i).get(col) + tiles.get(nextTile).get(col));
                    tiles.get(nextTile).set(col, emptyTile);
                } else if (tiles.get(i).get(col) == emptyTile) {
                    tiles.get(i).set(col, tiles.get(nextTile).get(col));
                    tiles.get(nextTile).set(col, emptyTile);
                    moveCol(up,col);
                }
            }
        }
        else if(direction == down){
            for (int i = boardsizeY-1; 0 < i; i--) {
                int nextTile = i - 1;
                if (isSameNumber((tiles.get(i).get(col)), tiles.get(nextTile).get(col))) {
                    tiles.get(i).set(col, tiles.get(i).get(col) + tiles.get(nextTile).get(col));
                    tiles.get(nextTile).set(col, emptyTile);
                } else if (tiles.get(i).get(col) == emptyTile) {
                    tiles.get(i).set(col, tiles.get(nextTile).get(col));
                    tiles.get(nextTile).set(col, emptyTile);
                    moveCol(down,col);
                }
            }
        }
    }

    private void moveRow(int direction, int row){
        if (direction == right){
                for (int i = boardsizeX- 1; 0 < i; i--) {
                    int nextTile = i - 1;
                    if (isSameNumber((tiles.get(row).get(i)), tiles.get(row).get(nextTile))) {
                        tiles.get(row).set(i, tiles.get(row).get(i) + tiles.get(row).get(nextTile));
                        tiles.get(row).set(nextTile, emptyTile);
                    } else if (tiles.get(row).get(i) == emptyTile) {
                        tiles.get(row).set(i, tiles.get(row).get(nextTile));
                        tiles.get(row).set(nextTile, emptyTile);
                        moveRow(right,row);
                    }
                }
            }
        else if (direction == left){
            for(int j = 0; j <  boardsizeX-1; j++) {
                int nextTile = j + 1;
                if (isSameNumber((tiles.get(row).get(j)), tiles.get(row).get(nextTile))) {
                    tiles.get(row).set(j, tiles.get(row).get(j) + tiles.get(row).get(nextTile));
                    tiles.get(row).set(nextTile, emptyTile);
                } else if (tiles.get(row).get(j) == emptyTile) {
                    tiles.get(row).set(j, tiles.get(row).get(nextTile));
                    tiles.get(row).set(nextTile, emptyTile);
                    moveRow(left, row); //calls recursive function
                }
            }
        }
    }
    private boolean isSameNumber(int x, int y){
        if(x == y){
            return true;
        }
        else{
            return false;
        }
    }

    public int getValueOfTile(int x , int y){
        return tiles.get(y).get(x);
    }

    public void print(){
        for (int i = 0; i < tiles.size(); i++) {
            for (int j = 0; j < tiles.get(i).size(); j++) {
                if(tiles.get(i).get(j) == emptyTile){
                    System.out.print("    ");
                }
                else{
                    System.out.print(" " + tiles.get(i).get(j) + "  ");
                }
            }
            System.out.println("");
        }
        System.out.println("_______________");
    }




}
