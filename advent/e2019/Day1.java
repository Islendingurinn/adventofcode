package advent.e2019;

import advent.Advent;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class Day1 extends Advent {

    private List<Integer> parsed;

    public static void main(String[] args) throws IOException {
        new Day1();
    }

    protected Day1() throws IOException {
        super(1);
    }

    @Override
    protected void setup() {
        parsed = getInput().stream().map(Integer::parseInt).collect(Collectors.toList());
    }

    @Override
    protected Integer solveFirst() {

        int fuel = 0;
        for (Integer integer : parsed) fuel += Math.round(integer / 3) - 2;

        return fuel;
    }

    @Override
    protected Integer solveSecond() {

        int fuel = 0;
        for (Integer integer : parsed) {
            boolean keepgoing = true;
            int calcFuel = Math.round(integer / 3) - 2;

            while (keepgoing) {
                if (calcFuel > 0) {
                    fuel += calcFuel;
                    calcFuel = Math.round(calcFuel / 3) - 2;
                } else keepgoing = false;
            }
        }

        return fuel;
    }
}
