
import java.util.ArrayList;
import java.util.Random;



/**
 * Created by Oscar on 2016-09-04.
 */
public class GameBoard{

    private ArrayList<ArrayList<Integer>> tiles = new ArrayList<ArrayList<Integer>>();
    private ArrayList<Integer[]> notMoveList = new ArrayList<Integer[]>();
    private int boardsizeX;
    private int boardsizeY;
    private int emptyTile = 0;
    private Random rnd = new Random();

    public GameBoard(int boardsizeX,int boardsizeY){
        this.boardsizeX = boardsizeX;
        this.boardsizeY = boardsizeY;
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

    public void moveRight(){
        for (int i = 0; i < tiles.size(); i++) {
            for (int j = 0; j < tiles.get(i).size(); j++) {
                if(j != boardsizeX-1) {
                    int nextTile = j+1;
                    if (isSameNumber(tiles.get(i).get(j), tiles.get(i).get(nextTile)) && !isUnmovable(i, j)) {
                        tiles.get(i).set(nextTile, tiles.get(i).get(j) + tiles.get(i).get(nextTile));
                        tiles.get(i).set(j, emptyTile);
                        Integer unMovableTile[] = {i, nextTile};
                        notMoveList.add(unMovableTile);
                    }
                    else if(tiles.get(i).get(nextTile) == emptyTile){
                        tiles.get(i).set(nextTile, tiles.get(i).get(j));
                        tiles.get(i).set(j, emptyTile);
                    }
                }
            }
        }
        notMoveList.clear();
        generateNewTile();
    }



    public void moveLeft(){
        for (int i = boardsizeY-1; -1 < i; i--) {
            for (int j = boardsizeX-1; -1 < j; j--) {
                if(j != 0) {
                    int nextTile = j - 1;
                    if (isSameNumber(tiles.get(i).get(j), tiles.get(i).get(nextTile)) && !isUnmovable(i, j)) {
                        tiles.get(i).set(nextTile, tiles.get(i).get(j) + tiles.get(i).get(nextTile));
                        tiles.get(i).set(j, emptyTile);
                        Integer unMovableTile[] = {i, nextTile};
                        notMoveList.add(unMovableTile);
                    }
                    if (tiles.get(i).get(nextTile) == emptyTile) {
                        tiles.get(i).set(nextTile, tiles.get(i).get(j));
                        tiles.get(i).set(j, emptyTile);
                    }
                }
            }
        }
        notMoveList.clear();
        generateNewTile();
    }


    public void moveDown(){
        for (int i = 0; i < tiles.size(); i++) {
            for (int j = 0; j < tiles.get(i).size(); j++) {
                if(i != boardsizeY-1) {
                    int nextTile = i+1;
                    if (isSameNumber(tiles.get(i).get(j), tiles.get(nextTile).get(j)) && !isUnmovable(i, j)) {
                        tiles.get(nextTile).set(j, tiles.get(i).get(j) + tiles.get(nextTile).get(j));
                        tiles.get(i).set(j, emptyTile);
                        Integer unMovableTile[] = {nextTile, j};
                        notMoveList.add(unMovableTile);
                    }
                    else if(tiles.get(nextTile).get(j) == emptyTile){
                        tiles.get(nextTile).set(j, tiles.get(i).get(j));
                        tiles.get(i).set(j, emptyTile);
                    }
                }
            }
        }
        notMoveList.clear();
        generateNewTile();
    }

    public void moveUp(){
        for (int i = boardsizeY-1; 0 < i; i--) {
            for (int j = boardsizeX-1; -1 < j; j--) {
                int nextTile = i-1;
                if (isSameNumber(tiles.get(i).get(j), tiles.get(nextTile).get(j)) && !isUnmovable(i, j)) {
                    tiles.get(nextTile).set(j, tiles.get(i).get(j) + tiles.get(nextTile).get(j));
                    tiles.get(i).set(j, emptyTile);
                    Integer unMovableTile[] = {nextTile, j};
                    notMoveList.add(unMovableTile);
                }
                else if(tiles.get(nextTile).get(j) == emptyTile){
                    tiles.get(nextTile).set(j, tiles.get(i).get(j));
                    tiles.get(i).set(j, emptyTile);
                }
            }
        }
        notMoveList.clear();
        generateNewTile();
    }

    private boolean isUnmovable(int y, int x){
        for (int i = 0; i < notMoveList.size(); i++){
            if(notMoveList.get(i)[0] == y && notMoveList.get(i)[1] == x){
                return true;
            }
        }
        return false;
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
