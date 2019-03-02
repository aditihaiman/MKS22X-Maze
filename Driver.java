public class Driver {
  public static void main(String[] args) {

    String filename = "data3.dat";
      try{
        Maze f;
        f = new Maze(filename);//true animates the maze.
        //f.setAnimate(true);
        System.out.println(f.solve());
        System.out.println(f);
      }catch(Exception e){
        System.out.println("Invalid filename: "+filename);
      }


  }
}
