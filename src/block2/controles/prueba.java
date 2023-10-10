package block2.controles;

import java.util.concurrent.atomic.AtomicInteger;

public class prueba {
    public static void main(String args[]) {
        int numHebras;
        long vectorNumeros[] = {
                200000033L, 4L, 4L, 4L, 4L, 4L, 4L, 4L,
                200000039L, 4L, 4L, 4L, 4L, 4L, 4L, 4L,
                200000051L, 4L, 4L, 4L, 4L, 4L, 4L, 4L,
                200000069L, 4L, 4L, 4L, 4L, 4L, 4L, 4L,
                200000081L, 4L, 4L, 4L, 4L, 4L, 4L, 4L,
                200000083L, 4L, 4L, 4L, 4L, 4L, 4L, 4L,
                200000089L, 4L, 4L, 4L, 4L, 4L, 4L, 4L,
                200000093L, 4L, 4L, 4L, 4L, 4L, 4L, 4L
        };

        long    vectorNumeros2[] = {
                200000033L, 200000039L, 200000051L, 200000069L,
                200000081L, 200000083L, 200000089L, 200000093L,
                4L, 4L, 4L, 4L, 4L, 4L, 4L, 4L,
                4L, 4L, 4L, 4L, 4L, 4L, 4L, 4L,
                4L, 4L, 4L, 4L, 4L, 4L, 4L, 4L,
                4L, 4L, 4L, 4L, 4L, 4L, 4L, 4L,
                4L, 4L, 4L, 4L, 4L, 4L, 4L, 4L,
                4L, 4L, 4L, 4L, 4L, 4L, 4L, 4L,
                4L, 4L, 4L, 4L, 4L, 4L, 4L, 4L
        };


        numHebras = 4;

        implementacionSecuencial(vectorNumeros);

        implementacionCiclica(vectorNumeros, numHebras);

        implementacionBloques(vectorNumeros, numHebras);

        implementacionDinamica(vectorNumeros, numHebras);

        implementacionDinamica2(vectorNumeros2, numHebras);


    }

    static void implementacionSecuencial(long[] vectorNumeros) {
        long t1;
        long t2;
        double tt;

        System.out.println("");
        System.out.println("Implementacion secuencial.");

        t1 = System.nanoTime();
        //Escribe aquí la implementación secuencial
        for( int i = 0; i < vectorNumeros.length; i++ ) {
            if( esPrimo( vectorNumeros[ i ] ) ) {
                System.out.println( "\tEncontrado primo: " + vectorNumeros[ i ] );
            }
        }

        //Fin de la implementación secuencial
        t2 = System.nanoTime();
        tt = ((double) (t2 - t1)) / 1.0e9;

        System.out.println("Tiempo secuencial (seg.):\t\t\t" + tt);
    }

    static void implementacionCiclica(long[] vectorNumeros, int numHebras) {
        long t1;
        long t2;
        double tt;

        System.out.println("");
        System.out.println("Implementacion cíclica.");

        MiHebraCiclica v[] = new MiHebraCiclica[numHebras];

        t1 = System.nanoTime();

        for (int i = 0; i < numHebras; i++) {
            v[i] = new MiHebraCiclica(i, numHebras, vectorNumeros);
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

        System.out.println("Tiempo cíclico (seg.):\t\t\t" + tt);
    }


//------------------------------------------------------------------------------------------------------------

    static void implementacionBloques(long[] vectorNumeros, int numHebras) {

        long t1;
        long t2;
        double tt;

        System.out.println("");
        System.out.println("Implementacion por bloques.");

        MiHebraBloques v[] = new MiHebraBloques[numHebras];

        t1 = System.nanoTime();

        for (int i = 0; i < numHebras; i++) {
            v[i] = new MiHebraBloques(i, numHebras, vectorNumeros);
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

        System.out.println("Tiempo Bloques (seg.):\t\t\t" + tt);
    }

    static void implementacionDinamica(long[] vectorNumeros, int numHebras) {
        long t1;
        long t2;
        double tt;

        System.out.println( "" );
        System.out.println( "Implementacion dinámica." );

        MiHebraDinamica v[] = new MiHebraDinamica[numHebras];

        t1 = System.nanoTime();

        AtomicInteger indice = new AtomicInteger(0);

        for(int i = 0; i< numHebras; i++) {
            v[i] = new MiHebraDinamica(indice, vectorNumeros);
            v[i].start();
        }

        for(int i = 0; i < numHebras; i++) {
            try {
                v[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        t2 = System.nanoTime();
        tt = ( ( double ) ( t2 - t1 ) ) / 1.0e9;

        System.out.println( "Tiempo dinamico (seg.):\t\t\t" + tt );
    }

    static void implementacionDinamica2(long[] vectorNumeros, int numHebras) {
        long t1;
        long t2;
        double tt;

        System.out.println( "" );
        System.out.println( "Implementacion dinámica." );

        MiHebraDinamica2 v[] = new MiHebraDinamica2[numHebras];

        t1 = System.nanoTime();

        AtomicInteger indice = new AtomicInteger(0);

        for(int i = 0; i< numHebras; i++) {
            v[i] = new MiHebraDinamica2(indice, vectorNumeros);
            v[i].start();
        }

        for(int i = 0; i < numHebras; i++) {
            try {
                v[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        t2 = System.nanoTime();
        tt = ( ( double ) ( t2 - t1 ) ) / 1.0e9;

        System.out.println( "Tiempo dinamico (seg.):\t\t\t" + tt );
    }

    static boolean esPrimo( long num ) {
        boolean primo;
        if( num < 2 ) {
            primo = false;
        } else {
            primo = true;
            long i = 2;
            while( ( i < num )&&( primo ) ) {
                primo = ( num % i != 0 );
                i++;
            }
        }
        return( primo );
    }
}

class MiHebraCiclica extends Thread {
    private int idHebra;
    private int numHebras;
    private long vector [];


    public MiHebraCiclica(int i, int numHebras, long vector[] ) {

        this.idHebra = i;
        this.numHebras = numHebras;
        this.vector = vector;
    }

    public void run() {

        for (int i = this.idHebra; i < this.vector.length; i += this.numHebras) {
            if (CalculoPrimosVectorB.esPrimo(this.vector[i]))
                System.out.println("\tEncontrado primo: " + this.vector[i]);
        }
    }
}

class MiHebraBloques extends Thread {
    private int idHebra;
    private int numHebras;
    private long vector[];


    public MiHebraBloques(int i, int numHebras, long vector[]) {

        this.idHebra = i;
        this.numHebras = numHebras;
        this.vector = vector;
    }

    public void run() {
        int Tam = (this.vector.length + this.numHebras - 1) / this.numHebras;
        int ini = this.idHebra * Tam;
        int fin = Math.min(this.vector.length, (idHebra + 1) * Tam);

        for (int i = ini; i < fin; i++) {
            if (CalculoPrimosVectorB.esPrimo(this.vector[i]))
                System.out.println("\tEncontrado primo: " + this.vector[i]);
        }
    }

}
class MiHebraDinamica extends Thread {

    private AtomicInteger indice;
    private long vector[];


    public MiHebraDinamica(AtomicInteger indice, long vector[]) {

        this.indice = indice;
        this.vector = vector;
    }


    public void run() {

        final int n = this.vector.length;

        int pos;

        while ((pos = this.indice.getAndIncrement()) < n) {

            if (CalculoPrimosVectorB.esPrimo(this.vector[pos]))
                System.out.println("\tEncontrado primo: " + this.vector[pos]);
        }

    }


}

class MiHebraDinamica2 extends Thread {

    private AtomicInteger indice;
    private long vector[];


    public MiHebraDinamica2(AtomicInteger indice, long vector[]) {

        this.indice = indice;
        this.vector = vector;
    }


    public void run() {

        final int n = this.vector.length;

        int pos;

        while ((pos = this.indice.getAndIncrement()) < n) {

            if (CalculoPrimosVectorB.esPrimo(this.vector[pos]))
                System.out.println("\tEncontrado primo: " + this.vector[pos]);
        }

    }


}