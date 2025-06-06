package TicTacToeNew.models;

import java.util.ArrayList;
import java.util.List;

public class Board {
    private int dimension;
    private List<List<Cell>> board;


//    How do we initialize the board for the first time - How do we do it??
//     --> Initialize it with an Empty Board Object
//    To create the board object we need Dimension/Size of the board in the input
//    We  need to create a board of n x n dimension

    public Board(int dimension) {
        this.dimension = dimension;

//        Board is nothing but List<List<>>
//        Create a board of this dimension
        board = new ArrayList<>();

//        Go to every Index and add a new ArrayList

        for(int i = 0; i < dimension; i++){
            board.add(new ArrayList<>());
            for(int j = 0; j < dimension; j++){
                board.get(i).add(new Cell(i,j));
            }
        }
    }


    //        Print Board
    public void printBoard(){
        for(List<Cell> row : board){
            for(Cell cell : row){
//                    The display should done by the Cell class as we will be diplaying each cell in the board.
//                    So if we add the functionality in the Board class then we would be violating the SRP principle
                cell.display();
            }
//                Move to next row
            System.out.print("\n");
        }
    }

    public int getDimension() {
        return dimension;
    }

    public void setDimension(int dimension) {
        this.dimension = dimension;
    }

    public List<List<Cell>> getBoard() {
        return board;
    }

    public void setBoard(List<List<Cell>> board) {
        this.board = board;
    }
}
