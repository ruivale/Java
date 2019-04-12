
package jdk1_7examples.fork_join;

import java.util.concurrent.*;
import java.util.concurrent.RecursiveAction;


/**
 *
 * @author C2334
 */
public class MaxWithFJ extends RecursiveAction {

  private final int threshold;
  private final SelectMaxProblem problem;
  public int result;

  /**
   * 
   * @param problem
   * @param threshold
   */
  public MaxWithFJ(final SelectMaxProblem problem, final int threshold) {
    this.problem = problem;
    this.threshold = threshold;
  }

  /**
   * 
   */
  protected void compute() {
    if (problem.size < threshold) {
      result = problem.solveSequentially();
      
    } else {
      final int midpoint = problem.size / 2;
      final MaxWithFJ left = new MaxWithFJ(problem.subproblem(0, midpoint), threshold);
      final MaxWithFJ right = new MaxWithFJ(problem.subproblem(midpoint + 1, problem.size), threshold);

      ///////////////////////////////////////////
      ///////////////////////////////////////////
      ///////////////////////////////////////////
      ///////////////////////////////////////////
//      coInvoke(left, right);
      ///////////////////////////////////////////
      ///////////////////////////////////////////
      ///////////////////////////////////////////

      result = Math.max(left.result, right.result);
    }
  }

  public static void main(final String[] args) {
    SelectMaxProblem problem = new SelectMaxProblem(new int[]{5,67,345,3,32},0,3);
    int threshold = 5;
    int nThreads = 2;
    MaxWithFJ mfj = new MaxWithFJ(problem, threshold);
    ForkJoinPool fjPool = new ForkJoinPool(nThreads);

    fjPool.invoke(mfj);
    
    int result = mfj.result;
  }
}
