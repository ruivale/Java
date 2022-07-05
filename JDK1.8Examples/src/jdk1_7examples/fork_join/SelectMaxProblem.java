package jdk1_7examples.fork_join;


/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

public class SelectMaxProblem {
    private final int[] numbers;
    private final int start;
    private final int end;
    public final int size;

    protected SelectMaxProblem(int[] numbers) {
        this.numbers = numbers;
        this.size = this.numbers != null? this.numbers.length: 0;
        this.start = 0;
        this.end = this.size-1;
    }
    
    protected SelectMaxProblem(int[] numbers, int start, int end) {
        this.numbers = numbers;
        this.size = this.numbers != null? this.numbers.length: 0;
        this.start = start;
        this.end = end;
    }

    // constructors elided

    public int solveSequentially() {
        int max = Integer.MIN_VALUE;
        for (int i=start; i<end; i++) {
            int n = numbers[i];
            if (n > max)
                max = n;
        }
        return max;
    }

    public SelectMaxProblem subproblem(int subStart, int subEnd) {
        return new SelectMaxProblem(numbers, start + subStart,
                                    start + subEnd);
    }
}
