package block4;

import java.util.concurrent.atomic.AtomicInteger;
public class Accumulator {
    public static void main(String[] args) {
        double[] vector = new double[10000];
        for (int i = 0; i < vector.length; i++) {
            vector[i] = 10;
        }

        Acumule a = new Acumule();
        MyHebra h = new MyHebra(1, 4, 1000000000);

        long t1 = System.nanoTime();
        h.start();

        try {
            h.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        long t2 = System.nanoTime();
        double tt = (t2 - t1) / 1.0e9;

        System.out.println(a.dameResultado());

        System.out.println("Tiempo secuencial (seg.):\t\t\t" + tt);
    }
}

class MiHebra extends Thread {
    int miId, numHebras;
    double vector[];
    Acumula a;

    public MiHebra(int miId, int numHebras, double vector[], Acumula a) {
        this.miId = miId;
        this.numHebras = numHebras;
        this.vector = vector;
        this.a = a;
    }

    public void run() {
        for (int i = miId; i < vector.length; i += numHebras) {
            a.acumulaValor(vector[i]);
        }
    }
}

class Acumula {
    private AtomicInteger suma = new AtomicInteger(0);

    void acumulaValor(double valor) {
        suma.getAndAdd((int) valor);
    }

    double dameResultado() {
        return suma.get();
    }
}

