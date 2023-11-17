package block4;

import java.util.concurrent.atomic.AtomicInteger;

public class AccumulatorWithReductionMultithreading {
    public static void main(String[] args) {
        double[] vector = new double[1000000];
        for (int i = 0; i < vector.length; i++) {
            vector[i] = 10;
        }

        Acumula4 a = new Acumula4();
        int numHebras = 4;

        long t1 = System.nanoTime();

        // Crear y ejecutar hebras para realizar la acumulación en paralelo
        MiHebra4[] hebras = new MiHebra4[numHebras];
        for (int i = 0; i < numHebras; i++) {
            hebras[i] = new MiHebra4(i, numHebras, vector, a);
            hebras[i].start();
        }

        // Esperar a que todas las hebras terminen
        for (MiHebra4 hebra : hebras) {
            try {
                hebra.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        long t2 = System.nanoTime();
        double tt = (t2 - t1) / 1.0e9;

        System.out.println(a.dameResultado());
        System.out.println("Tiempo con reducciones multithreading (seg.): " + tt);
    }
}

class MiHebra4 extends Thread {
    int miId, numHebras;
    double vector[];
    Acumula4 a;

    public MiHebra4(int miId, int numHebras, double vector[], Acumula4 a) {
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

class Acumula4 {
    private AtomicInteger suma = new AtomicInteger(0);

    void acumulaValor(double valor) {
        suma.getAndAdd((int) valor);
    }

    double dameResultado() {
        return suma.get();
    }
}