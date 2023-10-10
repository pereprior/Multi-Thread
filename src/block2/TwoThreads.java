package block2;

public class TwoThreads {

    public static void main(String[] args) {
        AThread f = new AThread("Hola");
        AThread s = new AThread("Adios");

        f.start();
        s.start();
    }

}

class AThread extends Thread {
    String message;

    public AThread(String message){
        this.message=message;
    }

    public void run(){
        int number = 3;

        for (int i = 0; i<number; i++){
            System.out.println(message);
        }
    }
}
