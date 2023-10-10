package block2.controles;

public class Threads {

    public static void main(String[] args) {
        MyThread[] array = new MyThread[101];

        for (int i = 0; i < array.length; i++){
            array[i] = new MyThread(i);
            array[i].start();
        }

        for (MyThread thread : array) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("\nEl programa ha terminado");

    }

}

class MyThread extends Thread{
    int id;

    public MyThread(int id){
        this.id=id;
    }

    @Override
    public void run(){
        if (id % 3 == 0 && id % 5 == 0){
            System.out.println("La hebra "+id+" es divisible per 3 y 5");
        }
    }
}