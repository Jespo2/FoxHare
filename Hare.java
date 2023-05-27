import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Random;
public class Hare extends Organism{

    ArrayList children;

    public Hare(int x, int y) {
        super(x, y);
        children = new ArrayList<>();
        daysAlive = 0;
        breedCountdown = 3;

    }

    @Override
    public void move() throws NoSuchAlgorithmException {
        Random rando = SecureRandom.getInstance("SHA1PRNG");
        int randomInt = rando.nextInt(4);
        World.setAt(x, y, null);  // Entfernt den Organismus erst

        if(randomInt==0){
            if(y-1>=0&&isFreeSpace(x,y-1)){
                y=y-1;
            }
        };//Hoch
        if(randomInt==1){
            if(x+1<=24&&isFreeSpace(x+1,y)){
                x=x+1;
            }
        };//Rechts
        if(randomInt==2){
            if(y+1<=24&&isFreeSpace(x,y+1)){
                y=y+1;
            }
        };//Runter
        if(randomInt==3){
            if(x-1>=0&&isFreeSpace(x-1,y)){
                x=x-1;
            }
        };//Links
        setX(x);
        setY(y);

        World.setAt(x, y, this);// Fügt ihn wieder hinzu
        daysAlive++;

    }

    @Override
    public void setX(int x) {
        this.x = x;
    }
    public void setY(int y) {
        this.y = y;
    }

    @Override
    public void breed() {
        if (daysAlive  == 3) {
            int newX = x;
            int newY = y;

            // Check above
            if (y - 1 >= 0 && isFreeSpace(x, y - 1)) {
                newY = y - 1;
            }
            // Check below
             if (y + 1 < World.getHeight() && isFreeSpace(x, y + 1)) {
                newY = y + 1;
            }
            // Check left
             if (x - 1 >= 0 && isFreeSpace(x - 1, y)) {
                newX = x - 1;
            }
            // Check right
             if (x + 1 < World.getWidth() && isFreeSpace(x + 1, y)) {
                newX = x + 1;
            }

            // Place a new hare at the available coordinate
            if (newX != x || newY != y) {
                Hare newHare = new Hare(newX, newY);
                children.add(newHare);
                World.setAt(newX, newY, newHare);
                World.hareChildren.add(newHare); // Add new hare child to the hare children list
            }
            daysAlive=0;
        }
    }

    @Override
    public void starve() {

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
        }else
        return false; // Kein freies Feld in den vier Richtungen gefunden
    }




    public char getPrintableChar(){
        return 'H';
    }
    public int getX() {
        return x;
    }
    public int getY(){return y;}


}
