package block2;

import java.util.ArrayList;

public class Vector {

    public static void main(String[] args) {
        ArrayList<ThreadTest> threads = new ArrayList<>();

        exercice1(threads);
        exercice2(threads);
    }

    private static void exercice1(ArrayList<ThreadTest> threads) {
        int maxThreads = 5;
        for (int i = 0; i < maxThreads; i++) {
            ThreadTest thread = new ThreadTest(i);

            threads.add(thread);
            thread.start();
        }
    }

    private static void exercice2(ArrayList<ThreadTest> threads) {
        for (ThreadTest thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("All the threads finally end");
    }

}

class ThreadTest extends Thread {
    int id;

    public ThreadTest(int id){
        this.id=id;
    }

    @Override
    public void run(){
        int messagesNum = 10;

        for (int i = 0; i< messagesNum; i++){
            System.out.println("Hello, I'm the thread " + id);
        }
    }

}