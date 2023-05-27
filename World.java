import java.util.ArrayList;
import java.util.List;

public class World {
    public static Organism [][] landscape;
    static int width;
    static int height;
    static List<Hare> hareChildren = new ArrayList<>();
    static List<Fox> foxChildren = new ArrayList<>();
    World (){
        width = 25;
        height = 25;
        landscape = new Organism[height][width];

    }
    static public int getWidth() {
        return width;
    }

    static  public int getHeight() {
        return height;
    }
    static public Organism getAt(int x , int y){
        return landscape[x][y];
    }
    static void setAt(int x , int y , Organism org){
        landscape[x][y] = org;
    }

    static  public void printWorld() {
        for(int i = 0; i < landscape.length; i++){
            for(int j = 0; j < landscape[i].length; j++){
                Organism org = getAt(j,i);
                if (org == null) {
                    System.out.print(" .");
                } else {
                    System.out.print(" " + org.getPrintableChar());
                }
            }
            System.out.println(); //i-th line
        }
        System.out.println("____________");
    }
}
