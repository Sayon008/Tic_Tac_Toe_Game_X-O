package TicTacToeNew.strategies.winningstrategy;

import TicTacToe.models.Player;
import TicTacToeNew.models.Board;
import TicTacToeNew.models.Cell;
import TicTacToeNew.models.Move;
import TicTacToeNew.models.Symbol;

import java.util.HashMap;
import java.util.Map;


// We need to track counts for each row
// Each row can have multiple symbols (X, O, etc.)
// So: Map<Row_Number, Map<Symbol, Count>>


//Mental Model:
//Board:           Count Tracking:
//        | X | - | O |    Row 0: {X: 1, O: 1}
//        | O | O | - |    Row 1: {O: 2}
//        | - | X | X |    Row 2: {X: 2}
//
//After X moves to (0,1):
//        | X | X | O |    Row 0: {X: 2, O: 1}  ← X has 2, not 3 yet
//| O | O | - |    Row 1: {O: 2}
//        | - | X | X |    Row 2: {X: 2}
//
//After X moves to (2,0):
//        | X | X | O |    Row 0: {X: 2, O: 1}
//        | O | O | - |    Row 1: {O: 2}
//        | X | X | X |    Row 2: {X: 3}  ← X has 3 = dimension, X WINS!

public class RowWinningStrategy implements WinningStrategy{

//    We can use List<HashMap> as well instead of HashMap<HashMap>  --> we are using the HashMap<HashMap> here for easy iteration

    private final Map<Integer, HashMap<Character, Integer>> rowMap = new HashMap<>();
    //                    ↑              ↑           ↑
//                   Row Number        Symbol        Count of that symbol
    @Override
    public boolean checkWinner(Board board, Move move) {

        // Step 1: Extract information from the move
        int row = move.getCell().getRow();

        Character aChar = move.getCell().getPlayer().getSymbol().getaChar();

        // Step 2: Initialize row map if this is first move in this row
        if(!rowMap.containsKey(row)){
            rowMap.put(row, new HashMap<>());
        }

        // Step 3: Get the count map for this specific row
        Map<Character,Integer> currRowMap = rowMap.get(row);

        // Step 4: Update count for this symbol in this row
        if(!currRowMap.containsKey(aChar)){
            currRowMap.put(aChar, 1);   // First occurrence
        }
        else{
            currRowMap.put(aChar, currRowMap.get(aChar) + 1);
        }

        // Step 5: Check if this symbol now has enough count to win
        if(currRowMap.get(aChar) == board.getDimension()){
            return true;
        }

        return false;       // No winner yet
    }

    @Override
    public void handleUndo(Board board, Move move) {
        int row = move.getCell().getRow();
        Character symbol = move.getCell().getPlayer().getSymbol().getaChar();

        Map<Character,Integer> currRowMap = rowMap.get(row);

        if(currRowMap != null && currRowMap.containsKey(symbol)){
            int currentCount = currRowMap.get(symbol);
            if(currentCount == 1){
                currRowMap.remove(symbol);
                if(currRowMap.isEmpty()) {
                    rowMap.remove(row);
                }
            }
            else{
                currRowMap.put(symbol, currentCount - 1);
            }
        }
    }
}
