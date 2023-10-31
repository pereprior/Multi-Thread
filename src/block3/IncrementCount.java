package block3;

import java.util.concurrent.atomic.AtomicLong;

public class IncrementCount {

    public static void main(String[] args) {
        long t1, t2;
        double tt;
        long tope;
        int numHebras;

        numHebras = 4;
        tope = 1000000;

        System.out.println("Código modificado ....");
        System.out.println("numHebras: " + numHebras);
        System.out.println("tope:      " + tope);

        MiHebra v[] = new MiHebra[numHebras];
        CuentaIncrementos c = new CuentaIncrementos();
        t1 = System.nanoTime();

        System.out.println("Creando y arrancando " + numHebras + " hebras.");

        for (int i = 0; i < numHebras; i++) {
            v[i] = new MiHebra(tope, c);
            v[i].start();
        }

        for (int i = 0; i < numHebras; i++) {
            try {
                v[i].join();
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }

        t2 = System.nanoTime();
        tt = ((double) (t2 - t1)) / 1.0e9;
        System.out.println("Total de incrementos: " + c.dameNumIncrementos());
        System.out.println("Tiempo Transcurrido en segs.:" + tt);
        System.out.println();
    }

}



class CuentaIncrementos {
    private AtomicLong numIncrementos = new AtomicLong(0);

    void incrementaNumIncrementos() {
        numIncrementos.incrementAndGet();
    }

    long dameNumIncrementos() {
        return numIncrementos.get();
    }
}

class MiHebra extends Thread {
    long tope;
    CuentaIncrementos c;

    public MiHebra(long tope, CuentaIncrementos c) {
        this.tope = tope;
        this.c = c;
    }

    public void run() {
        for (long i = 0; i < tope; i++) {
            c.incrementaNumIncrementos();
        }
    }
}