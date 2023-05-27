import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Random;

public class Fox extends Organism{

    ArrayList children;
    int daysDying = 0;
    Fox(int x, int y){
        super(x,y);
        children = new ArrayList<>();
        daysAlive = 0;
        breedCountdown = 3;
        int daysDying = 0;
    }
    @Override
    public void move() throws NoSuchAlgorithmException {
        Organism nearbyHare = findNearbyHare();

        if (nearbyHare==null){
            // Random movement if no nearby hare
            Random rando = SecureRandom.getInstance("SHA1PRNG");
            int randomInt = rando.nextInt(4);
            World.setAt(x, y, null);

            if (randomInt == 0 && y - 1 >= 0 && isFreeSpace(x, y - 1)) {
                y = y - 1;  // Move up
            } else if (randomInt == 1 && x + 1 <= 3 && isFreeSpace(x + 1, y)) {
                x = x + 1;  // Move right
            } else if (randomInt == 2 && y + 1 <= 3 && isFreeSpace(x, y + 1)) {
                y = y + 1;  // Move down
            } else if (randomInt == 3 && x - 1 >= 0 && isFreeSpace(x - 1, y)) {
                x = x - 1;  // Move left
            }
            daysDying++;
              // Add the organism back
        }else if (nearbyHare instanceof Hare){
            World.setAt(x,y,null);
            int newX = nearbyHare.getX();
            int newY = nearbyHare.getY();
            World.hareChildren.remove(nearbyHare);
            x=newX;
            y=newY;
            daysDying=0;

        }
        World.setAt(x, y, this);

        daysAlive++;;

    }



    @Override
    public void breed() {
        if (daysAlive == 8 ) {
            int newX = x;
            int newY = y;

            // Check above
            if (y - 1 >= 0 && isFreeSpace(x, y - 1)) {
                newY = y - 1;
            }
            // Check below
            else if (y + 1 < World.getHeight() && isFreeSpace(x, y + 1)) {
                newY = y + 1;
            }
            // Check left
            else if (x - 1 >= 0 && isFreeSpace(x - 1, y)) {
                newX = x - 1;
            }
            // Check right
            else if (x + 1 < World.getWidth() && isFreeSpace(x + 1, y)) {
                newX = x + 1;
            }

            // Place a new hare at the available coordinate
            if (newX != x || newY != y) {
                Fox newFox = new Fox(newX, newY);
                children.add(newFox);
                World.setAt(newX, newY, newFox);
                World.foxChildren.add(newFox); // Add new hare child to the hare children list
                daysAlive=0;
            }

        }
    }

    @Override
    public void starve() {
        if(daysDying==4){

            World.setAt(x, y, null);
            World.foxChildren.remove(this);
        }

    }

    @Override
    public char getPrintableChar() {
        return 'F';
    }
    public boolean isFreeSpace(int x, int y) {
        // Check oben
        if (y  >= 0 && World.getAt(x, y ) == null) {
            return true;
        }

        // Check unten
        if (y < World.getHeight() && World.getAt(x, y) == null) {
            return true;
        }

        // Check links
        if (x >= 0 && World.getAt(x , y) == null) {
            return true;
        }

        // Check rechts
        if (x < World.getWidth() && World.getAt(x , y) == null) {
            return true;
        }

        return false; // Kein freies Feld in den vier Richtungen gefunden
    }
    public Organism findNearbyHare() {
        // Check oben
        if (y - 1 >= 0 && World.getAt(x, y - 1) instanceof Hare) {
            return  World.getAt(x,y-1);
        }

        // Check unten
        if (y + 1 < World.getHeight() && World.getAt(x, y + 1) instanceof Hare) {
            return World.getAt(x,y+1);
        }

        // Check links
        if (x - 1 >= 0 && World.getAt(x - 1, y) instanceof Hare) {
            return World.getAt(x-1,y);
        }

        // Check rechts
        if (x + 1 < World.getWidth() && World.getAt(x + 1, y) instanceof Hare) {
            return World.getAt(x+1,y);
        }

        return null;
    }

    public void setX(int x) {
        this.x = x;
    }
    public void setY(int y) {
        this.y = y;
    }



}
