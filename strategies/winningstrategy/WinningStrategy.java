package TicTacToeNew.strategies.winningstrategy;

import TicTacToe.models.Player;
import TicTacToeNew.models.Board;
import TicTacToeNew.models.Cell;
import TicTacToeNew.models.Move;

public interface WinningStrategy {
    boolean checkWinner(Board board, Move move);
}
