package block4;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.Arrays;

public class AccumulatorWithReduction {
    public static void main(String[] args) {
        double[] vector = new double[1000000];
        for (int i = 0; i < vector.length; i++) {
            vector[i] = 10;
        }

        Acumula3 a = new Acumula3();

        long t1 = System.nanoTime();

        // Utilizando Streams y reducción para la acumulación en paralelo
        double resultadoParalelo = Arrays.stream(vector)
                .parallel()
                .reduce(0.0, (subtotal, elemento) -> subtotal + elemento);

        // Almacenar el resultado paralelo en el acumulador
        a.acumulaValor(resultadoParalelo);

        long t2 = System.nanoTime();
        double tt = (t2 - t1) / 1.0e9;

        System.out.println(a.dameResultado());
        System.out.println("Tiempo con reducciones (seg.): " + tt);
    }
}

class Acumula3 {
    private AtomicInteger suma = new AtomicInteger(0);

    void acumulaValor(double valor) {
        suma.getAndAdd((int) valor);
    }

    double dameResultado() {
        return suma.get();
    }
}
