package block4;

import java.util.Arrays;

import static block4.NumeroPI.f;

class Acumule {
    double suma;

    public Acumule() {
        this.suma = 0.0;
    }

    synchronized void acumulaValor(double valor) {
        suma+=valor;
    }

    synchronized double dameResultado() {
        return suma;
    }
}

class MyHebra extends Thread {
    int miId;
    int numHebras;
    long numRectangulos;
    Acumule a;

    public MyHebra(int miId, int numHebras, long numRectangulos) {
        this.miId = miId;
        this.numHebras = numHebras;
        this.numRectangulos = numRectangulos;
        this.a = new Acumule();
    }

    @Override
    public void run() {
        double baseRectangulo = 1.0 / numRectangulos;
        double sumaParcial = 0.0;

        for (int i = miId; i < numRectangulos; i += numHebras) {
            double x = baseRectangulo * (i + 0.5);
            sumaParcial += f(x);
        }

        a.acumulaValor(sumaParcial);
    }
}
public class NumeroPI {

    static double f(double x) {
        return 4.0 / (1.0 + x * x);
    }

    public static void main ( String args[] ) {
        long numRectangulos = 1000000000;
        double baseRectangulo;
        double x;
        double suma;
        double pi;
        int numHebras = 4;
        long t1;
        long t2;
        double tSec, tPar;

        System.out.println ();
        System.out.println ( "Calculo del numero PI mediante integracion " );

        //Calculo del número PI de forma secuencial
        System.out.println();
        System.out.println("Comienzo del cálculo secuencial");
        t1 = System.nanoTime();
        baseRectangulo = 1.0 / numRectangulos;
        suma = 0.0;
        for (long i = 0; i < numRectangulos; i++) {
            x = baseRectangulo * (i + 0.5);
            suma += f(x);
        }

        pi = baseRectangulo * suma;
        t2 = System.nanoTime();
        tSec = (t2 - t1) / 1.0e9;
        System.out.println("Versión Secuencial. Número PI:" + pi);
        System.out.println("Tiempo transcurrido (s.):     " + tSec);



        //Calculo del número PI de forma cíclica
        System.out.println();
        System.out.println("Comienzo del cálculo cíclico");
        t1 = System.nanoTime();
        MyHebra[] hebras = new MyHebra[numHebras];

        for (int i = 0; i < numHebras; i++) {
            hebras[i] = new MyHebra(i, numHebras, numRectangulos);
            hebras[i].start();
        }

        for (int i = 0; i < numHebras; i++) {
            try {
                hebras[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        suma = 0.0;
        for (int i = 0; i < numHebras; i++) {
            suma += hebras[i].a.dameResultado();
        }

        pi = baseRectangulo * suma;
        t2 = System.nanoTime();
        tPar = (t2 - t1) / 1.0e9;
        System.out.println("Versión Cíclica. Número PI: " + pi);
        System.out.println("Tiempo transcurrido (s.):     " + tPar);



        // Reducciones
        System.out.println();
        System.out.println("Comienzo del cálculo con Reducciones cíclico");
        t1 = System.nanoTime();
        MyHebra[] hebrasR = new MyHebra[numHebras];

        for (int i = 0; i < numHebras; i++) {
            hebrasR[i] = new MyHebra(i, numHebras, numRectangulos);
            hebrasR[i].start();
        }

        for (int i = 0; i < numHebras; i++) {
            try {
                hebrasR[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // Utilizando una reducción para calcular la suma total
        pi = baseRectangulo * Arrays.stream(hebrasR)
                .mapToDouble(hebra -> hebra.a.dameResultado())
                .reduce(0, (a, b) -> a + b);

        t2 = System.nanoTime();
        tPar = (t2 - t1) / 1.0e9;
        System.out.println("Versión Cíclica con Reducciones. Número PI: " + pi);
        System.out.println("Tiempo transcurrido (s.):     " + tPar);



        // METODO DE LOS DOS PASOS
        System.out.println();
        System.out.println("Comienzo del cálculo con el metodo de los dos pasos:");
        t1 = System.nanoTime();
        MyHebra[] hebras2 = new MyHebra[numHebras];

        for (int i = 0; i < numHebras; i++) {
            hebras2[i] = new MyHebra(i, numHebras, numRectangulos);
            hebras2[i].start();
        }

        for (int i = 0; i < numHebras; i++) {
            try {
                hebras2[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        pi = 0.0;
        for (int i = 0; i < numHebras; i++) {
            pi += baseRectangulo * hebras2[i].a.dameResultado();
        }

        t2 = System.nanoTime();
        tPar = (t2 - t1) / 1.0e9;
        System.out.println("Versión Cíclica con Método de los Pasos. Número PI: " + pi);
        System.out.println("Tiempo transcurrido (s.):     " + tPar);


        System.out.println();
        System.out.println( "Fin de programa " );
    }

}
