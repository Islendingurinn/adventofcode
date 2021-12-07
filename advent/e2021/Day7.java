package advent.e2021;

import advent.Advent;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Day7 extends Advent {

    private List<Integer> parsed;

    public static void main(String[] args) throws IOException {
        new Day7();
    }

    protected Day7() throws IOException {
        super(7);
    }

    @Override
    protected void setup() {
        parsed = Arrays.stream(getInput().get(0).split(",")).map(Integer::parseInt).collect(Collectors.toList());
    }

    private int fuelToPosition(int position){
        int fuel = 0;
        for(int in : parsed){
            fuel += Math.abs((position - in));
        }

        return fuel;
    }

    @Override
    protected Object solveFirst() {
        int previousPosition = 0;
        int previousFuel = fuelToPosition(0);
        while (true){
            int fuel = fuelToPosition(previousPosition+1);
            if(previousFuel < fuel){
                return previousFuel;
            }
            previousPosition++;
            previousFuel = fuel;
        }
    }

    private int fuelToPosition2(int position){
        int fuel = 0;
        for(int in : parsed){
            int n = Math.abs((position - in));
            fuel += (n*(n+1)/2);
        }

        return fuel;
    }

    @Override
    protected Object solveSecond() {
        int previousPosition = 0;
        int previousFuel = fuelToPosition2(0);
        while (true){
            int fuel = fuelToPosition2(previousPosition+1);
            if(previousFuel < fuel){
                return previousFuel;
            }
            previousPosition++;
            previousFuel = fuel;
        }
    }
}
