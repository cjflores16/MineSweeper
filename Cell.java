/**
 * READ THROUGH CLASS, BUT DO NOT MODIFY
 */ 
public class Cell{
   
    /** 
     * value attribute encodes whether the cell is a mine and, if not, how many mines the cell is touching
     * 9 means cell is a mine 
     * 0 through 8 means cell is NOT a mine but cell is neighboring that specific number of mines
     */
    private int value;  
    
    
    /**
     * display attribute encodes what the player sees in the cell as they are playing
     * 'X' means closed tile (tile hasn't been "clicked on")
     * 'F' is for flagged tile (tile has been "clicked on" as suspected mine)
     * ' ' or '0' or '1' ... are for an open tile (tile has been clicked on) displaying how many mines cell is touching
     */ 
    private char display; 
    
    
    /**
     * Constructor that initializes cell as "closed" (not been clicked on) with no neighboring mines
     */
    public Cell(){
        value = 0;    //no mines placed initially
        display = 'X'; 
    }
    
    
    /**
     *  Tags the cell as a mine
     */
    public void setAsMine(){
        value = 9; 
    }
    
    /**
     *  Returns true if the cell is a mine false otherwise.
     */ 
    public boolean isAMine(){
        return value == 9; 
    }
    
    
    /**
     *  Getter method for the value attribute. 
     * 9 means cell is a mine 
     * 0 through 8 means cell is NOT a mine but cell is neighboring that specific number of mines
     */ 
    public int getValue(){
        return value; 
    }
    
    /**
     * Text representation of what the player sees in the cell as they are playing
     */ 
    public String toString(){
        return "" + display;
    }
    
    public void setVal(int val){
        value = val;
    }
    
    public void flag(){
        display = 'F';
    }
    
    public void open(){
        if(value == 9){
            display = '*';
        }
        if(value >= 0 && value < 9){
            display = (char)(value + 48);
        } 
    }
    
    public boolean isOpen(){
        if(display == 'X'){
            return false;
        } else {
            return true;
        }
    }
}
