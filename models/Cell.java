package TicTacToeNew.models;

public class Cell {
    private int row;
    private int col;
    private CellState cellState;
    private Player player;

    // By default CellState will be empty and Player will be null
//    So this parameters are not needed in the constructor for now - CellState cellState, Player player
    public Cell(int row, int col) {
        this.row = row;
        this.col = col;
//        Initially CellState will be Empty
        this.cellState = CellState.EMPTY;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public CellState getCellState() {
        return cellState;
    }

    public void setCellState(CellState cellState) {
        this.cellState = cellState;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }


//    Display cell method
    public void display() {
//        We first check if the player is present is not
        if(player == null){
//            Display a empty cell without a symbol
            System.out.print("| -- |");
        }else {
//            Player is present and player is having a symbol --> Then print that symbol in that cell
            System.out.print("| " + player.getSymbol().getaChar() + " |");
        }
    }
}
