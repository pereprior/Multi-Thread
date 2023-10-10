package block2;

public class Sequence {

    public static void main(String[] args) {
        /*MyThreadOne taskOne = new MyThreadOne();
        MyThreadTwo taskTwo = new MyThreadTwo();*/
        Thread threadOne = new Thread( new MyThreadThree() );
        Thread threadTwo = new Thread( new MyThreadFour() );

        threadOne.start();
        threadTwo.start();
    }

}

/*class MyThreadOne extends Thread {
    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.println ( "Task One" );
        }
    }
}

class MyThreadTwo extends Thread {
    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.println ( "Task Two" );
        }
    }
}*/

class MyThreadThree implements Runnable {
    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.println ( "Task Three" );
        }
    }
}

class MyThreadFour implements Runnable {
    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.println ( "Task Four" );
        }
    }
}