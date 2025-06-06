package TicTacToeNew.controllers;

import TicTacToeNew.exception.InvalidMoveException;
import TicTacToeNew.models.Game;
import TicTacToeNew.models.GameStatus;
import TicTacToeNew.models.Player;
import TicTacToeNew.strategies.winningstrategy.WinningStrategy;

import java.util.List;

public class GameController {
//    We will write here all the functionalities/method that a client actually need while playing the game

//    1. MakeMove Function
//    2. Undo Function
//    3. CheckWinner
//    4. CheckGameState Function

//    In the client class we are exposing a lot of things of the Game class to the client like Builder patterns and WinningStrategies etc..
//    Client should only start the game
//    Initiate Game() -> Only this method will be called by the client to start a game
    public Game startGame(int dimension, List<Player> players, List<WinningStrategy> winningStrategies) {
        Game game = Game.getBuilder().setDimensions(dimension)
                .setPlayers(players)
                .setWinningStrategies(winningStrategies)
                .build();

        return game;
    }

//    If client want to print the current state of the Board
    public void printBoard(Game game){
        game.printBoard();
    }


//    1. MakeMove Method
    public void makeMove(Game game) throws InvalidMoveException {
        // The GameController should call the makeMove() of the Game clas since GameController is only the frontEnd and Game class is the one which is controlling the Game
        game.makeMove();
    }


//    2. Undo Method
    public void Undo(Game game){

    }

//    4.Check Game Status Method
    public GameStatus checkGameStatus(Game game){
        return game.getGameStatus();
    }

}
