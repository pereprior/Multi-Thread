package block2;

public class NoJoin {
    public static void main(String[] args) {
        Task t1 = new Task(0);
        Task t2 = new Task(1);
        Task t3 = new Task(2);

        t1.start();
        t2.start();
        t3.start();

        /*

        try {
            t1.join();
            t2.join();
            t3.join();
        } catch ( InterruptedException ex){
            ex.printStackTrace();
        }

        */

        System.out.println("Final del programa");
    }
}

class Task extends Thread{
    private int idHebra;
    public Task(int idHebra){
        this.idHebra = idHebra;
    }
    public void run(){
        try{
            int x = (int) (Math.random() * 5000);
            Thread.sleep(x);
            System.out.println("Soy la hebra: "+ idHebra + "( " + x + ")" );
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
    }
}