package advent.e2019;

import advent.Advent;

import java.io.IOException;

public class Day2 extends Advent {

    private int[] parsed;

    public static void main(String[] args) throws IOException {
        new Day2();
    }

    protected Day2() throws IOException {
        super(2);
    }

    @Override
    protected void setup() {
        String[] split = getInput().get(0).split(",");

        parsed = new int[split.length];
        for (int i = 0; i < split.length; i++) {
            parsed[i] = Integer.parseInt(split[i]);
        }
    }

    @Override
    protected Integer solveFirst() {
        return run(12, 2);
    }

    private Integer run(int noun, int verb){
        parsed[1] = noun;
        parsed[2] = verb;

        int pos = 0;
        while(parsed[pos] != 99){
            switch(parsed[pos]){
                case 1:
                    set(parsed[pos+3], Math.addExact(parsed[parsed[pos+1]], parsed[parsed[pos+2]]));
                    break;
                case 2:
                    set(parsed[pos+3], Math.multiplyExact(parsed[parsed[pos+1]], parsed[parsed[pos+2]]));
                    break;
            }

            pos += 4;
        }

        return parsed[0];
    }

    private void set(int pos, int val){
        parsed[pos] = val;
    }

    @Override
    protected Integer solveSecond() {
        for (int i = 0; i < 100; i++) {
            for (int j = 0; j < 100; j++) {
                setup();
                run(i, j);
                if(parsed[0] == 19690720) return 100 * i + j;
            }
        }

        return null;
    }
}
