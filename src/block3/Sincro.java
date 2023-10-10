package block3;

public class Sincro {
    int numServices = 0;

    synchronized void increment(){
        numServices++;
    }

    synchronized int returnNumServices(){
        return numServices;
    }

}
