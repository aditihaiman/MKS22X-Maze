public class Driver {
  public static void main(String[] args) {

    try {

      Maze test = new Maze("Maze1.txt");
      System.out.println(test);
      test.solve();
      System.out.println(test);

    }
    catch(Exception e){}


  }
}
