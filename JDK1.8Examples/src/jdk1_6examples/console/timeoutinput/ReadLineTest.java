package jdk1_6examples.console.timeoutinput;


import java.io.*;


public class ReadLineTest {
  public static void main(String[] args)
      throws IOException {
    BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    in.readLine();
  }
}
