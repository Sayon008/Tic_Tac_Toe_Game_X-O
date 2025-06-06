package TicTacToeNew.models;

import java.util.Scanner;

public class Player {
    private Symbol symbol;
    private String name;
    private PlayerType playerType;

    private Scanner scanner;

    // OR --> Make the scanner object static

//    private static Scanner scanner = new Scanner(System.in);


    public Player(Symbol symbol, String name, PlayerType playerType) {
        this.symbol = symbol;
        this.name = name;
        this.playerType = playerType;
        this.scanner = new Scanner(System.in);
    }

    public Symbol getSymbol() {
        return symbol;
    }

    public void setSymbol(Symbol symbol) {
        this.symbol = symbol;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public PlayerType getPlayerType() {
        return playerType;
    }

    public void setPlayerType(PlayerType playerType) {
        this.playerType = playerType;
    }


//    Make a Move --> Using this method a Player will choose or can select a move, that it
    public Move makeMove(Board board) {

        //How Player should select a move
        // Take row, col in the input from the Player
        System.out.println("Please enter the row number where you want to make the move");
//        Scanner scanner = new Scanner(System.in);
        int row = scanner.nextInt();

        // Now take input for the column
        System.out.println("Please enter the colum number where you want to make the move");
        int col = scanner.nextInt();

        // Now we want to validate the row,col cell position is empty
        // So it should be the game class responsibility to validate the move

        return new Move(this, new Cell(row, col));

        // This is not a cell object from the board, this is juct a cell object that we are creating to encapsulate to store the row and colum together
    }
}
