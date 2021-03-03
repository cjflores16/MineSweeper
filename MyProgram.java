import java.util.Scanner; 

public class MyProgram
{
    public static void main(String[] args)
    {
        Minesweeper m = new Minesweeper(8, 8, 10);
        System.out.println("FOR DEBUGGING, here are all mine locations:"); 
        m.printMines(); 
        System.out.println("-----------------------");
        System.out.println("\n\n");
        
        System.out.println("Let's play minesweeper.  Three valid commands: \"open #row #col\" or \"flag #row #col\" or \"quit\"");  
        
        System.out.println(m);
        
        String response = "";
        Scanner s = new Scanner(System.in);
        
        //this is a "forever" loop that plays the game
        while(true){
            System.out.println("Type your command:");
            response = s.nextLine();   //wait for user to type command and store in response variable
            if(response.equals("quit"))
                break;  //when player types quit, end the "forever" loop
            String[] tokens = response.split(" ");   //split up response into its parts
            //Uncomment playMove() call below once you've written the method in class Minesweeper
            m.playMove(Integer.valueOf(tokens[1]), Integer.valueOf(tokens[2]), tokens[0]);
            System.out.println(m);  //print the updated board
            if(m.lose(Integer.valueOf(tokens[1]), Integer.valueOf(tokens[2]))){
                System.out.println("You lose!");
                break;
            } else if(m.win()){
                System.out.println("You win!");
                break;
            }
        }
        System.out.println("good bye");
    }
}

