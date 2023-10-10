package block1;

public class ThreadDistribution2 {

    public static void main(String[] args) {
        int n = 17;
        int numHebr = 4;
        int myId = 3;

        int tam = (n+numHebr-1)/numHebr;
        int ini = myId*tam;
        int fin = Math.min(n, (myId+1)*tam);

        System.out.println("Autor: Pere Prior");
        System.out.println("hebra "+myId+":");
        for (int i=ini; i<fin; i++) {
            System.out.println(i);
        }

    }

}
