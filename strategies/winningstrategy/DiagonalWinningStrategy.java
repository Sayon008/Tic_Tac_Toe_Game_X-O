package TicTacToeNew.strategies.winningstrategy;

import TicTacToe.models.Player;
import TicTacToeNew.models.Board;
import TicTacToeNew.models.Cell;
import TicTacToeNew.models.Move;

import java.util.HashMap;

public class DiagonalWinningStrategy implements WinningStrategy{

//    We have two diagonal
    private final HashMap<Character, Integer> leftDiagonalMap = new HashMap<>();       // starting from 0,0
    private final HashMap<Character, Integer> rightDiagonalMap = new HashMap<>();      // starting from 0, n-1

    @Override
    public boolean checkWinner(Board board, Move move) {
        int row = move.getCell().getRow();
        int col = move.getCell().getCol();

        Character symbol = move.getCell().getPlayer().getSymbol().getaChar();

//        Condition for left diagonal --> row == col
        if(row == col){
            if(!leftDiagonalMap.containsKey(symbol)){
                leftDiagonalMap.put(symbol, 0);         // we are not putting 1 instead we are using 0 if the symbol doesnot exist
                // because we are not using if else statement, we are first putting 0 and when the if statement finishes  we are incrementing the 0 value to 1
            }

            leftDiagonalMap.put(symbol,leftDiagonalMap.get(symbol) + 1);
        }

//        Condition for right diagonal --> row + col == N -1

        if(row + col == board.getDimension() - 1){
            if(!rightDiagonalMap.containsKey(symbol)){
                rightDiagonalMap.put(symbol,0);         // we are not putting 1 instead we are using 0 if the symbol doesnot exist
                // because we are not using if else statement, we are first putting 0 and when the if statement finishes  we are incrementing the 0 value to 1
            }

            rightDiagonalMap.put(symbol,rightDiagonalMap.get(symbol) + 1);
        }

        if(row == col && leftDiagonalMap.get(symbol) == board.getDimension()){
            return true;
        }

        if(row + col == board.getDimension() - 1 && rightDiagonalMap.get(symbol) == board.getDimension()){
            return true;
        }

        return false;
    }
}
