package block2.practicas;

public class ThreadCreator {

    public static void main(String[] args) {
        //exercice1();
        exercice2();
    }

    public static void exercice1(){
        SubThread t1 = new SubThread(0);
        SubThread t2 = new SubThread(1);

        t1.start();
        t2.start();
    }

    public static void exercice2(){
        Thread t1 = new Thread(new SubRunnable(0));
        Thread t2 = new Thread(new SubRunnable(1));

        t1.start();
        t2.start();
    }

}

class SubThread extends Thread {
    int id;

    public SubThread(int id){
        this.id=id;
    }

    @Override
    public void run(){
        int messagesNum = 1000;

        for (int i = 0; i< messagesNum; i++){
            System.out.println(id);
        }
    }
}

class SubRunnable implements Runnable {
    int id;

    public SubRunnable(int id){
        this.id=id;
    }

    @Override
    public void run(){
        int messagesNum = 1000;

        for (int i = 0; i< messagesNum; i++){
            System.out.println(id);
        }
    }
}