package exp.ints;

import java.awt.Dimension;


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
public class MyIntTests {

  public static void main(String[] args) {

    final Dimension[] dimValues = {new Dimension(220, 235),
                                   new Dimension(220, 347),
                                   new Dimension(220, 150)};

    final int iMainJSplitPaneHeight = 658;

    if (dimValues != null) {
      int iSavedJSplitPaneHeightTotal = 0;
      for (int i = 0; i < dimValues.length; i++) {
        iSavedJSplitPaneHeightTotal += dimValues[i].height;
      }

      final double[] iHeightPercent = new double[dimValues.length];
      for (int i = 0; i < dimValues.length; i++) {
        iHeightPercent[i] = (double)dimValues[i].height / iSavedJSplitPaneHeightTotal;
      }

      for (int i = 0; i < dimValues.length; i++) {
        System.out.println("-----------------------------------------------");
        System.out.println("before dimValues[i].height=" + dimValues[i].height);
        dimValues[i].height =
          new Double( iHeightPercent[i] * iMainJSplitPaneHeight).intValue();
        System.out.println("after dimValues[i].height=" + dimValues[i].height);
      }
    }
  }
}






