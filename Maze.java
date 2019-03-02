import java.util.*;
import java.io.*;
public class Maze{

    private char[][]maze;
    private boolean animate;//false by default

    /*Constructor loads a maze text file, and sets animate to false by default.

      1. The file contains a rectangular ascii maze, made with the following 4 characters:
      '#' - Walls - locations that cannot be moved onto
      ' ' - Empty Space - locations that can be moved onto
      'E' - the location of the goal (exactly 1 per file)
      'S' - the location of the start(exactly 1 per file)

      2. The maze has a border of '#' around the edges. So you don't have to check for out of bounds!

      3. When the file is not found OR the file is invalid (not exactly 1 E and 1 S) then:
         throw a FileNotFoundException or IllegalStateException
    */
    public Maze(String filename) throws FileNotFoundException {
      maze = ReadFile.readFile(filename);
      animate = false;
        //COMPLETE CONSTRUCTOR
    }

    public String toString(){
      String output = "";
      for(int x = 0; x < this.maze.length; x++) {
        for(int y = 0; y < this.maze[x].length; y++) {
          output+=this.maze[x][y];
        }
        output+="\n";
      }
      return output;
    }

    private void wait(int millis){
      try {
         Thread.sleep(millis);
      }
      catch (InterruptedException e) {
      }
     }

    public void setAnimate(boolean b){
      animate = b;
    }

    public void clearTerminal(){
      //erase terminal, go to top left of screen.
      System.out.println("\033[2J\033[1;1H");
    }


    /*Wrapper Solve Function returns the helper function
      Note the helper function has the same name, but different parameters.
      Since the constructor exits when the file is not found or is missing an E or S, we can assume it exists.
    */
    public int solve(){
      int row=0, col=0;
      for(int x = 0; x < maze.length; x++) { //find the location of the S.
        for(int y = 0; y < maze[x].length; y++) {
          if (maze[x][y] == 'S') {
            row = x;
            col = y;
          }
        }
      }
      maze[row][col] = '@';//erase the S
      return solve(row, col, 1);//start solving at the location of the s.
    }

    /*
      Recursive Solve function:

      A solved maze has a path marked with '@' from S to E.

      Returns the number of @ symbols from S to E when the maze is solved,
      Returns -1 when the maze has no solution.

      Postcondition:
        The S is replaced with '@' but the 'E' is not.
        All visited spots that were not part of the solution are changed to '.'
        All visited spots that are part of the solution are changed to '@'
    */
    private int solve(int row, int col, int steps){ //you can add more parameters since this is private
      //automatic animation! You are welcome.
      int[] movesY = {0,0,1,-1}, movesX = {1, -1, 0, 0};
      if(animate){
        clearTerminal();
        char save = maze[row][col];
        maze[row][col] = '\u2588';
        System.out.println(this);
        maze[row][col] = save;
        wait(50);
      }
      //COMPLETE SOLVE
      if(maze[row][col]=='E') {
        return steps;
      }
      for(int x = 0; x < 4; x++) { //tests if square in front is empty or "E"
        int newX = row + movesX[x], newY = col + movesY[x];
        if (maze[newX][newY]=='E') return solve(newX, newY, steps);
        if(maze[newX][newY]==' ') {
          maze[newX][newY]='@'; // keep track of where program has been
          return solve(newX, newY, steps+1);
        }
      }
      maze[row][col]='.';
      for(int x = 0; x < 4; x++) { //tests if square in front is '@' ; if there are no other moves possible
        int newX = row + movesX[x], newY = col + movesY[x];
        if(maze[newX][newY]=='@') {
          //System.out.println("A");
          return solve(newX, newY, steps-1); //backtracking step
        }
      }

      return -1; //only reaches here if there are no possible solutions
    }



}
