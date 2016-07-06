package data;

/**
 * @author Abelsen, Tommy
 *
 */
public class C4Game {

    private char[][] gameBoard = new char[6][7];


    public void fillGameBoard(){

        char x = '0';

        for(int i = 0; i < gameBoard.length; i++){
            for (int j = 0; j < gameBoard[0].length; j++){
                x++;
                gameBoard[i][j] = x;
            }
        }
    }

    @Override
    public String toString(){

        String retString = "";

        for (int i = 0; i < 6; i++){
            retString += "\n";
            for (int j = 0; j < 7; j++){
                retString += gameBoard[i][j];
            }
        }
        return retString;
    }
}
