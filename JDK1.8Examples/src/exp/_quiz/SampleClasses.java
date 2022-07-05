
package exp._quiz;



public class SampleClasses {
  
    static class Char { // line 1
        char c;
        public Char(char c) {
            this.c = c;
        }
        public void print() {
            System.out.print(c);
        }
    }

    public static void main(String[] args) {
        typeText(new Char('A'));
        typeText(new Char('B') { // line 2
            public void print() {
                System.out.print("[");
                super.print();  // line 3
                System.out.print("]");
            }
        });
    }
    private static void typeText(Char c) {
        c.print();
    }  
 
  //
  //
  // Which snippets successfully compile?
  //
//  class C1 {
//      void foo(int a) { 
//         for (int a = 0; a < 5; a++) { }
//      }
//  }
//  class C2 {
//      int a = 0;
//      { int a = 1; }
//  }
//  class C3 {
//      { int a = 0; }
//      { int a = 1; }
//  }
//
//  class C4 {
//      {
//          int a = 0; 
//          for (int a = 0; a < 5; a++) { }
//      }
//  }
//  class C5 {
//      { 
//          for (int a = 0; a < 5; a++) { }
//          int a = 0; 
//      }
//  }
}
