import java.io.*;
import java.util.*;

public class ReadFile {
  public static void main(String args[]) throws FileNotFoundException {
        //instead of a try/catch, you can throw the FileNotFoundException.
        //This is generally bad behavior

        // File text = new File("Maze1.txt");
        // // can be a path like: "/full/path/to/file.txt" or "../data/file.txt"
        //
        // //inf stands for the input file
        // Scanner inf = new Scanner(text);
        //
        // int len1=0,len2 = 0;
        //
        // while(inf.hasNextLine()){
        //     String line = inf.nextLine();
        //     for(int x = 0; x < line.length(); x++) {
        //       len2++;
        //     }
        //     len1++;
        // }
        //
        // inf = new Scanner(text);

        char[][] maze = readFile("Maze1.txt");

        // int idx = 0;
        //
        // while(inf.hasNextLine()){
        //     String line = inf.nextLine();
        //     for(int x = 0; x < line.length(); x++) {
        //       maze[idx][x] = line.charAt(x);
        //     }
        //     idx++;
        // }

        for(int x = 0; x < maze.length; x++) {
          for(int y = 0; y < maze[x].length; y++) {
            System.out.print(maze[x][y]);
          }
          System.out.println();
        }
    }

    public static char[][] readFile(String filename) throws FileNotFoundException {
      File text = new File(filename);
      Scanner inf = new Scanner(text);
      int len1=0,len2 = 0;
      while(inf.hasNextLine()){
          String line = inf.nextLine();
          for(int x = 0; x < line.length(); x++) {
            len2++;
          }
          len1++;
      }
      inf = new Scanner(text);
      char[][] maze = new char[len1][len2];
      int idx = 0;
      while(inf.hasNextLine()){
          String line = inf.nextLine();
          for(int x = 0; x < line.length(); x++) {
            maze[idx][x] = line.charAt(x);
          }
          idx++;
      }
      return maze;
    }
}
