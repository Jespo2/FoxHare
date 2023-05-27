import java.security.NoSuchAlgorithmException;


abstract class Organism {
    int x;
    int y;
    int daysAlive;
    int breedCountdown;
    int daysDying;
    public Organism(int x, int y) {
        this.x = x;
        this.y = y;

    }
    public abstract void move () throws NoSuchAlgorithmException;
    public abstract void breed ();
    public abstract void starve();
    public abstract char getPrintableChar();

    public int getX() {
        return this.x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return this.y;
    }

    public void setY(int y) {
        this.y = y;
    }

}


