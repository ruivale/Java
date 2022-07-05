package exp.performance;

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
public class ForCicles {
  static final int NBR_ITENS = 5000000;
  static final int is[] = new int[NBR_ITENS];
  static final java.util.List l = new java.util.Vector(NBR_ITENS);

  public static long countCicleTime() {
    long before = System.currentTimeMillis();

    for (int i = 0; i < l.size(); i++) {
      l.get(i);
    }

    long interval = System.currentTimeMillis() - before;

    System.out.println("Interval= " + interval + ".");

    return interval;
  }

  public static void main(String[] args) {

    for (int i = 0; i < NBR_ITENS; i++) {
      l.add("" + i);
    }

    long[] _intervals = {
        ForCicles.countCicleTime(),
        ForCicles.countCicleTime(),
        ForCicles.countCicleTime(),
        ForCicles.countCicleTime(),
        ForCicles.countCicleTime(),
        ForCicles.countCicleTime()};

    int intMaxIndex = returnMaxIndex(_intervals);
    int intMinIndex = returnMinIndex(_intervals);

    long longIntervalsSum = 0;
    for (int i = 0; i < _intervals.length; i++) {
      if(i != intMaxIndex && i != intMinIndex){
        longIntervalsSum += _intervals[i];
      }
    }
    long longAverageIntervals = longIntervalsSum/(_intervals.length-2);

    System.out.println("\nThe interval average is "+longAverageIntervals+". ("+
    longIntervalsSum+"/("+(_intervals.length-2)+"))");


  }

  static int returnMaxIndex(long[] l){
    int index = 0;
    long max = 0;

    for (int i = 0; i < l.length; i++) {
      if(l[i] > max){
        max = l[i];
        index = i;
      }
    }
    System.out.println("The max values is "+max+" at the "+index+" index.");
    return index;
  }

  static int returnMinIndex(long[] l){
    int index = 0;
    long min = 999999999;

    for (int i = 0; i < l.length; i++) {
      if(l[i] < min){
        min = l[i];
        index = i;
      }
    }
    System.out.println("The min values is "+min+" at the "+index+" index.");
    return index;
  }

}


