
public class Minesweeper{
    /** 2D array od Cell object - each Cell stores info like whether it's a mine*/
    private Cell[][] grid; 

    /** Number of mines to be placed on the board */ 
    private int numMines;

    /** Number of mines on the board*/
    private int totalMines;

    /**Counter of how many cells open*/
    private int count;

    /**
     * Constructor of a Minesweeper object
     * 
     * Initializes grid and calls method to place mines
     * 
     * @param m number of rows on the board
     * @param n number of columns on the board
     * @param numMines number of mines on the board
     */
    public Minesweeper(int m, int n, int numMines){
        //IMPLEMENT THIS
        //You're on right track if you've gotten rid of any null pointer exceptions
        grid = new Cell[m][n];
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                grid[i][j] = new Cell();
            }
        }
        int count = 0;
        totalMines = numMines;
        this.numMines = numMines;
        placeMines();
        fillInCellValues();
    }

    /**
     * Randomly places the appropriate number of mines
     * 
     * Watch out: Make sure the correct number of mines always gets
     * on the board.  For example, when trying to place 10 mines, it 
     * is easy to accidentally place 9 (or fewer) mines if the same 
     * row/column gets randomly selected more than once. 
     * 
     * Notice: "private" method because called only internally by
     * the constructor.
     */
    private void placeMines(){
        //IMPLEMENT THIS
        while(numMines > 0){
            int row = (int)(Math.random() * grid.length);
            int col = (int)(Math.random() * grid[0].length);
            while(grid[row][col].isAMine()){
                row = (int)(Math.random() * grid.length);
                col = (int)(Math.random() * grid[0].length);
            } 
            grid[row][col].setAsMine();
            numMines--;
        }
    }

    private boolean isValidRowCol(int row, int col){
        if(row < grid.length && row >= 0 && col < grid[0].length && col >= 0){
            return true;
        } else {
            return false;
        }
    }

    private int countNeighboringMines(int row, int col){
        int count = 0;
        for(int i = Math.max(0,(row - 1)); i <= (Math.min((grid.length-1),(row + 1))); i++){
            for(int j = Math.max(0, (col -1)); j <= (Math.min((grid[0].length - 1),(col + 1))); j++){
                if(isValidRowCol(i, j)){
                    if(grid[row][col].isAMine()){
                        count = count;
                    }
                    if(grid[i][j].isAMine()){
                        count++;
                    }
                }
            }
        }
        return count;
    }

    private void fillInCellValues(){
        for(int i = 0; i < grid.length; i++){
            for(int j = 0; j < grid[0].length; j++){
                if(!(grid[i][j].isAMine())){
                    grid[i][j].setVal(countNeighboringMines(i,j));
                }
            }
        }
    }

    public void playMove(int row, int col, String command){
        if(!(grid[row][col].isOpen())){
            if(command.equals("flag")){
                grid[row][col].flag();
            }
            if(command.equals("open")){
                grid[row][col].open();
                count++;
                if(grid[row][col].getValue() == 9){
                    for(int i = 0; i < grid.length; i++){
                        for(int j = 0; j < grid[0].length; j++){
                            if(grid[i][j].isAMine()){
                                grid[i][j].open();
                            }
                        }
                    }
                } 
                if(grid[row][col].getValue() == 0){
                    for(int i = Math.max(0,(row - 1)); i <= (Math.min((grid.length-1),(row + 1))); i++){
                        for(int j = Math.max(0, (col -1)); j <= (Math.min((grid[0].length - 1),(col + 1))); j++){
                            if(grid[row][col].getValue() == 0){
                                playMove(i, j, "open");
                            }
                        }
                    }
                }
            }
        }
    }

    public boolean lose(int row, int col){
        if(grid[row][col].getValue() == 9){
            return true;
        } else {
            return false;
        }
    }

    public boolean win(){
        if(count == ((grid.length * grid[0].length) - totalMines)){
            return true;
        } else {
            return false;
        }
    }

    /**
     * Outputs text representation of the board as seen by the player.  
     * Do not modify!!
     */ 
    public String toString(){
        String s = "   ";
        for(int j = 0; j < grid[0].length; j++){
            if(j < 10)
                s+= " " + j + " ";
            else
                s+= j + " ";
        }
        s+="\n   ";
        for(int j = 0; j < grid[0].length; j++){
            s+="---";
        }
        s+="\n";

        for(int i =0; i < grid.length; i++){
            if(i < 10)
                s+= " " +  i + "|"; 
            else
                s+=  i + "|";  
            for(int j = 0; j < grid[i].length; j++){
                s+= " " + grid[i][j] +" ";
            }
            s+="\n";
        }
        return s; 
    }

    /**
     * Prints the locations of all mines. 
     * Won't be displayed to the player in the final version ofgame, but important 
     * for the programmer to debug and understand what's going on.
     * Do not modify. 
     */ 
    public void printMines(){
        System.out.print("   "); 
        for(int j = 0; j < grid[0].length; j++){
            if(j < 10)
                System.out.print(" " + j + " ");
            else
                System.out.print(j + " ");
        }
        System.out.println(); 
        System.out.print("   "); 
        for(int j = 0; j < grid[0].length; j++){
            System.out.print("---");
        }
        System.out.println(); 

        for(int i =0; i < grid.length; i++){
            if(i < 10)
                System.out.print(" " + i + "|");
            else 
                System.out.print(i + "|");
            for(int j = 0; j < grid[i].length; j++){
                System.out.print(" " + grid[i][j].getValue() +" " );
            }
            System.out.println(); 
        }
    }
}