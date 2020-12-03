package advent.e2020;

import advent.Advent;

import java.io.IOException;
import java.math.BigInteger;

public class Day3 extends Advent {

    public static void main(String[] args) throws IOException {
        new Day3();
    }

    protected Day3() throws IOException {
        super(3);
    }

    @Override
    protected void setup() {

    }

    @Override
    protected BigInteger solveFirst() {
        return new BigInteger(String.valueOf(getTrees(3, 1)));
    }

    @Override
    protected BigInteger solveSecond() {
        return new BigInteger(String.valueOf(
        getTrees(1, 1)
        * getTrees(3, 1)
        * getTrees(5, 1)
        * getTrees(7, 1)
        * getTrees(1, 2)));
    }

    private long getTrees(int xSlope, int ySlope){
        int max = getInput().size();
        int x = 0;
        int y = 0;
        long trees = 0;

        while(y < max){
            if(containsTree(x, y))
                trees++;

            x += xSlope;
            y += ySlope;
        }

        return trees;
    }

    private boolean containsTree(int x, int y){
        String path = getInput().get(y);

        int pathLength = path.length();
        int minus = (int) (x / pathLength);
        int newX = x - (31 * minus);

        if(path.charAt(newX) == '#')
            return true;
        else
            return false;
    }
}
