package block2.practicas;

public class IncreaseValues {

    public static void main(String[] args) {
        int numThreads = 4;
        Increase counter = new Increase();
        SubThread2[] threads = new SubThread2[numThreads];

        for (int i = 0; i < numThreads; i++) {
            threads[i] = new SubThread2(i,counter);
            threads[i].start();
        }

        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("Counter final value: " + counter.getCounter());
    }

}

class Increase {
    long counter = 0;

    void increaseCounter(){
        counter++;
    }

    long getCounter(){
        return counter;
    }
}

class SubThread2 extends Thread {
    int id;
    Increase counter;

    public SubThread2(int id, Increase counter){
        this.id=id;
        this.counter=counter;
    }

    @Override
    public void run(){
        System.out.println("Thread " + id + " starts.");
        for (int j = 0; j < 1000000; j++) {
            counter.increaseCounter();
        }
        System.out.println("Thread " + id + " finishes.");
    }
}