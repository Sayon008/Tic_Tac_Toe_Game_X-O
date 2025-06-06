package TicTacToeNew;

import TicTacToeNew.controllers.GameController;
import TicTacToeNew.exception.InvalidMoveException;
import TicTacToeNew.models.*;
import TicTacToeNew.strategies.winningstrategy.ColumnWinningStrategy;
import TicTacToeNew.strategies.winningstrategy.DiagonalWinningStrategy;
import TicTacToeNew.strategies.winningstrategy.RowWinningStrategy;
import TicTacToeNew.strategies.winningstrategy.WinningStrategy;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) throws InvalidMoveException {
        System.out.println("GAME STARTS");

        Scanner scanner = new Scanner(System.in);

//        Creating an object of the GameController class
        GameController gameController = new GameController();

//        Taking input from users
//        int dimension = scanner.nextInt();

//        Hard Coding values for testing purpose
            int dimension = 3;

//        Take players information in the input
        List<Player> players = new ArrayList<Player>();

        players.add(new Player(new Symbol('X'), "Sayon", PlayerType.HUMAN));

        players.add(new Bot(new Symbol('O'), "Scaler", PlayerType.BOT,BotDifficultyLevel.EASY));

        List<WinningStrategy> winningStrategies = List.of(
                new RowWinningStrategy(),
                new ColumnWinningStrategy(),
                new DiagonalWinningStrategy()
        );


        Game game = gameController.startGame(dimension, players, winningStrategies);

//        gameController.printBoard(game);

//        Let's start playing the game
//        If the game status is IN_PROGRESS -> we keep playing the game
        while(gameController.checkGameStatus(game).equals(GameStatus.IN_PROCESS)){
            //1. Display the Board --> printBoard
            //2. Make the decision where to put the next symbol --> makeMove()
            // if player is Human -> Ask for user input   Or  If the palyer is bot then user input is not needed

            gameController.printBoard(game);

            //Do you Undo the last move
            System.out.println("Do You Want To UNDO? (y/n)");

            String isUndo = scanner.next();

            if(isUndo.equalsIgnoreCase("y")){
//                Call the undo method from the gameController
                gameController.Undo(game);
                continue;
            }

//            Else make a move
            gameController.makeMove(game);
        }
    }
}


//After building the game object, now we need to play the game
//So to play the game we have to implement the winning algo, makeMove algo, print board algo, Undo lago etc..
//
//Tic Tac Toe is a very simple game
//Here we  can implement the makeMove() or printBoard() or Undo() in the game class itself and call this methods like game.undo() or game.makeMove()
//while playing the game

//But we should have a controller class to handle the client.
//Client should not directly communicate with the game class instead the communication should happen via controllers

// Client ---> Controller ---> Game class


//