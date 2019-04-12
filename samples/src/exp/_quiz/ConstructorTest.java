
package exp._quiz;

public class ConstructorTest {
    int y = 3;
    public ConstructorTest(int i) {
        y += i;
    }
    public ConstructorTest(int i, int i2) {
        y += (i + i2);
        System.out.print(y);
    }
    public int method(int i) {
        y += i;
        return  y;
    }
    public static void main(String[] args) {
        new ConstructorTest(new ConstructorTest(5).method(2), 4);
    }
}