package block4;

import java.util.concurrent.atomic.AtomicInteger;

public class AccumulatorTwoSteps {
    public static void main(String[] args) {
        int numHebras = 4;
        double[] vector = new double[1000000];
        for (int i = 0; i < vector.length; i++) {
            vector[i] = 10;
        }

        // Paso 1: Cada hebra acumula valores en paralelo
        Acumula2 a = new Acumula2();
        MyHebra2 h1 = new MyHebra2(1, numHebras, vector, a);
        MyHebra2 h2 = new MyHebra2(2, numHebras, vector, a);
        MyHebra2 h3 = new MyHebra2(3, numHebras, vector, a);
        MyHebra2 h4 = new MyHebra2(3, numHebras, vector, a);

        long t1 = System.nanoTime();
        h1.start();
        h2.start();
        h3.start();
        h4.start();

        try {
            h1.join();
            h2.join();
            h3.join();
            h4.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Paso 2: Obtener el resultado final
        long t2 = System.nanoTime();
        double tt = (t2 - t1) / 1.0e9;

        System.out.println(a.dameResultado());
        System.out.println("Tiempo total (seg.): " + tt);
    }
}

class MyHebra2 extends Thread {
    int miId, numHebras;
    double vector[];
    Acumula2 a;

    public MyHebra2(int miId, int numHebras, double vector[], Acumula2 a) {
        this.miId = miId;
        this.numHebras = numHebras;
        this.vector = vector;
        this.a = a;
    }

    public void run() {
        // Paso 1: Cada hebra acumula valores en paralelo
        for (int i = miId - 1; i < vector.length; i += numHebras) {
            a.acumulaValor(vector[i]);
        }
    }
}

class Acumula2 {
    private AtomicInteger suma = new AtomicInteger(0);

    void acumulaValor(double valor) {
        suma.getAndAdd((int) valor);
    }

    double dameResultado() {
        return suma.get();
    }
}
