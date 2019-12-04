package advent.e2019;

import advent.Advent;

import java.io.IOException;

public class Day4 extends Advent {

    private int lower, upper;

    public static void main(String[] args) throws IOException {
        new Day4();
    }

    protected Day4() throws IOException {
        super(4);
    }

    @Override
    protected void setup() {
        lower = Integer.parseInt(getInput().get(0).split("-")[0]);
        upper = Integer.parseInt(getInput().get(0).split("-")[1]);
    }

    @Override
    protected Object solveFirst() {
        int combinations = 0;

        for (int i = lower; i < upper; i++) {
            int no = i;
            boolean multiple = false;
            boolean increments = true;
            int prev = Integer.MAX_VALUE;

            while(no > 0){
                int dig = no % 10;
                if(dig == prev) multiple = true;
                if(prev < dig) increments = false;
                no /= 10;
                prev = dig;
            }

            if(multiple && increments) combinations++;
        }

        return combinations;
    }

    @Override
    protected Object solveSecond() {
        int combinations = 0;

        for (int i = lower; i < upper; i++) {
            int no = i;
            int[] multiple = new int[10];
            boolean increments = true;
            int prev = Integer.MAX_VALUE;

            while(no > 0){
                int dig = no % 10;
                if(dig == prev) multiple[dig]++;
                if(prev < dig) increments = false;
                no /= 10;
                prev = dig;
            }

            boolean m = false;
            for (int j = 0; j < 10; j++) {
                if (multiple[j] == 1) {
                    m = true;
                    break;
                }
            }
            
            if(m && increments) combinations++;
        }

        return combinations;
    }
}
