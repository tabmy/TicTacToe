package data;

import java.util.NoSuchElementException;
import java.util.logging.Logger;

/**
 * @author Abelsen, Tommy
 *
 */
public class C4Game {

    private static final Logger LOGGER = Logger.getLogger(C4Game.class.getName());

    private int width = 7, height = 6;

    private char[][] gameBoard = new char[height][width];

    public char playerTurn = 'B';

    public void changePlayerTurn(){
        if (playerTurn == 'R'){
            playerTurn = 'B';
        }else if (playerTurn == 'B'){
            playerTurn = 'R';
        }
    }

    public char getPlayerTurn(){
        return playerTurn;
    }

    public void fillGameBoard(){

        char x = '-';

        for(int i = 0; i < gameBoard.length; i++){
            for (int j = 0; j < gameBoard[0].length; j++){
                gameBoard[i][j] = x;
            }
        }
    }

    public boolean setGameMove(int column){
         for (int i = 5; i >= 0; i--) {
             if (gameBoard[i][column] != 'R' && gameBoard[i][column] != 'B') {
                 gameBoard[i][column] = playerTurn;
                 return true;
             }
         }
        return false;
    }

    /**
     * Gets a double value that's the X - position of the game, making up the columns of the board,
     * and checks for which column the values indicate, and returns the correct numeric value of the
     * column the x-position indicates.
     * @param xPos
     * @return
     */
    public int getColumn(double xPos){

        if(xPos < 50){
            return 0;
        }else if(xPos < 115){
            return 1;
        }else if(xPos < 200){
            return 2;
        }else if(xPos < 270){
            return 3;
        }else if(xPos < 350){
            return 4;
        }else if (xPos < 420){
            return 5;
        }else if (xPos < 500){
            return 6;
        }

        return -1;
    }

    public int getRow(int column){

        for (int i = 5; i >= 0; i--) {
            if (gameBoard[i][column] != 'R' && gameBoard[i][column] != 'B') {
                return i;
            }
        }

        return -1;
    }

    public int getHeight(){return height;}
    public int getWidth() {return width;}

    public char getSquare(int x, int y){
        if (x < 0 || x > width || y < 0 || y > width) throw new NoSuchElementException("Illegal square coordinates.");
        return gameBoard[x][y];
    }

    @Override
    public String toString(){

        String retString = "";

        for (int i = 0; i < height; i++){
            retString += "\n";
            for (int j = 0; j < width; j++){
                retString += gameBoard[i][j];
            }
        }
        return retString;
    }
}
