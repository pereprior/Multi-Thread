package block3;

public class CalculoPrimos {
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

        numHebras = 4;

        implementacionSecuencial(vectorNumeros);

        implementacionCiclica(vectorNumeros, numHebras);

        implementacionBloques(vectorNumeros, numHebras);

    }

    static void implementacionSecuencial(long[] vectorNumeros) {
        long t1;
        long t2;
        double tt;

        System.out.println();
        System.out.println("Implementación secuencial.");

        t1 = System.nanoTime();

        int countPrimos = 0;
        for (long numero : vectorNumeros) {
            if (CalculoPrimos.esPrimo(numero)) {
                countPrimos++;
            }
        }

        t2 = System.nanoTime();
        tt = (t2 - t1) / 1.0e9;

        System.out.println("Tiempo secuencial (seg.):\t\t\t" + tt);
        System.out.println("Número de primos encontrados: " + countPrimos);
    }


    static void implementacionCiclica(long[] vectorNumeros, int numHebras) {
        long t1;
        long t2;
        double tt;

        System.out.println("");
        System.out.println("Implementación cíclica.");

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
        tt = (t2 - t1) / 1.0e9;

        System.out.println("Tiempo cíclico (seg.):\t\t\t" + tt);
    }

    static void implementacionBloques(long[] vectorNumeros, int numHebras) {

        long t1;
        long t2;
        double tt;

        System.out.println("");
        System.out.println("Implementación por bloques.");

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
        tt = (t2 - t1) / 1.0e9;

        System.out.println("Tiempo Bloques (seg.):\t\t\t" + tt);
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
    long[] vectorNumeros;
    int numHebras;
    int idHebra;

    public MiHebraCiclica(int idHebra, int numHebras, long[] vectorNumeros) {
        this.vectorNumeros = vectorNumeros;
        this.numHebras = numHebras;
        this.idHebra = idHebra;
    }

    @Override
    public void run() {
        int countPrimos = 0;

        for (int i = idHebra; i < vectorNumeros.length; i += numHebras) {
            if (CalculoPrimos.esPrimo(vectorNumeros[i])) {
                countPrimos++;
            }
        }

        System.out.println("Hebra " + idHebra + ": " + countPrimos + " primos.");
    }
}

class MiHebraBloques extends Thread {
    long[] vectorNumeros;
    int numHebras;
    int idHebra;

    public MiHebraBloques(int idHebra, int numHebras, long[] vectorNumeros) {
        this.vectorNumeros = vectorNumeros;
        this.numHebras = numHebras;
        this.idHebra = idHebra;
    }

    @Override
    public void run() {
        int countPrimos = 0;
        int tamanyo = (vectorNumeros.length+numHebras-1)/numHebras;
        int inicio = idHebra*tamanyo;
        int fin = Math.min(vectorNumeros.length,(idHebra+1)*tamanyo);

        for (int i = inicio; i< fin; i++) {
            if (CalculoPrimos.esPrimo(vectorNumeros[i])) {
                countPrimos++;
            }
        }

        System.out.println("Hebra " + idHebra + ": " + countPrimos + " primos.");
    }
}