package exp._quiz;

public class Sub extends Base{
  int y = 1;

  static void StaticMethod(){
    int u = new Sub().x;
    u = new Sub().y;
  }
}
