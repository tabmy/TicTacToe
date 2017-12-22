package data;

import java.util.NoSuchElementException;
import java.util.logging.Logger;


public class C4Game {

    private static final Logger LOGGER = Logger.getLogger(C4Game.class.getName());

    private int width = 7, height = 6;

    private char[][] gameBoard = new char[height][width];

    private char playerTurn = 'B';

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



    public void newBoard() {
        gameBoard = new char[height][width];
        fillGameBoard();
    }

    public boolean hasWon() {
        return checkWin(playerTurn);
    }

    private boolean checkWin(char c){


        int _height = gameBoard.length;
        int _width = gameBoard[0].length;

        // vertical check
        for (int i = 0; i < _height -3 ; i++) {
            for (int j = 0; j < _width; j++) {
                if(gameBoard[i][j] == c && gameBoard[i+1][j] == c && gameBoard[i + 2][j] == c && gameBoard[i + 3][j] == c){
                    return true;
                }

            }
        }

        // horizontal check

        for (int i = 0; i < _height ; i++) {
            for (int j = 0; j < _width - 3; j++) {
                if(gameBoard[i][j] == c && gameBoard[i][j + 1] == c && gameBoard[i][j + 2] == c && gameBoard[i][j + 3] == c){
                    return true;
                }
            }
        }

        // diagonal up-right check

        for (int i = 0; i < _height -3; i++) {
            for (int j = 0; j < _width -3; j++) {
                if (gameBoard[i][j] == c && gameBoard[i+1][j +1] == c && gameBoard[i + 2][j + 2] == c && gameBoard[i + 3][j + 3] == c){
                    return true;
                }

            }
        }

        // diagonal up-left check

        for (int i = 0; i < _height - 3; i++) {
            for (int j = 3; j < _width ; j++) {
                if(gameBoard[i][j] == c && gameBoard[i + 1][j - 1] == c && gameBoard[i + 2][j - 2] == c && gameBoard[i + 3][j - 3] == c){
                    return true;
                }
            }
        }

        return false;
    }




    @Override
    public String toString(){

        StringBuilder str = new StringBuilder();

        for (char[] c : gameBoard) {
            for (int i = 0; i < width ; i++) {
                str.append(c[i]);
            }
            str.append("\n");
        }
        return str.toString();
    }


}
