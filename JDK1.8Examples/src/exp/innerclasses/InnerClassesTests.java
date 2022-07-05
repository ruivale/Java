package exp.innerclasses;


/**
 * <p>Title: </p>
 *
 * <p>Description: </p>
 *
 * <p>Copyright: Copyright (c) </p>
 *
 * <p>Company: </p>
 *
 * @author not attributable
 * @version 1.0
 */
public class InnerClassesTests {

  public interface Inner {
    public void notifyEvent();
  }


  private Inner inner;

  public void setInner(Inner inner) {
    this.inner = inner;
  }

  public Inner getInner() {
    return inner;
  }

  public static void main(String[] args) {

    final int testVar = 10;
    InnerClassesTests outer = new InnerClassesTests();
    outer.setInner(
        new Inner() {
      public void notifyEvent() {
        System.out.println(testVar);
      }
    }
    );
    outer.getInner().notifyEvent();
  }
}
