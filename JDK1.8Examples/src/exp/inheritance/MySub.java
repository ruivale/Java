package exp.inheritance;

public class MySub extends MySuper
{
    String str2 = "y";

    void myMethod()
    {
        System.out.print(str2);
    }
    public static void main(String[] args)
    {
        MySub mySub = new MySub();
   }
}