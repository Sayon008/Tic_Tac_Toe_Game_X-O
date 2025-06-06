package TicTacToeNew.models;

import TicTacToeNew.exception.InvalidMoveException;
import TicTacToeNew.strategies.winningstrategy.WinningStrategy;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Game {
    private Board board;
    private List<Player> players;
    private List<Move> moves;
    private GameStatus gameStatus;
    private Player winner;
    private int nextPlayerIndex;
    private List<WinningStrategy> winningStrategies;

//    In our main class we should have a getBuilder() -> This will return the object of builder
    public static Builder getBuilder(){
        return new Builder();
    }


//    Game Constructor  ---> This constructor will be called in the build method
    private Game(int dimension,List<Player> players, List<WinningStrategy> winningStrategies){
        this.board = new Board(dimension);
        this.players = players;
        this.moves = new ArrayList<>();
        this.gameStatus = GameStatus.IN_PROCESS;
        this.nextPlayerIndex = 0;
        this.winningStrategies = winningStrategies;
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public Player getWinner() {
        return winner;
    }

    public void setWinner(Player winner) {
        this.winner = winner;
    }

    public List<WinningStrategy> getWinningStrategies() {
        return winningStrategies;
    }

    public void setWinningStrategies(List<WinningStrategy> winningStrategies) {
        this.winningStrategies = winningStrategies;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public List<Move> getMoves() {
        return moves;
    }

    public void setMoves(List<Move> moves) {
        this.moves = moves;
    }

    public GameStatus getGameStatus() {
        return gameStatus;
    }

    public void setGameStatus(GameStatus gameStatus) {
        this.gameStatus = gameStatus;
    }

    public int getNextPlayerIndex() {
        return nextPlayerIndex;
    }

    public void setNextPlayerIndex(int nextPlayerIndex) {
        this.nextPlayerIndex = nextPlayerIndex;
    }


//    PrintBoard Method
    public void printBoard(){
//        Printing the Board should be the responsibility of the Board class
//        If we implement the functionality of printingBoard in the game class we would be violating the SRP principle ---> Game class should talk about the game related things
        board.printBoard();
    }


    private boolean validateMove(Move move){
        // How we validate a move
        int row = move.getCell().getRow();
        int col = move.getCell().getCol();

        // 1. If the selected row and col is withing the boundaries of the board
        if(row < 0 || row >= board.getDimension() || col < 0 || col >= board.getDimension()){
            return false;
        }

        //2. Whether the cell in which player is trying to make a move is empty or not?
        if(!board.getBoard().get(row).get(col).getCellState().equals(CellState.EMPTY)){
            return false;
        }

        return true;
    }


//    Method For Make Move
    public Move makeMove() throws InvalidMoveException {
        //For this method we need input from the User/Player(If the Player is HUMAN) -> row and col to put the symbol
        //It is the responsibility of a Player to make a move

        //Before making a move we need to check which Player is having the current turn to make the move --> whose turn to make move now in the board
        Player currentPlayer = players.get(nextPlayerIndex);

        System.out.println("This is " + currentPlayer.getName() + "'s move.");

//        It is the Players responsibility to choose the move
            //1. Check which cells are currently empty so that the currentPlayer can make the move --> And based on the empty cell the player will choose the move

        // The Game class should call the makeMove() from the Player class for the current Player

        //Player will choose the move that they want to make
        Move move = currentPlayer.makeMove(board);          // here the player is only choosing the move he/she want to make

        //And Game will validate if the move that the player has chosen is valid or not

        if(!validateMove(move)){
            //throw some exception to the player  --> that the current position where you are trying to apply the move is not a valid position
            throw new InvalidMoveException("Invalid move, please retry!!");
        }

        //Else the position selected by the player is valid, so apply this move to the board

        // Got the row and col from the move that the player is going to make
        int row = move.getCell().getRow();
        int col = move.getCell().getCol();

        // We got the cell using the row and col
        Cell cell = board.getBoard().get(row).get(col);

        // Updated the cell as Filled using CellState
        cell.setCellState(CellState.FILLED);

//        We set the player that which Player is trying to make the move
        cell.setPlayer(currentPlayer);

        // For Undo operation we have to maintain List of moves that we are storing in this Game class -> List<Move> moves
        // As soon as a move is made we need to store the move in the List of Moves
        //moves.add(move);        // this move object is not the actual move object that where we are making the move, This is the move object which the player has selected for the move is a dummy cell not the actual cell of the board

        Move finalMove = new Move(currentPlayer, cell);  // W eare making a final move object with the current player and the actual cell object where the move is being applied in the board

        moves.add(finalMove);


        // Then we incremented the Player index count in a circular fashion --> If 2 player s are playing the game then Player 1 then Player 2 then again Player 1 and so on..
        nextPlayerIndex = (nextPlayerIndex + 1) % players.size();

        // After every move the game will check for the winner
        // Check the status of the game
        //Iterate over the winningStrategies
        // We can have separate function - checkWinner() to check the winner in the game after the final move

        if(checkWinner(finalMove)){
            winner = currentPlayer;
            gameStatus = GameStatus.ENDED;
        }
        else if(moves.size() == board.getDimension() * board.getDimension()){
            //Check for DRAW state
            gameStatus = GameStatus.DRAW;
        }


        return null;
    }


    private boolean checkWinner(Move move){
        for(WinningStrategy winningStrategy : winningStrategies){
            if(winningStrategy.checkWinner(board,move)){
                return true;
            }
        }
        return false;
    }




//    Builder Pattern ->
    public static class Builder{
//        We will be having the parameters same as the Game class parameters

//    This parameter inputs will be coming from the user and based on that the game will be created.
//    So Do we really need this many parameters from the user??
//    A lot of the parameters we cvan hard code

//    We dont need board from the user instead we need only the dimensions
//        private Board board;
        private int dimension;

//        Player will be needed -> player name, player symbol etc..
        private List<Player> players;

//        List<Moves> moves will be empty initially, so not needed
//        private List<Move> moves;

//    GameState will be in progress, so not needed as an input
//        private GameStatus gameStatus;

//        Winner is also not needed as an input, Winner will be null
//        private Player winner;

//    NextPlayerIndex is also not needed as a user input
//        private int nextPlayerIndex;

//    We need the winning strategies as we will ask the player how they would like the winning rules for the game
        private List<WinningStrategy> winningStrategies;


//        Now we will add only the setters in the Builder class
//    For the Setter methods the return type will be Builder
//    This help us for method chaining
//    .setDimension().setPlayers().setWinningStrategies() --> Like this


//    Private constructor of Builder
        private Builder() {
            this.dimension = 0;
            this.players = new ArrayList<>();
            this.winningStrategies = new ArrayList<>();
        }

//        Bot Count Validation
        private void validateBotCount(){
            int count = 0;

            for(Player player: players){
                if(player.getPlayerType().equals(PlayerType.BOT)){
                    count++;

                    if(count > 1){
                        throw new RuntimeException("Only One BOT is allowed per game!!!");
                    }
                }
            }
        }

//        Validation Method to check if two players don't have same symbol
        private void validateUniqueSymbol(){
            Set<Character> symbolSet = new HashSet<>();
            for(Player player:players){
                symbolSet.add(player.getSymbol().getaChar());
            }

            if(symbolSet.size() != dimension - 1){
                throw new RuntimeException("Every Player should have a unique symbol!!!");
            }
        }

//        Validation Method for giving dimension input as negative
        private void validateDimensionNegative(){
            if(dimension < 0){
                throw new RuntimeException("Dimension can't be negative!!!");
            }
        }


        private void validations(){
            validateBotCount();
            validateDimensionNegative();
            validateUniqueSymbol();
        }

//        We will have a build method and the build method will return us a object of a Game class
        public Game build(){
//            Before building the game we should validate -> do our validations part
            validations();

//            We will only be able to create the game object only when all the validations are passed
//            If one of the validation getting failed ---> game object will not be created
            return new Game(dimension, players, winningStrategies);
        }


        public int getDimensions() {
            return dimension;
        }

    //    For the Setter methods the return type will be Builder
        public Builder setDimensions(int dimensions) {
            this.dimension = dimensions;
            return this;
        }

        public List<Player> getPlayers() {
            return players;
        }

    //    For the Setter methods the return type will be Builder
        public Builder setPlayers(List<Player> players) {
            this.players = players;
            return this;
        }

        public List<WinningStrategy> getWinningStrategies() {
            return winningStrategies;
        }

    //    For the Setter methods the return type will be Builder
        public Builder setWinningStrategies(List<WinningStrategy> winningStrategies) {
            this.winningStrategies = winningStrategies;
            return this;
        }
    }
}

//For Game building we will be using the Builder Pattern in the game class
//Why we will be using builder pattern?
// We will be using the builder pattern for building the game and Builder Pattern is used when we have to handle a lot of parameters and validations
//Validation -> No. of players will be dimension - 1 or (n-1), No two players have a same symbol
