package block2;

public class InformationReceipt {

    public static void main(String[] args) {
        Thread e1 = new Thread(new MyThread(0));
        Thread e2 = new Thread(new MyThread(1));

        e1.start();
        e2.start();
    }
}

/*class MyThread extends Thread {
    int threadID;

    public MyThread(int threadID){
        this.threadID=threadID;
    }

    public void run(){
        System.out.println("I'm the thread "+threadID);
    }
}*/

class MyThread implements Runnable {
    int threadID;

    public MyThread(int threadID){
        this.threadID=threadID;
    }

    public void run(){
        System.out.println("I'm the thread "+threadID);
    }
}