package exp;

/**
 * Title:
 * Description:
 * Copyright:    Copyright (c)
 * Company:
 * @author
 * @version 1.0
 */


import java.util.*;
import java.lang.*;

public class MyBundle {

    public static void main(String[] args) {

        ResourceBundle rb = ResourceBundle.getBundle("A");

        System.out.println("A var A.a e': "+ rb.getString("A.a") +".");


    }
}