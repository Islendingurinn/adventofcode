package advent.e2020;

import advent.Advent;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Day10 extends Advent {

    public static void main(String[] args) throws IOException {
        new Day10();
    }

    protected Day10() throws IOException {
        super(10);
    }

    List<Integer> sorted;

    @Override
    protected void setup() {
        sorted = getInput().stream().map(Integer::parseInt).collect(Collectors.toList());
        sorted.add(0);
        Collections.sort(sorted);
    }

    @Override
    protected Object solveFirst() {

        int one = 0;
        int three = 1;

        int current = 0;
        for (Integer integer : sorted) {

            int delta = integer - current;
            current += delta;

            if (delta == 1) one++;
            else if (delta == 3) three++;

        }

        return one * three;
    }

    @Override
    protected Object solveSecond() {

        int anchor = 0;
        long combinations = 1;
        List<List<Integer>> groups = new ArrayList<>();

        for (int i = 1; i < sorted.size(); i++) {

            if(sorted.get(i) - sorted.get(i-1) > 2){

                List<Integer> sub = new ArrayList<>(sorted.subList(anchor, i));
                anchor = i;
                groups.add(sub);

            }
        }

        int[] fib = { 1, 1, 1, 2, 4, 7, 12, 20 };
        for (List<Integer> group : groups)
            combinations *= fib[group.size()];

        return combinations;
    }
}
