/**
 * <p>
 * Classname: jdk23examples.thread.virtual.ThreadInspector
 * </p>
 *
 * Virtual Thread States
 *    I have never seen a new Java feature get adopted as quickly as virtual threads. Major milestones
 * were generics, streams, but even these took years before programmers used them with enthusiasm. 
 * However, even before virtual threads were fully baked, I was receiving requests for consulting 
 * help to iron out some virtual thread issues in production. 
 *    Over the years, they have improved substantially, and the way that I like to show students of 
 * my Mastering Virtual Threads in Java Course what is going on, is to explore the state machine. 
 * It is well documented in the VirtualThread class, and you don't need to visit my course to 
 * understand it - just read the comments in the class. The platform thread states are still the old 
 * NEW, RUNNABLE, etc. that we've always had, but the virtual thread states also have states such as 
 * RUNNING, YIELDING, PARKING, BLOCKING, BLOCKED, etc. Java does not expose the virtual thread states 
 * though, so if we call getState(), we get a matching platform thread state. 
 *    In order to get this detailed information, I wrote a ThreadInspector, which returns a String 
 * containing the virtual thread state, followed by the platform thread state. We use deep reflection 
 * to find all the private static final int fields in the VirtualThread class, plus their values and 
 * field names. We then put them in a Map and use that to return the internal virtual thread state name. 
 * For example, if a virtual thread is busy executing on a carrier thread, it's compound state would 
 * be RUNNING/RUNNABLE. If it is busy yielding with Thread.yield(), it might be YIELDING/RUNNABLE or 
 * YIELDED/RUNNABLE. 
 * 
 *
 * <p>
 * Copyright: Dr Heinz M. Kabutz <heinz@javaspecialists.eu>
 * </p>
 */
package jdk24examples.thread.virtual.inspec;

import java.lang.invoke.*;
import java.lang.reflect.*;
import java.util.*;
import java.util.function.*;
import java.util.stream.*;
// --add-opens java.base/java.lang=ALL-UNNAMED

public class ThreadInspector {

  private static final Map<Integer, String> virtualThreadStates;
  private static final VarHandle STATE;
  private static final Predicate<Field> INT_TYPE
    = field -> field.getType() == int.class;
  private static final Set<AccessFlag> ACCESS_FLAGS
    = Set.of(AccessFlag.PRIVATE, AccessFlag.STATIC,
      AccessFlag.FINAL);
  private static final Predicate<Field> PRIVATE_STATIC_FINAL
    = field -> field.accessFlags().equals(
      ACCESS_FLAGS);
  // To support older versions of Java, such as Java 21 to 23
  private static final Predicate<Field> NOT_TRACE_PINNING_MODE
    = field -> !field.getName().equals("TRACE_PINNING_MODE");

  static {
    var vthreadClass = Thread.ofVirtual()
      .unstarted(() -> {
      })
      .getClass();
    virtualThreadStates = Stream.of(
      vthreadClass.getDeclaredFields())
      .filter(INT_TYPE)
      .filter(PRIVATE_STATIC_FINAL)
      .filter(NOT_TRACE_PINNING_MODE)
      .collect(Collectors.toMap(
        ThreadInspector::getStateValue,
        Field::getName
      ));
    try {
      STATE = MethodHandles.privateLookupIn(vthreadClass,
        MethodHandles.lookup())
        .findVarHandle(vthreadClass,
          "state", int.class);
    } catch (ReflectiveOperationException e) {
      throw new Error(e);
    }
  }

  private static int getStateValue(Field field) {
    try {
      field.setAccessible(true);
      return (int) field.get(null);
    } catch (IllegalAccessException e) {
      throw new Error(e);
    }
  }

  public static String getCompoundThreadStates(Thread thread) {
    return (thread.isVirtual() ? virtualThreadStates.get(
      STATE.get(thread)) + "/"
      : "") + thread.getState();
  }
}
