
package jdk1_6examples.grep;


import java.util.logging.Level;
import java.util.logging.Logger;
import java.lang.reflect.InvocationTargetException;

public interface IProcessor<I,O> {
    O process(I input) throws InterruptedException, InvocationTargetException;
}