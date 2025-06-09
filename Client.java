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
        System.out.println();

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

//           First show the board
            gameController.printBoard(game);

//            First Make the move
            gameController.makeMove(game);

            gameController.printBoard(game);

            //Now after the first move or after evry move made we ask if we want to UNDO the last move
            //Do you Undo the last move
            System.out.println("Do You Want To UNDO? (y/n)");

            String isUndo = scanner.next();

            if(isUndo.equalsIgnoreCase("y")){
//                Call the undo method from the gameController
                gameController.Undo(game);
                System.out.println("Undo is Successfull!!");
//                gameController.printBoard(game);
            }
        }

        gameController.printBoard(game);

        // If someone wins the game the while loop will end and it will close the game
        if(gameController.checkGameStatus(game).equals(GameStatus.ENDED)){
            System.out.println(gameController.getWinner(game).getName() + " has won the game");
        }else{
            System.out.println("GAME DRAW");
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