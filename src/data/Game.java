package data;

/**
 * @author Abelsen, Tommy
 */
public class Game {

    private char[][] gameMove = {
            {'-', '-', '-'},
            {'-', '-', '-'},
            {'-', '-', '-'}};

    public boolean setGameMove(int i, int j, char move){
        if (gameMove[i][j] != 'X' && gameMove[i][j] != 'O'){
            gameMove[i][j] = move;
            return true;
        }
        else return false;
    }

    public char[][] getGameMove() {
        return gameMove;
    }

    @Override
    public String toString(){
        String retString = "";
        int gameMoveLength = gameMove.length;

        for(int i = 0; i < gameMoveLength; i++){
            for (int j = 0; j < gameMoveLength; j++){
                retString += gameMove[i][j];
                if(j == 2) {
                    retString += "\r\n";
                }
            }
        }

        return retString;
    }
}
