package data;

/**
 * @author Abelsen, Tommy
 */
public class Game {

    private byte[][] gameboard;



    public void nextmove(byte move, byte x, byte y){
        if(move == 0 || move == 1){
            gameboard[x][y] = move;
        }
    }
}
