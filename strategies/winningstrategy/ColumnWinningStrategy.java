package TicTacToeNew.strategies.winningstrategy;

import TicTacToe.models.Player;
import TicTacToeNew.models.Board;
import TicTacToeNew.models.Cell;
import TicTacToeNew.models.Move;

import java.util.HashMap;
import java.util.Map;


public class ColumnWinningStrategy implements WinningStrategy {

    private final HashMap<Integer, HashMap<Character,Integer>> colMap = new HashMap<>();

    @Override
    public boolean checkWinner(Board board, Move move) {
       int col = move.getCell().getCol();
       Character symbol = move.getCell().getPlayer().getSymbol().getaChar();

//      Initialize col map if this is first move made in this col
        if(!colMap.containsKey(col)){
            colMap.put(col, new HashMap<>());
        }

//        Get the count map for this specific row
        HashMap<Character,Integer> currColMap = colMap.get(col);

        if(!currColMap.containsKey(symbol)){
            currColMap.put(symbol, 1);
        }
        else{
            currColMap.put(symbol, currColMap.get(symbol) + 1);
        }

        if(currColMap.get(symbol).equals(board.getDimension())){
            return true;
        }

        return false;
    }

    @Override
    public void handleUndo(Board board, Move move) {
        int col = move.getCell().getCol();
        Character symbol = move.getCell().getPlayer().getSymbol().getaChar();

        Map<Character,Integer> currColMap = colMap.get(col);

        if(currColMap != null && currColMap.containsKey(symbol)){
            int currentCount = currColMap.get(symbol);

            if(currentCount == 1){
                currColMap.remove(symbol);
                if(currColMap.isEmpty()){
                    colMap.remove(col);
                }
            }
            else{
                currColMap.put(symbol, currColMap.get(symbol) - 1);
            }
        }
    }
}
