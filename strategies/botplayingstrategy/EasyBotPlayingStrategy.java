package TicTacToeNew.strategies.botplayingstrategy;

import TicTacToeNew.models.Board;
import TicTacToeNew.models.Cell;
import TicTacToeNew.models.CellState;
import TicTacToeNew.models.Move;

import java.util.List;

public class EasyBotPlayingStrategy implements BotPlayingStrategy {

    @Override
    public Move makeMove(Board board) {
        //Iterate over the board and make a move on the first empty cell
        for(List<Cell> row : board.getBoard()){
            for(Cell cell : row){
                if(cell.getCellState().equals(CellState.EMPTY)){
                    return new Move(null, cell);
                }
            }
        }
        return null;
    }
}
