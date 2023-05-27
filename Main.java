import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws NoSuchAlgorithmException {
        World world1 = new World();
        Hare hare1 = new Hare(1,2);
        Hare hare2 = new Hare(1,1);
        Fox fox1 = new Fox(2,5);
        Fox fox2 = new Fox(5,2);
        Fox fox3 = new Fox(3,2);
        Fox fox4 = new Fox(2,2);
        int i = 0;
        World.foxChildren.add(fox1);
        World.foxChildren.add(fox2);
        World.hareChildren.add(hare1);
        World.hareChildren.add(hare2);
        World.foxChildren.add(fox3);
        World.foxChildren.add(fox4);
        while(i<50){



            List<Fox> foxChildren = new ArrayList<>(World.foxChildren);
            for (Fox fox : foxChildren) {

                fox.move();
                fox.breed();
                fox.starve();
            }





            List<Hare> hareChildren = new ArrayList<>(World.hareChildren); // Create a copy of hareChildren
            for (Hare hare : hareChildren) {
                hare.move();
                hare.breed();

            }







            i++;
            System.out.println("Runde: "+(i));
            world1.printWorld();


        }


    }
}