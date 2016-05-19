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


    public int[] hasWon(){
       int winner[] = checkForWinner();

        for (int i = 0; i < winner.length; i++){
            if (winner[i] > 0){
                //System.out.println(winner[i] + "\n");
                return winner;
            }
        }
        return null;
    }

    private int[] checkForWinner() {
        int retInt[] = new int[8];
        byte x = 0;

        if (gameMove[0][0] == gameMove[1][1] && gameMove[0][0] == gameMove[2][2]) {
            if (gameMove[0][0] != '-') {
                //System.out.println("Winner: " + gameMove[0][0] + " diagonal down");
                retInt[x] = 1;
                x += 1;
            }
        }
        if (gameMove[2][0] == gameMove[1][1] && gameMove[2][0] == gameMove[0][2]) {
            if (gameMove[0][2] != '-') {
                //System.out.println("Winner: " + gameMove[2][0] + " diagonal up");
                retInt[x] = 2;
                x += 1;
            }
        }

        for (int i = 0; i < 3; i++) {
            if (gameMove[i][0] == gameMove[i][1] && gameMove[i][0] == gameMove[i][2]) {
                if (gameMove[i][0] != '-') {
                    //System.out.println("Winner: " + gameMove[i][0] + " " + (i + 1) + ". row");
                    retInt[x] = i + 3;
                    x += 1;
                }
            }
        }

        for (int i = 0; i < 3; i++) {
            if (gameMove[0][i] == gameMove[1][i] && gameMove[0][i] == gameMove[2][i]) {
                if (gameMove[0][i] != '-') {
                    //System.out.println("Winner: " + gameMove[i][0] + " " + (i + 1) + ". column");
                    retInt[x] = i + 6;
                    x += 1;
                }
            }
        }
        return retInt;
    }


    @Override
    public String toString(){
        String retString = "";
        int gameMoveLength = gameMove.length;

        for(int i = 0; i < gameMoveLength; i++){
            for (int j = 0; j < gameMoveLength; j++){
                retString +=  gameMove[i][j];
                if(j == 2) {
                    retString += "\r\n";
                }
            }
        }

        return retString;
    }
}
