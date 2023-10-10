package block3;

public class Example {
    private int data = 0;

    synchronized void fixData(int data) {
        this.data = data;
    }

    synchronized int returnData() {
        return data;
    }
}
