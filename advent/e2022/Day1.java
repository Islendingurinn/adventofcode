package advent.e2022;

import advent.Advent;

import java.io.IOException;
import java.util.Comparator;
import java.util.HashSet;

public class Day1 extends Advent {

    public static void main(String[] args) throws IOException {
        new Day1();
    }

    protected Day1() throws IOException {
        super(1);
    }

    private HashSet<Integer> calories;

    @Override
    protected void setup() {
        int sum = 0;
        calories = new HashSet<>();
        for(String input : getInput()){
            if (input.isEmpty()) {
                calories.add(sum);
                sum = 0;
            } else sum += Integer.parseInt(input);
        }
    }

    @Override
    protected Object solveFirst() {
        return calories
                .stream()
                .max(Integer::compare)
                .orElse(null);
    }

    @Override
    protected Object solveSecond() {
        return calories
                .stream()
                .sorted(Comparator.reverseOrder())
                .limit(3)
                .reduce(Integer::sum)
                .orElse(null);
    }
}
