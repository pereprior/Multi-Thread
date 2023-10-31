package block3;

public class Example {
    volatile private int data = 0;

    void fixData(int data) {
        this.data = data;
    }

    int returnData() {
        return data;
    }
}
