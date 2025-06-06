package TicTacToeNew.strategies.botplayingstrategy;

import TicTacToeNew.models.Board;
import TicTacToeNew.models.Move;

public interface BotPlayingStrategy {
    Move makeMove(Board board);  // --> returns a move object
}
