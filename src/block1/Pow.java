package block1;

/**
 * Cmomparación del tiempo de ejecucion entre un metodo iterativo y un metodo recursivo
 * Metodo iterativo: mediante bucles
 * Metodo recursivo: llamando a un metodo dentro de ese mismo metodo
 *
 * @author Pere Prior
 * @since 13/09/23
 */
public class Pow {
    public static void main(String[] args) {
        double base = 2;
        int exponent = 10;
        long t1;
        long t2;
        double tt;


        t1 = System.nanoTime();
        double resultadoIterativo = iterativePow(base, exponent);
        t2 = System.nanoTime();
        tt =  (t2 - t1) / 1.0e9;

        System.out.println("Iterative result: " + resultadoIterativo);
        System.out.println("Iterative execution time (sec.):\t\t" + tt);


        t1 = System.nanoTime();
        double resultadoRecursivo = recursivePow(base, exponent);
        t2 = System.nanoTime();
        tt =  (t2 - t1) / 1.0e9;

        System.out.println("Recursive result: " + resultadoRecursivo);
        System.out.println("Recursive execution time (sec.):\t\t" + tt);
    }

    public static double iterativePow(double base, int exponente) {
        double result = 1;

        for (int i = 0; i<exponente; i++){
            result*=base;
        }

        return result;
    }

    public static double recursivePow(double base, int exponente) {
        double result = 1;

        if (exponente != 0){
            result = recursivePow(base,exponente-1)*base;
        }

        return result;
    }
}

