package block1;

/**
 * Reparto de hilos de manera ciclica y en bloque
 *
 * @author Pere Prior
 * @since 15/09/23
 */
public class ThreadDistribution {

    public static void main(String[] args) {
        cyclic();
        blocks();
    }

    public static void cyclic(){
        int n = 24;
        int maxThreadNumber = 4; // 0-1-2-3
        int thread = 1;

        System.out.println("Cyclic thread distribution\n" +
                           "---------------------------\n" +
                           "h"+thread);

        for(int i = thread; i < n; i += maxThreadNumber) {

            System.out.println(i);

        }
    }

    public static void blocks(){
        int n = 24;
        int maxThreadNumber = 4; // 0-1-2-3
        int thread = 1;

        int tamanyo = (n+maxThreadNumber-1)/maxThreadNumber;
        int inicio = thread*tamanyo;
        int fin = Math.min(n,(thread+1)*tamanyo);

        System.out.println("Block thread distribution\n" +
                           "---------------------------\n" +
                           "h"+thread);

        for (int i = inicio; i< fin; i++) {
            System.out.println(i);
        }
    }

}